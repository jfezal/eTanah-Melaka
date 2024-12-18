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
public class NoLotService extends WebService {
    public AccountInfo getNoLotDetails(String kodDaerah, String kodMukim,
            String kodLot, String noLot) throws AccountInfoFaultException {

        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        AccountInfo ai = new AccountInfo();

        // add 0 padding if noLot length is less than 7
        if(noLot.length() < 7) noLot = String.format("%07d", Integer.parseInt(noLot));

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun from kew_akaun ka, hakmilik hm where "
                    + "ka.kod_akaun = 'AC' and hm.id_hakmilik = ka.id_hakmilik "
                    + "and hm.kod_daerah = ? and hm.kod_bpm = ? and "
                    + "hm.kod_lot = ? and hm.no_lot = ?");
            ps.setString(1, kodDaerah);
            ps.setLong(2, Long.parseLong(kodMukim));
            ps.setString(3, kodLot);
            ps.setString(4, noLot);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                String accountNo = ocrs.getString(1);

                ai = (new AccountService()).getAccountDetails(accountNo);

            } else {
                throwEmptyFaultException("getNoLotDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("NoLotService.getNoLotDetails: SQLException.");
            se.printStackTrace();

           throwInternalFaultException("getNoLotDetails");
        }

        return ai;
    }
}
