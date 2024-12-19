/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 *
 * @author Izam
 */
public class OracleDB {
    Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException cnfe) {
            System.out.println("OracleDB: Class not found exception.");
        }

//        String url = "jdbc:oracle:thin:@10.66.130.13:1521:etanahml2";
//        String username = "jasin3";
//        String password = "qweASD123#";
//
       String url = "jdbc:oracle:thin:@192.168.0.11:1521:etanahml";
        String username = "etml";
        String password = "etanah123";

        
//        String url = "jdbc:oracle:thin:@10.66.130.13:1521:etanahml2";
//        String username = "fat";
//        String password = "ASDqwe123#";
////        
//
//        String url = "jdbc:oracle:thin:@10.0.4.38:1521:etanah";
//        String username = "negeri";
//        String password = "negeri"; 
        
//        String url = "jdbc:oracle:thin:@10.66.128.12:1521:etanahns1";
//        String username = "negeri1";
//        String password = "N3geri#";
// String url = "jdbc:oracle:thin:@10.66.128.13:1521:etanahns2";
//        String username = "seremban";
//        String password = "seremban";
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            
        } catch (SQLException se) {
            System.out.println("OracleDB.getConnection: SQL Exception.");
        }

        return conn;
    }

    public void releaseConnection(PreparedStatement ps) {
        try {
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("OracleDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public void releaseConnection(PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("OracleDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public void releaseConnection(PreparedStatement ps, OracleCachedRowSet ocrs) {
        try {
            ocrs.close();
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("OracleDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public void releaseConnection(PreparedStatement ps, ResultSet rs, OracleCachedRowSet ocrs) {
        try {
            ocrs.close();
            rs.close();
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("OracleDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public int getRowCount(OracleCachedRowSet ocrs) throws SQLException {
        return ocrs.last() ? ocrs.getRow() : 0;
    }

    public String getNextSequence(String seqName) throws SQLException {
        String seqNo = "";

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select " + seqName + ".nextval from dual");
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                seqNo = ocrs.getString(1);
            }

            // release the connection
            ocrs.close();
            rs.close();
            ps.close();

        } catch(SQLException se) {
            System.out.println("OracleDB.getNextSequence: SQLException.");
            se.printStackTrace();
        }

        return seqNo;
    }
}
