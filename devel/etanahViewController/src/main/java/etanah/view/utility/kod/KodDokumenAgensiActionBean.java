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
import etanah.model.KodDokumen;
import etanah.model.KodDokumenAgensi;
import etanah.model.KodGelaran;
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
@UrlBinding("/utility_kod/kodDokumenAgensi")
public class KodDokumenAgensiActionBean extends AbleActionBean {
   
  
    private String nama;
    private String kodUrusanAgensi;
    private String kodDokumen;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private String aktif;
    private boolean status;
    private String kod;
    private boolean statusTambah = false;
    private long id_rekod_dokumen_agensi;
    private List<KodDokumenAgensi>listKodDokumenAgensi;
    private KodDokumenAgensi details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen_agensi/listKodDokumenAgensi.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
         if(StringUtils.isBlank(kodUrusanAgensi)||kodUrusanAgensi.equals("0")){
             kodUrusanAgensi = null;
         }
          if(StringUtils.isBlank(kodDokumen)||kodDokumen.equals("0")){
             kodDokumen = null;
         }
     
        listKodDokumenAgensi =service.allKodDokumenAgensi();
         
        
         
         if(listKodDokumenAgensi.size()>0){
             setFlag(true);
             addSimpleMessage(listKodDokumenAgensi.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen_agensi/listKodDokumenAgensi.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         
         
         //nama = getContext().getRequest().getParameter("nama");
         kodUrusanAgensi = getContext().getRequest().getParameter("kodUrusanAgensi");
         kodDokumen = getContext().getRequest().getParameter("kodDokumen");
       
        
          if(StringUtils.isBlank(kodUrusanAgensi)||kodUrusanAgensi.equals("0")){
             kodUrusanAgensi = null;
         }
          if(StringUtils.isBlank(kodDokumen)||kodDokumen.equals("0")){
             kodDokumen = null;
         }
          
        listKodDokumenAgensi =service.findKodDokumenAgensi2(kodUrusanAgensi, kodDokumen);
        
         
         if(listKodDokumenAgensi.size()>0){
             setFlag(true);
             addSimpleMessage(listKodDokumenAgensi.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen_agensi/listKodDokumenAgensi.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_dokumen_agensi = service.countKodDokumenAgensi();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen_agensi/tambahRekodDokumenAgensi.jsp");
   }
   public Resolution viewKodDokumenAgensi()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodDokumenAgensi(kod);
       return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen_agensi/kemaskiniKodDokumenAgensi.jsp");
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
        //LOG.debug("nameTable = "+nameTable+" kodMenteri = "+details.getKodKementerian()+" nama = "+details.getNama()+"alamat1 = "+details.getAlamat1()+" alamat2 = "+details.getAlamat2()+" alamat3 = "+details.getAlamat3()+"alamat 4 = "+details.getAlamat4()+" kodnegeri = "+details.getKodNegeri().getKod()+" poskod = "+details.getPoskod()+" aktif = "+details.getAktif()+" KATG_AGENSI = "+details.getKategoriAgensi().getKod()+" emel = "+details.getEmel()+" kodgelaran = "+details.getKodGelaran().getKod());
       
            
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set id_kod_urusan_agensi= " + details.getKodUrusanAgensi().getIdKodUrusanAgensi() + ", kod_dokumen= '"+details.getKodDokumen().getKod()+"',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif()+ "' WHERE id_kod_dok_agensi = '" + Long.valueOf(kod) + "'";
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
        
          return new RedirectResolution(KodDokumenAgensiActionBean.class, "viewKodDokumenAgensi").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
   
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         kodUrusanAgensi = getContext().getRequest().getParameter("kodUrusanAgensi");
         kodDokumen = getContext().getRequest().getParameter("kodDokumen");
         aktif = getContext().getRequest().getParameter("aktif");
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_dokumen_agensi++;
         KodDokumenAgensi KDA = new KodDokumenAgensi();
         KodDokumen KD = new KodDokumen();
         KodUrusanAgensi KUA = new KodUrusanAgensi();
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KD.setKod(kodDokumen);
         KUA.setIdKodUrusanAgensi(Long.valueOf(kodUrusanAgensi));
         KDA.setKodUrusanAgensi(KUA);
         KDA.setKodDokumen(KD);
         KDA.setAktif(aktif);
         KDA.setInfoAudit(ia);
         LOG.info("Kod Dokumen Agensi "+kodUrusanAgensi+" "+kodDokumen+" "+aktif+" "+id_rekod_dokumen_agensi+" "+pguna.getIdPengguna()+" "+d);
         service.simpanKodDokumenAgensi(KDA);
         statusTambah = true;
        
          return new RedirectResolution(KodDokumenAgensiActionBean.class, "viewKodDokumenAgensi").addParameter("table_name", nameTable).addParameter("kod", KDA.getIdKodDokAgensi());

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

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public KodDokumenAgensi getDetails() {
        return details;
    }

    public void setDetails(KodDokumenAgensi details) {
        this.details = details;
    }

    public long getId_rekod_dokumen_agensi() {
        return id_rekod_dokumen_agensi;
    }

    public void setId_rekod_dokumen_agensi(long id_rekod_dokumen_agensi) {
        this.id_rekod_dokumen_agensi = id_rekod_dokumen_agensi;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getKodUrusanAgensi() {
        return kodUrusanAgensi;
    }

    public void setKodUrusanAgensi(String kodUrusanAgensi) {
        this.kodUrusanAgensi = kodUrusanAgensi;
    }

    public List<KodDokumenAgensi> getListKodDokumenAgensi() {
        return listKodDokumenAgensi;
    }

    public void setListKodDokumenAgensi(List<KodDokumenAgensi> listKodDokumenAgensi) {
        this.listKodDokumenAgensi = listKodDokumenAgensi;
    }

    
    
    
}


