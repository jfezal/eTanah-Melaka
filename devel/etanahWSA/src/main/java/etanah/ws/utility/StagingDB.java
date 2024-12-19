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
public class StagingDB {
    Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException cnfe) {
            System.out.println("OracleDB: Class not found exception.");
        }

//        String url = "jdbc:oracle:thin:@10.0.4.38:1521:etanah";
//        String username = "jasin";
//        String password = "jasin";

      
        String url = "jdbc:oracle:thin:@10.66.130.23:1521:etmlstg1";
        String username = "portal2";
        String password = "portal123";
//
//         String url = "jdbc:oracle:thin:@10.0.4.38:1521:etanah";
//        String username = "portal";
//        String password = "portal123"; 
        
       /* //String url = "jdbc:oracle:thin:@10.66.128.45:1521:etnsstg1";
        //String url ="jdbc:oracle:thin:@ (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.66.128.45)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = etnsstg)))"; 
        String username = "portal";
        String password = "portal123";*/
        
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
