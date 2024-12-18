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
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodTanah;
import etanah.model.KodUrusan;
import etanah.model.KodUrusanAgensi;
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
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodUrusanAgensi")
public class KodUrusAgensiActionBean extends AbleActionBean{
    
    private List<KodUrusanAgensi>listKodUrusAgensi;
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String tanah;
    private String nameTable;
    private String urusan;
    private boolean status;
    private String agensi;
    private long kod;
    private boolean statusTambah = false;
      HttpSession s = null;
    private KodUrusanAgensi details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/listKodUrusanAgensi.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
        
 if(StringUtils.isBlank(urusan)){
             urusan = null;
         }
          if(StringUtils.isBlank(agensi)){
             agensi = null;
         }
     
        listKodUrusAgensi =service.allKodUrusAgensi(); 
         
        
         
         if(listKodUrusAgensi.size()>0){
             setFlag(true);
             addSimpleMessage(listKodUrusAgensi.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/listKodUrusanAgensi.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         
         urusan = getContext().getRequest().getParameter("urusan");
         agensi = getContext().getRequest().getParameter("agensi");
         
         if(StringUtils.isBlank(urusan)||urusan.equals("0")){
             urusan = null;
         }
          if(StringUtils.isBlank(agensi)||agensi.equals("0")){
             agensi = null;
         }
         listKodUrusAgensi =service.findKodUrusanAgensi2(urusan,agensi); 
        
         
         if(listKodUrusAgensi.size()>0){
             setFlag(true);
             addSimpleMessage(listKodUrusAgensi.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/listKodUrusanAgensi.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       
        return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/tambahRekod.jsp");
   }
   public Resolution viewKodUrusAgensi()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = Long.valueOf(getContext().getRequest().getParameter("kod"));
        
       details = service.viewKodUrusAgensi(kod);
        if(statusTambah==true){
       addSimpleMessage("Tambah rekod telah berjaya");
       statusTambah = false;
        }
         else{
             addSimpleError("Tambah rekod gagal");
         }
       
       
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/kemaskiniKodUrusanAgensi.jsp");
   }
    
   
   public Resolution updateData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         kod = Long.valueOf(getContext().getRequest().getParameter("kod"));
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date d = new java.util.Date();
         String ds = sdf.format(d); 
        // Date trhkkni = sdf.parse(ds);
         
         LOG.debug("Date Tarikh kkni ==>"+d.toString());
          LOG.debug("Date Tarikh kkni ==>"+ds);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String html;
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set KOD_URUSAN= '" + details.getKodUrusan().getKod() + "', KOD_AGENSI= '"+details.getKodAgensi().getKod()+"', AKTIF = '" + details.getAktif() + "', DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss') WHERE ID_KOD_URUSAN_AGENSI = '" + kod + "'";
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
            addSimpleError("Error, Terdapat Masalah Teknikal") ;
        }
        else {
            addSimpleMessage("Data telah berjaya dikemaskini") ;
           
        }
        
          return new RedirectResolution(KodUrusAgensiActionBean.class, "viewKodUrusAgensi").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         String urusan1 = getContext().getRequest().getParameter("urusan");
         String agensi1 = getContext().getRequest().getParameter("agensi");
         String aktif1 = getContext().getRequest().getParameter("aktif");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
        KodUrusanAgensi KUA = new KodUrusanAgensi();
        Date trhmsk = new Date();
        KodUrusan ku = new KodUrusan();
        KodAgensi ka = new KodAgensi();
        InfoAudit ia = new InfoAudit();
        ku.setKod(urusan1);
        ka.setKod(agensi1);
        KUA.setKodUrusan(ku);
        KUA.setKodAgensi(ka);
        KUA.setAktif(aktif1);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(trhmsk);
        KUA.setInfoAudit(ia);
        service.simpanKodUrusanAgensi(KUA);
        
          return new RedirectResolution(KodUrusAgensiActionBean.class, "viewKodUrusAgensi").addParameter("table_name", nameTable).addParameter("kod", KUA.getIdKodUrusanAgensi());

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

    public List<KodUrusanAgensi> getListKodUrusAgensi() {
        return listKodUrusAgensi;
    }

    public void setListKodUrusAgensi(List<KodUrusanAgensi> listKodUrusAgensi) {
        this.listKodUrusAgensi = listKodUrusAgensi;
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

    public KodUrusanAgensi getDetails() {
        return details;
    }

    public void setDetails(KodUrusanAgensi details) {
        this.details = details;
    }

    public long getKod() {
        return kod;
    }

    public void setKod(long kod) {
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

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public HttpSession getS() {
        return s;
    }

    public void setS(HttpSession s) {
        this.s = s;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
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
    
    
    
}
