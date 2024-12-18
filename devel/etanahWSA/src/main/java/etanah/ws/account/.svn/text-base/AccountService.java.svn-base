/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.account;

import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.utility.OracleDB;
import etanah.ws.utility.StagingDB;
import etanah.ws.utility.StatusInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 *
 * @author Izam
 */
public class AccountService extends WebService {

    public String idKewDok = "";

    /*public AccountInfo getAccountDetails(String accountNo) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        AccountInfo ai = new AccountInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.id_hakmilik, p.nama, p.no_pengenalan, "
                    + "p.alamat1, p.alamat2, p.alamat3, p.alamat4, p.poskod, kn.nama, "
                    + "ka.id_hakmilik, kh.nama, ka.baki "
                    + "from kew_akaun ka, pihak p, kod_negeri kn, hakmilik h, kod_hakmilik kh "
                    + "where ka.no_akaun = ? and "
                    + "ka.kod_akaun = 'AC' and p.id_pihak = ka.dipegang and "
                    + "p.kod_negeri = kn.kod and h.id_hakmilik = ka.id_hakmilik and "
                    + "h.kod_hakmilik = kh.kod");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                ps = conn.prepareStatement(
                        "select ph.no_pengenalan from pihak ph, hakmilik_pihak hp where "
                        + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? and rownum = 1");
                ps.setString(1, ocrs.getString(2));

                rs = ps.executeQuery();
                rs.next();

                ai.setNoAkaun(ocrs.getString(1));
                ai.setIdHakmilik(ocrs.getString(2));
                //ai.setNamaPemilik(ocrs.getString(14));
                ai.setNamaPembayar(ocrs.getString(3));
                //ai.setIcPembayar(ocrs.getString(4));
                ai.setIcPemilik(rs.getString(1));

                ai.setAlamat(ocrs.getString(5) + ", " + ocrs.getString(6) + ", " + ocrs.getString(7)
                        + ", " + ocrs.getString(8) + ", " + ocrs.getString(9) + ", " + ocrs.getString(10));

                ai.setIdHakmilik(ocrs.getString(11));
                ai.setJenisHakmilik(ocrs.getString(12));

                long bakiAkaun = ocrs.getLong(13);
                String searchCriteria, status;

                if (bakiAkaun > 0) { // have to pay cukai tanah
                    searchCriteria = "no_akaun_dt";
                    status = "BELUM BAYAR";

                } else { // already paid for that accountNo
                    searchCriteria = "no_akaun_kr";
                    status = "TELAH BAYAR";
                }

                // search the transaction for that accountNo
                ps = conn.prepareStatement(
                        "select kod_trans, amaun, id_kew_dok, trh_masuk from kew_trans where "
                        + searchCriteria + " = ?");
                ps.setString(1, accountNo);
                rs = ps.executeQuery();

                long cukaiSemasa = 0;
                long tunggakanCukai = 0;
                long dendaLewat = 0;
                long remisyen = 0;
                long notis6a = 0;
                long jumlah = 0;

                idKewDok = "";
                Timestamp tarikhMasuk = null;

                while (rs.next()) {
                    String kodTrans = rs.getString(1);

                    if (kodTrans.equals("61401")) {
                        cukaiSemasa += rs.getLong(2);
                    } else if (kodTrans.equals("61402")) {
                        tunggakanCukai += rs.getLong(2);
                    } else if (kodTrans.equals("76152")) {
                        dendaLewat += rs.getLong(2);
                    } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
                            || kodTrans.equals("99002") || kodTrans.equals("99003")) {
                        remisyen += rs.getLong(2);
                    } else if (kodTrans.equals("61018")) {
                        notis6a += rs.getLong(2);
                    }

                    idKewDok = rs.getString(3);
                    tarikhMasuk = rs.getTimestamp(4);
                }

                if (bakiAkaun <= 0) { // already paid for that accountNo
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                    ai.setTarikhBayaran(sdf.format(tarikhMasuk));
                    ai.setNoResit(idKewDok);
                }

                jumlah = cukaiSemasa + tunggakanCukai + dendaLewat + remisyen + notis6a;

                ai.setCukaiSemasa(cukaiSemasa);
                ai.setTunggakanCukai(tunggakanCukai);
                ai.setDendaLewat(dendaLewat);
                ai.setRemisyen(remisyen);
                ai.setNotis6a(notis6a);
                ai.setJumlahBayaran(jumlah);
                ai.setStatusBayaran(status);

            } else {
                throwEmptyFaultException("getAccountDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("QuitRent.checkAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getAccountDetails");

        } catch (NullPointerException npe) {
            System.out.println("QuitRent.checkAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("getAccountDetails");
        }

        return ai;
    }
*/
    public AccountInfo getAccountDetails(String accountNo) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        AccountInfo ai = new AccountInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.id_hakmilik, p.nama, p.no_pengenalan, "
                    + "p.alamat1, p.alamat2, p.alamat3, p.alamat4, p.poskod, kn.nama, "
                    + "ka.id_hakmilik, kh.nama, ka.baki "
                    + "from kew_akaun ka, pihak p, kod_negeri kn, hakmilik h, kod_hakmilik kh "
                    + "where ka.no_akaun = ? and "
                    + "ka.kod_akaun = 'AC' and p.id_pihak = ka.dipegang and "
                    + "p.kod_negeri = kn.kod and h.id_hakmilik = ka.id_hakmilik and "
                    + "h.kod_hakmilik = kh.kod");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                ps = conn.prepareStatement(
                        "select ph.no_pengenalan from pihak ph, hakmilik_pihak hp where "
                        + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? and rownum = 1");
                ps.setString(1, ocrs.getString(2));

                rs = ps.executeQuery();
                rs.next();

                ai.setNoAkaun(ocrs.getString(1));
                ai.setIdHakmilik(ocrs.getString(2));
                //ai.setNamaPemilik(ocrs.getString(14));
                ai.setNamaPembayar(ocrs.getString(3));
                //ai.setIcPembayar(ocrs.getString(4));
                ai.setIcPemilik(rs.getString(1));

                ai.setAlamat(ocrs.getString(5) + ", " + ocrs.getString(6) + ", " + ocrs.getString(7)
                        + ", " + ocrs.getString(8) + ", " + ocrs.getString(9) + ", " + ocrs.getString(10));

                ai.setIdHakmilik(ocrs.getString(11));
                ai.setJenisHakmilik(ocrs.getString(12));

                long bakiAkaun = ocrs.getLong(13);
                String searchCriteria, status;

                if (bakiAkaun > 0) { // have to pay cukai tanah
                    searchCriteria = "no_akaun_dt";
                    status = "BELUM BAYAR";

                } else { // already paid for that accountNo
                    searchCriteria = "no_akaun_kr";
                    status = "TELAH BAYAR";
                }

                // search the transaction for that accountNo
                ps = conn.prepareStatement(
                        "select kod_trans, amaun, id_kew_dok, trh_masuk from kew_trans where "
                        + searchCriteria + " = ?");
                ps.setString(1, accountNo);
                rs = ps.executeQuery();

                long cukaiSemasa = 0;
                long tunggakanCukai = 0;
                long dendaLewat = 0;
                BigDecimal remisyen = BigDecimal.ZERO;
                BigDecimal notis6a   = BigDecimal.ZERO;
                long jumlah = 0;

                idKewDok = "";
                Timestamp tarikhMasuk = null;

                while (rs.next()) {
                    String kodTrans = rs.getString(1);

                    if (kodTrans.equals("61401")) {
                        cukaiSemasa += rs.getLong(2);
                    } else if (kodTrans.equals("61402")) {
                        tunggakanCukai += rs.getLong(2);
                    } else if (kodTrans.equals("76152")) {
                        dendaLewat += rs.getLong(2);
                    } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
                            || kodTrans.equals("99002") || kodTrans.equals("99003")) {
                        remisyen.add(rs.getBigDecimal(2));
                    } else if (kodTrans.equals("99011")) { //ns = 99011, mlk = 72457
                        notis6a.add(rs.getBigDecimal(2));
                    }

                    idKewDok = rs.getString(3);
                    tarikhMasuk = rs.getTimestamp(4);
                }

                if (bakiAkaun <= 0) { // already paid for that accountNo
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                    ai.setTarikhBayaran(sdf.format(tarikhMasuk));
                    ai.setNoResit(idKewDok);
                }

