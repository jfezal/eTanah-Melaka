/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import oracle.jdbc.rowset.OracleCachedRowSet;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.utility.OracleDB;

/**
 *
 * @author Izam
 */
public class ICService extends WebService {
    public List<AccountInfo> getHakmilikList(String icNo) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<AccountInfo> aiList = new LinkedList<AccountInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select p.nama, ka.baki, ka.id_hakmilik, ka.no_akaun from "
                    + "kew_akaun ka, pihak p, hakmilik_pihak hp where p.no_pengenalan = ? "
                    + "and hp.id_pihak = p.id_pihak and ka.id_hakmilik = hp.id_hakmilik");
            ps.setString(1, icNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    AccountInfo ai = new AccountInfo();

                    ai.setNamaPembayar(ocrs.getString(1));
                    ai.setBakiAkaun(ocrs.getBigDecimal(2));
                    ai.setIdHakmilik(ocrs.getString(3));
                    ai.setNoAkaun(ocrs.getString(4));

                    aiList.add(ai);
                }
            } else {
               throwEmptyFaultException("getHakmilikList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("ICService.getHakmilikList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getHakmilikList");
        }

        return aiList;
    }
}
