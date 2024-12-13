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
import etanah.model.KodAgensiKutipan;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.KodAkaun;
import etanah.model.KodKategoriAkaun;
import etanah.model.Pengguna;
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
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;
/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodAkaun")
public class KodAkaunActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private String kod_Akaun;
    private String katgAkaun;
    private boolean status;
    private String kod;
    private boolean statusTambah = false;
    private int id_rekod_akaun;
    private List<KodAkaun>listKodAkaun;

    private KodAkaun details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_akaun/listKodAkaun.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
        
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          
        
     
        listKodAkaun =service.allKodAkaun(); 
         
        
         
         if(listKodAkaun.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAkaun.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_akaun/listKodAkaun.jsp");
        
    }
   
       public Resolution KodList2() throws Exception {
         
         katgAkaun = getContext().getRequest().getParameter("katgAkaun");
        
          if(StringUtils.isBlank(katgAkaun)||katgAkaun.equals("0")){
             katgAkaun = null;
         }
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
         listKodAkaun =service.findKodAkaun2(katgAkaun, nama);
        
         
         if(listKodAkaun.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAkaun.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_akaun/listKodAkaun.jsp");
        
    }
      
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_akaun = service.countKodAgensiKutipanCawangan();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_akaun/tambahRekodAkaun.jsp");
   }
    
   public Resolution viewKodAkaun()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
      
       details = service.viewKodAkaun(kod);
       return new JSP("/utiliti/utiliti_kod_ruj/kod_akaun/kemaskiniKodAkaun.jsp");
   }
    
  
   public Resolution updateData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         kod = getContext().getRequest().getParameter("kod");
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date d = new java.util.Date();
         String ds = sdf.format(d);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String html;
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        if(details.getNama()==null){
            details.setNama("");
        }
            
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set kod_katg_akaun= '" + details.getKategoriAkaun().getKod() + "', NAMA= '"+details.getNama()+"',AKTIF ='"+details.isAktif()+"',DIKKINI ='"+pguna.getIdPengguna()+"', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss') WHERE KOD = '" + kod + "'";
        LOG.info("Query executed ==>"+query);
        
        
        try { 
            
              LOG.info("Entering 3rd phase updateData() function.......");
         
            Class.forName(JDBC_DRIVER);
         
            Session t = sessionProvider.get();
             LOG.info("Entering  4th phase updateData() function.......");
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            
            stmt = conn.createStatement();
               LOG.info("Entering final updateData() function.......");
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
            addSimpleError("Error, Data gagal dikemaskini") ;
        }
        else {
            addSimpleMessage("Data telah berjaya dikemaskini") ;
           
        }
        
          return new RedirectResolution(KodAkaunActionBean.class, "viewKodAkaun").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
  
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         katgAkaun = getContext().getRequest().getParameter("katgAkaun");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_akaun++;
         String id_rek_agensi = String.valueOf(id_rekod_akaun);
        KodAkaun KA = new KodAkaun();
        KodKategoriAkaun KKA = new KodKategoriAkaun();
         KKA.setKod(katgAkaun);
        KA.setKod(kod_Akaun);
        KA.setKategoriAkaun(KKA);
        KA.setNama(nama);
        KA.setAktif(aktif1);
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KA.setInfoAudit(ia);
         service.simpanKodAkaun(KA);
        statusTambah = true;
          return new RedirectResolution(KodAkaunActionBean.class, "viewKodAkaun").addParameter("table_name", nameTable).addParameter("kod", KA.getKod());

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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }
    public String getTable_act_name() {
        return table_act_name;
    }

    public void setTable_act_name(String table_act_name) {
        this.table_act_name = table_act_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public KodAkaun getDetails() {
        return details;
    }

    public void setDetails(KodAkaun details) {
        this.details = details;
    }

    public int getId_rekod_akaun() {
        return id_rekod_akaun;
    }

    public void setId_rekod_akaun(int id_rekod_akaun) {
        this.id_rekod_akaun = id_rekod_akaun;
    }

    public String getKatgAkaun() {
        return katgAkaun;
    }

    public void setKatgAkaun(String katgAkaun) {
        this.katgAkaun = katgAkaun;
    }

    public List<KodAkaun> getListKodAkaun() {
        return listKodAkaun;
    }

    public void setListKodAkaun(List<KodAkaun> listKodAkaun) {
        this.listKodAkaun = listKodAkaun;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    public String getKod_Akaun() {
        return kod_Akaun;
    }

    public void setKod_Akaun(String kod_Akaun) {
        this.kod_Akaun = kod_Akaun;
    }

    
}


