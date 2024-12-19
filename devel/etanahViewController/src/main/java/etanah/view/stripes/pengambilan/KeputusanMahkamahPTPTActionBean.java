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
import etanah.service.PengambilanService;
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
@UrlBinding("/pengambilan/keputusanMahkamahPTPT")
public class KeputusanMahkamahPTPTActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KeputusanMahkamahPTPTActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PengambilanService pengambilanService;
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
    private String adaKerosakanTanah;
    private String keteranganKerosakanTanah;
    private BigDecimal kosKerosakanTanah;
    private String adaKerosakanLain;
    private String keteranganKerosakanLain;
    private BigDecimal kosKerosakanLain;
    private Boolean simpanLaporan = Boolean.TRUE;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pemulihantanah/keputusanMahkamahPTPT.jsp").addParameter("tab", "true");
    }

    public Resolution hideSimpanLaporan() {
        simpanLaporan = Boolean.FALSE;
        return new JSP("pengambilan/negerisembilan/pemulihantanah/keputusanMahkamahPTPT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null && permohonan.getPermohonanSebelum() != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        logger.debug("idPihak " + idPihak);
        logger.debug("idsblm " + idSblm);
        logger.debug("idPermohonan " + idPermohonan);
        if (idSblm != null && idHakmilik != null) {
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, hakmilik.getIdHakmilik(), idPihak);
            // logger.debug("hakmilikPermohonan.getId() >> "+hakmilikPermohonan.getId());
            //logger.debug("permohonanPihak.getIdPermohonanPihak() >> "+permohonanPihak.getIdPermohonanPihak());
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            try {
                laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByJenisLaporan(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak(), "MKH");
                if (laporanPemulihanTanah != null) {
                    adaKerosakanTanah = laporanPemulihanTanah.getAdaKerosakanTanah();
                    keteranganKerosakanTanah = laporanPemulihanTanah.getKeteranganKerosakanTanah();
                    kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                    adaKerosakanLain = laporanPemulihanTanah.getAdaKerosakanLain();
                    keteranganKerosakanLain = laporanPemulihanTanah.getKeteranganKerosakanLain();
                    kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                }
            } catch (Exception j) {
                //addSimpleError("Id hakmilik tersebut tiada dalam urusan sebelum");
            }
        }

        if (getContext().getRequest().getParameter("simpanLaporan").equalsIgnoreCase("false")) {
            simpanLaporan = Boolean.FALSE;
        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pemulihantanah/keputusanMahkamahPTPT.jsp").addParameter("tab", "true");
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
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
        PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, idHakmilik, idPihak);
        InfoAudit infoAudit;
//        laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
        laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByJenisLaporan(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak(), "MKH");
        if (laporanPemulihanTanah == null) {
            laporanPemulihanTanah = new LaporanPemulihanTanah();
            infoAudit = new InfoAudit();
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            laporanPemulihanTanah.setPermohonan(permohonan);
            laporanPemulihanTanah.setJenisLaporan("MKH");
            laporanPemulihanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanPemulihanTanah.setPermohonanPihak(permohonanPihak);
            laporanPemulihanTanah.setInfoAudit(infoAudit);
            laporanPemulihanTanah.setAdaKerosakanTanah(adaKerosakanTanah);
            laporanPemulihanTanah.setKeteranganKerosakanTanah(keteranganKerosakanTanah);
            laporanPemulihanTanah.setKosKerosakanTanah(kosKerosakanTanah);
            laporanPemulihanTanah.setAdaKerosakanLain(adaKerosakanLain);
            laporanPemulihanTanah.setKeteranganKerosakanLain(keteranganKerosakanLain);
            laporanPemulihanTanah.setKosKerosakanLain(kosKerosakanLain);
            pengambilanService.simpanLaporanPemulihanTanah(laporanPemulihanTanah);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            infoAudit = laporanPemulihanTanah.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            laporanPemulihanTanah.setInfoAudit(infoAudit);
            laporanPemulihanTanah.setAdaKerosakanTanah(adaKerosakanTanah);
            laporanPemulihanTanah.setKeteranganKerosakanTanah(keteranganKerosakanTanah);
            laporanPemulihanTanah.setKosKerosakanTanah(kosKerosakanTanah);
            laporanPemulihanTanah.setAdaKerosakanLain(adaKerosakanLain);
            laporanPemulihanTanah.setKeteranganKerosakanLain(keteranganKerosakanLain);
            laporanPemulihanTanah.setKosKerosakanLain(kosKerosakanLain);
            pengambilanService.simpanLaporanPemulihanTanah(laporanPemulihanTanah);
            addSimpleMessage("Maklumat telah berjaya dikemaskini");
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pemulihantanah/keputusanMahkamahPTPT.jsp").addParameter("tab", "true");
    }

    public String getAdaKerosakanLain() {
        return adaKerosakanLain;
    }

    public void setAdaKerosakanLain(String adaKerosakanLain) {
        this.adaKerosakanLain = adaKerosakanLain;
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

    public String getKeteranganKerosakanLain() {
        return keteranganKerosakanLain;
    }

    public void setKeteranganKerosakanLain(String keteranganKerosakanLain) {
        this.keteranganKerosakanLain = keteranganKerosakanLain;
    }

    public String getKeteranganKerosakanTanah() {
        return keteranganKerosakanTanah;
    }

    public void setKeteranganKerosakanTanah(String keteranganKerosakanTanah) {
        this.keteranganKerosakanTanah = keteranganKerosakanTanah;
    }

    public BigDecimal getKosKerosakanLain() {
        return kosKerosakanLain;
    }

    public void setKosKerosakanLain(BigDecimal kosKerosakanLain) {
        this.kosKerosakanLain = kosKerosakanLain;
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
}
