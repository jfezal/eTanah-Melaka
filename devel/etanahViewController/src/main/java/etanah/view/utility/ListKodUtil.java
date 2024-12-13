/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.ForwardResolution;

import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author afham
 */
@UrlBinding("/utility/kodlist")
public class ListKodUtil extends AbleActionBean {

    public static final String TEST_SQL_DEFAULT = "select * from tab where tname like '%KOD%' and tname not in "
            + "('KOD_SEKATAN','KOD_SEKATAN1','KOD_SEKATANBAK','KOD_SEKATANOLD','KOD_SYARAT_NYATA',"
            + "'KOD_SYARAT_NYATA1','KOD_SYARAT_NYATABAK','KOD_SYARAT_NYATAOLD','KOD_AGENSI_KOD_CAW','KOD_DOK_AGENSI',"
            + "'KOD_DOK_IRINGAN','KOD_KADAR_BAYARAN','KOD_KADAR_PREMIUM','KOD_URUSAN_AGENSI','KOD_JAB_UNIT',"
            + "'KOD_LAPORAN_EMMKN','KOD_UNIT','KOD_URUSAN_HALANG','AUDIT_REKOD_HAPUS','SENARAI_KOD_RUJ','V_JTEK_KOD_DOKUMEN'"
            + "'KOD_STATUS_PORTAL','KOD_AGENSI','KOD_URUSAN','KOD_CAW_JABATAN','KOD_KATG_TANAH_XXX','KOD_KEMENTERIAN','KOD_KEW_TRANS'"
            + "'KOD_SEKOLAH_BANTUAN')"
            + "order by tname asc";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(ListKodUtil.class);
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private List test;
    private String[] a = {};
    private List array;
    private List arrayColumnName;
    private List data = new ArrayList();
    private Vector columnNames = new Vector();
    private Vector dataR = new Vector();
    private Vector row;
    private static String nameTable;
    private String isiBaruTemp;
    private Vector isiBaru = new Vector();
    private Vector tempKod1 = new Vector();
    private List table = new ArrayList(0);
    private List results = new ArrayList(0);
    private String tempKod;
    private int columns;
    boolean rowData;
    boolean newData;
    boolean sudahAdd;
    private int dataRow;
    private int tempData;
    private String kod;
    private String nama;
    private String aktif;
    private String diMasukOleh;
    private String diKemaskiniOleh;
    private Date tarikhMasuk;
    private Date tarikhKemaskini;
    boolean dataFlag = false;

    @DefaultHandler
    public Resolution showForm() {
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        rowData = false;
        newData = false;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(TEST_SQL_DEFAULT);
            int i = 0;
            array = new ArrayList();
            while (rs.next()) {
//                LOG.info("Row = " + rs.getString("TNAME"));
                array.add(i, rs.getString("TNAME"));
                i++;
            }
//            System.out.println("size =" + array.size());

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

        return new JSP("/utiliti/list_kod.jsp");
    }

    public Resolution pilihKod() {
        rowData = true;

        LOG.info("nameTable" + nameTable);
        if (nameTable == null) {
            addSimpleError("Sila pilih jadual");
            return showForm();
        } else {
            // Vector dataType = getDataType(nameTable);
            String html = null;
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ResultSetMetaData rsmd = null;
            try {
                Class.forName(JDBC_DRIVER);
                Session t = sessionProvider.get();
                LOG.info("Connecting to database...");
                conn = t.connection();
                LOG.info("Creating statement");
                //stmt = conn.createStatement();

//                stmt = conn.prepareStatement("select * from " + nameTable + " order by KOD", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt = conn.prepareStatement("select * from " + nameTable);
                stmt.setFetchSize(100);

                rs = stmt.executeQuery();
//                rs.relative(paging);

                rsmd = rs.getMetaData();
                columns = rsmd.getColumnCount();
                tempData = 1;
                for (int i = 1; i <= columns; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                }

//                rs.setFetchDirection(ResultSet.FETCH_FORWARD);
                //        rs.first();
                // rs.absolute(1);
//                rs.last();
                while (rs.next()) {
                    //              if (tempData > 20) break;
                    row = new Vector(columns);
                    for (int i = 1; i <= columns; i++) {
//                        if(rsmd.getColumnName(i).equals("KOD")){
//                            LOG.info(rs.getObject(i));
//                            tempKod1.addElement(rs.getObject(i));
//                        }
//                        }
                        row.addElement(rs.getObject(i));
                    }
                    dataR.addElement(row);
                    tempData++;
                }
                dataRow = dataR.size();
//                if(dataRow == 1)
//                {
//                    rs.relative(21);
//                    rs = stmt.executeQuery();
//                }
            } catch (Exception e) {
                html = "Db Connection is NOT OK. Exception is " + e.getMessage();
                LOG.error(e.getMessage(), e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e1) {
                        LOG.error(e1.getMessage(), e1);
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Exception e2) {
                        LOG.error(e2.getMessage(), e2);
                    }
                }
            }
            if (html == null) {
                html = "Db Connection is OK. No exception occured";
            }
        }

