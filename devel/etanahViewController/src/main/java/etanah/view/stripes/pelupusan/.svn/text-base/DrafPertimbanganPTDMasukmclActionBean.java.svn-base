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
import etanah.dao.KodHakmilikDAO ;
import etanah.dao.PermohonanTuntutanKosDAO;


import etanah.model.Pemohon;
import etanah.model.PemohonTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.KodHakmilik ;
import etanah.model.PermohonanTuntutanKos;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import etanah.service.PelupusanService;
import java.math.BigDecimal;


import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


/**
 *
 * @author afham
 */

@UrlBinding("/pelupusan/draf_pertimbangan_ptd_masuk_mcl")
public class DrafPertimbanganPTDMasukmclActionBean extends AbleActionBean {
    
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
    KodKeputusanDAO kodKpsnDAO ;
    @Inject
    KodHakmilikDAO kodHakMilikDAO ;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO ;
    
    private HakmilikPermohonan hakmilikPermohonan ;
    private Permohonan permohonan ;
    private PemohonTanah pemohonTanah ;
    private Hakmilik hakmilik ;
    private Pemohon pemohon ;
    private String tajuk ;
    private PermohonanKertas ppmn ;
    private KodKeputusan kodKpsn ;
    private PermohonanKertasKandungan kertasKandungan ;
    private KodHakmilik kodHakMilik ;
    private String tujuan;
    private String daerah ;
    private String khm ;
    private String tmph ;
    private PermohonanTuntutanKos permohonanTuntutanKos ;
    private String perihalPermohonan;
    private String latarBelakang1 ;
    private String latarBelakang2 ;
    private BigDecimal byrnhms ;
    private BigDecimal byrnhmk ;
  
    @DefaultHandler
    public Resolution showForm(){
        
        return new JSP("pelupusan/draf_PTD_Masuk_MCL.jsp").addParameter("tab", "true") ;

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


        List<PermohonanTuntutanKos> listptk = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
        List<PermohonanKertas> listppmn = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null) ;
        daerah = hakmilik.getDaerah().getNama().toUpperCase() ;
        for(int i = 0 ; i < listptk.size() ; i++){
            permohonanTuntutanKos = listptk.get(i) ;
            if(permohonanTuntutanKos.getItem().equalsIgnoreCase("pendaftaran hakmilik kekal")){
                byrnhmk = permohonanTuntutanKos.getAmaunTuntutan() ;
            }
            if(permohonanTuntutanKos.getItem().equalsIgnoreCase("pendaftaran hakmilik sementara")){
                byrnhms = permohonanTuntutanKos.getAmaunTuntutan() ;
            }
        }
        if(listppmn.isEmpty()) {
            getContext().getRequest().setAttribute("data", Boolean.FALSE);
            
            tajuk = "KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH " + daerah + " BAGI PERMOHONAN UNTUK MENJADIKAN TANAH ADAT MELAKA (MCL)";
            tujuan = "Tujuan kertas ini ialah untuk mendapatkan pertimbangan Pentadbir Tanah " + hakmilik.getDaerah().getNama() + " mengenai permohonan kebenaran untuk mencatit MCL dan kebenran untuk serah dan mohon semula tanah daripada " + pemohon.getPihak().getNama() + " No. Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + " untuk mendaftarkan tanahnya jenis dan No. Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik() + ", Lot " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + " sebagai Tanah Adat Melaka (Malacca Customary Land)" ;
            latarBelakang1 = "Pentadbir Tanah telah menerima permohonan Jadual XIV (Peraturan 18A) daripada " + pemohon.getPihak().getNama() + " No.Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + " pada " + permohonan.getInfoAudit().getTarikhMasuk().toString() + " untuk mendaftarkan tanah kepunyaannya sebagai Tanah Adat Melaka (MCL) sebagaimana yang ditanda tepi dengan warna merah dalam pelan berkembar." ;
            latarBelakang2 = "Pentadbir Tanah juga telah menerima permohonan serah dan mohon semula (Borang 12A dan Jadual 1) pada " + permohonan.getInfoAudit().getTarikhMasuk().toString() + " supaya hakmilik tersebut menjadi Tanah MCL Kekal." ;

    }
        else{

            getContext().getRequest().setAttribute("data", Boolean.FALSE);
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
  }

    public Resolution simpanMohonKertas(){

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        //hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(idPermohonan)) ;
//        kodKpsn = kodKpsnDAO.findById(kodSementara);
//        permohonan.setKeputusan(kodKpsn) ;
//        plpservice.simpanPermohonan(permohonan) ;
        

        String kndgn = tujuan + " - " + latarBelakang1 + " - " + latarBelakang2 ;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        InfoAudit infoAudit = new InfoAudit() ;
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodHakmilik kodhakmlik = kodHakMilikDAO.findById("MCL") ;
          if(hakmilikPermohonan != null){
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date()) ;
            infoAudit.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk()) ;

            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setKodHakmilik(kodhakmlik) ;
//            hakmilikPermohonan.setTempohHakmilik(Integer.MIN_VALUE) ;
//            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
//            hakmilikPermohonan.setKegunaanTanah(hakmilik.getKegunaanTanah());
//            hakmilikPermohonan.setKodHakmilik(hakmilik.getKodHakmilik());
            hakmilikPermohonan.setTempohHakmilik("Kekal") ;
//            hakmilikPermohonan.setLot(hakmilik.getLot());
//            hakmilikPermohonan.setNoLot(hakmilik.getNoLot()) ;
//            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
//            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
//            hakmilikPermohonan.setSyaratNyata(hakmilik.getSyaratNyata()) ;
//            hakmilikPermohonan.setSekatanKepentingan(hakmilik.getSekatanKepentingan()) ;
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);

        }

        if(ppmn != null){

            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setTajuk(tajuk);
            plpservice.simpanPermohonanKertas(ppmn);
            if(!ppmn.getSenaraiKandungan().isEmpty()){
                for(int a=0 ; a < ppmn.getSenaraiKandungan().size() ; a++){
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = ppmn.getSenaraiKandungan().get(a) ;
                    kertasKdgn.setKandungan(kndgn);
                    kertasKdgn.setInfoAudit(infoAudit);
                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
        }
        else{

            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date()) ;

            ppmn = new PermohonanKertas() ;
            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setTajuk(tajuk);

            plpservice.simpanPermohonanKertas(ppmn);

            infoAudit.setTarikhMasuk(new java.util.Date());
            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan() ;
            kertasKdgn.setKertas(ppmn);
            kertasKdgn.setBil(1);
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKandungan(kndgn);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan") ;

         return new JSP("pelupusan/draf_PTD_Masuk_MCL.jsp").addParameter("tab", "true");


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

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
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

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getkhm() {
        return khm;
    }

    public void setkhm(String khm) {
        this.khm = khm;
    }

    public String getTmph() {
        return tmph;
    }

    public void setTmph(String tmph) {
        this.tmph = tmph;
    }

    public String getKhm() {
        return khm;
    }

    public void setKhm(String khm) {
        this.khm = khm;
    }

    public KodHakmilik getKodHakMilik() {
        return kodHakMilik;
    }

    public void setKodHakMilik(KodHakmilik kodHakMilik) {
        this.kodHakMilik = kodHakMilik;
    }

    public BigDecimal getByrnhmk() {
        return byrnhmk;
    }

    public void setByrnhmk(BigDecimal byrnhmk) {
        this.byrnhmk = byrnhmk;
    }

    public BigDecimal getByrnhms() {
        return byrnhms;
    }

    public void setByrnhms(BigDecimal byrnhms) {
        this.byrnhms = byrnhms;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

   


    


}
