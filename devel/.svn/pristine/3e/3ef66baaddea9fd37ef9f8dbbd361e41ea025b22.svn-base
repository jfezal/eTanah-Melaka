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
import etanah.model.KodAliran;
import etanah.model.KodBilik;
import etanah.model.KodKategoriAkaun;
import etanah.model.KodUrusan;
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
@UrlBinding("/utility_kod/kodBilik")
public class KodBilikActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod;
    private boolean statusTambah = false;
    private int id_rekod_bilik;
    private List<KodBilik>listKodBilik;

    private KodAliran details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bilik/listKodBilik.jsp");
    }
    /**
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
     
        listKodBilik =service.allKodAliran(); 
         
        
         
         if(listKodAliran.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAliran.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_aliran/listKodAliran.jsp");
        
    }
   
       public Resolution KodList2() throws Exception {
         
         urusan = getContext().getRequest().getParameter("urusan");
        
          if(StringUtils.isBlank(urusan)||urusan.equals("0")){
             urusan = null;
         }
           if(StringUtils.isBlank(idAliran)||idAliran.equals("0")){
             idAliran = null;
         }
            if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
         listKodAliran =service.findKodAliran2(urusan, idAliran, nama);
        
         
         if(listKodAliran.size()>0){
             setFlag(true);
             addSimpleMessage(listKodAliran.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_aliran/listKodAliran.jsp");
        
    }
      
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_aliran = service.countKodAliran();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_aliran/tambahRekodAliran.jsp");
   }
    
   public Resolution viewKodAliran()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
       details = service.viewKodAliran(kod);
       
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_aliran/kemaskiniKodAliran.jsp");
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
        String query="update " + nameTable + " set kod_urusan= '" + details.getKodUrusan().getKod() + "', id_aliran= '"+details.getIdAliran()+"',nama= '"+details.getNama()+"',keterangan_ringkas= '"+details.getKeteranganRingkas()+"',AKTIF ='"+details.isAktif()+"'DIKKINI ='"+pguna.getIdPengguna()+"', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'), WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodAgensiActionBean.class, "viewKodAliran").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
  
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         urusan = getContext().getRequest().getParameter("urusan");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_aliran++;
         String id_rek_aliran = String.valueOf(id_rekod_aliran);
        KodAliran KA = new KodAliran();
        KodUrusan KU = new KodUrusan();
        KU.setKod(urusan);
        KA.setNama(nama);
        KA.setKeteranganRingkas(keteranganRingkas);
        KA.setAktif(aktif1);
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KA.setInfoAudit(ia);
         service.simpanKodAliran(KA);
        statusTambah = true;
          return new RedirectResolution(KodAgensiActionBean.class, "viewKodAliran").addParameter("table_name", nameTable).addParameter("kod", KA.getKod());

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

    public KodAliran getDetails() {
        return details;
    }

    public void setDetails(KodAliran details) {
        this.details = details;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public int getId_rekod_aliran() {
        return id_rekod_aliran;
    }

    public void setId_rekod_aliran(int id_rekod_aliran) {
        this.id_rekod_aliran = id_rekod_aliran;
    }

    public String getKeteranganRingkas() {
        return keteranganRingkas;
    }

    public void setKeteranganRingkas(String keteranganRingkas) {
        this.keteranganRingkas = keteranganRingkas;
    }

    public List<KodAliran> getListKodAliran() {
        return listKodAliran;
    }

    public void setListKodAliran(List<KodAliran> listKodAliran) {
        this.listKodAliran = listKodAliran;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }
    
    **/

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    
}


