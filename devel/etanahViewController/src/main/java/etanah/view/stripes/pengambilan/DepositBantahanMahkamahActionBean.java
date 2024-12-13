/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import com.thoughtworks.xstream.converters.Converter;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanDAO;
//import etanah.dao.HakmilikPihakBerkepentingan;
import etanah.dao.PenggunaDAO;
import etanah.model.*;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.util.List;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.KodCawangan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService ;
import java.math.BigDecimal;
import etanah.service.common.PengambilanDepositService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/depositBantahanMahkamah")
public class DepositBantahanMahkamahActionBean extends AbleActionBean {

    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PelupusanService plpservice ;
    @Inject
    LaporanPelukisPelanService serviceMohonTuntut;
     @Inject
    PengambilanDepositService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;

    PermohonanTuntutanKos mohontuntut;
    private String idPermohonan;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private List<PermohonanTuntutanKos> mohonTuntutList;
    private LaporanPelukisPelanService serviceMohon;
    private String nilaiTanah;
    private BigDecimal nilaitanah;
    private BigDecimal amaun50;
    private BigDecimal amaun75;
    private BigDecimal a1;
    private BigDecimal a2;
    private BigDecimal kira1;
    private BigDecimal kira2;
    private BigDecimal baki;
    List<PermohonanTuntutanKos> senaraiDeposit;
    private String kod="11304";
    List<CaraBayaran> senaraiCaraPembayaran;
    private List<CaraBayaran> caraBayaranList;
    private List<DokumenKewanganBayaran> dokumenKewanganBayaranList;
    private List<Transaksi> transaksiList;    

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/AkaunDepositBantahanMahkamah.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
          Permohonan p = permohonanDAO.findById(idPermohonan);
          caraBayaranList = new ArrayList<CaraBayaran>();
          dokumenKewanganBayaranList = new ArrayList<DokumenKewanganBayaran>();
          transaksiList = new ArrayList<Transaksi>();
          senaraiDeposit=service.findByIDMohonTuntut(idPermohonan, kod);

