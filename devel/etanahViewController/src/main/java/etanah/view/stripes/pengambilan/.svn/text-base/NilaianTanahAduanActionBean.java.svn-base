/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import etanah.service.PengambilanAduanService;
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

@UrlBinding("/pengambilan/nilaianTanahAduan")
public class NilaianTanahAduanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LaporanPemulihanTanahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PengambilanAduanService aduanService;
    private PermohonanPihak permohonanPihak;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private Permohonan permohonan;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private String idSblm;
    private String idPihak;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> pp;
    private String adaKerosakanTanah;
    private String keteranganKerosakanTanah;
    private BigDecimal kosKerosakanTanah;
    private String adaKerosakanBangunan;
    private String keteranganKerosakanBangunan;
    private BigDecimal kosKerosakanBangunan;
    private String adaKerosakanPokok;
    private String keteranganKerosakanPokok;
    private BigDecimal kosKerosakanPokok;
    private String adaKerosakanInfra;
    private String keteranganKerosakanInfra;
    private BigDecimal kosKerosakanInfra;
    private String adaKerosakanLain;
    private String keteranganKerosakanLain;
    private BigDecimal kosKerosakanLain;
    private String adaKecacatanTanah;
    private String keteranganKecacatanTanah;
    private String syor;
    private String ulasan;
    private BigDecimal kosKecacatanTanah;
    private Pengguna peng;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("pengambilan/NilaianTanahAduanNS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        return new JSP("pengambilan/NilaianTanahAduanSemakNS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pp = aduanService.findPihak(idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution pihakDetails() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (hakmilik != null) {
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
            if (laporanPemulihanTanah == null && idSblm != null) {
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
                permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, hakmilik.getIdHakmilik(), idPihak);
                if (hakmilikPermohonan != null && permohonanPihak != null) {
                    laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
                }
            }
            if (laporanPemulihanTanah != null) {
                adaKerosakanTanah = laporanPemulihanTanah.getAdaKerosakanTanah();
                keteranganKerosakanTanah = laporanPemulihanTanah.getKeteranganKerosakanTanah();
                kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                adaKerosakanBangunan = laporanPemulihanTanah.getAdaKerosakanBangunan();
                keteranganKerosakanBangunan = laporanPemulihanTanah.getKeteranganKerosakanBangunan();
                kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                adaKerosakanPokok = laporanPemulihanTanah.getAdaKerosakanPokok();
                keteranganKerosakanPokok = laporanPemulihanTanah.getKeteranganKerosakanPokok();
                kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                adaKerosakanInfra = laporanPemulihanTanah.getAdaKerosakanInfra();
                keteranganKerosakanInfra = laporanPemulihanTanah.getKeteranganKerosakanInfra();
                kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                adaKerosakanLain = laporanPemulihanTanah.getAdaKerosakanLain();
                keteranganKerosakanLain = laporanPemulihanTanah.getKeteranganKerosakanLain();
                kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                adaKecacatanTanah = laporanPemulihanTanah.getAdaKecacatanTanah();
                keteranganKecacatanTanah = laporanPemulihanTanah.getKeteranganKecacatanTanah();
                kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                syor = laporanPemulihanTanah.getSyor();
                ulasan = laporanPemulihanTanah.getUlasan();

            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/NilaianTanahAduanNS.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails2() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (hakmilik != null) {
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
            if (laporanPemulihanTanah == null && idSblm != null) {
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
                permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, hakmilik.getIdHakmilik(), idPihak);
                if (hakmilikPermohonan != null && permohonanPihak != null) {
                    laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
                }
            }
            if (laporanPemulihanTanah != null) {
                adaKerosakanTanah = laporanPemulihanTanah.getAdaKerosakanTanah();
                keteranganKerosakanTanah = laporanPemulihanTanah.getKeteranganKerosakanTanah();
                kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                adaKerosakanBangunan = laporanPemulihanTanah.getAdaKerosakanBangunan();
                keteranganKerosakanBangunan = laporanPemulihanTanah.getKeteranganKerosakanBangunan();
                kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                adaKerosakanPokok = laporanPemulihanTanah.getAdaKerosakanPokok();
                keteranganKerosakanPokok = laporanPemulihanTanah.getKeteranganKerosakanPokok();
                kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                adaKerosakanInfra = laporanPemulihanTanah.getAdaKerosakanInfra();
                keteranganKerosakanInfra = laporanPemulihanTanah.getKeteranganKerosakanInfra();
                kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                adaKerosakanLain = laporanPemulihanTanah.getAdaKerosakanLain();
                keteranganKerosakanLain = laporanPemulihanTanah.getKeteranganKerosakanLain();
                kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                adaKecacatanTanah = laporanPemulihanTanah.getAdaKecacatanTanah();
                keteranganKecacatanTanah = laporanPemulihanTanah.getKeteranganKecacatanTanah();
                kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                syor = laporanPemulihanTanah.getSyor();
                ulasan = laporanPemulihanTanah.getUlasan();

            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/NilaianTanahAduanSemakNS.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        InfoAudit infoAudit;
        laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());
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
        laporanPemulihanTanah.setAdaKerosakanBangunan(adaKerosakanBangunan);
        laporanPemulihanTanah.setKeteranganKerosakanBangunan(keteranganKerosakanBangunan);
        laporanPemulihanTanah.setKosKerosakanBangunan(kosKerosakanBangunan);
        laporanPemulihanTanah.setAdaKerosakanPokok(adaKerosakanPokok);
        laporanPemulihanTanah.setKeteranganKerosakanPokok(keteranganKerosakanPokok);
        laporanPemulihanTanah.setKosKerosakanPokok(kosKerosakanPokok);
        laporanPemulihanTanah.setAdaKerosakanInfra(adaKerosakanInfra);
        laporanPemulihanTanah.setKeteranganKerosakanInfra(keteranganKerosakanInfra);
        laporanPemulihanTanah.setKosKerosakanInfra(kosKerosakanInfra);
        laporanPemulihanTanah.setAdaKerosakanLain(adaKerosakanLain);
        laporanPemulihanTanah.setKeteranganKerosakanLain(keteranganKerosakanLain);
        laporanPemulihanTanah.setKosKerosakanLain(kosKerosakanLain);
        laporanPemulihanTanah.setAdaKecacatanTanah(adaKecacatanTanah);
        laporanPemulihanTanah.setKeteranganKecacatanTanah(keteranganKecacatanTanah);
        laporanPemulihanTanah.setKosKecacatanTanah(kosKecacatanTanah);
        laporanPemulihanTanah.setSyor(syor);
        laporanPemulihanTanah.setUlasan(ulasan);
        pengambilanService.simpanLaporanPemulihanTanah(laporanPemulihanTanah);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/NilaianTanahAduanNS.jsp").addParameter("tab", "true");

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

    public List<PermohonanPihak> getPp() {
        return pp;
    }

    public void setPp(List<PermohonanPihak> pp) {
        this.pp = pp;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }
    
}
