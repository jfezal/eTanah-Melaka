/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanNotaDAO;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodNegeriDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.stripes.pembangunan.PembangunanUtility;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.StringTokenizer;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.WorkflowException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author khairul.hazwan
 */
@UrlBinding("/utiliti/tujuanPermohonan")
public class UtilitiTujuanPermohonanActionBean extends AbleActionBean {
    
    private static final Logger LOG = Logger.getLogger(UtilitiTujuanPermohonanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService pembangunanService;   
    @Inject
    etanah.Configuration conf;   
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider; 
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanUtility  pu;
    
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private Boolean ltshow = false;
    private Boolean idMohonShow = false;  
    private String tujuan;
    private String idPermohonan;
    private String kodNegeri;   
    private String nama;
    
    @DefaultHandler
    public Resolution showForm() {      
        LOG.info("--Masukkan ID MOHON--");
        idMohonShow = true;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_tujuan_permohonan.jsp");
    }
     
    public Resolution reset() {       
        idMohonShow = true;      
        permohonan = new Permohonan();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_tujuan_permohonan.jsp");
    }

    public Resolution checkPermohonan() {
    
        LOG.info("-------------Check Permohonan Start---------");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("======== idPermohonan========== :" + idPermohonan);
        
        permohonan = permohonanDAO.findById(idPermohonan);
        if(permohonan == null){                    
            addSimpleMessage("Id Permohonan" + idPermohonan + " ini tidak wujud'.");           
            
            ltshow = false;
            idMohonShow = true;
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_tujuan_permohonan.jsp");
         }
         else {
            if (idPermohonan == null) {
                ltshow = false;
                idMohonShow = true;
            } else {
                ltshow = true;
                idMohonShow = false;
                LOG.info("--------ltShow---------");
            }
 

        if (idPermohonan != null) {
            addSimpleMessage("Tujuan Permohonan Untuk " + idPermohonan + " Telah Dijumpai. Sila Kemaskini Tujuan Permohonan Diruang Yang Disediakan.");
            permohonan = permohonanDAO.findById(idPermohonan);
          
            if(permohonan.getSebab() != null){
                tujuan = permohonan.getSebab();
            }else{
                getTujuanDefault(idPermohonan);
            }  
        }
            
        LOG.info("--------Check Permohonan Stop---------");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_tujuan_permohonan.jsp");
    }
    }
      
    public final void getTujuanDefault(String idPermohonan) {
        LOG.info("getTujuanDefault");
        kodNegeri = conf.getProperty("kodNegeri"); 
        if (permohonan.getSebab() == null) {
            LOG.info("Sebab Start :"+permohonan.getSebab());
            String urusan  ="";
            String lokasi = "";
            String kodNegeriNama ="";
            String daerah="";                       
            if(kodNegeri!=null){
            kodNegeriNama = kodNegeriDAO.findById(kodNegeri).getNama();
            }
            
            List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(w);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                StringBuffer kodBPM = new StringBuffer();
                // make first letter Caps in each word
                if (hakmilik.getBandarPekanMukim() != null) {
                    String str1 = hakmilik.getBandarPekanMukim().getNama();
                    StringTokenizer st1 = new StringTokenizer(str1);
                    while (st1.hasMoreTokens()) {
                        String t1 = st1.nextToken();
                        t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                        kodBPM.append(t1 + " ");
                    }
                }
                      
                String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
                String noLot = hakmilik.getNoLot().replaceAll("^0*", "");
                  
                if (w == 0) {
                    lokasi = " Hakmilik "+ hakmilik.getKodHakmilik().getKod()+" "+ noHakmilik + ", " +hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";                           
                }
                                
                if (w > 0) {
                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                        System.out.println("--ID HM > 1---");
                        lokasi = lokasi + ", "+ " Hakmilik "+ hakmilik.getKodHakmilik().getKod()+" "+ noHakmilik + ", " +hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                                           
                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                        System.out.println("--ID HM = 1---");
                        lokasi = lokasi + " dan " + " Hakmilik "+ hakmilik.getKodHakmilik().getKod()+" "+ noHakmilik + ", " +hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";             
                    }
                }  
                daerah = kodBPM + ", Daerah " + hakmilik.getDaerah().getNama() + ", " + kodNegeriNama;
            }
            
               //nama
                HakmilikPermohonan hp1 = new HakmilikPermohonan();
                hp1 = permohonan.getSenaraiHakmilik().get(0);
                hakmilik = hakmilikDAO.findById(hp1.getHakmilik().getIdHakmilik());
                String kodPihak="PM";
                List<HakmilikPihakBerkepentingan> hakmilikPihakList=new ArrayList<HakmilikPihakBerkepentingan>();
                hakmilikPihakList = pembangunanService.findHakmilikPihakByKod(hakmilik.getIdHakmilik(), kodPihak);
                if (!(hakmilikPihakList.isEmpty())) {            
                   hakmilikPihak = hakmilikPihakList.get(0);
                }
                
                nama ="";
                for(int j = 0;j < hakmilikPihakList.size(); j++){
                HakmilikPihakBerkepentingan pm = new HakmilikPihakBerkepentingan();
                pm = hakmilikPihakList.get(j);

                if(j == 0){
                     nama = pm.getPihak().getNama();
                 }
                if(j > 0){
                if(j <= ((hakmilikPihakList.size() + 2)- 4)){
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if(j == (hakmilikPihakList.size() - 1)){
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
                }
                }

               LOG.info("------Nama----"+nama);
               nama = pu.convertStringtoInitCap(nama);
                
        
          urusan = permohonan.getKodUrusan().getNama();
          
          if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSKKT")){ 
                 if (hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("0") 
                     ||hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("4") 
                     || hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("5")){
                     
                     urusan = "Permohonan Tukar Syarat Pengenaan Kategori Kegunaan Tanah ";                                        
                 }
          }else if(permohonan.getKodUrusan().getKod().equals("RNS")){
                if(permohonan.getPermohonanSebelum().getSebab() != null){              
                    tujuan = "Rayuan Penilaian Semula Tanah Untuk "+ permohonan.getPermohonanSebelum().getSebab();
                }else{
                    tujuan = "Rayuan Penilaian Semula Tanah Untuk "+ permohonan.getPermohonanSebelum().getKodUrusan().getNama() +" di bawah Seksyen "+ permohonan.getPermohonanSebelum().getKodUrusan().getRujukanKanun() +" di atas " + lokasi +""+daerah+ ".";
                }              
          }else if(permohonan.getKodUrusan().getKod().equals("RPMMK") || permohonan.getKodUrusan().getKod().equals("RLTB") || permohonan.getKodUrusan().getKod().equals("RPP")){   
                if(nama != null){ 
                    tujuan = urusan + " Daripada " + nama + " di atas" + lokasi +""+daerah+ ".";        
                }else{
                    tujuan = urusan +" di atas " + lokasi +""+daerah+ "."; 
                }
                
          }else if(permohonan.getKodUrusan().getKod().equals("RTLK") || permohonan.getKodUrusan().getKod().equals("PBSK")){                  
                    tujuan = urusan + " di atas" + lokasi +""+daerah+ ".";          
          }else {
                    tujuan = urusan +" di bawah Seksyen "+permohonan.getKodUrusan().getRujukanKanun() +" di atas " + lokasi +""+daerah+ ".";
          }       
          
          LOG.info("Tujuan Permohonan :"+tujuan);
        }            
    }


    public Resolution simpan() throws Exception {
        idPermohonan = permohonan.getIdPermohonan();
        LOG.info("###### ID Permohonan" + idPermohonan);  
        
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);       
        InfoAudit infoAudit = new InfoAudit();
  
        if (idPermohonan != null) {           

            LOG.info("###### SimpanMohonSebab #####");  
            permohonan = permohonanDAO.findById(idPermohonan);               
                                       
            if(permohonan != null){
                
                permohonan.setSebab(tujuan);
                infoAudit = new InfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                pembangunanService.simpanPermohonan(permohonan);
            }   
        }
        
        ltshow = true;
        addSimpleMessage("Maklumat Tujuan Permohonan Untuk " + idPermohonan + " Telah Disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_tujuan_permohonan.jsp");
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
 
    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Boolean getLtshow() {
        return ltshow;
    }

    public void setLtshow(Boolean ltshow) {
        this.ltshow = ltshow;
    }

    public Boolean getIdMohonShow() {
        return idMohonShow;
    }

    public void setIdMohonShow(Boolean idMohonShow) {
        this.idMohonShow = idMohonShow;
    }  
    
    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
