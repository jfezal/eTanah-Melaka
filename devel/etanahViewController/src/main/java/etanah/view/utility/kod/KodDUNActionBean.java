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
import etanah.model.KodDUN;
import etanah.model.KodKawasanParlimen;
import etanah.model.Pengguna;
import etanah.service.KodService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
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
@UrlBinding("/utility_kod/kodDUN")
public class KodDUNActionBean extends AbleActionBean {

    private String nama;
    private String table_name;
    private String table_act_name;
    private Pengguna pguna;
    private String nameTable;
    private boolean status;
    private String kod;
    private String kod_Dun;
    private String agensi;
    private String parlimen;
    private String aktif;
    private String kodNegeri;
    private boolean statusTambah = false;
    private int id_rekod_DUN;
    private List<KodDUN> listKodDUN;
    private List<KodAgensi> listAdun;
    private KodDUN details;
    private boolean flag = false;
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    @Inject
    KodService service;
    @Inject
    DisLaporanTanahService disLaporanTanahService;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_DUN/listKodDUN.jsp");
    }

    public Resolution KodList() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (StringUtils.isBlank(nama) || nama.equals("0")) {
            nama = null;
        }
        if (StringUtils.isBlank(agensi) || agensi.equals("0")) {
            agensi = null;
        }
        listKodDUN = service.allKodDUN();



        if (listKodDUN.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodDUN.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_DUN/listKodDUN.jsp");

    }

    public Resolution KodList2() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        parlimen = getContext().getRequest().getParameter("parlimen");

        if (StringUtils.isBlank(nama) || nama.equals("0")) {
            nama = null;
        }
        if (StringUtils.isBlank(parlimen) || parlimen.equals("0")) {
            parlimen = null;
        }
        listKodDUN = service.findKodDUN2(nama, parlimen);


        if (listKodDUN.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodDUN.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }


        return new JSP("/utiliti/utiliti_kod_ruj/kod_DUN/listKodDUN.jsp");

    }

    public Resolution viewAddForm() throws Exception {

        nameTable = getContext().getRequest().getParameter("table_name");
        id_rekod_DUN = service.countKodDUN();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        listAdun = service.searchKodAgensiADN(kodNegeri);
        return new JSP("/utiliti/utiliti_kod_ruj/kod_DUN/tambahRekodDUN.jsp");
    }

    public Resolution viewKodDUN() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");

        details = service.viewKodDUN(kod);
        aktif = "" + details.isAktif();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        listAdun = service.searchKodAgensiADN(kodNegeri);

        return new JSP("/utiliti/utiliti_kod_ruj/kod_DUN/kemaskiniKodDUN.jsp");
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
        if (details.getNama() == null) {
            details.setNama("");
        }

        int result = 0;
        LOG.info("Entering 2nd phase updateData() function.......");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String query = new String();
        if (kodNegeri.equals("04")) {
            query = "update " + nameTable + " set NAMA= '" + details.getNama() + "', KOD_AGENSI = '" + details.getKodAgensi().getKod()
                    + "', DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('" + ds + "','dd/MM/yyyy HH24:mi:ss'),"
                    + "AKTIF = '" + aktif.charAt(0) + "' WHERE KOD = '" + kod + "'";
        } else {
            query = "update " + nameTable + " set NAMA= '" + details.getNama()
                    + "', DIKKINI = '" + pguna.getIdPengguna() + "', TRH_KKINI = TO_DATE('" + ds + "','dd/MM/yyyy HH24:mi:ss'),"
                    + "AKTIF = '" + aktif.charAt(0) + "' WHERE KOD = '" + kod + "'";
        }
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

        return new RedirectResolution(KodDUNActionBean.class, "viewKodDUN").addParameter("table_name", nameTable).addParameter("kod", kod);

    }

    public Resolution simpanData() throws Exception {
        LOG.info("Entering updateData() function.......");
        nameTable = getContext().getRequest().getParameter("table_name");
        agensi = getContext().getRequest().getParameter("agensi");
        parlimen = getContext().getRequest().getParameter("parlimen");
        char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_DUN++;
        String id_rek_dun = String.valueOf(id_rekod_DUN);
        KodAgensi KA = new KodAgensi();
        KodDUN KD = new KodDUN();
        KodKawasanParlimen KP = new KodKawasanParlimen();
        InfoAudit ia = new InfoAudit();
        Date d = new Date();
        KD.setKod(kod_Dun);
        if (agensi != null) {
            KA = disLaporanTanahService.getKodAgensiDAO().findById(agensi);
            KD.setKodAgensi(KA);
        }
        if (parlimen != null) {
            KP = disLaporanTanahService.getKodKawasanParlimenDAO().findById(parlimen);
            KD.setKodKawasanParlimen(KP);
        }
        KD.setNama(nama);
        KD.setAktif(aktif1);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(d);
        KD.setInfoAudit(ia);
        service.simpanKodDUN(KD);
        statusTambah = true;

        return new RedirectResolution(KodDUNActionBean.class, "viewKodDUN").addParameter("table_name", nameTable).addParameter("kod", KD.getKod());

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

    public KodDUN getDetails() {
        return details;
    }

    public void setDetails(KodDUN details) {
        this.details = details;
    }

    public int getId_rekod_DUN() {
        return id_rekod_DUN;
    }

    public void setId_rekod_DUN(int id_rekod_DUN) {
        this.id_rekod_DUN = id_rekod_DUN;
    }

    public List<KodDUN> getListKodDUN() {
        return listKodDUN;
    }

    public void setListKodDUN(List<KodDUN> listKodDUN) {
        this.listKodDUN = listKodDUN;
    }

    public String getKod_Dun() {
        return kod_Dun;
    }

    public void setKod_Dun(String kod_Dun) {
        this.kod_Dun = kod_Dun;
    }

    public String getParlimen() {
        return parlimen;
    }

    public void setParlimen(String parlimen) {
        this.parlimen = parlimen;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public List<KodAgensi> getListAdun() {
        return listAdun;
    }

    public void setListAdun(List<KodAgensi> listAdun) {
        this.listAdun = listAdun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
