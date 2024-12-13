/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

/**
 *
 * @author afham
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
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
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Vector;
import etanah.service.PelupusanService;
import etanah.view.ListUtil;


import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


@UrlBinding("/pelupusan/keputusan_draf_pertimbangan_ptd_catit_mcl")
public class KeputusanPermohonanPertimbanganPTD_CatitMCLActionBean extends AbleActionBean {

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
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO ;
    @Inject
    KodKeputusanDAO kodkeputusanDAO;
     @Inject
    LaporanTanahDAO laporanTanahDAO ;
    // @Inject
    // FasaPermohonanDAO fasaPermohonanDAO;
    private ListUtil senaraiKeputusan ;
    private KodKeputusan kodKpsn;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private PemohonTanah pemohonTanah;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    //private PermohonanKertas permohonanKertas;
    //private PermohonanKertasKandungan permohonanKK;
    private String tajuk;
   
    private PermohonanKertas ppmn;
    private PermohonanKertasKandungan kertasKandungan;
    
    private String smpdnU;
    private String smpdnS;
    private String smpdnT;
    private String smpdnB;
    private String jarak ;
   
    private String tujuan;
    private String perihalPermohonan;
    private String kpsn ;
    private String daerah ;
    boolean test;
    boolean simpan;
    boolean notSimpan = true;

     @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/kpsn_draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");

    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};

        
        List senaraiPemohon = pemohonDAO.findByEqualCriterias(tname, tvalue, null);
        pemohon = (Pemohon) senaraiPemohon.get(0);
        List hakmilikL = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        hakmilikPermohonan = (HakmilikPermohonan) hakmilikL.get(0);
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        List kertas = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null);
       
        List<PermohonanKertas> listppmn = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null);
        daerah = hakmilik.getDaerah().getNama().toUpperCase() ;
            this.ppmn = listppmn.get(0);
            kertasKandungan = (PermohonanKertasKandungan) ppmn.getSenaraiKandungan().get(0) ;
            tajuk = ppmn.getTajuk() ;
            String kandungan1 = kertasKandungan.getKandungan() ;
            String temp[] = null ;
            temp = kandungan1.split(" - ") ;

            for(int i = 0 ; i < temp.length ; i++)
            {
                tujuan = temp[0] ;
//                perihalPermohonan = temp[1] ;
            }
            
        List<LaporanTanah> listtnh = laporanTanahDAO.findByEqualCriterias(tname, tvalue, null);

        if(listtnh.isEmpty()){
            smpdnU = "Tiada maklumat" ;
            smpdnS = "Tiada maklumat" ;
            smpdnB = "Tiada maklumat" ;
            smpdnT = "Tiada maklumat" ;
        }
        else {
            LaporanTanah laporTanah = listtnh.get(0) ;
            smpdnU = laporTanah.getSempadanUtaraKegunaan() ;
            smpdnS = laporTanah.getSempadanSelatanKegunaan() ;
            smpdnB = laporTanah.getSempadanBaratKegunaan() ;
            smpdnT = laporTanah.getSempadanTimurKegunaan() ;
        }

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

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
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

    




}

