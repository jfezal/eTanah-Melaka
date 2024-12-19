/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
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
import etanah.model.AmbilPampasan;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BPelService;

import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.RegService;
import etanah.service.common.PerbicaraanService;
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
 * @author Navin
 */

@UrlBinding("/pelupusan/rekod_pembayaran_pampasanPHLL")
public class RekodPembayaranPampasanPHLLActionBean extends AbleActionBean {
    
    private Hakmilik hakmilik;
    @Inject
    HakmilikDAO hakmilikDAO;
     @Inject
    PerbicaraanService perbicaraanService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PengambilanService pengambilanService ;
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
    private HakmilikPerbicaraan hbicara ;
    private AmbilPampasan ambilPampasan ;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private BigDecimal jumTerimaPampasan;
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    
 @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/hak_lalulalang/rekod_pembayaran_pampasanPHLL.jsp").addParameter("tab", "true");
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
        
        permohonanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
        System.out.println("------permohonanPihakList----"+permohonanPihakList);
        permohonanPihak = permohonanPihakList.get(0);
        System.out.println("------permohonanPihak----"+permohonanPihak);         
        pihakl = permohonanPihak.getPihak().getIdPihak();
        System.out.println("------pihak1----"+pihakl);
        if(senaraiHakmilikTerlibat.size()>0){
                             hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                             System.out.println("------hakmilikPermohonan----"+hakmilikPermohonan);
                             hbicara = new HakmilikPerbicaraan();
                             hbicara = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                             if(hbicara != null){
                                 perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hbicara.getIdPerbicaraan());
                                 if (perbicaraanKehadiran == null) {
                                     perbicaraanKehadiran = new PerbicaraanKehadiran();
                                     InfoAudit infoAudit = new InfoAudit();
                                     infoAudit.setTarikhMasuk(new java.util.Date());
                                     infoAudit.setDimasukOleh(pguna);
                                     perbicaraanKehadiran.setInfoAudit(infoAudit);
                                     perbicaraanKehadiran.setCawangan(pguna.getKodCawangan());
                                     perbicaraanKehadiran.setPerbicaraan(hbicara);
                                     perbicaraanKehadiran.setPihak(permohonanPihak);
                                     pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                                 }
                                 
                             }
                     }
                 
             }
        
        

        listDisMohonPihak = new ArrayList<DisPermohonanPihak>();
        for(PermohonanPihak pp:permohonanPihakList){
           DisPermohonanPihak dpp = new DisPermohonanPihak();
           dpp.setPermohonanPihak(pp);
           listDisMohonPihak.add(dpp);
        }
        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hbicara.getIdPerbicaraan());
        if(perbicaraanKehadiran != null){
        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
            if (!ambilPampasanList1.isEmpty()) {
                ambilPampasan = ambilPampasanList1.get(0);
                kodCaraBayaran = ambilPampasan.getKodCaraBayaran() ;
                kodBank = ambilPampasan.getKodBank() ;
                noDok = ambilPampasan.getNoDok() ;
                tarikhDok = ambilPampasan.getTarikhDok() ;
            }
        }
 }
 
 public Resolution simpanMaklumatPembayaranPampasan() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikTerlibat = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
        System.out.println("------HakmilikPermohonanList----"+senaraiHakmilikTerlibat);
        
        permohonanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
        System.out.println("------permohonanPihakList----"+permohonanPihakList);
        permohonanPihak = permohonanPihakList.get(0);
        System.out.println("------permohonanPihak----"+permohonanPihak);         
        pihakl = permohonanPihak.getPihak().getIdPihak();
        System.out.println("------pihak1----"+pihakl);
       if (senaraiHakmilikTerlibat.size() > 0) {
         hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
         System.out.println("------hakmilikPermohonan----" + hakmilikPermohonan);
         hbicara = new HakmilikPerbicaraan();
         hbicara = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
         if (hbicara != null) {
             perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hbicara.getIdPerbicaraan());
             if (perbicaraanKehadiran != null) {
                 List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                 if (ambilPampasanList1.isEmpty()) {
                     ambilPampasan = new AmbilPampasan();
                     ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                     jumTerimaPampasan = permohonanPihak.getNilai();
                     ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                     ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                     ambilPampasan.setNoDok(noDok);
                     ambilPampasan.setTarikhDok(tarikhDok);
                     ambilPampasan.setKodBank(kodBank);
                     InfoAudit info = new InfoAudit();
                     info.setDimasukOleh(pguna);
                     info.setTarikhMasuk(new java.util.Date());
                     ambilPampasan.setInfoAudit(info);
                     pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                 }else{
                     ambilPampasan = ambilPampasanList1.get(0);
                     ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                     jumTerimaPampasan = permohonanPihak.getNilai();
                     ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                     ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                     ambilPampasan.setNoDok(noDok);
                     ambilPampasan.setTarikhDok(tarikhDok);
                     ambilPampasan.setKodBank(kodBank);
                     InfoAudit info = new InfoAudit();
                     info = ambilPampasan.getInfoAudit() ;
                     info.setDiKemaskiniOleh(pguna);
                     info.setTarikhKemaskini(new java.util.Date());
                     ambilPampasan.setInfoAudit(info);
                     pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                 }
             }
         }

     }
       addSimpleMessage("Maklumat telah berjaya disimpan");
     return new JSP("pelupusan/hak_lalulalang/rekod_pembayaran_pampasanPHLL.jsp").addParameter("tab", "true");
    
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

    public HakmilikPerbicaraan getHbicara() {
        return hbicara;
    }

    public void setHbicara(HakmilikPerbicaraan hbicara) {
        this.hbicara = hbicara;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public BigDecimal getJumTerimaPampasan() {
        return jumTerimaPampasan;
    }

    public void setJumTerimaPampasan(BigDecimal jumTerimaPampasan) {
        this.jumTerimaPampasan = jumTerimaPampasan;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public Date getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(Date tarikhDok) {
        this.tarikhDok = tarikhDok;
    }

}

