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
import etanah.model.KodBangsa;
import etanah.model.KodKawasanParlimen;
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
 * @author afham
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodParlimen")
public class KodKawasanParlimenActionBean extends AbleActionBean {

    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod_Parlimen;
    private String kod;
    private boolean statusTambah = false;
    private int id_rekod_parlimen;
    private List<KodKawasanParlimen> listKodKawasanParlimen;
    private KodKawasanParlimen details;
    private boolean flag = false;
    private String aktif ;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;

    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_parlimen/listKodParlimen.jsp");
    }

    public Resolution KodList() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        if (StringUtils.isBlank(nama) || nama.equals("0")) {
            nama = null;
        }

        listKodKawasanParlimen = service.allKodKawasanParlimen();



        if (listKodKawasanParlimen.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodKawasanParlimen.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_parlimen/listKodParlimen.jsp");

    }

    public Resolution KodList2() throws Exception {

        //nama = getContext().getRequest().getParameter("nama");



        listKodKawasanParlimen = service.findKodKawasanParlimen2(nama);


        if (listKodKawasanParlimen.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodKawasanParlimen.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_parlimen/listKodParlimen.jsp");

    }

    public Resolution viewKodParlimen() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");

        details = service.viewKodParlimen(kod);
        aktif = "" + details.isAktif();

        return new JSP("/utiliti/utiliti_kod_ruj/kod_parlimen/kemaskiniKodParlimen.jsp");
    }
    
    public Resolution viewAddForm() throws Exception {

        nameTable = getContext().getRequest().getParameter("table_name");
        id_rekod_parlimen = service.countKodParlimen();

        return new JSP("/utiliti/utiliti_kod_ruj/kod_parlimen/tambahRekodParlimen.jsp");
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
        if (details.getNama() == null) {
            details.setNama("");
        }
        int result = 0;
        LOG.info("Entering 2nd phase updateData() function.......");
        String query = "update " + nameTable + " set nama= '" + details.getNama() + "',"
                + " DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('" + ds + "','dd/MM/yyyy HH24:mi:ss'),"
                + "AKTIF = '" + aktif.charAt(0) + "' WHERE KOD = '" + kod + "'";
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

        return new RedirectResolution(KodKawasanParlimenActionBean.class, "viewKodParlimen").addParameter("table_name", nameTable).addParameter("kod", kod);

    }
    
    public Resolution simpanData() throws Exception {
        LOG.info("Entering updateData() function.......");
        nameTable = getContext().getRequest().getParameter("table_name");
        char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_parlimen++;
        String id_rek_parlimen = String.valueOf(id_rekod_parlimen);
        KodKawasanParlimen KP = new KodKawasanParlimen();
        InfoAudit ia = new InfoAudit();
        Date d = new Date();
        KP.setKod(kod_Parlimen);
        KP.setNama(nama);
        KP.setAktif(aktif1);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(d);
        KP.setInfoAudit(ia);
        service.simpanKodParlimen(KP);
        statusTambah = true;

        return new RedirectResolution(KodKawasanParlimenActionBean.class, "viewKodParlimen").addParameter("table_name", nameTable).addParameter("kod", KP.getKod());

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_act_name() {
        return table_act_name;
    }

    public void setTable_act_name(String table_act_name) {
        this.table_act_name = table_act_name;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getKod_Parlimen() {
        return kod_Parlimen;
    }

    public void setKod_Parlimen(String kod_Parlimen) {
        this.kod_Parlimen = kod_Parlimen;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public boolean isStatusTambah() {
        return statusTambah;
    }

    public void setStatusTambah(boolean statusTambah) {
        this.statusTambah = statusTambah;
    }

    public int getId_rekod_parlimen() {
        return id_rekod_parlimen;
    }

    public void setId_rekod_parlimen(int id_rekod_parlimen) {
        this.id_rekod_parlimen = id_rekod_parlimen;
    }

    public List<KodKawasanParlimen> getListKodKawasanParlimen() {
        return listKodKawasanParlimen;
    }

    public void setListKodKawasanParlimen(List<KodKawasanParlimen> listKodKawasanParlimen) {
        this.listKodKawasanParlimen = listKodKawasanParlimen;
    }

    public KodKawasanParlimen getDetails() {
        return details;
    }

    public void setDetails(KodKawasanParlimen details) {
        this.details = details;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public KodService getService() {
        return service;
    }

    public void setService(KodService service) {
        this.service = service;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
    
    
}
