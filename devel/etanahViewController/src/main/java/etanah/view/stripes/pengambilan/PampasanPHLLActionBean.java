package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PihakDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.List;
import org.hibernate.Session;
import java.math.BigDecimal;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/pampasanPhlla")
public class PampasanPHLLActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PampasanPHLLActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    etanah.Configuration conf;
    private PermohonanPihak permohonanPihak;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private Permohonan permohonan;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private String idSblm;
    private String idPihak;
    private String sum;
    private String kodNegeri;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private String adaKerosakanTanah;
    private String keteranganKerosakanTanah;
    private BigDecimal kosKerosakanTanah;
    private BigDecimal nilaiTanahJpph;
    private String adaKerosakanBangunan;
    private String keteranganKerosakanBangunan;
    private BigDecimal kosKerosakanBangunan;
    private BigDecimal nilaiBngnJpph;
    private String adaKerosakanPokok;
    private String keteranganKerosakanPokok;
    private BigDecimal kosKerosakanPokok;
    private BigDecimal nilaiPokokJpph;
    private String adaKerosakanInfra;
    private String keteranganKerosakanInfra;
    private BigDecimal kosKerosakanInfra;
    private BigDecimal nilaiInfraJpph;
    private String adaKerosakanLain;
    private String keteranganKerosakanLain;
    private BigDecimal kosKerosakanLain;
    private BigDecimal nilaiLainJpph;
    private String adaKecacatanTanah;
    private String keteranganKecacatanTanah;
    private BigDecimal kosKecacatanTanah;
    private BigDecimal nilaiCatatJpph;
    private Boolean simpanLaporan = Boolean.TRUE;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/pampasanPHLL.jsp").addParameter("tab", "true");
    }

    public Resolution hideSimpanLaporan() {
        simpanLaporan = Boolean.FALSE;
        return new JSP("pengambilan/pampasanPHLL.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
//            before this use all hakmilik terdahulu that not selected hakmilik in carian
//            if(permohonan != null && permohonan.getPermohonanSebelum() != null) {
//                hakmilikPermohonanList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
//            }
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution pihakDetails() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        KodCawangan cawangan = permohonan.getCawangan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));


        if (idPermohonan != null && idHakmilik != null) {
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
                kodNegeri = conf.getProperty("kodNegeri");
                if (kodNegeri.equals("04")) {
                    permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                    HakmilikPihakBerkepentingan hakmilikPihak = pengambilanService.getHakmilikPihak(idHakmilik, idPihak);
//            senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
//            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                    InfoAudit infoAudit;
                    if (permohonanPihak == null) {
                        permohonanPihak = new PermohonanPihak();
                        infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        permohonanPihak.setPermohonan(permohonan);
                        permohonanPihak.setPihak(pihakDAO.findById(idPihak));
                        permohonanPihak.setCawangan(cawangan);
                        permohonanPihak.setJenis(hakmilikPihak.getJenis());
                    } else {
                        infoAudit = permohonanPihak.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                    permohonanPihak.setInfoAudit(infoAudit);
                    permohonanPihak.setHakmilik(hakmilik);
                    pengambilanService.saveOrupdatePermohonanPihak(permohonanPihak);
                    laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanah(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
                    if (laporanPemulihanTanah != null) {
                        adaKerosakanTanah = laporanPemulihanTanah.getAdaKerosakanTanah();
                        keteranganKerosakanTanah = laporanPemulihanTanah.getKeteranganKerosakanTanah();
                        kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                        nilaiTanahJpph = laporanPemulihanTanah.getNilaiTanah();
                        adaKerosakanBangunan = laporanPemulihanTanah.getAdaKerosakanBangunan();
                        keteranganKerosakanBangunan = laporanPemulihanTanah.getKeteranganKerosakanBangunan();
                        kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                        nilaiBngnJpph = laporanPemulihanTanah.getNilaiBngnJpph();
                        adaKerosakanPokok = laporanPemulihanTanah.getAdaKerosakanPokok();
                        keteranganKerosakanPokok = laporanPemulihanTanah.getKeteranganKerosakanPokok();
                        kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                        nilaiPokokJpph = laporanPemulihanTanah.getNilaiPokokJpph();
                        adaKerosakanInfra = laporanPemulihanTanah.getAdaKerosakanInfra();
                        keteranganKerosakanInfra = laporanPemulihanTanah.getKeteranganKerosakanInfra();
                        kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                        nilaiInfraJpph = laporanPemulihanTanah.getNilaiInfraJpph();
                        adaKerosakanLain = laporanPemulihanTanah.getAdaKerosakanLain();
                        keteranganKerosakanLain = laporanPemulihanTanah.getKeteranganKerosakanLain();
                        kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                        nilaiLainJpph = laporanPemulihanTanah.getNilaiLainJpph();
                        adaKecacatanTanah = laporanPemulihanTanah.getAdaKecacatanTanah();
                        keteranganKecacatanTanah = laporanPemulihanTanah.getKeteranganKecacatanTanah();
                        kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                        nilaiCatatJpph = laporanPemulihanTanah.getNilaiCatatJpph();

                    } else {
                        laporanPemulihanTanah = new LaporanPemulihanTanah();
                        infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        laporanPemulihanTanah.setPermohonan(permohonan);
                        laporanPemulihanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                        laporanPemulihanTanah.setPermohonanPihak(permohonanPihak);
                    }
                }

            } else {
                permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                HakmilikPihakBerkepentingan hakmilikPihak = pengambilanService.getHakmilikPihak(idHakmilik, idPihak);
//            senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
//            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                InfoAudit infoAudit;
                if (permohonanPihak == null) {
                    permohonanPihak = new PermohonanPihak();
                    infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    permohonanPihak.setPermohonan(permohonan);
                    permohonanPihak.setPihak(pihakDAO.findById(idPihak));
                    permohonanPihak.setCawangan(cawangan);
                    permohonanPihak.setJenis(hakmilikPihak.getJenis());
                } else {
                    infoAudit = permohonanPihak.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
                permohonanPihak.setInfoAudit(infoAudit);
                permohonanPihak.setHakmilik(hakmilik);
                pengambilanService.saveOrupdatePermohonanPihak(permohonanPihak);
                laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanah(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
                if (laporanPemulihanTanah != null) {
                    adaKerosakanTanah = laporanPemulihanTanah.getAdaKerosakanTanah();
                    keteranganKerosakanTanah = laporanPemulihanTanah.getKeteranganKerosakanTanah();
                    kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                    nilaiTanahJpph = laporanPemulihanTanah.getNilaiTanah();
                    adaKerosakanBangunan = laporanPemulihanTanah.getAdaKerosakanBangunan();
                    keteranganKerosakanBangunan = laporanPemulihanTanah.getKeteranganKerosakanBangunan();
                    kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                    nilaiBngnJpph = laporanPemulihanTanah.getNilaiBngnJpph();
                    adaKerosakanPokok = laporanPemulihanTanah.getAdaKerosakanPokok();
                    keteranganKerosakanPokok = laporanPemulihanTanah.getKeteranganKerosakanPokok();
                    kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                    nilaiPokokJpph = laporanPemulihanTanah.getNilaiPokokJpph();
                    adaKerosakanInfra = laporanPemulihanTanah.getAdaKerosakanInfra();
                    keteranganKerosakanInfra = laporanPemulihanTanah.getKeteranganKerosakanInfra();
                    kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                    nilaiInfraJpph = laporanPemulihanTanah.getNilaiInfraJpph();
                    adaKerosakanLain = laporanPemulihanTanah.getAdaKerosakanLain();
                    keteranganKerosakanLain = laporanPemulihanTanah.getKeteranganKerosakanLain();
                    kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                    nilaiLainJpph = laporanPemulihanTanah.getNilaiLainJpph();
                    adaKecacatanTanah = laporanPemulihanTanah.getAdaKecacatanTanah();
                    keteranganKecacatanTanah = laporanPemulihanTanah.getKeteranganKecacatanTanah();
                    kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                    nilaiCatatJpph = laporanPemulihanTanah.getNilaiCatatJpph();

                } else {
                    laporanPemulihanTanah = new LaporanPemulihanTanah();
                    infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    laporanPemulihanTanah.setPermohonan(permohonan);
                    laporanPemulihanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                    laporanPemulihanTanah.setPermohonanPihak(permohonanPihak);
                }
            }

//            }
        }

        if (getContext().getRequest().getParameter("simpanLaporan").equalsIgnoreCase("false")) {
            simpanLaporan = Boolean.FALSE;
        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/pampasanPHLL.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
            kodNegeri = conf.getProperty("kodNegeri");
            if (kodNegeri.equals("04")) {
                permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);

                InfoAudit infoAudit;
//        laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
                laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanah(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
                if (laporanPemulihanTanah == null) {
                    laporanPemulihanTanah = new LaporanPemulihanTanah();
                    infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    laporanPemulihanTanah.setPermohonan(permohonan);
                    laporanPemulihanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                    laporanPemulihanTanah.setPermohonanPihak(permohonanPihak);
                } else {
                    infoAudit = laporanPemulihanTanah.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
                laporanPemulihanTanah.setInfoAudit(infoAudit);
                laporanPemulihanTanah.setAdaKerosakanTanah(adaKerosakanTanah);
                laporanPemulihanTanah.setKeteranganKerosakanTanah(keteranganKerosakanTanah);
                laporanPemulihanTanah.setKosKerosakanTanah(kosKerosakanTanah);
                laporanPemulihanTanah.setNilaiTanah(nilaiTanahJpph);
                laporanPemulihanTanah.setAdaKerosakanBangunan(adaKerosakanBangunan);
                laporanPemulihanTanah.setKeteranganKerosakanBangunan(keteranganKerosakanBangunan);
                laporanPemulihanTanah.setKosKerosakanBangunan(kosKerosakanBangunan);
                laporanPemulihanTanah.setNilaiBngnJpph(nilaiBngnJpph);
                laporanPemulihanTanah.setAdaKerosakanPokok(adaKerosakanPokok);
                laporanPemulihanTanah.setKeteranganKerosakanPokok(keteranganKerosakanPokok);
                laporanPemulihanTanah.setKosKerosakanPokok(kosKerosakanPokok);
                laporanPemulihanTanah.setNilaiPokokJpph(nilaiPokokJpph);
                laporanPemulihanTanah.setAdaKerosakanInfra(adaKerosakanInfra);
                laporanPemulihanTanah.setKeteranganKerosakanInfra(keteranganKerosakanInfra);
                laporanPemulihanTanah.setKosKerosakanInfra(kosKerosakanInfra);
                laporanPemulihanTanah.setNilaiInfraJpph(nilaiInfraJpph);
                laporanPemulihanTanah.setAdaKerosakanLain(adaKerosakanLain);
                laporanPemulihanTanah.setKeteranganKerosakanLain(keteranganKerosakanLain);
                laporanPemulihanTanah.setKosKerosakanLain(kosKerosakanLain);
                laporanPemulihanTanah.setNilaiLainJpph(nilaiLainJpph);
                laporanPemulihanTanah.setAdaKecacatanTanah(adaKecacatanTanah);
                laporanPemulihanTanah.setKeteranganKecacatanTanah(keteranganKecacatanTanah);
                laporanPemulihanTanah.setKosKecacatanTanah(kosKecacatanTanah);
                laporanPemulihanTanah.setNilaiCatatJpph(nilaiCatatJpph);
                pengambilanService.simpanLaporanPemulihanTanah(laporanPemulihanTanah);
            }

        } else {
            PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
//        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
//        for (PermohonanPihak pp : senaraiPermohonanPihak) {
            InfoAudit infoAudit;
//        laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            try {
                laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanah(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
            } catch (Exception b) {}

                if (laporanPemulihanTanah == null) {
                    laporanPemulihanTanah = new LaporanPemulihanTanah();
                    infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    laporanPemulihanTanah.setPermohonan(permohonan);
                    laporanPemulihanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                    laporanPemulihanTanah.setPermohonanPihak(permohonanPihak);
                } else {
                    infoAudit = laporanPemulihanTanah.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
                laporanPemulihanTanah.setInfoAudit(infoAudit);
                laporanPemulihanTanah.setAdaKerosakanTanah(adaKerosakanTanah);
                laporanPemulihanTanah.setKeteranganKerosakanTanah(keteranganKerosakanTanah);
                laporanPemulihanTanah.setKosKerosakanTanah(kosKerosakanTanah);
                laporanPemulihanTanah.setNilaiTanah(nilaiTanahJpph);
                laporanPemulihanTanah.setAdaKerosakanBangunan(adaKerosakanBangunan);
                laporanPemulihanTanah.setKeteranganKerosakanBangunan(keteranganKerosakanBangunan);
                laporanPemulihanTanah.setKosKerosakanBangunan(kosKerosakanBangunan);
                laporanPemulihanTanah.setNilaiBngnJpph(nilaiBngnJpph);
                laporanPemulihanTanah.setAdaKerosakanPokok(adaKerosakanPokok);
                laporanPemulihanTanah.setKeteranganKerosakanPokok(keteranganKerosakanPokok);
                laporanPemulihanTanah.setKosKerosakanPokok(kosKerosakanPokok);
                laporanPemulihanTanah.setNilaiPokokJpph(nilaiPokokJpph);
                laporanPemulihanTanah.setAdaKerosakanInfra(adaKerosakanInfra);
                laporanPemulihanTanah.setKeteranganKerosakanInfra(keteranganKerosakanInfra);
                laporanPemulihanTanah.setKosKerosakanInfra(kosKerosakanInfra);
                laporanPemulihanTanah.setNilaiInfraJpph(nilaiInfraJpph);
                laporanPemulihanTanah.setAdaKerosakanLain(adaKerosakanLain);
                laporanPemulihanTanah.setKeteranganKerosakanLain(keteranganKerosakanLain);
                laporanPemulihanTanah.setKosKerosakanLain(kosKerosakanLain);
                laporanPemulihanTanah.setNilaiLainJpph(nilaiLainJpph);
                laporanPemulihanTanah.setAdaKecacatanTanah(adaKecacatanTanah);
                laporanPemulihanTanah.setKeteranganKecacatanTanah(keteranganKecacatanTanah);
                laporanPemulihanTanah.setKosKecacatanTanah(kosKecacatanTanah);
                laporanPemulihanTanah.setNilaiCatatJpph(nilaiCatatJpph);
                pengambilanService.simpanLaporanPemulihanTanah(laporanPemulihanTanah);
            
        }

//        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        pihakDetails();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/pampasanPHLL.jsp").addParameter("tab", "true");
    }

    public String getAdaKecacatanTanah() {
        return adaKecacatanTanah;
    }

    public void setAdaKecacatanTanah(String adaKecacatanTanah) {
        this.adaKecacatanTanah = adaKecacatanTanah;
    }

    public String getAdaKerosakanBangunan() {
        return adaKerosakanBangunan;
    }

    public void setAdaKerosakanBangunan(String adaKerosakanBangunan) {
        this.adaKerosakanBangunan = adaKerosakanBangunan;
    }

    public String getAdaKerosakanInfra() {
        return adaKerosakanInfra;
    }

    public void setAdaKerosakanInfra(String adaKerosakanInfra) {
        this.adaKerosakanInfra = adaKerosakanInfra;
    }

    public String getAdaKerosakanLain() {
        return adaKerosakanLain;
    }

    public void setAdaKerosakanLain(String adaKerosakanLain) {
        this.adaKerosakanLain = adaKerosakanLain;
    }

    public String getAdaKerosakanPokok() {
        return adaKerosakanPokok;
    }

    public void setAdaKerosakanPokok(String adaKerosakanPokok) {
        this.adaKerosakanPokok = adaKerosakanPokok;
    }

    public String getAdaKerosakanTanah() {
        return adaKerosakanTanah;
    }

    public void setAdaKerosakanTanah(String adaKerosakanTanah) {
        this.adaKerosakanTanah = adaKerosakanTanah;
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeteranganKecacatanTanah() {
        return keteranganKecacatanTanah;
    }

    public void setKeteranganKecacatanTanah(String keteranganKecacatanTanah) {
        this.keteranganKecacatanTanah = keteranganKecacatanTanah;
    }

    public String getKeteranganKerosakanBangunan() {
        return keteranganKerosakanBangunan;
    }

    public void setKeteranganKerosakanBangunan(String keteranganKerosakanBangunan) {
        this.keteranganKerosakanBangunan = keteranganKerosakanBangunan;
    }

    public String getKeteranganKerosakanInfra() {
        return keteranganKerosakanInfra;
    }

    public void setKeteranganKerosakanInfra(String keteranganKerosakanInfra) {
        this.keteranganKerosakanInfra = keteranganKerosakanInfra;
    }

    public String getKeteranganKerosakanLain() {
        return keteranganKerosakanLain;
    }

    public void setKeteranganKerosakanLain(String keteranganKerosakanLain) {
        this.keteranganKerosakanLain = keteranganKerosakanLain;
    }

    public String getKeteranganKerosakanPokok() {
        return keteranganKerosakanPokok;
    }

    public void setKeteranganKerosakanPokok(String keteranganKerosakanPokok) {
        this.keteranganKerosakanPokok = keteranganKerosakanPokok;
    }

    public String getKeteranganKerosakanTanah() {
        return keteranganKerosakanTanah;
    }

    public void setKeteranganKerosakanTanah(String keteranganKerosakanTanah) {
        this.keteranganKerosakanTanah = keteranganKerosakanTanah;
    }

    public BigDecimal getKosKecacatanTanah() {
        return kosKecacatanTanah;
    }

    public void setKosKecacatanTanah(BigDecimal kosKecacatanTanah) {
        this.kosKecacatanTanah = kosKecacatanTanah;
    }

    public BigDecimal getKosKerosakanBangunan() {
        return kosKerosakanBangunan;
    }

    public void setKosKerosakanBangunan(BigDecimal kosKerosakanBangunan) {
        this.kosKerosakanBangunan = kosKerosakanBangunan;
    }

    public BigDecimal getKosKerosakanInfra() {
        return kosKerosakanInfra;
    }

    public void setKosKerosakanInfra(BigDecimal kosKerosakanInfra) {
        this.kosKerosakanInfra = kosKerosakanInfra;
    }

    public BigDecimal getKosKerosakanLain() {
        return kosKerosakanLain;
    }

    public void setKosKerosakanLain(BigDecimal kosKerosakanLain) {
        this.kosKerosakanLain = kosKerosakanLain;
    }

    public BigDecimal getKosKerosakanPokok() {
        return kosKerosakanPokok;
    }

    public void setKosKerosakanPokok(BigDecimal kosKerosakanPokok) {
        this.kosKerosakanPokok = kosKerosakanPokok;
    }

    public BigDecimal getKosKerosakanTanah() {
        return kosKerosakanTanah;
    }

    public void setKosKerosakanTanah(BigDecimal kosKerosakanTanah) {
        this.kosKerosakanTanah = kosKerosakanTanah;
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanah() {
        return laporanPemulihanTanah;
    }

    public void setLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah) {
        this.laporanPemulihanTanah = laporanPemulihanTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public Boolean getSimpanLaporan() {
        return simpanLaporan;
    }

    public void setSimpanLaporan(Boolean simpanLaporan) {
        this.simpanLaporan = simpanLaporan;
    }

    public BigDecimal getNilaiBngnJpph() {
        return nilaiBngnJpph;
    }

    public void setNilaiBngnJpph(BigDecimal nilaiBngnJpph) {
        this.nilaiBngnJpph = nilaiBngnJpph;
    }

    public BigDecimal getNilaiCatatJpph() {
        return nilaiCatatJpph;
    }

    public void setNilaiCatatJpph(BigDecimal nilaiCatatJpph) {
        this.nilaiCatatJpph = nilaiCatatJpph;
    }

    public BigDecimal getNilaiInfraJpph() {
        return nilaiInfraJpph;
    }

    public void setNilaiInfraJpph(BigDecimal nilaiInfraJpph) {
        this.nilaiInfraJpph = nilaiInfraJpph;
    }

    public BigDecimal getNilaiLainJpph() {
        return nilaiLainJpph;
    }

    public void setNilaiLainJpph(BigDecimal nilaiLainJpph) {
        this.nilaiLainJpph = nilaiLainJpph;
    }

    public BigDecimal getNilaiPokokJpph() {
        return nilaiPokokJpph;
    }

    public void setNilaiPokokJpph(BigDecimal nilaiPokokJpph) {
        this.nilaiPokokJpph = nilaiPokokJpph;
    }

    public BigDecimal getNilaiTanahJpph() {
        return nilaiTanahJpph;
    }

    public void setNilaiTanahJpph(BigDecimal nilaiTanahJpph) {
        this.nilaiTanahJpph = nilaiTanahJpph;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
