/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.Notis;
import etanah.model.Permohonan;
import etanah.service.common.EnforcementService;
import etanah.service.common.HakmilikPermohonanService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Tengku.Fauzan
 * 13/3/2014
 */

@HttpCache(allow = false)
@UrlBinding("/enf/utiliti_status_tempoh_notis")


public class UtilitiStatusTempohNotisActionBean extends AbleActionBean {
    
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    NotisDAO notisDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    
    private static final Logger LOG = Logger.getLogger(UtilitiPertanyaanNotisActionBean.class);
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idHakmilik;
    private List<Notis> listNotis;
    private Notis notis;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String recordCount02;
    private String recordCount01;
  
    
     @DefaultHandler
        public Resolution showform() {
        
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
    }
   
       
     public Resolution searchMilik() {  
     
         LOG.info("-------------Check Hakmilik Start---------");
       
         if (idHakmilik == null) {
            
             addSimpleError("Sila masukkan ID Hakmilik.");
             return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
                }     
      
           System.out.println("idHakmilik : "+idHakmilik);
         
           hakmilik = hakmilikDAO.findById(idHakmilik);
         
        if (hakmilik != null) {
                          
                hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList= hakmilikPermohonanService.findIdPermohonanbyIdHakmilikENF(idHakmilik);
                if (hakmilikPermohonanList.size() != 0) {
                    hakmilikPermohonan = hakmilikPermohonanList.get(0);
                    recordCount01 = String.valueOf(hakmilikPermohonanList.size());
                
            }
        }
        else  {
              System.out.print("Hakmilik " + idHakmilik + " tidak dijumpai.");
              addSimpleError("Permohonan " + idHakmilik + " tidak dijumpai.");
              //return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/pertanyaan_notis.jsp");
               }
         
      return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
         
     }
        
        
     
      public Resolution checkID() {
          
           LOG.info("-------------Check Permohonan Start---------");
       
         if (idPermohonan == null) {
            
             addSimpleError("Sila masukkan ID Permohonan.");
             return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
                }   
           
       
        
        permohonan = permohonanDAO.findById(idPermohonan);
        
        System.out.println("idPermohonan : "+idPermohonan);
        
        hakmilikPermohonan = hakmilikPermohonanService.findIdhakmilikbyIdPermohonanENF(idPermohonan);
        
        if (permohonan != null) {
            
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                if (listNotis.size() != 0) {
                    notis = listNotis.get(0);
                    recordCount02 = String.valueOf(listNotis.size());
            
                }
        }
        else  {
              System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
              addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
              //return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/pertanyaan_notis.jsp");
               }
           return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
      }
      
      
      public Resolution popupNotis(){
        
        
            //System.out.println("idHakmilik : "+idHakmilik);
          hakmilik = hakmilikDAO.findById(idHakmilik);
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList= hakmilikPermohonanService.findIdPermohonanbyIdHakmilikENF(idHakmilik);
                if (hakmilikPermohonanList.size() != 0) {
                    hakmilikPermohonan = hakmilikPermohonanList.get(0);
                    recordCount02 = String.valueOf(hakmilikPermohonanList.size());
                }
                System.out.println("idPermohonan : "+idPermohonan);
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                if (listNotis.size() != 0) {
                    notis = listNotis.get(0);
                    recordCount02 = String.valueOf(listNotis.size());

      }
                 return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/status_tempoh_notis.jsp");
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

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getRecordCount02() {
        return recordCount02;
    }

    public void setRecordCount02(String recordCount02) {
        this.recordCount02 = recordCount02;
    }

    public String getRecordCount01() {
        return recordCount01;
    }

    public void setRecordCount01(String recordCount01) {
        this.recordCount01 = recordCount01;
    }


      
}


  