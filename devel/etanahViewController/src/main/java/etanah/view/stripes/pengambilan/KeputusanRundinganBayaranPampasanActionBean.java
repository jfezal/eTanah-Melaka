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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTandaSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


/**
 *
 * @author Massita
 */
@UrlBinding("/pengambilan/keputusanPerundingan")
public class KeputusanRundinganBayaranPampasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PengambilanService pengambilanService;
    
    private PermohonanPihak permohonanPihak;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private LaporanTandaSempadan laporanTandaSempadan;
    private String keteranganNilaiTanah;
    private BigDecimal nilaiTanah;
    private String keteranganNilaiBangunan;
    private BigDecimal nilaiBangunan;
    private String keteranganNilaiPokok;
    private BigDecimal nilaiPokok;
    private String keteranganNilaiInfra;
    private BigDecimal nilaiInfra;
    private String keteranganNilaiLain;
    private BigDecimal nilaiLain;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanRundinganBayaranPampasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/rekodTerimaBayaranOrgBerkepentingan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null && idHakmilik != null) {
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
            laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            if(laporanTandaSempadan != null){
                keteranganNilaiTanah = laporanTandaSempadan.getKeteranganNilaiTanah();
                nilaiTanah = laporanTandaSempadan.getNilaiTanah();
                keteranganNilaiBangunan = laporanTandaSempadan.getKeteranganNilaiBangunan();
                nilaiBangunan = laporanTandaSempadan.getNilaiBangunan();
                keteranganNilaiPokok = laporanTandaSempadan.getKeteranganNilaiPokok();
                nilaiPokok = laporanTandaSempadan.getNilaiPokok();
                keteranganNilaiInfra = laporanTandaSempadan.getKeteranganNilaiInfra();
                nilaiInfra = laporanTandaSempadan.getNilaiInfra();
                keteranganNilaiLain = laporanTandaSempadan.getKeteranganNilaiLain();
                nilaiLain = laporanTandaSempadan.getNilaiLain();
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanRundinganBayaranPampasan.jsp").addParameter("tab", "true");
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
        laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
        if(laporanTandaSempadan == null) {
            laporanTandaSempadan = new LaporanTandaSempadan();
            infoAudit = new InfoAudit();
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            laporanTandaSempadan.setPermohonan(permohonan);
            laporanTandaSempadan.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTandaSempadan.setPermohonanPihak(permohonanPihak);
            laporanTandaSempadan.setOffsetBangunanPagar('t');
            laporanTandaSempadan.setPiketTanda('t');
        }else{
            infoAudit = laporanTandaSempadan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        laporanTandaSempadan.setAdaIkatanBatu("p");
        laporanTandaSempadan.setInfoAudit(infoAudit);
        laporanTandaSempadan.setKeteranganNilaiTanah(keteranganNilaiTanah);
        laporanTandaSempadan.setNilaiTanah(nilaiTanah);
        laporanTandaSempadan.setKeteranganNilaiBangunan(keteranganNilaiBangunan);
        laporanTandaSempadan.setNilaiBangunan(nilaiBangunan);
        laporanTandaSempadan.setKeteranganNilaiPokok(keteranganNilaiPokok);
        laporanTandaSempadan.setNilaiPokok(nilaiPokok);
        laporanTandaSempadan.setKeteranganNilaiInfra(keteranganNilaiInfra);
        laporanTandaSempadan.setNilaiInfra(nilaiInfra);
        laporanTandaSempadan.setKeteranganNilaiLain(keteranganNilaiLain);
        laporanTandaSempadan.setNilaiLain(nilaiLain);
        pengambilanService.simpanLaporanTandaSempadan(laporanTandaSempadan);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanRundinganBayaranPampasan.jsp").addParameter("tab", "true");

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

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public LaporanTandaSempadan getLaporanTandaSempadan() {
        return laporanTandaSempadan;
    }

    public void setLaporanTandaSempadan(LaporanTandaSempadan laporanTandaSempadan) {
        this.laporanTandaSempadan = laporanTandaSempadan;
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

    public String getKeteranganNilaiBangunan() {
        return keteranganNilaiBangunan;
    }

    public void setKeteranganNilaiBangunan(String keteranganNilaiBangunan) {
        this.keteranganNilaiBangunan = keteranganNilaiBangunan;
    }

    public String getKeteranganNilaiInfra() {
        return keteranganNilaiInfra;
    }

    public void setKeteranganNilaiInfra(String keteranganNilaiInfra) {
        this.keteranganNilaiInfra = keteranganNilaiInfra;
    }

    public String getKeteranganNilaiLain() {
        return keteranganNilaiLain;
    }

    public void setKeteranganNilaiLain(String keteranganNilaiLain) {
        this.keteranganNilaiLain = keteranganNilaiLain;
    }

    public String getKeteranganNilaiPokok() {
        return keteranganNilaiPokok;
    }

    public void setKeteranganNilaiPokok(String keteranganNilaiPokok) {
        this.keteranganNilaiPokok = keteranganNilaiPokok;
    }

    public String getKeteranganNilaiTanah() {
        return keteranganNilaiTanah;
    }

    public void setKeteranganNilaiTanah(String keteranganNilaiTanah) {
        this.keteranganNilaiTanah = keteranganNilaiTanah;
    }

    public BigDecimal getNilaiBangunan() {
        return nilaiBangunan;
    }

    public void setNilaiBangunan(BigDecimal nilaiBangunan) {
        this.nilaiBangunan = nilaiBangunan;
    }

    public BigDecimal getNilaiInfra() {
        return nilaiInfra;
    }

    public void setNilaiInfra(BigDecimal nilaiInfra) {
        this.nilaiInfra = nilaiInfra;
    }

    public BigDecimal getNilaiLain() {
        return nilaiLain;
    }

    public void setNilaiLain(BigDecimal nilaiLain) {
        this.nilaiLain = nilaiLain;
    }

    public BigDecimal getNilaiPokok() {
        return nilaiPokok;
    }

    public void setNilaiPokok(BigDecimal nilaiPokok) {
        this.nilaiPokok = nilaiPokok;
    }

    public BigDecimal getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(BigDecimal nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

 }
