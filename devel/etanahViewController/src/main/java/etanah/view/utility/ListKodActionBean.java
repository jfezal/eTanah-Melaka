package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.KutipanHasilManager;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author haqqiem
 * Tue May 8 2012 10:42:52 AM
 *
 */
@UrlBinding("/utility/kod_list")
public class ListKodActionBean extends AbleActionBean {

    public static final String TEST_SQL_DEFAULT = "select * from tab where tname in ('KOD_SEKATAN','KOD_SYARAT_NYATA') order by tname asc";
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject KutipanHasilManager manager;
    @Inject PenggunaDAO penggunaDAO;

    private static final Logger LOG = Logger.getLogger(ListKodUtil.class);
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private KodSekatanKepentingan sekatan = new KodSekatanKepentingan();
    private KodSyaratNyata syarat = new KodSyaratNyata();
    private List array;
    private String nameTable = null;
    boolean rowData = false;
    boolean addNew = false;
    boolean flagSave = false;
    private List<KodSekatanKepentingan> senaraiKSK = new ArrayList<KodSekatanKepentingan>();
    private List<KodSyaratNyata> senaraiKSN = new ArrayList<KodSyaratNyata>();

    @DefaultHandler
    public Resolution showForm() {
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(TEST_SQL_DEFAULT);
            int i = 0;
            array = new ArrayList();
            while (rs.next()) {
                array.add(i, rs.getString("TNAME"));
                i++;
            }
        } catch (Exception e) {
            html = "Db Connection is NOT OK. Exception is " + e.getMessage();
            LOG.error(e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e1) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e2) {
                }
            }
        }

        if (html == null) {
            html = "Db Connection is OK. No exception occured";
        }

        return new JSP("/utiliti/senarai_kod.jsp");
    }

    public Resolution pilihKod() {
        rowData = true;
        senaraiKSK = new ArrayList<KodSekatanKepentingan>();
        senaraiKSN = new ArrayList<KodSyaratNyata>();

        String tab = null;
        if (nameTable.equals("KOD_SEKATAN")) {
            tab = "KodSekatanKepentingan";
        }
        if (nameTable.equals("KOD_SYARAT_NYATA")) {
            tab = "KodSyaratNyata";
        }

        String query = "SELECT a FROM etanah.model." + tab + " a";
        Query q = sessionProvider.get().createQuery(query);
        if (nameTable.equals("KOD_SEKATAN")) {
            senaraiKSK = q.list();
        }
        if (nameTable.equals("KOD_SYARAT_NYATA")) {
            senaraiKSN = q.list();
        }

        showForm();
        addSimpleMessage("Maklumat telah dijumpai");
        return new ForwardResolution("/WEB-INF/jsp/utiliti/senarai_kod.jsp");
    }

    public Resolution popup() {
        addNew = false;

        String kod = getContext().getRequest().getParameter("kod");
        nameTable = getContext().getRequest().getParameter("table");
        LOG.info("kod : " + kod);
        LOG.info("nameTable : " + nameTable);

        String tab = null;
        if (nameTable.equals("KOD_SEKATAN")) {
            tab = "KodSekatanKepentingan";
        }
        if (nameTable.equals("KOD_SYARAT_NYATA")) {
            tab = "KodSyaratNyata";
        }

        String query = "SELECT a FROM etanah.model." + tab + " a WHERE a.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kod);
        if (nameTable.equals("KOD_SEKATAN")) {
            sekatan = (KodSekatanKepentingan) q.uniqueResult();
        }
        if (nameTable.equals("KOD_SYARAT_NYATA")) {
            syarat = (KodSyaratNyata) q.uniqueResult();
        }

        return new JSP("utiliti/senarai_kod_1.jsp").addParameter("popup", "true");
    }

    public Resolution addData() {
        LOG.info("INSIDE addData");
        addNew = true;
        nameTable = getContext().getRequest().getParameter("table");
        LOG.info("nameTable : " + nameTable);

        sekatan = new KodSekatanKepentingan();
        syarat = new KodSyaratNyata();

        return new JSP("utiliti/senarai_kod_1.jsp").addParameter("popup", "true");
    }

    public Resolution saveEditData() {
        InfoAudit ia = new InfoAudit();
        Pengguna pengguna = new Pengguna();
        flagSave = false;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();

        if (nameTable.equals("KOD_SEKATAN")) {
            LOG.info("..:: INSIDE KOD_SEKATAN ::.. " + sekatan.getKod()+" : "+pengguna.getIdPengguna());
            
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            sekatan.setInfoAudit(ia);
            manager.saveOrUpdate(sekatan);
            flagSave = true;
        }
        if (nameTable.equals("KOD_SYARAT_NYATA")) {
            LOG.info("..:: INSIDE KOD_SYARAT_NYATA ::.. " + sekatan.getKod()+" : "+pengguna.getIdPengguna());

            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            sekatan.setInfoAudit(ia);
            manager.saveOrUpdate(syarat);
            flagSave = true;
        }

        return new JSP("utiliti/senarai_kod_1.jsp").addParameter("popup", "true");
    }

    public List getArray() {
        return array;
    }

    public void setArray(List array) {
        this.array = array;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public boolean isRowData() {
        return rowData;
    }

    public void setRowData(boolean rowData) {
        this.rowData = rowData;
    }

    public List<KodSekatanKepentingan> getSenaraiKSK() {
        return senaraiKSK;
    }

    public void setSenaraiKSK(List<KodSekatanKepentingan> senaraiKSK) {
        this.senaraiKSK = senaraiKSK;
    }

    public List<KodSyaratNyata> getSenaraiKSN() {
        return senaraiKSN;
    }

    public void setSenaraiKSN(List<KodSyaratNyata> senaraiKSN) {
        this.senaraiKSN = senaraiKSN;
    }

    public KodSekatanKepentingan getSekatan() {
        return sekatan;
    }

    public void setSekatan(KodSekatanKepentingan sekatan) {
        this.sekatan = sekatan;
    }

    public KodSyaratNyata getSyarat() {
        return syarat;
    }

    public void setSyarat(KodSyaratNyata syarat) {
        this.syarat = syarat;
    }

    public boolean isAddNew() {
        return addNew;
    }

    public void setAddNew(boolean addNew) {
        this.addNew = addNew;
    }

    public boolean isFlagSave() {
        return flagSave;
    }

    public void setFlagSave(boolean flagSave) {
        this.flagSave = flagSave;
    }
}
