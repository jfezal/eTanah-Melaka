/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.AduanLokasiDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanLokasi;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodLot;
import etanah.model.KodNegeri;
import etanah.model.KodPemilikan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.AduanService;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Tengku.Fauzan
 */

@HttpCache(allow = false)
@UrlBinding("/enf/utiliti_kemaskini_aduan")

public class UtilitiKemaskiniAduanActionBean extends AbleActionBean {
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private KodBandarPekanMukimDAO kodbandarPekanMukimDAO;
    @Inject
    private KodPemilikanDAO kodPemilikanDAO;
    @Inject
    private AduanLokasiDAO aduanLokasiDAO;
    @Inject
    private AduanService aduanService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(UtilitiKemaskiniAduanActionBean.class);
    private Permohonan permohonan;
    private String idPermohonan;
    private List<Permohonan> ListMaklumatAduan;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KodCaraPermohonan> senaraiKodCaraPermohonan;
    private List<KodBandarPekanMukim> senaraiKodBandarPekanMukim;
    private List<KodUrusan> senaraiUrusan;
    private String recordCount01;
    private String recordCount02;
    private KodCawangan cawangan;
    private String kodDaerah;
    private String daerah;
    private String sebab;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private String penyerahNoPengenalanBaru;
    private String penyerahNoPengenalanLain;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private KodNegeri penyerahNegeri;
    private String penyerahEmail;
    private String penyerahNoTelefon1;
    private AduanLokasi aduanLokasi;
    private KodPemilikan pemilikan;
    private KodBandarPekanMukim bandarPekanMukim;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<AduanLokasi> senaraiAduanLokasi;
    private String noLot;
    private String lokasi;
    private KodCaraPermohonan caraPermohonan;
    private KodNegeri negeri;
    private KodUrusan kodUrusan;
    private KodLot kodLot;
    private String tarikhMasuk;
    private Pengguna pengguna;
    
    
 
    
    @DefaultHandler
    public Resolution showform() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/kemaskini_aduan.jsp");
    }
     
     public Resolution trackAduan() {
          
           LOG.info("-------------Check Permohonan Start---------");
        // permohonan = permohonanDAO.findById(idPermohonan);
         if (idPermohonan == null) {
            
             addSimpleError("Sila masukkan ID Permohonan.");
             return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/kemaskini_aduan.jsp");
                }   
       
        
        System.out.println("idPermohonan : "+idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiKodCaraPermohonan = aduanService.getSenaraiKodCaraPermohonan();
        senaraiUrusan = aduanService.getSenaraiUrusan();
        senaraiKodBandarPekanMukim = aduanService.getSenaraiKodBandarPekanMukim();
        
        //System.out.println("idPermohonan : "+idPermohonan);
        
        if (permohonan != null) {
               
                ListMaklumatAduan = new ArrayList<Permohonan>();
                ListMaklumatAduan = aduanService.getMaklumatAduanbyIdPermohonanENF(idPermohonan);
               
                  if (ListMaklumatAduan.size() != 0) {
                    permohonan = ListMaklumatAduan.get(0);
                    recordCount01 = String.valueOf(ListMaklumatAduan.size());
                  }
             
               senaraiAduanLokasi =  new ArrayList<AduanLokasi>();
               senaraiAduanLokasi =   aduanService.findSenaraiLokasiByID(idPermohonan);
                if (senaraiAduanLokasi.size() != 0) {
                    aduanLokasi = senaraiAduanLokasi.get(0);
                    recordCount02 = String.valueOf(senaraiAduanLokasi.size());
                  }
                               
             tarikhMasuk = dateF.format(permohonan.getInfoAudit().getTarikhMasuk());    
             cawangan = permohonan.getCawangan();                                             
             caraPermohonan = permohonan.getCaraPermohonan();
             sebab = permohonan.getSebab();
             kodUrusan = permohonan.getKodUrusan();
             penyerahNama = permohonan.getPenyerahNama();
             penyerahJenisPengenalan = permohonan.getPenyerahJenisPengenalan(); 
             penyerahNoPengenalanLain = permohonan.getPenyerahNoPengenalan();
                                  
             penyerahAlamat1 = permohonan.getPenyerahAlamat1();                         
             penyerahAlamat2 = permohonan.getPenyerahAlamat2();                          
             penyerahAlamat3 = permohonan.getPenyerahAlamat3();                         
             penyerahAlamat4 = permohonan.getPenyerahAlamat4();                         
             penyerahPoskod = permohonan.getPenyerahPoskod();                         
             penyerahNegeri = permohonan.getPenyerahNegeri();                            
             penyerahEmail = permohonan.getPenyerahEmail();                         
             penyerahNoTelefon1 = permohonan.getPenyerahNoTelefon1();                         
  
             
             String bandarPekanMukim1 = cawangan.getDaerah().getKod();      
             setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
             
             if (aduanLokasi != null){
             //String bandarPekanMukim1 = cawangan.getDaerah().getKod();      
             //setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));    
             bandarPekanMukim = aduanLokasi.getBandarPekanMukim();
             pemilikan = aduanLokasi.getPemilikan();
             kodLot = aduanLokasi.getKodLot();
             noLot = aduanLokasi.getNoLot();
             lokasi = aduanLokasi.getLokasi();
             }       
             
        }
        else  {
              System.out.print("Aduan " + idPermohonan + " tidak dijumpai.");
              addSimpleError("Aduan " + idPermohonan + " tidak dijumpai.");
              //return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/pertanyaan_notis.jsp");
               }
           
      
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/kemaskini_aduan.jsp");
     }
     
     
     public Resolution updateAduan(){
     
     LOG.debug("saveMohon");
     pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
     LOG.info("id permohonan------------" + permohonan.getIdPermohonan());
 
     permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());    
     senaraiAduanLokasi = aduanService.findSenaraiLokasiByID(permohonan.getIdPermohonan());
       if (senaraiAduanLokasi.size() != 0) {
                    aduanLokasi = senaraiAduanLokasi.get(0);
                    recordCount02 = String.valueOf(senaraiAduanLokasi.size());
                  }
       
             
       permohonan.setPenyerahNama(penyerahNama);
       permohonan.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
       permohonan.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
       
       permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
         
        

        permohonan.setPenyerahAlamat1(penyerahAlamat1);
        permohonan.setPenyerahAlamat2(penyerahAlamat2);
        permohonan.setPenyerahAlamat3(penyerahAlamat3);
        permohonan.setPenyerahAlamat4(penyerahAlamat4);
        permohonan.setPenyerahPoskod(penyerahPoskod);
        permohonan.setPenyerahNegeri(penyerahNegeri);
        permohonan.setPenyerahNoTelefon1(penyerahNoTelefon1);
        permohonan.setPenyerahEmail(penyerahEmail);
        
        
        if (aduanLokasi == null){
       
        InfoAudit iu = new InfoAudit();
        iu.setDimasukOleh(pengguna);
        iu.setTarikhMasuk(new java.util.Date());    
            
        aduanLokasi = new AduanLokasi();
        aduanLokasi.setPermohonan(permohonan);
        aduanLokasi.setCawangan(permohonan.getCawangan());
         if (bandarPekanMukim == null) {
              String[] tname = {"bandarPekanMukim"};
               Object[] tvalue = {"00"};
              for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                aduanLokasi.setBandarPekanMukim(kbpm);
              }
              } else {
                 aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
               }
      
               cawangan = permohonan.getCawangan();
              
               aduanLokasi.setPemilikan(pemilikan);
               aduanLokasi.setNoLot(noLot);
               aduanLokasi.setKodLot(kodLot);
               aduanLokasi.setLokasi(lokasi);
        
              String bandarPekanMukim1 = cawangan.getDaerah().getKod();      
              setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
              bandarPekanMukim = aduanLokasi.getBandarPekanMukim();
   
              aduanLokasi.setInfoAudit(iu); 
              enforceService.simpanAduanLokasi(aduanLokasi);
        
        }
        
        else{
        
          aduanLokasi.setPermohonan(permohonan);
        
          if (bandarPekanMukim == null) {
              String[] tname = {"bandarPekanMukim"};
               Object[] tvalue = {"00"};
              for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                aduanLokasi.setBandarPekanMukim(kbpm);
              }
              } else {
                 aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
               }
      
               cawangan = permohonan.getCawangan();
        
               aduanLokasi.setPemilikan(pemilikan);
               aduanLokasi.setNoLot(noLot);
               aduanLokasi.setKodLot(kodLot);
               aduanLokasi.setLokasi(lokasi);
        
              String bandarPekanMukim1 = cawangan.getDaerah().getKod();      
              setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
              bandarPekanMukim = aduanLokasi.getBandarPekanMukim();
              
              InfoAudit iu = aduanLokasi.getInfoAudit();
              iu.setDiKemaskiniOleh(pengguna);
              iu.setTarikhKemaskini(new java.util.Date());
              
              enforceService.simpanAduanLokasi(aduanLokasi);
            }

    
        
               InfoAudit ia = permohonan.getInfoAudit();   
               ia.setDiKemaskiniOleh(pengguna);
               ia.setTarikhKemaskini(new java.util.Date());
    
               
             
      enforceService.simpanPermohonan(permohonan);
      //enforceService.simpanAduanLokasi(aduanLokasi);
      addSimpleMessage("Maklumat telah berjaya dikemaskini.");
       
     return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/kemaskini_aduan.jsp");
     
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

    public List<Permohonan> getListMaklumatAduan() {
        return ListMaklumatAduan;
    }

    public void setListMaklumatAduan(List<Permohonan> ListMaklumatAduan) {
        this.ListMaklumatAduan = ListMaklumatAduan;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return senaraiKodCaraPermohonan;
    }

    public void setSenaraiKodCaraPermohonan(List<KodCaraPermohonan> senaraiKodCaraPermohonan) {
        this.senaraiKodCaraPermohonan = senaraiKodCaraPermohonan;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return senaraiKodBandarPekanMukim;
    }

    public void setSenaraiKodBandarPekanMukim(List<KodBandarPekanMukim> senaraiKodBandarPekanMukim) {
        this.senaraiKodBandarPekanMukim = senaraiKodBandarPekanMukim;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalanBaru() {
        return penyerahNoPengenalanBaru;
    }

    public void setPenyerahNoPengenalanBaru(String penyerahNoPengenalanBaru) {
        this.penyerahNoPengenalanBaru = penyerahNoPengenalanBaru;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public String getPenyerahNoTelefon1() {
        return penyerahNoTelefon1;
    }

    public void setPenyerahNoTelefon1(String penyerahNoTelefon1) {
        this.penyerahNoTelefon1 = penyerahNoTelefon1;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public List<AduanLokasi> getSenaraiAduanLokasi() {
        return senaraiAduanLokasi;
    }

    public void setSenaraiAduanLokasi(List<AduanLokasi> senaraiAduanLokasi) {
        this.senaraiAduanLokasi = senaraiAduanLokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(String tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public String getRecordCount01() {
        return recordCount01;
    }

    public void setRecordCount01(String recordCount01) {
        this.recordCount01 = recordCount01;
    }

    public String getRecordCount02() {
        return recordCount02;
    }

    public void setRecordCount02(String recordCount02) {
        this.recordCount02 = recordCount02;
    }

    public String getPenyerahNoPengenalanLain() {
        return penyerahNoPengenalanLain;
    }

    public void setPenyerahNoPengenalanLain(String penyerahNoPengenalanLain) {
        this.penyerahNoPengenalanLain = penyerahNoPengenalanLain;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    
}
