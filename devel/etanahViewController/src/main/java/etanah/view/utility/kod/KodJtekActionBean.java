/**
 *
 * @author khairul.hazwan
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility.kod;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodDaerah;
import etanah.model.KodGelaran;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.service.KodService;
import etanah.service.uam.UamService;
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

@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodJtek")
public class KodJtekActionBean extends AbleActionBean {

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
    @Inject
    UamService uamServ;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/kod_jtek/listKodJtek.jsp");
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

        //listKodAgensi = service.allKodAgensi();
        listKodAgensi = uamServ.findJTEKAgensi();

        if (listKodAgensi.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodAgensi.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }

        return new JSP("/utiliti/utiliti_kod_ruj/kod_jtek/listKodJtek.jsp");

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
        listKodAgensi = service.findKodAgensi3(nama, daerah);
        //listKodAgensi = service.findKodAgensi2(kodKementerian1, nama, katgAgensi, negeri, gelaran, daerah);

        if (listKodAgensi.size() > 0) {
            setFlag(true);
            addSimpleMessage(listKodAgensi.size() + " kod ditemui.");

        } else {
            addSimpleError("Tiada kod ditemui.");
        }

        return new JSP("/utiliti/utiliti_kod_ruj/kod_jtek/listKodJtek.jsp");
    }

    public Resolution viewAddForm() throws Exception {

        nameTable = getContext().getRequest().getParameter("table_name");
        id_rekod_agensi = service.countKodAgensi();
        return new JSP("/utiliti/utiliti_kod_ruj/kod_jtek/tambahRekodJtek.jsp");
    }

    public Resolution viewKodAgensi() throws Exception {
        nameTable = getContext().getRequest().getParameter("table_name");
        kod = getContext().getRequest().getParameter("kod");

        details = service.viewKodAgensi(kod);
        if (statusTambah == true) {
            addSimpleMessage("Tambah rekod telah berjaya");
            statusTambah = false;
        }

        return new JSP("/utiliti/utiliti_kod_ruj/kod_jtek/kemaskiniKodJtek.jsp");
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
        if (details.getPoskod() == null) {
            details.setPoskod("");
        }
        if (details.getEmel() == null) {
            details.setEmel("");
        }
        if (details.getKodGelaran() == null) {
            details.setKodGelaran(null);
        }
        if (details.getKategoriAgensi() == null) {
            details.setKategoriAgensi(null);
        }
        if (details.getKodKementerian() == 0) {
            details.setKodKementerian(0);
        }

        KodAgensi kodAgensi = uamServ.findKodAgensiTerpilih(kod);
        LOG.info("Kod Agensi Terpilih :: " + kodAgensi.getKod());
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(d);

        if (kodAgensi != null) {
            kodAgensi.setNama(details.getNama());
            kodAgensi.setAlamat1(details.getAlamat1());
            kodAgensi.setAlamat2(details.getAlamat2());
            kodAgensi.setAlamat3(details.getAlamat3());
            kodAgensi.setAlamat4(details.getAlamat4());
            kodAgensi.setKodNegeri(kodNegeriDAO.findById(details.getKodNegeri().getKod()));
            kodAgensi.setPoskod(details.getPoskod());
            kodAgensi.setAktif(details.getAktif());
            kodAgensi.setEmel(details.getEmel());
            if (details.getKodDaerah() != null) {
                kodAgensi.setKodDaerah(kodDaerahDAO.findById(details.getKodDaerah().getKod()));
            }
            kodAgensi.setInfoAudit(ia);
            service.simpanKodAgensi(kodAgensi);
        } else {
            addSimpleError("Data tidak ditemui dan tidak berjaya dikemaskini");
        }

        addSimpleMessage("Data telah berjaya dikemaskini");
        return new RedirectResolution(KodJtekActionBean.class, "viewKodAgensi").addParameter("table_name", nameTable).addParameter("kod", kod);

    }

    public Resolution simpanData() throws Exception {
        LOG.info("Entering updateData() function.......");
        nameTable = getContext().getRequest().getParameter("table_name");
        kodKementerian = "30";
        negeri = getContext().getRequest().getParameter("negeri");
        katgAgensi = "JTK";
        //gelaran = getContext().getRequest().getParameter("gelaran");
        daerah = getContext().getRequest().getParameter("daerah");
        char aktif1 = getContext().getRequest().getParameter("aktif").charAt(0);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        id_rekod_agensi++;
        String id_rek_agensi = String.valueOf(id_rekod_agensi);

        KodAgensi KA = new KodAgensi();
        InfoAudit ia = new InfoAudit();
        Date d = new Date();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(d);

        KA.setKod(id_rek_agensi);
        KA.setKodKementerian(Integer.parseInt(kodKementerian));
        KA.setNama(nama);
        KA.setAlamat1(alamat1);
        KA.setAlamat2(alamat2);
        KA.setAlamat3(alamat3);
        KA.setAlamat4(alamat4);
        KA.setKodNegeri(kodNegeriDAO.findById(negeri));
        KA.setKategoriAgensi(kodKategoriAgensiDAO.findById(katgAgensi));
        //KA.setKodGelaran(KG);
        KA.setKodDaerah(kodDaerahDAO.findById(daerah));
        KA.setPoskod(poskod);
        KA.setAktif(aktif1);
        KA.setEmel(email);
        KA.setInfoAudit(ia);
        service.simpanKodAgensi(KA);
        statusTambah = true;

        return new RedirectResolution(KodJtekActionBean.class, "viewKodAgensi").addParameter("table_name", nameTable).addParameter("kod", KA.getKod()).addParameter("statusTambah", true);
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
}
