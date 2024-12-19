/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Kompaun;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
/**
 *
 * @author Tengku.Fauzan
 */


@HttpCache(allow = false)
@UrlBinding("/enf/utiliti_status_bayaran")
public class UtilitiStatusBayaranActionBean extends AbleActionBean  {
    
    @Inject
    private PermohonanDAO permohonanDAO;   
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    
    
    private Permohonan permohonan;
    private String idPermohonan;
    private List<Kompaun> senaraiKompaun;
    private Kompaun kompaun;
    private List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(UtilitiStatusBayaranActionBean.class);
    
    
    
    @DefaultHandler
    public Resolution showform() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_bayaran.jsp");
    }
    
    
     public Resolution searchKompaun() {
         
          LOG.info("-------------Check Permohonan Start---------");
          System.out.println("idPermohonan : "+idPermohonan);
       
         if (idPermohonan == null) {
            
             addSimpleError("Sila masukkan ID Permohonan.");
             return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_bayaran.jsp");
                }  
         
        permohonan = permohonanDAO.findById(idPermohonan);
        
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaunByID(permohonan.getIdPermohonan());
                LOG.info("size senaraiKompaun: " + senaraiKompaun.size());
                
                senaraiMohonTuntutBayar = enforceService.getSenaraiPtb(idPermohonan);
                LOG.info("size list senarai mohon tuntut bayar : " + senaraiMohonTuntutBayar.size());

            } else {
               
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
         
            if (senaraiKompaun.isEmpty()) {
                addSimpleMessage("Permohonan " + idPermohonan + " tidak mempunyai rekod kompaun.");
            } else {
                addSimpleMessage("Permohonan " + idPermohonan + " mempunyai " + senaraiKompaun.size() + " rekod kompaun.");
            }
         
         
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_bayaran.jsp");
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

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public List<PermohonanTuntutanBayar> getSenaraiMohonTuntutBayar() {
        return senaraiMohonTuntutBayar;
    }

    public void setSenaraiMohonTuntutBayar(List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar) {
        this.senaraiMohonTuntutBayar = senaraiMohonTuntutBayar;
    }

   
    
     
     
     
     
    
}
