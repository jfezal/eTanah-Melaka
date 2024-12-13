/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.account;

import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.utility.OracleDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 *
 * @author Izam
 */
public class HakmilikService extends WebService {

    public AccountInfo getHakmilikDetails(String idHakmilik) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        AccountInfo ai = new AccountInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select no_akaun from kew_akaun where kod_akaun = 'AC' "
                    + "and id_hakmilik = ?");
            ps.setString(1, idHakmilik);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                String accountNo = ocrs.getString(1);

                ai = (new AccountService()).getAccountDetails(accountNo);

            } else {
                throwEmptyFaultException("getHakmilikDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("HakmilikService.getHakmilikDetails: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getHakmilikDetails");
        }

        return ai;
    }
}