          for(PermohonanTuntutanKos mt:senaraiDeposit){
              CaraBayaran caraBayaran = service.findCaraBayarbyIdKos(mt.getIdKos());
              caraBayaranList.add(caraBayaran);
              DokumenKewanganBayaran dokumenKewanganBayaran = new DokumenKewanganBayaran();
              if(caraBayaran != null)
                  dokumenKewanganBayaran = service.getDokumenKewanganBayaranbyCaraBayaran(caraBayaran.getIdCaraBayaran());
              dokumenKewanganBayaranList.add(dokumenKewanganBayaran);
              Transaksi transaksi = new Transaksi();
              if(dokumenKewanganBayaran != null & dokumenKewanganBayaran.getDokumenKewangan()!= null)
                  transaksi = service.getTranskasiByidKewanganBayaran(dokumenKewanganBayaran.getDokumenKewangan().getIdDokumenKewangan());
              transaksiList.add(transaksi);
              
          }            
          
          
//          for(PermohonanTuntutanKos mt:senaraiDeposit)
//          {
//          senaraiCaraPembayaran=service.findCaraBayarlist(Long.toString(mt.getIdKos()));
//          System.out.println(senaraiCaraPembayaran.size());
//          }
//                  amaun50=mohontuntut.getAmaunTuntutan();
//                  nilaitanah=mohontuntut.getAmaunSebenar();
//          nilaitanah=new BigDecimal(nilaiTanah);
//            hakmilikPermohonanList = p.getSenaraiHakmilik();

        }
    }

     public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(DepositBantahanMahkamahActionBean.class, "locate");
}


    public Resolution simpan(){

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        nilaitanah=new BigDecimal(nilaiTanah);
        a1 = BigDecimal.valueOf(0.5);
        a2 = BigDecimal.valueOf(0.25);
        amaun50 =nilaitanah.multiply(a1);
        amaun75 = nilaitanah.multiply(a2);
//        amaun75=nilaitanah.multiply(getKira2());
        baki = amaun50.add(amaun75);

        System.out.println("Nilai Tanah"+nilaitanah);
        System.out.println("Deposit 50%"+ amaun50);
        System.out.println("Deposit 75%"+ baki);
          System.out.println("Deposit 75%"+amaun75);

        System.out.println("Nilai Tanah");
        System.out.println("Deposit 50%");
        System.out.println("Deposit 75%");
        System.out.println("Pnegguna" + peng.getInfoAudit().toString());
        System.out.println(peng.getInfoAudit());

        



        if (idPermohonan != null) {
           mohontuntut= new PermohonanTuntutanKos();
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            mohontuntut.setInfoAudit(info);
            mohontuntut.setPermohonan(permohonan);
            mohontuntut.setCawangan(cawangan);
            KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
            mohontuntut.setKodTransaksi(kodTransaksi);
            mohontuntut.setItem("Deposit 50%");
            mohontuntut.setAmaunSebenar(nilaitanah);
            mohontuntut.setAmaunTuntutan(getAmaun50());
            plpservice.simpanPermohonanTuntutanKos(mohontuntut) ;
//            serviceMohon.saveOrUpdateMohonTuntut(mohontuntut);
            simpanDepositKedua();
            

            
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/AkaunDepositBantahanMahkamah.jsp").addParameter("tab", "true");
    }

     public Resolution simpanDepositKedua(){

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        nilaitanah=new BigDecimal(nilaiTanah);
        a1 = BigDecimal.valueOf(0.5);
        a2 = BigDecimal.valueOf(0.25);
        amaun50 =nilaitanah.multiply(a1);
        amaun75 = nilaitanah.multiply(a2);
        baki = amaun50.add(amaun75);
        
        if (idPermohonan != null) {
           mohontuntut= new PermohonanTuntutanKos();
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            mohontuntut.setInfoAudit(info);
            mohontuntut.setPermohonan(permohonan);
            mohontuntut.setCawangan(cawangan);
            KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
            mohontuntut.setKodTransaksi(kodTransaksi);
            mohontuntut.setItem("Deposit 75%");
            mohontuntut.setAmaunSebenar(nilaitanah);
            mohontuntut.setAmaunTuntutan(getBaki());
            plpservice.simpanPermohonanTuntutanKos(mohontuntut) ;
//            serviceMohon.saveOrUpdateMohonTuntut(mohontuntut);

        rehydrate();


    }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/AkaunDepositBantahanMahkamah.jsp").addParameter("tab", "true");

}

    /**
     * @return the a1
     */
    public BigDecimal getA1() {
        return a1;
    }

    /**
     * @param a1 the a1 to set
     */
    public void setA1(BigDecimal a1) {
        this.a1 = a1;
    }

    /**
     * @return the a2
     */
    public BigDecimal getA2() {
        return a2;
    }

    /**
     * @param a2 the a2 to set
     */
    public void setA2(BigDecimal a2) {
        this.a2 = a2;
    }

    /**
     * @return the amaun50
     */
    public BigDecimal getAmaun50() {
        return amaun50;
    }

    /**
     * @param amaun50 the amaun50 to set
     */
    public void setAmaun50(BigDecimal amaun50) {
        this.amaun50 = amaun50;
    }

    /**
     * @return the kira1
     */
    public BigDecimal getKira1() {
        return kira1;
    }

    /**
     * @param kira1 the kira1 to set
     */
    public void setKira1(BigDecimal kira1) {
        this.kira1 = kira1;
    }

    /**
     * @return the kira2
     */
    public BigDecimal getKira2() {
        return kira2;
    }

    /**
     * @param kira2 the kira2 to set
     */
    public void setKira2(BigDecimal kira2) {
        this.kira2 = kira2;
    }

    /**
     * @return the amaun75
     */
    public BigDecimal getAmaun75() {
        return amaun75;
    }

    /**
     * @param amaun75 the amaun75 to set
     */
    public void setAmaun75(BigDecimal amaun75) {
        this.amaun75 = amaun75;
    }

    /**
     * @return the baki
     */
    public BigDecimal getBaki() {
        return baki;
    }

    /**
     * @param baki the baki to set
     */
    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }
    public BigDecimal getNilaitanah() {
        return nilaitanah;
    }

    public void setNilaitanah(BigDecimal nilaitanah) {
        this.nilaitanah = nilaitanah;
    }
    public String getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(String nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public LaporanPelukisPelanService getServiceMohon() {
        return serviceMohon;
    }

    public void setServiceMohon(LaporanPelukisPelanService serviceMohon) {
        this.serviceMohon = serviceMohon;
    }

    public List<PermohonanTuntutanKos> getMohonTuntutList() {
        return mohonTuntutList;
    }

    public void setMohonTuntutList(List<PermohonanTuntutanKos> mohonTuntutList) {
        this.mohonTuntutList = mohonTuntutList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }



    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanTuntutanKos getMohontuntut() {
        return mohontuntut;
    }

    public void setMohontuntut(PermohonanTuntutanKos mohontuntut) {
        this.mohontuntut = mohontuntut;
    }

    public List<PermohonanTuntutanKos> getSenaraiDeposit() {
        return senaraiDeposit;
    }

    public void setSenaraiDeposit(List<PermohonanTuntutanKos> senaraiDeposit) {
        this.senaraiDeposit = senaraiDeposit;
    }
    public List<CaraBayaran> getSenaraiCaraPembayaran() {
        return senaraiCaraPembayaran;
    }

    public void setSenaraiCaraPembayaran(List<CaraBayaran> senaraiCaraPembayaran) {
        this.senaraiCaraPembayaran = senaraiCaraPembayaran;
    }

    public List<CaraBayaran> getCaraBayaranList() {
        return caraBayaranList;
    }

    public void setCaraBayaranList(List<CaraBayaran> caraBayaranList) {
        this.caraBayaranList = caraBayaranList;
    }

    public List<DokumenKewanganBayaran> getDokumenKewanganBayaranList() {
        return dokumenKewanganBayaranList;
    }

    public void setDokumenKewanganBayaranList(List<DokumenKewanganBayaran> dokumenKewanganBayaranList) {
        this.dokumenKewanganBayaranList = dokumenKewanganBayaranList;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    

    
}
