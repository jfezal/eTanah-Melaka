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
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodGelaran;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.service.KodService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
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
@UrlBinding("/utility_kod/kodHakmilik")
public class KodHakmilikActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String kodDokumen;
    private String nameTable;
    private boolean status;
    private String kod;
    private String kod_Hakmilik;
    private String milikDaerah;
    private boolean statusTambah = false;
    private int id_rekod_hakmilik;
    private List<KodHakmilik>listKodHakmilik;
    private KodHakmilik details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_hakmilik/listKodHakmilik.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
            if(StringUtils.isBlank(kodDokumen)||kodDokumen.equals("0")){
             kodDokumen = null;
         }
     
        listKodHakmilik =service.allKodHakmilik();
         
        
         
         if(listKodHakmilik.size()>0){
             setFlag(true);
             addSimpleMessage(listKodHakmilik.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_hakmilik/listKodHakmilik.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         //nama = getContext().getRequest().getParameter("nama");
         kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        
         
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          if(StringUtils.isBlank(kodDokumen)||kodDokumen.equals("0")){
             kodDokumen = null;
         }
           
           
        listKodHakmilik =service.findKodHakmilik2(nama, kodDokumen);
        
         
         if(listKodHakmilik.size()>0){
             setFlag(true);
             addSimpleMessage(listKodHakmilik.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_hakmilik/listKodHakmilik.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_hakmilik = service.countKodHakmilik();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_hakmilik/tambahRekodHakmilik.jsp");
   }
   public Resolution viewKodHakmilik()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodHakmilik(kod);
       
       
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_hakmilik/kemaskiniKodHakmilik.jsp");
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
        //LOG.debug("nameTable = "+nameTable+" nama = "+details.getNama()+"alamat1 = "+details.getAlamat1()+" alamat2 = "+details.getAlamat2()+" alamat3 = "+details.getAlamat3()+"alamat 4 = "+details.getAlamat4()+" kodnegeri = "+details.getKodNegeri().getKod()+" poskod = "+details.getPoskod()+" aktif = "+details.getAktif()+" KATG_AGENSI = "+details.getKategoriAgensi().getKod()+" emel = "+details.getEmel()+" kodgelaran = "+details.getKodGelaran().getKod());
        if(details.getNama()==null){
            details.setNama("");
        }
        
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set  NAMA= '"+details.getNama()+"',kod_dokumen= '"+details.getKodDokumen().getKod()+"',milik_daerah= '"+details.getMilikDaerah()+"',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif() + "' WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodHakmilikActionBean.class, "viewKodHakmilik").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
   
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         kodDokumen = getContext().getRequest().getParameter("kodDokumen");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         char milikDaerah1 = getContext().getRequest().getParameter("milikDaerah").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_hakmilik++;
         String id_rek_hakmilik = String.valueOf(id_rekod_hakmilik);
         KodDokumen KD = new KodDokumen();
         KodHakmilik KH = new KodHakmilik();
         KD.setKod(kodDokumen);
         KH.setKod(kod_Hakmilik);
         KH.setNama(nama);
         KH.setKodDokumen(KD);
         KH.setMilikDaerah(milikDaerah1);
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         KH.setAktif(aktif1);
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KH.setInfoAudit(ia);
        
         service.simpanKodHakmilik(KH);
         statusTambah = true;
        
          return new RedirectResolution(KodHakmilikActionBean.class, "viewKodHakmilik").addParameter("table_name", nameTable).addParameter("kod", KH.getKod());

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

    public KodHakmilik getDetails() {
        return details;
    }

    public void setDetails(KodHakmilik details) {
        this.details = details;
    }

    public int getId_rekod_hakmilik() {
        return id_rekod_hakmilik;
    }

    public void setId_rekod_hakmilik(int id_rekod_hakmilik) {
        this.id_rekod_hakmilik = id_rekod_hakmilik;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public List<KodHakmilik> getListKodHakmilik() {
        return listKodHakmilik;
    }

    public void setListKodHakmilik(List<KodHakmilik> listKodHakmilik) {
        this.listKodHakmilik = listKodHakmilik;
    }

    public String getMilikDaerah() {
        return milikDaerah;
    }

    public void setMilikDaerah(String milikDaerah) {
        this.milikDaerah = milikDaerah;
    }

    public String getKod_Hakmilik() {
        return kod_Hakmilik;
    }

    public void setKod_Hakmilik(String kod_Hakmilik) {
        this.kod_Hakmilik = kod_Hakmilik;
    }

    
    
    
}