//                jumlah = cukaiSemasa + tunggakanCukai + dendaLewat + remisyen + notis6a;

//                ai.setCukaiSemasa(cukaiSemasa);
//                ai.setTunggakanCukai(tunggakanCukai);
//                ai.setDendaLewat(dendaLewat);
                ai.setRemisyen(remisyen);
                ai.setNotis6a(notis6a);
//                ai.setJumlahBayaran(jumlah);
                ai.setStatusBayaran(status);

            } else {
                throwEmptyFaultException("getAccountDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("QuitRent.checkAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getAccountDetails");

        } catch (NullPointerException npe) {
            System.out.println("QuitRent.checkAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("getAccountDetails");
        }

        return ai;
    }

    
    public StatusInfo updateAccountByKutipan(String accountNo, long amaun, String idKewDok,
            String kodCaw, String kodCaraBayar, String noRuj, String kodBank, String bankCaw,
            String tarikhCek, List<BayarInfo> bayarList, List<TransaksiInfo> transList,
            List<LaporInfo> laporList) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.baki from kew_akaun ka where ka.no_akaun = ?");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                // insert kew_dok by idKewDok
                generateKewDokByKutipan(idKewDok, kodCaw, amaun, noRuj);

                String idKewDokBayar = "";

                for (BayarInfo bi : bayarList) {
                    java.util.Date trhCek = new java.util.Date();

                    if (!"".equals(bi.getTarikhCek())) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        trhCek = df.parse(bi.getTarikhCek().substring(0, 10));
                    }

                    String idCaraBayar = generateCaraBayarByKutipan(kodCaw, bi.getKodCaraBayar(),
                            bi.getAmaun(), bi.getNoRuj(), bi.getKodBank(),
                            bi.getBankCaw(), new Date(trhCek.getTime()));

                    idKewDokBayar = generateKewDokBayar(idKewDok, idCaraBayar, bi.getAmaun());
                }

                long bakiAkaun = ocrs.getLong(2);
                long telahBayar = 0;

                for (TransaksiInfo ti : transList) {
                    generateKewTransByKutipan(ti.getKodCaw(), ti.getIdKewDok(), ti.getKodTrans(),
                            ti.getUtkThn(), ti.getNoAkaunDt(), ti.getNoAkaunKr(), ti.getKntt(),
                            ti.getAmaun(), ti.getIdMohon(), ti.getKodStatus(), ti.getKodBatal(),
                            ti.getIdPos(), ti.getDimasuk(), ti.getTrhMasuk(), ti.getKodUrusan(),
                            ti.getPerihal(), ti.getThnKew());

                    telahBayar += ti.getAmaun();
                }

                for (LaporInfo li : laporList) {
                    generateLaporPnyataPmungut(li.getIdKewDok(), li.getIdTrans(), li.getIdKdb(),
                            li.getAmaun());
                }

                updateKewAkaun(accountNo, bakiAkaun - telahBayar);

                si.setFunctionName("updateAccountByKutipan");
                si.setStatusCode("SUCCESS");
                si.setStatusMessage("QuitRentService the account has been updated successfully");

                // release the connection
                db.releaseConnection(ps, rs, ocrs);

            } else {
                throwEmptyFaultException("updateAccountByKutipan");
            }

        } catch (SQLException se) {
            System.out.println("QuitRent.updateAccountByKutipan: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateAccountByKutipan");

        } catch (ParseException pe) {
            System.out.println("QuitRentAgent.updateAccountByKutipan: ParseException.");
            pe.printStackTrace();

            throwInternalFaultException("updateAccountByKutipan");
        }

        return si;
    }

    public StatusInfo updateAccountByAgency(String accountNo, long amaun, String noFPX, String noResit)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.baki, kt.kod_caw, kt.kod_trans, kt.amaun, "
                    + "kt.id_mohon, kt.kod_status, kt.kod_batal, kt.utk_thn, kt.kntt "
                    + "from kew_akaun ka, kew_trans kt where ka.no_akaun = ? and "
                    + "ka.no_akaun = kt.no_akaun_dt order by kt.utk_thn asc, (case "
                    + "kt.kod_trans when '61402' then 0 when '61401' then 1 "
                    + "when '76152' then 2 when '61014' then 3 when '61607' then 4 "
                    + "when '99000' then 5 when '99001' then 6 when '99002' then 7 "
                    + "when '99003' then 8 when '61018' then 9 end)");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                // amount should be the same from the portal
                /*if (ocrs.getLong(2) != amaun) {
                    throwValFaultException("updateAccount");
                }*/

                String kodCaw = ocrs.getString(3);
                String kodDaerah = "00";

                String tarikh = (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date());
