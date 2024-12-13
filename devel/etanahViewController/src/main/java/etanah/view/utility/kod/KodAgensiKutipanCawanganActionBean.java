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
@UrlBinding("/utility_kod/kodAgensiKutipCawangan")
public class KodAgensiKutipanCawanganActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String kodKementerian;
    private String nameTable;
    private boolean status;
    private String lokasi;
    private boolean statusTambah = false;
    private String kod;
    private int id_rekod_agensi_kutip_caw;
    private List<KodAgensiKutipanCawangan>listKodAgensiKutipCaw;
    private String agensiKutip;

    private KodAgensiKutipanCawangan details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi_kutipan_cawangan/listKodAgensiKutipCaw.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
         if(StringUtils.isBlank(kodKementerian)||kodKementerian.equals("0")){
             kodKementerian = "0";
         }
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          
           int kodKementerian1 = Integer.valueOf(kodKementerian);
     
        listKodAgensiKutipCaw =service.allKodAgensiKutipanCawangan(); 
         
        
         
         if(listKodAgensiKutipCaw.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAgensiKutipCaw.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi_kutipan_cawangan/listKodAgensiKutipCaw.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         
         agensiKutip = getContext().getRequest().getParameter("agensiKutip");
         lokasi = getContext().getRequest().getParameter("lokasi");
         nama = getContext().getRequest().getParameter("nama");
        
          if(StringUtils.isBlank(agensiKutip)||agensiKutip.equals("0")){
             agensiKutip = null;
         }
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
         listKodAgensiKutipCaw =service.findKodAgensiKutipanCawangan2(agensiKutip, nama, lokasi);
        
         
         if(listKodAgensiKutipCaw.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAgensiKutipCaw.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi_kutipan_cawangan/listKodAgensiKutipCaw.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_agensi_kutip_caw = service.countKodAgensiKutipanCawangan();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi_kutipan_cawangan/tambahRekodAgensiKutipCaw.jsp");
   }
   public Resolution viewKodAgensiKutipanCawangan()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodAgensiKutipCaw(kod);
       if(statusTambah==true){
       addSimpleMessage("Tambah rekod telah berjaya");
       statusTambah = false;
        }
         else{
             addSimpleError("Tambah rekod gagal");
         }
       
       
       
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi_kutipan_cawangan/kemaskiniKodAgensiKutipCaw.jsp");
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
         if(details.getLokasi()==null){
            details.setLokasi("");
        }
          
            
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set kod_agensi_kutipan= " + details.getAgensiKutipan().getKod() + ", NAMA= '"+details.getNama()+"',LOKASI ='"+details.getLokasi()+"' WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodAgensiKutipanCawanganActionBean.class, "viewKodAgensiKutipanCawangan").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
  
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         agensiKutip = getContext().getRequest().getParameter("agensiKutip");
         //char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_agensi_kutip_caw++;
         String id_rek_agensi = String.valueOf(id_rekod_agensi_kutip_caw);
         KodAgensiKutipanCawangan KAKC = new KodAgensiKutipanCawangan();
         KodAgensiKutipan KAK = new KodAgensiKutipan();
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         KAK.setKod(agensiKutip);
         KAKC.setAgensiKutipan(KAK);
         KAKC.setKod(id_rek_agensi);
         KAKC.setNama(nama);
         KAKC.setLokasi(lokasi);
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         service.simpanKodAgensiKutipanCawangan(KAKC);
         statusTambah = true;

          return new RedirectResolution(KodAgensiKutipanCawanganActionBean.class, "viewKodAgensiKutipanCawangan").addParameter("table_name", nameTable).addParameter("kod", KAKC.getKod());

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

    public String getAgensiKutip() {
        return agensiKutip;
    }

    public void setAgensiKutip(String agensiKutip) {
        this.agensiKutip = agensiKutip;
    }

    public KodAgensiKutipanCawangan getDetails() {
        return details;
    }

    public void setDetails(KodAgensiKutipanCawangan details) {
        this.details = details;
    }
    
    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public List<KodAgensiKutipanCawangan> getListKodAgensiKutipCaw() {
        return listKodAgensiKutipCaw;
    }

    public void setListKodAgensiKutipCaw(List<KodAgensiKutipanCawangan> listKodAgensiKutipCaw) {
        this.listKodAgensiKutipCaw = listKodAgensiKutipCaw;
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

    public int getId_rekod_agensi_kutip_caw() {
        return id_rekod_agensi_kutip_caw;
    }

    public void setId_rekod_agensi_kutip_caw(int id_rekod_agensi_kutip_caw) {
        this.id_rekod_agensi_kutip_caw = id_rekod_agensi_kutip_caw;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }
    
    
    
}


