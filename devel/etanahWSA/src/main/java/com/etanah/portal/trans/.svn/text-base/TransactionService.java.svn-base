/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etanah.portal.trans;

import etanah.ws.agent.AccountHakmilikInfo;
import etanah.ws.utility.StagingDB;
import etanah.ws.utility.StatusInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author faidzal
 */
public class TransactionService {

    public List<TransactionInfo> listTransaction(String username, String dari,
            String hingga) throws ParseException {
        List<TransactionInfo> list = new ArrayList<TransactionInfo>();
        StagingDB db = new StagingDB();
        Connection conn = db.getConnection();

        try {
            // without kod_negeri kn selected (kn.nama, p.kod_negeri = kn.kod)
            String sb = "select id, username, amount, fpx_no, "
                    + "transaction_no, account_no, id_hakmilik, payment_date, "
                    + "payment_type,id_kew_dok from portal_transaction"
                    + " where username = ?";
            if (!dari.isEmpty()) {
                sb = sb + " and PAYMENT_DATE between to_date (?, 'yyyy/mm/dd') "
                        + "AND to_date (?, 'yyyy/mm/dd')";

            }
            sb = sb + " order by  (payment_date)desc";
//            PreparedStatement ps = conn.prepareStatement(
//                    "select id, username, amount, fpx_no, "
//                    + "transaction_no, account_no, id_hakmilik, payment_date, "
//                    + "payment_type,id_kew_dok from portal_transaction"
//                    + " where username = ? order by  (payment_date)desc");

            PreparedStatement ps = conn.prepareStatement(sb);

            ps.setString(1, username);
            if (!dari.isEmpty()) {
                ps.setString(2, dari);
                ps.setString(3, hingga);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TransactionInfo info = new TransactionInfo();
                info.setUserName(rs.getString(2));
                info.setAmount(rs.getBigDecimal(3));
                if (rs.getString(4) == null) {
                    info.setFpxNo("");
                } else {
                    info.setFpxNo(rs.getString(4));
                }
                if (rs.getString(5) == null) {
                    info.setTansactionNo("");
                } else {
                    info.setTansactionNo(rs.getString(5));
                }

                info.setNoAccount(rs.getString(6));
                info.setIdHakmilik(rs.getString(7));
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
                //date = sdf.parse(rs.getDate(8));
                date = rs.getTimestamp(8);
                System.out.println(sdf.format(date));
                info.setPaymentDate(sdf.format(date));
                info.setPaymentType(rs.getString(9));
                if (rs.getString(9) == null) {
                    info.setIdKewDok("");
                } else {
                    info.setIdKewDok(rs.getString(10));
                }

                list.add(info);

            }
            // release the connection
            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("QuitRentAgent.checkAccount: SQLException.");
            se.printStackTrace();


        }
        return list;
    }

    public String updateTransactionStatus(String fpxNo, String transactionNo, String idTrans) {
        String sts = new String();
        StagingDB db = new StagingDB();
        Connection conn = db.getConnection();
        String idHakmilik = null;
        String fpxNom = null;
        try {
            String sb = "select id_hakmilik,fpx_no from portal_transaction"
                    + " where id = ?";

            PreparedStatement ps2 = conn.prepareStatement(sb);

            ps2.setString(1, idTrans);
            ResultSet rs1 = ps2.executeQuery();

            while (rs1.next()) {
                idHakmilik = rs1.getString(1);
                fpxNom = rs1.getString(2);
            }
            if (fpxNom == null) {

                // without kod_negeri kn selected (kn.nama, p.kod_negeri = kn.kod)
                PreparedStatement ps = conn.prepareStatement(
                        "update portal_transaction set fpx_no = ?, transaction_no = ? where id = ?");

                ps.setString(1, fpxNo);
                ps.setString(2, transactionNo);
                ps.setString(3, idTrans);

                int rs = ps.executeUpdate();
                db.releaseConnection(ps);
            }
            // release the connection
            db.releaseConnection(ps2, rs1);


        } catch (SQLException se) {
            System.out.println("QuitRentAgent.checkAccount: SQLException.");
            se.printStackTrace();


        }
        return idHakmilik;
    }

    public String findTransactionFail(String idTrans) {
  String username = "";
          StagingDB db = new StagingDB();
        Connection conn = db.getConnection();

        try {
            String sb = "select id_hakmilik,fpx_no,username from portal_transaction"
                    + " where id = ? and fpx_no is null";

            PreparedStatement ps2 = conn.prepareStatement(sb);

            ps2.setString(1, idTrans);
            ResultSet rs1 = ps2.executeQuery();

            while (rs1.next()) {
               username = rs1.getString(3);
            }
            // release the connection
            db.releaseConnection(ps2, rs1);


        } catch (SQLException se) {
            System.out.println("QuitRentAgent.checkAccount: SQLException.");
            se.printStackTrace();


        }
        return username;
    }
}
