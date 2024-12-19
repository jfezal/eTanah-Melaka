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
public class MysqlDB {
    Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.out.println("MysqlDB: Class not found exception.");
        }

        String url = "jdbc:mysql://localhost:3306/portal";
        String username = "portal";
        String password = "portal123";

        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            
        } catch (SQLException se) {
            System.out.println("MysqlDB.getConnection: SQL Exception.");
        }

        return conn;
    }

    public void releaseConnection(PreparedStatement ps) {
        try {
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("MysqlDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public void releaseConnection(PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("MysqlDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public void releaseConnection(PreparedStatement ps, OracleCachedRowSet ocrs) {
        try {
            ocrs.close();
            ps.close();
            conn.close();

        } catch(SQLException se) {
            System.out.println("MysqlDB.releaseConnection: SQLException.");
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
            System.out.println("MysqlDB.releaseConnection: SQLException.");
            se.printStackTrace();
        }
    }

    public int getRowCount(OracleCachedRowSet ocrs) throws SQLException {
        return ocrs.last() ? ocrs.getRow() : 0;
    }
}