//                String noRuj = "FPX" + tarikh;
                String noRuj = noFPX;
                String akaunKutip = "AGPYT";

                idKewDok = generateKewDokByAgency(kodCaw, kodDaerah, amaun, noRuj, noResit);
                String idCaraBayar = generateCaraBayar(kodCaw, amaun);
                String idKewDokBayar = generateKewDokBayar(idKewDok, idCaraBayar, amaun);

                long bakiAkaun = ocrs.getLong(2);
                long telahBayar = 0;

                ocrs.beforeFirst();

                while (ocrs.next()) {
                    // get transaction details
                    String kodTrans = ocrs.getString(4);
                    long amaunTrans = ocrs.getLong(5);
                    String idMohon = ocrs.getString(6);
                    String kodStatus = "T";
                    int utkThn = ocrs.getInt(9);
                    int kntt = ocrs.getInt(10);

                    String idKewTrans = generateKewTrans(kodCaw, kodTrans, amaunTrans,
                            idMohon, idKewDok, kodStatus, accountNo,
                            akaunKutip, utkThn, kntt);

                    generateLaporPnyataPmungut(idKewDok, idKewTrans, idKewDokBayar,
                            amaunTrans);

                    telahBayar += amaunTrans;
                }

                updateKewAkaun(accountNo, bakiAkaun - telahBayar);

                si.setFunctionName("updateAccount");
                si.setStatusCode("SUCCESS");
                si.setStatusMessage("QuitRentService the account has been updated successfully");

                // release the connection
                db.releaseConnection(ps, rs, ocrs);

            } else {
                throwEmptyFaultException("updateAccount");
            }

        } catch (SQLException se) {
            System.out.println("QuitRent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateAccount");
        }

        return si;
    }

    public StatusInfo updateAccount(String accountNo, long amaun) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.baki, kt.kod_caw, kt.kod_trans, kt.amaun, "
                    + "kt.id_mohon, kt.kod_status, kt.kod_batal, kt.utk_thn, kt.kntt "
                    + "from kew_akaun ka, kew_trans kt where ka.no_akaun = ? and "
                    + "ka.no_akaun = kt.no_akaun_dt order by kt.utk_thn asc, (case "
                    + "kt.kod_trans when '61402' then 0 when '61401' then 1 "
                    + "when '76152' then 2 when '61014' then 3 when '61607' then 4 "
                    + "when '99000' then 5 when '99001' then 6 when '99002' then 7 "
                    + "when '99003' then 8 when '61018' then 9 end)");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                // amount should be the same from the portal
                if (ocrs.getLong(2) != amaun) {
                    throwValFaultException("updateAccount");
                }

                String kodCaw = ocrs.getString(3);
                String kodDaerah = "00";

                String tarikh = (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date());
                String noRuj = "FPX" + tarikh;
                String akaunKutip = "AGPYT";

                String idKewDok = generateKewDok(kodCaw, kodDaerah, amaun, noRuj);
                String idCaraBayar = generateCaraBayar(kodCaw, amaun);
                String idKewDokBayar = generateKewDokBayar(idKewDok, idCaraBayar, amaun);

                long bakiAkaun = ocrs.getLong(2);
                long telahBayar = 0;

                ocrs.beforeFirst();

                while (ocrs.next()) {
                    // get transaction details
                    String kodTrans = ocrs.getString(4);
                    long amaunTrans = ocrs.getLong(5);
                    String idMohon = ocrs.getString(6);
                    String kodStatus = ocrs.getString(7);
                    int utkThn = ocrs.getInt(9);
                    int kntt = ocrs.getInt(10);

                    String idKewTrans = generateKewTrans(kodCaw, kodTrans, amaunTrans,
                            idMohon, idKewDok, kodStatus, accountNo,
                            akaunKutip, utkThn, kntt);

                    generateLaporPnyataPmungut(idKewDok, idKewTrans, idKewDokBayar,
                            amaunTrans);

                    telahBayar += amaunTrans;
                }

                updateKewAkaun(accountNo, bakiAkaun - telahBayar);

                si.setFunctionName("updateAccount");
                si.setStatusCode("SUCCESS");
                si.setStatusMessage("QuitRentService the account has been updated successfully");

                // release the connection
                db.releaseConnection(ps, rs, ocrs);

            } else {
                throwEmptyFaultException("updateAccount");
            }

        } catch (SQLException se) {
            System.out.println("QuitRent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateAccount");
        }

        return si;
    }

    public StatusInfo updateAccountCarian(long amaun, String noResit) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ka.no_akaun, ka.baki, ka.KOD_CAW from kew_akaun ka where ka.KOD_CAW = '00' and ka.KOD_AKAUN = 'AGPYT'");
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();





                // get transaction details
                long telahBayar = 0;
                String akaunKutip = null;
