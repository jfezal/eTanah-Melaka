/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/permohonan_supaya_bantahanCaterOneTT")
public class PermohonanSupayaBantahan_caterOneTTOnlyActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    PengambilanService pengambilanService;

    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private String kepentinganTanah;
    private Character sebabUkurTanah;
    private Character sebabAmnPampasan;
    private Character sebabPihakPampasan;
    private Character sebabUmpukanPampasan;
    private String alasanBantah;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/permohonan_supaya_bantahan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,idHakmilik,idPihak);
        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah != null) {
                kepentinganTanah = permohonanMahkamah.getKepentinganTanah();
                sebabUkurTanah = permohonanMahkamah.getSebabUkurTanah();
                sebabAmnPampasan = permohonanMahkamah.getSebabAmnPampasan();
                sebabPihakPampasan = permohonanMahkamah.getSebabPihakPampasan();
                sebabUmpukanPampasan = permohonanMahkamah.getSebabUmpukanPampasan();
                alasanBantah = permohonanMahkamah.getAlasanBantah();
            }
        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/permohonan_supaya_bantahan.jsp").addParameter("tab", "true");
    }
    

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if(senaraiHakmilikPermohonan.size() > 0){
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if(hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    if(senaraiPermohonanPihak.size() > 0){
                        permohonanPihak = senaraiPermohonanPihak.get(0);
                    }
                }
            }
        }
    }

    public Resolution simpan() throws ParseException{
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,idHakmilik,idPihak);
        InfoAudit infoAudit;
        
        if(permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if(permohonanMahkamah == null) {
                permohonanMahkamah = new PermohonanMahkamah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }else {
                infoAudit = permohonanMahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setKepentinganTanah(kepentinganTanah);
            permohonanMahkamah.setSebabUkurTanah(sebabUkurTanah);
            permohonanMahkamah.setSebabAmnPampasan(sebabAmnPampasan);
            permohonanMahkamah.setSebabPihakPampasan(sebabPihakPampasan);
            permohonanMahkamah.setSebabUmpukanPampasan(sebabUmpukanPampasan);
            permohonanMahkamah.setAlasanBantah(alasanBantah);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(permohonanMahkamah);

        }        
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/melaka/bantahanmahkamah/permohonan_supaya_bantahan.jsp").addParameter("tab", "true");
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

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getAlasanBantah() {
        return alasanBantah;
    }

    public void setAlasanBantah(String alasanBantah) {
        this.alasanBantah = alasanBantah;
    }

    public String getKepentinganTanah() {
        return kepentinganTanah;
    }

    public void setKepentinganTanah(String kepentinganTanah) {
        this.kepentinganTanah = kepentinganTanah;
    }

    public Character getSebabAmnPampasan() {
        return sebabAmnPampasan;
    }

    public void setSebabAmnPampasan(Character sebabAmnPampasan) {
        this.sebabAmnPampasan = sebabAmnPampasan;
    }

    public Character getSebabPihakPampasan() {
        return sebabPihakPampasan;
    }

    public void setSebabPihakPampasan(Character sebabPihakPampasan) {
        this.sebabPihakPampasan = sebabPihakPampasan;
    }

    public Character getSebabUkurTanah() {
        return sebabUkurTanah;
    }

    public void setSebabUkurTanah(Character sebabUkurTanah) {
        this.sebabUkurTanah = sebabUkurTanah;
    }

    public Character getSebabUmpukanPampasan() {
        return sebabUmpukanPampasan;
    }

    public void setSebabUmpukanPampasan(Character sebabUmpukanPampasan) {
        this.sebabUmpukanPampasan = sebabUmpukanPampasan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}
