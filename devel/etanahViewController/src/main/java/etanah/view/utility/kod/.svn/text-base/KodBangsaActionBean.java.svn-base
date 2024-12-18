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
import etanah.model.KodBangsa;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDaerah;
import etanah.model.KodGelaran;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodNegeri;
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
@UrlBinding("/utility_kod/kodBangsa")
public class KodBangsaActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod_Bangsa;
    private String kod;
    private String perihal;
    private String utkIndividu;
    private String bangsa;
    private boolean statusTambah = false;
    private int id_rekod_bangsa;
    private List<KodBangsa>listKodBangsa;

    private KodBangsa details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bangsa/listKodBangsa.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
     
        listKodBangsa =service.allKodBangsa();
         
        
         
         if(listKodBangsa.size()>0){
             setFlag(true);
             addSimpleMessage(listKodBangsa.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bangsa/listKodBangsa.jsp");
        
    }
       public Resolution KodList2() throws Exception {
        
         //nama = getContext().getRequest().getParameter("nama");
        
         
         
         listKodBangsa =service.findKodBangsa2(nama);
        
         
         if(listKodBangsa.size()>0){
             setFlag(true);
             addSimpleMessage(listKodBangsa.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bangsa/listKodBangsa.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       //id_rekod_bangsa = service.countKodCawanganJabatan();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_bangsa/tambahRekodBangsa.jsp");
   }
   public Resolution viewKodBangsa()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodBangsa(kod);
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_bangsa/kemaskiniKodBangsa.jsp");
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
        //LOG.debug("nameTable = "+nameTable+" kod_caw = "+details.getCawangan().getKod()+" kod_jabatan = "+details.getJabatan().getKod()+"alamat1 = "+details.getAlamat1()+" alamat2 = "+details.getAlamat2()+" alamat3 = "+details.getAlamat3()+"alamat 4 = "+details.getAlamat4()+" kodnegeri = "+details.getNegeri().getKod()+" poskod = "+details.getPoskod()+" aktif = "+details.getAktif()+" no_tel1 = "+details.getNoTelefon1()+" no_tel2 = "+details.getNoTelefon2()+" kodgelaran = "+details.getKodGelaran().getKod());
        if(details.getNama()==null){
            details.setNama("");
        }
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set nama= '" + details.getNama() + "', utk_idvd= '"+details.getUntukIndividu()+"',bangsa= '"+details.getBangsa()+"',perihal= '"+details.getPerihal()+"',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif()+"' WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodBangsaActionBean.class, "viewKodBangsa").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
   
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         char utkIndividu1 = getContext().getRequest().getParameter("utkIndividu").charAt(0);
         char bangsa1 =getContext().getRequest().getParameter("bangsa").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_bangsa++;
         String id_rek_bangsa = String.valueOf(id_rekod_bangsa);
         KodBangsa KB = new KodBangsa();
         InfoAudit ia = new InfoAudit();
         KB.setKod(kod_Bangsa);
         KB.setNama(nama);
         KB.setUntukIndividu(utkIndividu1);
         KB.setBangsa(bangsa1);
         KB.setPerihal(perihal);
         Date d = new Date();
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KB.setAktif(aktif1);
         KB.setInfoAudit(ia);
         service.simpanKodBangsa(KB);
        
         statusTambah = true;
        
          return new RedirectResolution(KodBangsaActionBean.class, "viewKodBangsa").addParameter("table_name", nameTable).addParameter("kod", KB.getKod());

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
    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    public String getBangsa() {
        return bangsa;
    }

    public void setBangsa(String bangsa) {
        this.bangsa = bangsa;
    }

    public KodBangsa getDetails() {
        return details;
    }

    public void setDetails(KodBangsa details) {
        this.details = details;
    }

    public int getId_rekod_bangsa() {
        return id_rekod_bangsa;
    }

    public void setId_rekod_bangsa(int id_rekod_bangsa) {
        this.id_rekod_bangsa = id_rekod_bangsa;
    }

    public List<KodBangsa> getListKodBangsa() {
        return listKodBangsa;
    }

    public void setListKodBangsa(List<KodBangsa> listKodBangsa) {
        this.listKodBangsa = listKodBangsa;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getUtkIndividu() {
        return utkIndividu;
    }

    public void setUtkIndividu(String utkIndividu) {
        this.utkIndividu = utkIndividu;
    }

    public String getKod_Bangsa() {
        return kod_Bangsa;
    }

    public void setKod_Bangsa(String kod_Bangsa) {
        this.kod_Bangsa = kod_Bangsa;
    }
    
    
    
    
}