//                while (ocrs.next()) {
                    // get transaction details
                    String kodCaw = "00";
                    String kodDaerah = "00";
                    String tarikh = (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date());
                    String noRuj = "FPX" + tarikh;
                    akaunKutip = ocrs.getString(1);
                    idKewDok = generateKewDok(kodCaw, kodDaerah, amaun, noRuj);
                    String idCaraBayar = generateCaraBayar(kodCaw, amaun);
                    String idKewDokBayar = generateKewDokBayar(idKewDok, idCaraBayar, amaun);
                    String kodTrans = "30390";
                    long amaunTrans = amaun;
                    long baki = ocrs.getLong(2);
                    String idMohon = "";
                    String kodStatus = "T";
                    int utkThn = Calendar.YEAR;
                    int kntt = 1;

                    String idKewTrans = generateKewTrans(kodCaw, kodTrans, amaunTrans,
                            idMohon, idKewDok, kodStatus, "",
                            akaunKutip, utkThn, kntt);

//                    generateLaporPnyataPmungut(idKewDok, idKewTrans, idKewDokBayar,
//                            amaunTrans);

                    telahBayar = baki + amaunTrans;
//                }
                updateKewAkaun(akaunKutip, telahBayar);

                si.setFunctionName("updateAccount");
                si.setStatusCode("SUCCESS");
                si.setStatusMessage("QuitRentService the account has been updated successfully");

                // release the connection
                db.releaseConnection(ps, rs, ocrs);

            } else {
                throwEmptyFaultException("updateAccount");
            }

        } catch (SQLException se) {
            System.out.println("QuitRent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateAccount");
        }

        return si;
    }

    private void generateLaporPnyataPmungut(String idKewDok, String idTrans,
            String idKdb, long amaun) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idItem = getNextSequence("seq_lapor_pnyata_pmungut");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into lapor_pnyata_pmungut (id_item, id_kew_dok, id_trans, "
                    + "id_kdb, amaun, sts) values (?, ?, ?, ?, ?, ?)");

            ps.setInt(1, Integer.parseInt(idItem));
            ps.setString(2, idKewDok);
            ps.setInt(3, Integer.parseInt(idTrans));
            
            //ps.setString(3,idTrans);
            ps.setInt(4, Integer.parseInt(idKdb));
            ps.setLong(5, amaun);
            ps.setString(6, "A");

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateLaporPnyataPmungut: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateLaporPnyataPmungut");
        }
    }

    private String generateKewTrans(String kodCaw, String kodTrans, long amaun,
            String idMohon, String idKewDok, String kodStatus, String noAkaun,
            String akaunKutip, int utkThn, int kntt)
            throws AccountInfoFaultException {

        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idKewTrans = getNextSequence("seq_kew_trans");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_trans (id_trans, kod_caw, kod_trans, amaun, "
                    + "id_mohon, id_kew_dok, kod_status, dimasuk, trh_masuk, no_akaun_dt, "
                    + "no_akaun_kr, utk_thn, kntt, thn_kew) values (?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?)");

            ps.setString(1, idKewTrans);
            ps.setString(2, kodCaw);
            ps.setString(3, kodTrans);
            ps.setLong(4, amaun);
            ps.setString(5, idMohon);
            ps.setString(6, idKewDok);
            ps.setString(7, kodStatus);
            ps.setString(8, "portal");
            java.util.Date date = new java.util.Date();
            ps.setTimestamp(9, new Timestamp(date.getTime()));
            ps.setString(10, akaunKutip);
            ps.setString(11, noAkaun);
            ps.setInt(12, utkThn);
            ps.setInt(13, kntt);

            String thnKew = (new SimpleDateFormat("yyyy")).format(new java.util.Date());
            ps.setInt(14, Integer.parseInt(thnKew));

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewTrans: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewTrans");
        }

        return idKewTrans;
    }

    private String generateKewTransByKutipan(String kodCaw, String idKewDok, String kodTrans,
            int utkThn, String noAkaunDt, String noAkaunKr, int kntt, long amaun,
            String idMohon, String kodStatus, String kodBatal, int idPos, String dimasuk,
            String trhMasuk, String kodUrusan, String perihal, int thnKew)
            throws AccountInfoFaultException {

        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idKewTrans = getNextSequence("seq_kew_trans");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_trans (id_trans, kod_caw, id_kew_dok, kod_trans, "
                    + "utk_thn, no_akaun_dt, no_akaun_kr, kntt, amaun, id_mohon, "
                    + "kod_status, kod_batal, id_pos, dimasuk, trh_masuk, kod_urusan, "
                    + "perihal, thn_kew) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?)");

            ps.setString(1, idKewTrans);
            ps.setString(2, kodCaw);
            ps.setString(3, idKewDok);
            ps.setString(4, kodTrans);
            ps.setInt(5, utkThn);
            ps.setString(6, noAkaunDt);
            ps.setString(7, noAkaunKr);
            ps.setInt(8, kntt);
            ps.setLong(9, amaun);
            ps.setString(10, idMohon);
            ps.setString(11, kodStatus);
            ps.setString(12, kodBatal);
            ps.setInt(13, idPos);
            ps.setString(14, dimasuk);
            ps.setString(15, trhMasuk);
            ps.setString(16, kodUrusan);
            ps.setString(17, perihal);
            ps.setInt(18, thnKew);

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewTrans: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewTrans");
        }

        return idKewTrans;
    }

    private String generateKewDokBayar(String idKewDok, String idCaraBayar, long amaun)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idKewDokBayar = getNextSequence("seq_kew_dokumen_bayar");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_dokumen_bayar (id_kdb, id_kew_dok, id_cara_bayar, amaun, "
                    + "dimasuk, trh_masuk, aktif) values (?, ?, ?, ?, ?, ?, 'Y')");

            ps.setString(1, idKewDokBayar);
            ps.setString(2, idKewDok);
            ps.setString(3, idCaraBayar);
            ps.setLong(4, amaun);
            ps.setString(5, "portal");
            ps.setDate(6, new Date(new java.util.Date().getTime()));

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewDokBayar: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewDokBayar");
        }

        return idCaraBayar;
    }

    private void updateKewAkaun(String noAkaun, long baki) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "update kew_akaun set baki = ?, dikkini = ?, trh_kkini = ? where no_akaun = ?");

            ps.setLong(1, baki);
            ps.setString(2, "portal");
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            ps.setString(4, noAkaun);

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.updateKewAkaun: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateKewAkaun");
        }
    }

    private String generateCaraBayar(String kodCaw, long amaun)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idCaraBayar = getNextSequence("seq_cara_bayar");
        //String idCaraBayar = getNextSequence("SEQ_CARA_BAYAR1");
        //System.out.println("[SEQ_CARA_BAYAR1 Seq no :"+idCaraBayar+"] ...........");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into cara_bayar (id_cara_bayar, kod_caw, kod_cara_bayar, amaun, "
                    + "dimasuk, trh_masuk, aktif) values (?, ?, 'EP', ?, ?, ?, 'Y')");

            ps.setString(1, idCaraBayar);
            ps.setString(2, kodCaw);
            ps.setLong(3, amaun);
            ps.setString(4, "portal");
            ps.setDate(5, new Date(new java.util.Date().getTime()));

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateCaraBayar: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateCaraBayar");
        }

        return idCaraBayar;
    }

    private String generateCaraBayarByKutipan(String kodCaw, String kodCaraBayar, long amaun,
            String noRuj, String kodBank, String bankCaw, Date trhCek)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idCaraBayar = getNextSequence("seq_cara_bayar");

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into cara_bayar (id_cara_bayar, kod_caw, kod_cara_bayar, amaun, "
                    + "dimasuk, trh_masuk, aktif, no_ruj, kod_bank, bank_caw, trh_cek) values "
                    + "(?, ?, ?, ?, ?, ?, 'Y', ?, ?, ?, ?)");

            ps.setString(1, idCaraBayar);
            ps.setString(2, kodCaw);
            ps.setString(3, kodCaraBayar);
            ps.setLong(4, amaun);
            ps.setString(5, "kutipan");
            ps.setDate(6, new Date(new java.util.Date().getTime()));
            ps.setString(7, noRuj);
            ps.setString(8, kodBank);
            ps.setString(9, bankCaw);
            ps.setDate(10, trhCek);

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateCaraBayarByKutipan: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateCaraBayarByKutipan");
        }

        return idCaraBayar;
    }

    private String generateKewDok(String kodCaw, String kodDaerah, long amaun, String noRuj)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idKewDok = generateIdKewDok(kodDaerah, kodCaw);

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_dokumen (id_kew_dok, kod_caw, kod_dokumen, amaun_bayar, "
                    + "amaun_tunai, isu_kpd, kod_status, dimasuk, trh_masuk, no_ruj, id_kaunter, "
                    + "kod_kutip,kod_agensi_kutipan_caw) values (?, ?, 'RBY', ?, ?, ?, 'A', ?, ?, ?, ?, 'B',99)");

            ps.setString(1, idKewDok);
            ps.setString(2, kodCaw);
            ps.setLong(3, amaun);
            ps.setLong(4, amaun);
            ps.setString(5, "");
            ps.setString(6, "portal");
            ps.setDate(7, new Date(new java.util.Date().getTime()));
            ps.setString(8, noRuj);
            ps.setString(9, "");

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewDok: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewDok");
        }

        return idKewDok;
    }

    private String generateKewDokByKutipan(String idKewDok, String kodCaw, long amaun,
            String noRuj) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_dokumen (id_kew_dok, kod_caw, kod_dokumen, amaun_bayar, "
                    + "amaun_tunai, isu_kpd, kod_status, dimasuk, trh_masuk, no_ruj, id_kaunter, "
                    + "kod_kutip,kod_agensi_kutipan_caw) values (?, ?, 'RBY', ?, ?, ?, 'A', ?, ?, ?, ?, 'B',99)");

            ps.setString(1, idKewDok);
            ps.setString(2, kodCaw);
            ps.setLong(3, amaun);
            ps.setLong(4, amaun);
            ps.setString(5, "");
            ps.setString(6, "kutipan");
            ps.setDate(7, new Date(new java.util.Date().getTime()));
            ps.setString(8, noRuj);
            ps.setString(9, "");

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewDokByKutipan: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewDokByKutipan");
        }

        return idKewDok;
    }

    private String generateKewDokByAgency(String kodCaw, String kodDaerah, long amaun,
            String noRuj, String noResit) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String idKewDok = generateIdKewDok(kodDaerah, kodCaw);

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into kew_dokumen (id_kew_dok, kod_caw, kod_dokumen, amaun_bayar, "
                    + "amaun_tunai, isu_kpd, kod_status, dimasuk, trh_masuk, no_ruj, id_kaunter, "
                    + "kod_kutip, no_ruj_man,kod_agensi_kutipan_caw) values (?, ?, 'RBY', ?, ?, ?, 'A', "
                    + "?, ?, ?, ?, 'B', ?, ?)");

            ps.setString(1, idKewDok);//id_kew_dok
            ps.setString(2, kodCaw);//kod_caw
            ps.setLong(3, amaun);//amaun_bayar
            ps.setLong(4, amaun);//amaun_tunai
            ps.setString(5, "");//isu_kpd
            ps.setString(6, "portal");//dimasuk
            ps.setDate(7, new Date(new java.util.Date().getTime()));//trh_masuk
            ps.setString(8, noRuj);//no_ruj
            ps.setString(9, "");//id_kaunter
            ps.setString(10, "");//no_ruj_man
            ps.setString(11, "99");//kod_agensi_kutipan_caw

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRent.generateKewDokByAgency: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("generateKewDokByAgency");
        }

        return idKewDok;
    }

    private String generateIdKewDok(String kodDaerah, String kodCaw)
            throws AccountInfoFaultException {
        String tarikh = (new SimpleDateFormat("yyMMdd")).format(new java.util.Date());

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(4);
        df.setMinimumIntegerDigits(4);
        df.setGroupingUsed(false);

        //String kodDaerah = "05";
        String kaunter = "00"; // must reflect id kaunter for portal
        // String kodCaw = "05";

        String seqNo = df.format(Long.parseLong(getKewDokNextSequence()));

        String id = tarikh + kodDaerah + kodCaw + kaunter + seqNo;

        return id;
    }

    private String getKewDokNextSequence() throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String seqNo = "";

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select seq_kew_dokumen.nextval from dual");
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                seqNo = ocrs.getString(1);
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("QuitRent.getKewDokNextSequence: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getKewDokNextSequence");
        }

        return seqNo;
    }

    private String getNextSequence(String seqName) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        String seqNo = "";

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select " + seqName + ".nextval from dual");
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                seqNo = ocrs.getString(1);
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("QuitRent.getNextSequence: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getNextSequence");
        }

        return seqNo;
    }

    public StatusInfo insertBlob(byte[] s) throws IOException {
        StatusInfo si = new StatusInfo();
        try {

            StagingDB db = new StagingDB();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into portal_trans ("
                    + "id_portal_trans,"
                    + "jenis_trans,"
                    + "jenis_akaun,"
                    + "id_trans_asal,"
                    + "id_kew_dok,"
                    + "no_resit,"
                    + "trh_resit,"
                    + "no_trans_fpx,"
                    + "no_akaun,"
                    + "id_hakmilik,"
                    + "amaun,"
                    + "trh_masuk) values (?,?,?,?,?,?,?,?,?,?,?,?)");


            InputStream is = new ByteArrayInputStream(s);

            ps.setBinaryStream(1, is);

            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

            si.setFunctionName("updateAccount");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");


        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return si;
    }
}
