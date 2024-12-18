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
@UrlBinding("/utility_kod/kodCawanganJabatan")
public class KodCawanganJabatanActionBean extends AbleActionBean {
   
  
    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String tel1;
    private String tel2;
    private String kodCawangan;
    private String kodJabatan;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod;
    private String negeri;
    private boolean statusTambah = false;
    private int id_rekod_cawangan_jabatan;
    private List<KodCawanganJabatan>listKodCawJabatan;

    private KodCawanganJabatan details;
    private boolean flag=false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_urusan_agensi/listKodAgensi.jsp");
    }
    
     public Resolution KodList() throws Exception {
         nameTable = getContext().getRequest().getParameter("table_name");
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
           if(StringUtils.isBlank(negeri)||negeri.equals("0")){
             negeri = null;
         }
     
        listKodCawJabatan =service.allKodCawanganJabatan();
         
        
         
         if(listKodCawJabatan.size()>0){
             setFlag(true);
             addSimpleMessage(listKodCawJabatan.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_cawangan_jabatan/listKodCawJabatan.jsp");
        
    }
       public Resolution KodList2() throws Exception {
         kodCawangan = getContext().getRequest().getParameter("kodCawangan");
         negeri = getContext().getRequest().getParameter("negeri");
         kodJabatan = getContext().getRequest().getParameter("kodJabatan");
        
          if(StringUtils.isBlank(kodCawangan)||kodCawangan.equals("0")){
             kodCawangan = null;
         }
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          if(StringUtils.isBlank(negeri)||negeri.equals("0")){
             negeri = null;
         }
           if(StringUtils.isBlank(kodJabatan)||kodJabatan.equals("0")){
             kodJabatan = null;
         }
         
         listKodCawJabatan =service.findKodCawanganJabatan2(kodCawangan, nama, negeri, kodJabatan);
        
         
         if(listKodCawJabatan.size()>0){
             setFlag(true);
             addSimpleMessage(listKodCawJabatan.size()+" kod ditemui.");
             
         }
         else{
             addSimpleError("Tiada kod ditemui.");
         }
        
        
        return new JSP("/utiliti/utiliti_kod_ruj/kod_cawangan_jabatan/listKodCawJabatan.jsp");
        
    }
   public Resolution viewAddForm() throws Exception{
       
        nameTable = getContext().getRequest().getParameter("table_name");
       id_rekod_cawangan_jabatan = service.countKodCawanganJabatan();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_cawangan_jabatan/tambahRekodCawJabatan.jsp");
   }
   public Resolution viewKodCawJabatan()throws Exception{
       nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");
        
       details = service.viewKodCawanganJabatan(kod);
       
       return new JSP("/utiliti/utiliti_kod_ruj/kod_cawangan_jabatan/kemaskiniKodCawJabatan.jsp");
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
        if(details.getAlamat1()==null){
            details.setAlamat1("");
        }
         if(details.getAlamat2()==null){
            details.setAlamat2("");
        }
          if(details.getAlamat3()==null){
            details.setAlamat3("");
        }
           if(details.getAlamat4()==null){
            details.setAlamat4("");
        }
            if(details.getPoskod()==null){
            details.setPoskod("");
        }
            if(details.getNoTelefon1()==null){
            details.setNoTelefon1("");
        }
             if(details.getNoTelefon2()==null){
            details.setNoTelefon2("");
        }
        LOG.debug("Kod Cawangan "+ details.getCawangan().getKod());
        int result = 0 ;
           LOG.info("Entering 2nd phase updateData() function.......");
        String query="update " + nameTable + " set KOD_CAW= '" + details.getCawangan().getKod() + "', KOD_JABATAN= '"+details.getJabatan().getKod()+"',"
                + " NAMA = '"+details.getNama()+"', ALAMAT1 = '"+details.getAlamat1()+"', ALAMAT2 = '"+details.getAlamat2()+"', ALAMAT3 = '"+details.getAlamat3()+"',"
                + " ALAMAT4 = '"+details.getAlamat4()+"', KOD_NEGERI = '"+details.getNegeri().getKod()+"', POSKOD = '"+details.getPoskod()+"', "
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('"+ds+"','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif() + "', NO_TEL1 = '" + details.getNoTelefon1() + "',"
                + " NO_TEL2 = '" + details.getNoTelefon2() + "'"
                + " WHERE KOD = '" + kod + "'";
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
        
          return new RedirectResolution(KodCawanganJabatanActionBean.class, "viewKodCawJabatan").addParameter("table_name", nameTable).addParameter("kod", kod);

   }
   
  
    public Resolution simpanData() throws Exception {
         LOG.info("Entering updateData() function.......");
         nameTable = getContext().getRequest().getParameter("table_name");
         negeri = getContext().getRequest().getParameter("negeri");
         kodCawangan = getContext().getRequest().getParameter("kodCawangan");
         kodJabatan = getContext().getRequest().getParameter("kodJabatan");
         char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         id_rekod_cawangan_jabatan++;
         String id_rek_caw_jab = String.valueOf(id_rekod_cawangan_jabatan);
       
           if(StringUtils.isBlank(nama)||nama.equals("0")){
             nama = null;
         }
          if(StringUtils.isBlank(alamat1)||alamat1.equals("")){
             alamat1 = "";
         }
           if(StringUtils.isBlank(alamat2)||alamat2.equals("")){
             alamat2 = "";
         }
            if(StringUtils.isBlank(alamat3)||alamat3.equals("")){
             alamat3 = "";
         }
             if(StringUtils.isBlank(alamat4)||alamat4.equals("")){
             alamat4 = "";
         }
         KodNegeri KN = new KodNegeri();
         KodCawangan KC = new KodCawangan();
         KodJabatan KJ = new KodJabatan();
         KodCawanganJabatan KCJ = new KodCawanganJabatan();
         InfoAudit ia = new InfoAudit();
         Date d = new Date();
         KN.setKod(negeri);
         KC.setKod(kodCawangan);
         KJ.setKod(kodJabatan);
         KCJ.setKod(id_rek_caw_jab);
         KCJ.setCawangan(KC);
         KCJ.setJabatan(KJ);
         KCJ.setNegeri(KN);
         KCJ.setNama(nama);
         KCJ.setAlamat1(alamat1);
         KCJ.setAlamat2(alamat2);
         KCJ.setAlamat3(alamat3);
         KCJ.setAlamat4(alamat4);
         KCJ.setPoskod(poskod);
         KCJ.setNoTelefon1(tel1);
         KCJ.setNoTelefon2(tel2);
         ia.setDimasukOleh(pguna);
         ia.setTarikhMasuk(d);
         KCJ.setAktif(aktif1);
         KCJ.setInfoAudit(ia);
         service.simpanKodCawanganJabatan(KCJ);
        
         statusTambah = true;
        
          return new RedirectResolution(KodCawanganJabatanActionBean.class, "viewKodCawJabatan").addParameter("table_name", nameTable).addParameter("kod", KCJ.getKod());

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
    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
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

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }
    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    public KodCawanganJabatan getDetails() {
        return details;
    }

    public void setDetails(KodCawanganJabatan details) {
        this.details = details;
    }

    public int getId_rekod_cawangan_jabatan() {
        return id_rekod_cawangan_jabatan;
    }

    public void setId_rekod_cawangan_jabatan(int id_rekod_cawangan_jabatan) {
        this.id_rekod_cawangan_jabatan = id_rekod_cawangan_jabatan;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(String kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public List<KodCawanganJabatan> getListKodCawJabatan() {
        return listKodCawJabatan;
    }

    public void setListKodCawJabatan(List<KodCawanganJabatan> listKodCawJabatan) {
        this.listKodCawJabatan = listKodCawJabatan;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    
    
    
}


