/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.KodLotDAO;
import etanah.model.KodLot;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.service.BPelService;

import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPihak;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sitinorajar
 */
@UrlBinding("/pelupusan/maklumat_pembayaran_pampasan")

public class MaklumatPembayaranPampasanActionBean extends AbleActionBean {
    
    private Hakmilik hakmilik;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PihakDAO pihakDAO;
     @Inject
    private Permohonan p;
    private CaraBayaran caraBayar;
    private String idPihak;
    private String idHakmilik;
    private Permohonan permohonan;
    private List<PermohonanPihak> permohonanPihakList;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pengguna pguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<DisPermohonanPihak> listDisMohonPihak;
    private String idPermohonan;
    private Long pihakl;
    
 @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_pembayaran_pampasan.jsp").addParameter("tab", "true");
    }  
 @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
       
        permohonan = permohonanDAO.findById(idPermohonan);
        if(permohonan != null){
        List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikTerlibat = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
        System.out.println("------HakmilikPermohonanList----"+senaraiHakmilikTerlibat);
        if(senaraiHakmilikTerlibat.size()>0){
                             hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                             System.out.println("------hakmilikPermohonan----"+hakmilikPermohonan);
                     }
                 
             }
        permohonanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
        System.out.println("------permohonanPihakList----"+permohonanPihakList);
        permohonanPihak = permohonanPihakList.get(0);
        System.out.println("------permohonanPihak----"+permohonanPihak);         
        pihakl = permohonanPihak.getPihak().getIdPihak();
        System.out.println("------pihak1----"+pihakl);
        /*
         * TEMPORARY SINCE SPOC DON"T CATER PAYMENT BY PIHAK BUT INSTEAD PAYMENT BY PERMOHONAN ONLY???
         */
        Transaksi trans = new Transaksi();
        trans = pelupusanService.findTransaksiByIdPermohonan(idPermohonan);
        if(trans!=null){
            List<DokumenKewanganBayaran> listDokKewBayar = new ArrayList<DokumenKewanganBayaran>();
            listDokKewBayar = pelupusanService.findDokKewBayarByKewDok(trans.getDokumenKewangan().getIdDokumenKewangan());
            
            for(DokumenKewanganBayaran dokKew : listDokKewBayar){
                caraBayar = pelupusanService.findCaraBayarByKewDok(dokKew.getCaraBayaran().getIdCaraBayaran());
            }
            
        }
        listDisMohonPihak = new ArrayList<DisPermohonanPihak>();
        for(PermohonanPihak pp:permohonanPihakList){
           DisPermohonanPihak dpp = new DisPermohonanPihak();
           dpp.setPermohonanPihak(pp);
           listDisMohonPihak.add(dpp);
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
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

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Long getPihakl() {
        return pihakl;
    }

    public void setPihakl(Long pihakl) {
        this.pihakl = pihakl;
    }

    public List<DisPermohonanPihak> getListDisMohonPihak() {
        return listDisMohonPihak;
    }

    public void setListDisMohonPihak(List<DisPermohonanPihak> listDisMohonPihak) {
        this.listDisMohonPihak = listDisMohonPihak;
    }

    public CaraBayaran getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(CaraBayaran caraBayar) {
        this.caraBayar = caraBayar;
    }
 
    
}
