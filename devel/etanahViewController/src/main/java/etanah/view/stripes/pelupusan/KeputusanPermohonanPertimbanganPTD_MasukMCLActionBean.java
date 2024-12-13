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
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;

import etanah.model.KodKeputusan;
import etanah.model.Pemohon;
import etanah.model.PemohonTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.List;
import etanah.view.etanahActionBeanContext;
//import java.text.SimpleDateFormat;
//import java.util.Vector;
import etanah.service.PelupusanService;
//import etanah.view.ListUtil;


import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
//import net.sourceforge.stripes.action.ForwardResolution;
//import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author afham
 */

@UrlBinding("/pelupusan/keputusan_draf_pertimbangan_ptd_masuk_mcl")
public class KeputusanPermohonanPertimbanganPTD_MasukMCLActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    PemohonDAO pemohonDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO ;
    @Inject
    PelupusanService plpservice ;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO ;
    @Inject
    KodKeputusanDAO kodkeputusanDAO ;

    private HakmilikPermohonan hakmilikPermohonan ;
    private KodKeputusan kodKpsn ;
    private Permohonan permohonan ;
    private PemohonTanah pemohonTanah ;
    private Hakmilik hakmilik ;
    private Pemohon pemohon ;
    private String tajuk ;
    private PermohonanKertas ppmn ;
    private PermohonanKertasKandungan kertasKandungan ;
    private String tujuan;
    private String perihalPermohonan;
    private String latarBelakang1 ;
    private String latarBelakang2 ;
    private String kpsn ;
    private String daerah ;

    @DefaultHandler
    public Resolution showForm(){

        return new JSP("pelupusan/kpsn_draf_PTD_Masuk_MCL.jsp").addParameter("tab", "true") ;

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;

        List senaraiPemohon = pemohonDAO.findByEqualCriterias(tname, tvalue, null) ;
        pemohon = (Pemohon) senaraiPemohon.get(0);
        List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0) ;
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;

        List<PermohonanKertas> listppmn = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null) ;
        daerah = hakmilik.getDaerah().getNama().toUpperCase() ;
            this.ppmn = listppmn.get(0) ;
            kertasKandungan = (PermohonanKertasKandungan) ppmn.getSenaraiKandungan().get(0) ;
            tajuk = ppmn.getTajuk() ;
            String kandungan = kertasKandungan.getKandungan() ;
            String temp[] = null ;
            temp = kandungan.split(" - ") ;

            for(int i = 0 ; i < temp.length ; i++){
                tujuan = temp[0] ;
                latarBelakang1 = temp[1] ;
                latarBelakang2 = temp[2] ;
               
           
        }

          
               
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

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonTanah getPemohonTanah() {
        return pemohonTanah;
    }

    public void setPemohonTanah(PemohonTanah pemohonTanah) {
        this.pemohonTanah = pemohonTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public PermohonanKertas getPpmn() {
        return ppmn;
    }

    public void setPpmn(PermohonanKertas ppmn) {
        this.ppmn = ppmn;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public String getLatarBelakang2() {
        return latarBelakang2;
    }

    public void setLatarBelakang2(String latarBelakang2) {
        this.latarBelakang2 = latarBelakang2;
    }

    public KodKeputusan getKodKpsn() {
        return kodKpsn;
    }

    public void setKodKpsn(KodKeputusan kodKpsn) {
        this.kodKpsn = kodKpsn;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    




}
