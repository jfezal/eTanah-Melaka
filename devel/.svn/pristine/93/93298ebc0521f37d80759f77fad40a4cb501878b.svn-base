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
import etanah.model.KodDokumen;
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
@UrlBinding("/utility_kod/kodDokumen")
public class KodDokumenActionBean extends AbleActionBean {
   
  
    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod;
    private String kod_Dokumen;
    private String kawalCapaian;
    private String penjana;
    private boolean statusTambah = false;
    private int id_rekod_dokumen;
    private List<KodDokumen>listKodDokumen;

    private KodDokumen details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen/listKodDokumen.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          
     
        listKodDokumen =service.allKodDokumen();
         
        
         
         if(listKodDokumen.size()>0){
             setFlag(true);
             addSimpleMessage(listKodDokumen.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen/listKodDokumen.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         //nama = getContext().getRequest().getParameter("nama");
        
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          if(StringUtils.isBlank(penjana)||penjana.equals("0")){
             penjana = null;
         }
          
         listKodDokumen =service.findKodDokumen2(nama, penjana);
        
         
         if(listKodDokumen.size()>0){
             setFlag(true);
             addSimpleMessage(listKodDokumen.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen/listKodDokumen.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_dokumen = service.countKodDokumen();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen/tambahRekodDokumen.jsp");
   }
   public Resolution viewKodDokumen()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodDokumen(kod);
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_dokumen/kemaskiniKodDokumen.jsp");
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
        if(details.getNama()==null){
            details.setNama("");
        }
             if(details.getPenjana()==null){
            details.setPenjana("");
        }
            
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set nama= '" + details.getNama() + "', kwl_capai= '"+details.getKawalCapaian()+"',pjana= '"+details.getPenjana()+"',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif()+ "' WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodDokumenActionBean.class, "viewKodDokumen").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
   
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         char kwlCapai = kawalCapaian.charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_dokumen++;
         String id_rek_dokumen = String.valueOf(id_rekod_dokumen);
         InfoAudit ia = new InfoAudit();
         KodDokumen KD = new KodDokumen();
         Date d = new Date();
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KD.setKod(kod_Dokumen);
         KD.setNama(nama);
         KD.setPenjana(penjana);
         KD.setAktif(aktif1);
         KD.setKawalCapaian(kwlCapai);
         KD.setInfoAudit(ia);
         service.simpanKodDokumen(KD);
         statusTambah = true;
        
          return new RedirectResolution(KodDokumenActionBean.class, "viewKodDokumen").addParameter("table_name", nameTable).addParameter("kod", KD.getKod());

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

    public KodDokumen getDetails() {
        return details;
    }

    public void setDetails(KodDokumen details) {
        this.details = details;
    }

    public int getId_rekod_dokumen() {
        return id_rekod_dokumen;
    }

    public void setId_rekod_dokumen(int id_rekod_dokumen) {
        this.id_rekod_dokumen = id_rekod_dokumen;
    }

    public String getKawalCapaian() {
        return kawalCapaian;
    }

    public void setKawalCapaian(String kawalCapaian) {
        this.kawalCapaian = kawalCapaian;
    }

    public List<KodDokumen> getListKodDokumen() {
        return listKodDokumen;
    }

    public void setListKodDokumen(List<KodDokumen> listKodDokumen) {
        this.listKodDokumen = listKodDokumen;
    }

    public String getPenjana() {
        return penjana;
    }

    public void setPenjana(String penjana) {
        this.penjana = penjana;
    }

    public String getKod_Dokumen() {
        return kod_Dokumen;
    }

    public void setKod_Dokumen(String kod_Dokumen) {
        this.kod_Dokumen = kod_Dokumen;
    }

    
    
    
}


