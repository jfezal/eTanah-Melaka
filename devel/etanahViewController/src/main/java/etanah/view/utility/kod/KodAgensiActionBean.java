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
import etanah.model.KodGelaran;
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
@UrlBinding("/utility_kod/kodAgensi")
public class KodAgensiActionBean extends AbleActionBean {

    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String email;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String kodKementerian;
    private String nameTable;
    private boolean status;
    private String kod;
    private String agensi;
    private String negeri;
    private String katgAgensi;
    private boolean statusTambah = false;
    private String gelaran;
    private String daerah;
    private String noTelefon1;
    private String noTelefon2;
    private int id_rekod_agensi;
    private List<KodAgensi> listKodAgensi;
    private KodAgensi details;
    private boolean flag = false;
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
        if (StringUtils.isBlank(kodKementerian) || kodKementerian.equals("0")) {
            kodKementerian = "0";
        }
        if (StringUtils.isBlank(nama) || nama.equals("0")) {
            nama = null;
        }
        if (StringUtils.isBlank(katgAgensi) || katgAgensi.equals("0")) {
            katgAgensi = null;
        }
        if (StringUtils.isBlank(negeri) || negeri.equals("0")) {
            negeri = null;
        }
        if (StringUtils.isBlank(gelaran) || gelaran.equals("0")) {
            gelaran = null;
        }
        if (StringUtils.isBlank(daerah) || daerah.equals("0")) {
            daerah = null;
        }
        int kodKementerian1 = Integer.valueOf(kodKementerian);

        listKodAgensi = service.allKodAgensi();



        if (listKodAgensi.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodAgensi.size() + " Agensi ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi/listKodAgensi.jsp");

    }

    public Resolution KodList2() throws Exception {

        kodKementerian = getContext().getRequest().getParameter("kementerian");
        //nama = getContext().getRequest().getParameter("nama");
        katgAgensi = getContext().getRequest().getParameter("katgAgensi");
        negeri = getContext().getRequest().getParameter("negeri");
        gelaran = getContext().getRequest().getParameter("gelaran");
        daerah = getContext().getRequest().getParameter("daerah");

        if (StringUtils.isBlank(kodKementerian) || kodKementerian.equals("0")) {
            kodKementerian = "0";
        }
        if (StringUtils.isBlank(nama) || nama.equals("0")) {
            nama = null;
        }
        if (StringUtils.isBlank(katgAgensi) || katgAgensi.equals("0")) {
            katgAgensi = null;
        }
        if (StringUtils.isBlank(negeri) || negeri.equals("0")) {
            negeri = null;
        }
        if (StringUtils.isBlank(gelaran) || gelaran.equals("0")) {
            gelaran = null;
        }
        if (StringUtils.isBlank(daerah) || daerah.equals("0")) {
            daerah = null;
        }
        int kodKementerian1 = Integer.valueOf(kodKementerian);
        listKodAgensi = service.findKodAgensi2(kodKementerian1, nama, katgAgensi, negeri, gelaran, daerah);


        if (listKodAgensi.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodAgensi.size() + " Agensi ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi/listKodAgensi.jsp");

    }

    public Resolution viewAddForm() throws Exception {

        nameTable = getContext().getRequest().getParameter("table_name");
        id_rekod_agensi = service.countKodAgensi();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi/tambahRekodAgensi.jsp");
    }

    public Resolution viewKodAgensi() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");

        details = service.viewKodAgensi(kod);
        if (statusTambah == true) {
            addSimpleMessage("Tambah rekod telah berjaya");
            statusTambah = false;
        }



        return new JSP("/utiliti/utiliti_kod_ruj/kod_agensi/kemaskiniKodAgensi.jsp");
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
//        LOG.debug("nameTable = "+nameTable+" kodMenteri = "+details.getKodKementerian()+" nama = "+details.getNama()+"alamat1 = "+details.getAlamat1()+" alamat2 = "+details.getAlamat2()+" alamat3 = "+details.getAlamat3()+"alamat 4 = "+details.getAlamat4()+" kodnegeri = "+details.getKodNegeri().getKod()+" poskod = "+details.getPoskod()+" aktif = "+details.getAktif()+" KATG_AGENSI = "+details.getKategoriAgensi().getKod()+" emel = "+details.getEmel()+" kodgelaran = "+details.getKodGelaran().getKod());
        if (details.getAlamat1() == null) {
            details.setAlamat1("");
        }
        if (details.getAlamat2() == null) {
            details.setAlamat2("");
        }
        if (details.getAlamat3() == null) {
            details.setAlamat3("");
        }
        if (details.getAlamat4() == null) {
            details.setAlamat4("");
        }
        
          if (details.getNoTelefon1()== null) {
            details.setNoTelefon1("");
        }
          
          if (details.getNoTelefon2()== null) {
            details.setNoTelefon2("");
        }
        
        if (details.getPoskod() == null) {
            details.setPoskod("");
        }
        if (details.getEmel() == null) {
            details.setEmel("");
        }

