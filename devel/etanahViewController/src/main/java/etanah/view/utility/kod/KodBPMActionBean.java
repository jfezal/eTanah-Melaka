/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility.kod;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodTanah;
import etanah.model.Pengguna;
import etanah.model.SenaraiKodRujukan;
import etanah.service.KodService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import java.sql.*;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodBPM")
public class KodBPMActionBean extends AbleActionBean{
    
    private List<KodBandarPekanMukim>listKodBPM;
    private String daerah;
    private String kodbpm;
    private int id_kod_bpm;
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String tanah;
    private String nameTable;
    private String bpm;
    private boolean status;
    private int kod;
    private KodBandarPekanMukim details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bpm/listKodBPM.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
         //int kodDaerah = Integer.parseInt(daerah);
         
         if(StringUtils.isBlank(kodbpm)){
             kodbpm = null;
         }
         if(StringUtils.isBlank(daerah)){
             daerah = null;
         }
         if(StringUtils.isBlank(nama)){
             nama = null;
         }
        listKodBPM =service.findKodBPM(daerah,kodbpm,nama); 
         
        
         
         if(listKodBPM.size()>0){
             setFlag(true);
             addSimpleMessage(listKodBPM.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bpm/listKodBPM.jsp");
        
    }
     
       public Resolution KodList2() throws Exception {
         
         daerah = getContext().getRequest().getParameter("daerah");
         int kodDaerah = Integer.parseInt(daerah);
         int kod;
         if(StringUtils.isBlank(kodbpm)==false){
            kod = Integer.parseInt(kodbpm);
        
         
         if(kod<10 && kodbpm.startsWith("0")==false){
             kodbpm ="0"+kodbpm;
         }
          }
         
         if(StringUtils.isBlank(kodbpm)){
             kodbpm = null;
         }
         if(StringUtils.isBlank(daerah)|| kodDaerah ==0){
             daerah = null;
         }
         if(StringUtils.isBlank(nama)){
             nama = null;
         }
        listKodBPM =service.findKodBPM2(daerah,kodbpm,nama); 
         
        
         
         if(listKodBPM.size()>0){
             setFlag(true);
             addSimpleMessage(listKodBPM.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bpm/listKodBPM.jsp");
        
    }
        public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bpm/tambahRekodBPM.jsp");
   }
   
   public Resolution viewKodBPM()throws Exception{
       
      kod = Integer.valueOf(getContext().getRequest().getParameter("kod"));
      nameTable = getContext().getRequest().getParameter("table_kod");
      details = service.viewKodBPM(kod);
       
       
       
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_bpm/kemaskiniKodBPM.jsp");
   }
   
    public Resolution updateData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_kod");
          kod =Integer.parseInt( getContext().getRequest().getParameter("kod"));
          pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date d = new java.util.Date();
         String ds = sdf.format(d); 
          details.setKod(kod);
        if(details.getKodTanah().getKod().equals("0")){
            KodTanah kt = new KodTanah();
            kt.setKod("");
            details.setKodTanah(kt);
        }
                
       
        
        String html;
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        int result = 0 ;
        String query="update " + nameTable + " set nama= '" + details.getNama() + "', kod_tanah= '"+details.getKodTanah().getKod()+"', AKTIF = '" + details.isAktif() + "', DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss') WHERE kod = '" + kod + "'";
        LOG.info("Query String ==>"+query);
        
        try { 
            
           
         
            Class.forName(JDBC_DRIVER);
         
            Session t = sessionProvider.get();
          
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");

            stmt = conn.createStatement();
            LOG.debug("New Code Name"+details.getNama());
            
            LOG.info("Query :" + query);
            result = stmt.executeUpdate(query);
            conn.commit();
        } catch (Exception e) {
            html = "Db Connection is NOT OK. Exception is " + e.getMessage();
            LOG.error(e.getMessage(), e);
             addSimpleError(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e1) {
                    LOG.error(e1.getMessage(), e1);
                    addSimpleError(e1.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e2) {
                     LOG.error(e2.getMessage(), e2);
                     addSimpleError(e2.getMessage());
                }
            }
        }
        if(result == 0){
            addSimpleError("Error, Terdapat Masalah Teknikal") ;
        }
        else {
            addSimpleMessage("Data telah berjaya dikemaskini") ;
        }
        
         return new RedirectResolution(KodBPMActionBean.class, "viewKodBPM").addParameter("table_kod", nameTable).addParameter("kod", kod);
   }
       
      public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         String daerah1 = getContext().getRequest().getParameter("daerah");
         String tanah1 = getContext().getRequest().getParameter("tanah");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
       
        KodBandarPekanMukim kodbpm = new KodBandarPekanMukim();
        KodTanah KT = new KodTanah();
        KodDaerah KD = new KodDaerah();
        KT.setKod(tanah1);
        KD.setKod(daerah1);
        Date trhmsk = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(trhmsk);
        id_kod_bpm = service.countAll()+1;
        kodbpm.setNama(nama);
        kodbpm.setKodTanah(KT);
        kodbpm.setDaerah(KD);
        kodbpm.setBandarPekanMukim(bpm);
        kodbpm.setAktif(aktif1);
        kodbpm.setInfoAudit(ia);
        service.simpanKodBPM(kodbpm);
   

          return new RedirectResolution(KodBPMActionBean.class, "viewKodBPM").addParameter("table_name", nameTable).addParameter("kod", id_kod_bpm);

   }
       
     

   


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public KodService getService() {
        return service;
    }

    public void setService(KodService service) {
        this.service = service;
    }

    public List<KodBandarPekanMukim> getListKodBPM() {
        return listKodBPM;
    }

    public void setListKodBPM(List<KodBandarPekanMukim> listKodBPM) {
        this.listKodBPM = listKodBPM;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    
    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }
    

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKodbpm() {
        return kodbpm;
    }

    public void setKodbpm(String kodbpm) {
        this.kodbpm = kodbpm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodBandarPekanMukim getDetails() {
        return details;
    }

    public void setDetails(KodBandarPekanMukim details) {
        this.details = details;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public int getId_kod_bpm() {
        return id_kod_bpm;
    }

    public void setId_kod_bpm(int id_kod_bpm) {
        this.id_kod_bpm = id_kod_bpm;
    }
    
    
    
    
}