        addSimpleMessage("Maklumat telah dijumpai");
        return new ForwardResolution("/WEB-INF/jsp/utiliti/list_kod.jsp");
    }

    public Resolution editData() {
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        diMasukOleh = p.getIdPengguna();
        String test = getContext().getRequest().getParameter("no");
        System.out.println(test);
        System.out.println("nameTable" + nameTable);
        int a = Integer.parseInt(test);

        Vector tempSetiapKod = getKodTable(nameTable);
        LOG.info("TotalKod = " + tempSetiapKod);
        LOG.info("Kod = " + tempSetiapKod.get(a - 1));
        String ing = tempSetiapKod.get(a - 1).toString();
        LOG.info("Kod Sting =" + ing);
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select KOD, NAMA, AKTIF  from " + nameTable + " WHERE KOD = '" + ing + "'");
            rsmd = rs.getMetaData();
            //columns = rsmd.getColumnCount();

            while (rs.next()) {
                kod = rs.getString(1);
                nama = rs.getString(2);
                aktif = rs.getString(3);

            }
        } catch (Exception e) {
            html = "Db Connection is NOT OK. Exception is " + e.getMessage();
            LOG.error(e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e1) {
                    LOG.error("Error =" + e1);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e2) {
                    LOG.error("Error =" + e2);
                }
            }
            if (html == null) {
                html = "Db Connection is OK. No exception occured";
            }
        }
        return new JSP("utiliti/edit_kod2.jsp").addParameter("popup", "true");
    }

    public Resolution updateData() {
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String Daerah = getContext().getRequest().getParameter("Daerah");
        String kodBPM = getContext().getRequest().getParameter("KodBPM");

        diMasukOleh = p.getIdPengguna();
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        int result = 0;
//        int resultNama = 0;
//        int resultKod = 0;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();

//            String queryForKod = "select kod from " + nameTable + " where kod = '" + kod + "'";
//            LOG.info("QueryForKod :" + queryForKod);
//            resultKod = stmt.executeUpdate(queryForKod);
//
//            String queryForNama = "select nama from " + nameTable + " where nama = '" + nama + "'";
//            LOG.info("QueryForNama :" + queryForNama);
//            resultNama = stmt.executeUpdate(queryForNama);
//            
//            if (resultKod != 0 || resultNama != 0) {
//                if (resultKod != 0) {
//                    addSimpleError("Kod Telah Wujud. ");
//                } else if (resultNama != 0) {
//                    addSimpleError("Nama Telah Wujud");
//                } else {
//                    addSimpleError("Data Telah Wujud");
//                }
//            } else {
//            if (!Daerah.equals(null)) {
//                String query = "update " + nameTable + " set NAMA= '" + nama + "', KOD_DAERAH= '" + Daerah + "', AKTIF = '" + aktif + "', DIKKINI = '" + diMasukOleh + "', TRH_KKINI = SYSDATE WHERE KOD = '" + kod + "'";
//            }
//            if (!kodBPM.equals(null)) {
//                String query = "update " + nameTable + " set NAMA= '" + nama + "', BPM= '" + kodBPM + "', AKTIF = '" + aktif + "', DIKKINI = '" + diMasukOleh + "', TRH_KKINI = SYSDATE WHERE KOD = '" + kod + "'";
//            }

            String query = "update " + nameTable + " set NAMA= '" + nama + "', KOD_CAW= '" + Daerah + "', BPM= '" + kodBPM + "', KOD_DAERAH= '" + Daerah + "', AKTIF = '" + aktif + "', DIKKINI = '" + diMasukOleh + "', TRH_KKINI = SYSDATE WHERE KOD = '" + kod + "'";
            LOG.info("Query :" + query);
            result = stmt.executeUpdate(query);
            //}
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
            addSimpleError("Error, Terdapat Masalah Teknikal");
        } else {
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini");
        }

        return new JSP("utiliti/edit_kod2.jsp").addParameter("popup", "true");
    }

    public Resolution tambahData() {
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        diMasukOleh = p.getIdPengguna();
        if (dataFlag == false) {
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        }

        return new JSP("utiliti/add_kod.jsp").addParameter("popup", "true");
    }

    public Resolution addData() {
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        diMasukOleh = p.getIdPengguna();
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        int result = 0;
        int resultKod = 0;
        int resultNama = 0;

        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();

            String queryForKod = "select kod from " + nameTable + " where kod = '" + kod + "'";
            LOG.info("QueryForKod :" + queryForKod);
            resultKod = stmt.executeUpdate(queryForKod);

            String queryForNama = "select nama from " + nameTable + " where nama = '" + nama + "'";
            LOG.info("QueryForNama :" + queryForNama);
            resultNama = stmt.executeUpdate(queryForNama);

            if (resultKod != 0 || resultNama != 0) {
                if (resultKod != 0) {
                    addSimpleError("Kod Telah Wujud. Sila Masukkan Kod Yang Baru");
                } else if (resultNama != 0) {
                    addSimpleError("Nama Telah Wujud. Sila Masukkan Nama Yang Baru");
                } else {
                    addSimpleError("Kod Dan Nama Telah Wujud. Sila Masukkan Maklumat Yang Baru");
                }
            } else {
                String query = "insert into " + nameTable + "(KOD, NAMA, AKTIF, DIMASUK, TRH_MASUK)" + " values " + "('" + kod + "','" + nama + "', '" + aktif + "','" + diMasukOleh + "',SYSDATE)";
                LOG.info("Query :" + query);
                result = stmt.executeUpdate(query);
            }

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
            addSimpleError("Error, Terdapat Masalah Teknikal");
        } else {
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini");
        }
        return tambahData();
    }

    public boolean insertData() {

        newData = false;
        //String item = getContext().getRequest().getParameter("item");
        String tableName = getContext().getRequest().getParameter("tableName");
        //String[]test = item.split(",");
        int result = 0;
        // String[] columnName = getColumnName(tableName);
        //  Vector dataType = getDataType(tableName);
        Vector data = new Vector();
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();

            String query = "insert into " + nameTable + "(KOD, NAMA, AKTIF, DIMASUK, TRH_MASUK)" + " values " + "('" + kod + "','" + nama + "', '" + aktif + "','" + diMasukOleh + "',SYSDATE)";
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
            return false;
        }
        nameTable = tableName;
        return true;

    }

    public Resolution refreshPage() {
        System.out.println("refreshPage : start");
        pilihKod();
        System.out.println("refreshPage : finish");
        return pilihKod();
    }

    public Vector getKodTable(String tableName) {
        Vector kodTable = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        String html = null;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();
            LOG.info("tableName : " + tableName);
            rs = stmt.executeQuery("Select * from " + tableName);
            rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (rsmd.getColumnName(i).equals("KOD")) {
                        LOG.info(rs.getObject(i));
                        kodTable.addElement(rs.getObject(i));
                    }
                }
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
        return kodTable;
    }

    public String[] getColumnName(String tableName) {
        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        String[] item = null;
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();
            LOG.info("tableName : " + tableName);

            rs = stmt.executeQuery("Select * from " + tableName);
            rsmd = rs.getMetaData();
            item = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                LOG.info("column : " + rsmd.getColumnName(i));
                item[i - 1] = rsmd.getColumnName(i);
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
        return item;

    }

    public Vector getDataType(String tableName) {

        String html = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Vector dataType = new Vector();
        try {
            Class.forName(JDBC_DRIVER);
            Session t = sessionProvider.get();
            LOG.info("Connecting to database...");
            conn = t.connection();
            LOG.info("Creating statement");
            stmt = conn.createStatement();
            LOG.info("tableName : " + tableName);
//
//                    rs = stmt.executeQuery("Select COLUMN_NAME from user_tab_columns where table_name='" + nameTable + "'");
            rs = stmt.executeQuery("Select * from " + tableName);
            rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount();
            for (int i = 1; i <= size; i++) {
                LOG.info("datatype : " + rsmd.getColumnTypeName(i));
                dataType.addElement(rsmd.getColumnTypeName(i));

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
        return dataType;

    }

    public List getTest() {
        return test;
    }

    public void setTest(List test) {
        this.test = test;
    }

    public String[] getA() {
        return a;
    }

    public void setA(String[] a) {
        this.a = a;
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

    public List getArrayColumnName() {
        return arrayColumnName;
    }

    public void setArrayColumnName(List arrayColumnName) {
        this.arrayColumnName = arrayColumnName;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Vector getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(Vector columnNames) {
        this.columnNames = columnNames;
    }

    public Vector getDataR() {
        return dataR;
    }

    public void setDataR(Vector dataR) {
        this.dataR = dataR;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean isRowData() {
        return rowData;
    }

    public void setRowData(boolean rowData) {
        this.rowData = rowData;
    }

    public Vector getRow() {
        return row;
    }

    public void setRow(Vector row) {
        this.row = row;
    }

    public int getDataRow() {
        return dataRow;
    }

    public void setDataRow(int dataRow) {
        this.dataRow = dataRow;
    }

    public int getTempData() {
        return tempData;
    }

    public void setTempData(int tempData) {
        this.tempData = tempData;
    }

    public boolean isNewData() {
        return newData;
    }

    public void setNewData(boolean newData) {
        this.newData = newData;
    }

    public Vector getIsiBaru() {
        return isiBaru;
    }

    public void setIsiBaru(Vector isiBaru) {
        this.isiBaru = isiBaru;
    }

    public String getIsiBaruTemp() {
        return isiBaruTemp;
    }

    public void setIsiBaruTemp(String isiBaruTemp) {
        this.isiBaruTemp = isiBaruTemp;
    }

    public boolean isSudahAdd() {
        return sudahAdd;
    }

    public void setSudahAdd(boolean sudahAdd) {
        this.sudahAdd = sudahAdd;
    }

    public List getTable() {
        return table;
    }

    public void setTable(List table) {
        this.table = table;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getDiKemaskiniOleh() {
        return diKemaskiniOleh;
    }

    public void setDiKemaskiniOleh(String diKemaskiniOleh) {
        this.diKemaskiniOleh = diKemaskiniOleh;
    }

    public String getDiMasukOleh() {
        return diMasukOleh;
    }

    public void setDiMasukOleh(String diMasukOleh) {
        this.diMasukOleh = diMasukOleh;
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

    public Date getTarikhKemaskini() {
        return tarikhKemaskini;
    }

    public void setTarikhKemaskini(Date tarikhKemaskini) {
        this.tarikhKemaskini = tarikhKemaskini;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public String getTempKod() {
        return tempKod;
    }

    public void setTempKod(String tempKod) {
        this.tempKod = tempKod;
    }

    public boolean isDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(boolean dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Vector getTempKod1() {
        return tempKod1;
    }

    public void setTempKod1(Vector tempKod1) {
        this.tempKod1 = tempKod1;
    }
}