//        if (details.getKodGelaran() == null) {
//            details.setKodGelaran(null);
//        }
//        if (details.getKategoriAgensi() == null) {
//            details.setKategoriAgensi(null);
//        }
        if (details.getKodKementerian() == 0) {
            details.setKodKementerian(0);
        }

        int result = 0;
        LOG.info("Entering 2nd phase updateData() function.......");
        String query = "update KOD_AGENSI  set ";
        if (details.getKodKementerian() != 0) {
            query += " KOD_KEMENTERIAN= " + details.getKodKementerian();
        }
        if (details.getNama() != null) {
            query += " , NAMA= '" + details.getNama() + "'";
        }
        query += ",ALAMAT1 = '" + details.getAlamat1() + "',ALAMAT2 = '" + details.getAlamat2() + "',ALAMAT3 = '" + details.getAlamat3() + "',"
                + "ALAMAT4 = '" + details.getAlamat4() + "',KOD_NEGERI = '" + details.getKodNegeri().getKod() + "',POSKOD = '" + details.getPoskod() + "',NO_TEL1 = '"+ details.getNoTelefon1() + "',NO_TEL2 = '"+ details.getNoTelefon2()+ "',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('" + ds + "','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + details.getAktif()+ "'" ;
        
//        if(details.getKategoriAgensi() != null){
//           query += "',KATG_AGENSI = '" + details.getKategoriAgensi().getKod() + "'" ;
//        }
        query += ",EMEL = '" + details.getEmel() + "'" ;
//        if(details.getKodGelaran() != null){
//            query += ",KOD_GELARAN = '" + details.getKodGelaran().getKod() + "'" ;
//        }
//        if(details.getKodDaerah() != null){
//            query += ", KOD_DAERAH = '" + details.getKodDaerah().getKod() + "'";
//        }
        query += "WHERE KOD = '" + kod + "'";
        LOG.info("Query executed ==>" + query);


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
        if (result == 0) {
            addSimpleError("Error, Data gagal dikemaskini");
        } else {
            addSimpleMessage("Data telah berjaya dikemaskini");

        }

        return new RedirectResolution(KodAgensiActionBean.class, "viewKodAgensi").addParameter("table_name", nameTable).addParameter("kod", kod);

    }

    public Resolution simpanData() throws Exception {
        LOG.info("Entering updateData() function.......");
        nameTable = getContext().getRequest().getParameter("table_name");
        kodKementerian = getContext().getRequest().getParameter("kementerian");
        negeri = getContext().getRequest().getParameter("negeri");
//        katgAgensi = getContext().getRequest().getParameter("kategoriAgensi");
//        gelaran = getContext().getRequest().getParameter("gelaran");
//        daerah = getContext().getRequest().getParameter("daerah");
        char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_agensi++;
        String id_rek_agensi = String.valueOf(id_rekod_agensi);
        KodNegeri KN = new KodNegeri();
//        KodKategoriAgensi KKA = new KodKategoriAgensi();
//        KodGelaran KG = new KodGelaran();
//        KodDaerah KD = new KodDaerah();
        KodAgensi KA = new KodAgensi();
        InfoAudit ia = new InfoAudit();
        Date d = new Date();
        KN.setKod(negeri);
//        KKA.setKod(katgAgensi);
//        KG.setKod(gelaran);
//        KD.setKod(daerah);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(d);

        KA.setKod(id_rek_agensi);
        KA.setKodKementerian(Integer.parseInt(kodKementerian));
        KA.setNama(nama);
        KA.setAlamat1(alamat1);
        KA.setAlamat2(alamat2);
        KA.setAlamat3(alamat3);
        KA.setAlamat4(alamat4);
        KA.setKodNegeri(KN);
        KA.setNoTelefon1(noTelefon1);
        KA.setNoTelefon2(noTelefon2);
//        KA.setKodDaerah(KD);
        KA.setPoskod(poskod);
        KA.setAktif(aktif1);
        KA.setEmel(email);
        KA.setInfoAudit(ia);
        service.simpanKodAgensi(KA);
        statusTambah = true;

        return new RedirectResolution(KodAgensiActionBean.class, "viewKodAgensi").addParameter("table_name", nameTable).addParameter("kod", KA.getKod()).addParameter("statusTambah", true);

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

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public KodAgensi getDetails() {
        return details;
    }

    public void setDetails(KodAgensi details) {
        this.details = details;
    }

    public String getGelaran() {
        return gelaran;
    }

    public void setGelaran(String gelaran) {
        this.gelaran = gelaran;
    }

    public String getKatgAgensi() {
        return katgAgensi;
    }

    public void setKatgAgensi(String katgAgensi) {
        this.katgAgensi = katgAgensi;
    }

    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public int getId_rekod_agensi() {
        return id_rekod_agensi;
    }

    public void setId_rekod_agensi(int id_rekod_agensi) {
        this.id_rekod_agensi = id_rekod_agensi;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }
    
    
}
