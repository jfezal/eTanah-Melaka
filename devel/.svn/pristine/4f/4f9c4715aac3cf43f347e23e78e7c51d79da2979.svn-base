/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.dao.HakmilikDAO;
import etanah.service.PelupusanService ;
import etanah.dao.PermohonanDAO ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.model.Hakmilik;
import etanah.model.Permohonan ;
import etanah.model.Pengguna ;
import etanah.model.HakmilikPermohonan ;
import etanah.model.InfoAudit;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanKos;
import etanah.view.etanahActionBeanContext ;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before ;
import net.sourceforge.stripes.action.DefaultHandler ;
import net.sourceforge.stripes.action.UrlBinding ;
import net.sourceforge.stripes.action.Resolution ;
import net.sourceforge.stripes.controller.LifecycleStage ;



/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/suratKelulusan_MCL")
public class suratKelulusanMCLActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    private Permohonan permohonan ;
    private Hakmilik hakmilik ;
    private HakmilikPermohonan hakmilikPermohonan ;
    //private Pengguna pengguna ;
    String idPermohonan ;




    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/surat_kelulusan_MCL.jsp").addParameter("tab", "true") ;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){

        //pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;


        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan) ;
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;

        }
        
                /*
                 * CASE Bayaran Endosan MCL (MCMCL)
                */
//                PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                PermohonanTuntutanKos mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBMCL");
                PermohonanTuntutanKos mohonTuntutKos1 = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBPTK");
                Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = new InfoAudit();
//                mohonTuntutKos = new PermohonanTuntutanKos();
//                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISMCL");
                PermohonanTuntutan mohonTuntut = new PermohonanTuntutan();
                mohonTuntut = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(idPermohonan, "DISMCL");
                if(mohonTuntut==null){
                    mohonTuntut = new PermohonanTuntutan();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntut.setInfoAudit(info);
                    mohonTuntut.setPermohonan(permohonan);
                    mohonTuntut.setCawangan(permohonan.getCawangan());
                    mohonTuntut.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DISMCL"));        
                    mohonTuntut.setTarikhTuntutan(new java.util.Date());
                    pelupusanService.simpanPermohonanTuntutan(mohonTuntut); 
                }else{
                    info = mohonTuntut.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntut.setInfoAudit(info);
                    mohonTuntut.setPermohonan(permohonan);
                    mohonTuntut.setCawangan(permohonan.getCawangan());
                    mohonTuntut.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DISMCL"));
                    mohonTuntut.setTarikhTuntutan(new java.util.Date());
                    pelupusanService.simpanPermohonanTuntutan(mohonTuntut); 
                }
                
                if(mohonTuntutKos==null){
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Endosan MCL");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBMCL"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBMCL").getKodKewTrans());
                    pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos); 
                }else{
                    info = mohonTuntutKos.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Endosan MCL");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBMCL"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBMCL").getKodKewTrans());
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos); 
                }
                
                //For Bayaran Proses MCL
                
                 if(mohonTuntutKos1==null){
                    mohonTuntutKos1 = new PermohonanTuntutanKos();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos1.setInfoAudit(info);
                    mohonTuntutKos1.setPermohonan(permohonan);
                    mohonTuntutKos1.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos1.setItem("Bayaran Proses Permohonan Tanah (MCMCL)");
                    mohonTuntutKos1.setAmaunTuntutan(new BigDecimal(50));
                    mohonTuntutKos1.setKodTuntut(kodTuntutDAO.findById("DISBPTK"));
                    mohonTuntutKos1.setKodTransaksi(kodTuntutDAO.findById("DISBPTK").getKodKewTrans());
                    pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos1); 
                }else{
                    info = mohonTuntutKos.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKos1.setInfoAudit(info);
                    mohonTuntutKos1.setPermohonan(permohonan);
                    mohonTuntutKos1.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos1.setItem("Bayaran Proses Permohonan Tanah (MCMCL)");
                    mohonTuntutKos1.setAmaunTuntutan(new BigDecimal(50));
                    mohonTuntutKos1.setKodTuntut(kodTuntutDAO.findById("DISBPTK"));
                    mohonTuntutKos1.setKodTransaksi(kodTuntutDAO.findById("DISBPTK").getKodKewTrans());
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos1); 
                }
        
    }

    public Resolution simpan() {

        //pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit() ;
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk());
        if(hakmilikPermohonan != null){
        //InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        hakmilikPermohonan.setInfoAudit(infoAudit) ;
//        hakmilikPermohonan.setKegunaanTanah(hakmilik.getKegunaanTanah());
        hakmilikPermohonan.setSyaratNyata(hakmilik.getSyaratNyata());
        
        }
//        hakmilikPermohonan.setTempohHakmilik("Kekal");
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        addSimpleMessage("Maklumat Telah Disimpan") ;

        return new JSP("pelupusan/surat_kelulusan_MCL.jsp").addParameter("tab", "true");
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

    
}
