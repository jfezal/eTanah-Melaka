/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.agent;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.rowset.OracleCachedRowSet;

import com.mysql.jdbc.StringUtils;
import etanah.client.bayar.AccountInfo;
import etanah.client.bayar.ArrayOfAccountInfo;
import etanah.client.bayar.ArrayOfAkaunForm;
import etanah.client.bayar.ArrayOfSejarahCukai;
import etanah.client.bayar.BayaranOnline;
import etanah.client.bayar.BayaranOnlinePortType;
import etanah.client.bayar.ObjectFactory;
import etanah.client.carianenq.ArrayOfAnyType;
import etanah.client.carianenq.ArrayOfString;
import etanah.client.carianenq.CarianEnquiry;
import etanah.client.carianenq.CarianEnquiryPortType;
import etanah.ws.AkaunForm;
import etanah.ws.URLClient;

import etanah.ws.account.AccountService;
import etanah.ws.account.SejarahTransService;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.utility.MysqlDB;
import etanah.ws.utility.OracleDB;
import etanah.ws.utility.StagingDB;
import etanah.ws.utility.StatusInfo;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/**
 *
 * @author Izam
 */
public class AccountAgentService extends AccountService {

    boolean bakilebihan = false;

    public List<AccountHakmilikInfo> findHakmilikValid(String accountNo, String idHakmilik)
            throws AccountInfoFaultException {

        List<AccountHakmilikInfo> aHI = new ArrayList<AccountHakmilikInfo>();
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        try {
            // without kod_negeri kn selected (kn.nama, p.kod_negeri = kn.kod)
            PreparedStatement ps = conn.prepareStatement(
                    "select distinct ha1.ID_HAKMILIK, ka.NO_AKAUN, ha.ID_HAKMILIK  from hakmilik ha, hakmilik_sblm has, kew_akaun ka, hakmilik ha1 where ha.KOD_STS_HAKMILIK <> 'D' "
                    + "and ha.ID_HAKMILIK = ? and ha.ID_HAKMILIK = has.ID_HAKMILIK_SBLM and has.ID_HAKMILIK = ha1.ID_HAKMILIK and ha1.KOD_STS_HAKMILIK = 'D' and ha1.ID_HAKMILIK = ka.ID_HAKMILIK");
            ps.setString(1, idHakmilik);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AccountHakmilikInfo accHak = new AccountHakmilikInfo();
                accHak.setAccountNo(rs.getString(2));
                accHak.setIdHakmilik(rs.getString(1));
                accHak.setIdHakmilikBatal(idHakmilik);
                aHI.add(accHak);

            }
            // release the connection
            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("QuitRentAgent.checkAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("findHakmilikValid");

        } catch (NullPointerException npe) {
            System.out.println("QuitRentAgent.checkAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("findHakmilikValid");
        }

        return aHI;
    }

    public List<AccountInfoAgent> getAccountDetails(String accountNo, String idHakmilik)
            throws AccountInfoFaultException, ParseException, MalformedURLException {

//        String statusAkaun = null;
//        boolean flagBatal = false;
        List<AccountInfoAgent> Listaig = new ArrayList<AccountInfoAgent>();
//        List<AccountHakmilikInfo> listHakmilikInfo = new ArrayList<AccountHakmilikInfo>();
//
//        try {
//            OracleDB db = new OracleDB();
//            Connection conn = db.getConnection();
//            PreparedStatement ps2 = conn.prepareStatement(
//                    "select ka.ID_HAKMILIK, ka.no_akaun, ka.sts from kew_akaun ka where (ka.NO_AKAUN = ? or ka.id_hakmilik = ?) and ka.sts = 'A'");
//            ps2.setString(1, accountNo);
//            ps2.setString(2, idHakmilik);
//            ResultSet rs1 = ps2.executeQuery();
//            OracleCachedRowSet ocrs = new OracleCachedRowSet();
//            ocrs.populate(rs1);
//            if (db.getRowCount(ocrs) > 0) {
//
//                ocrs.beforeFirst();
//                ocrs.next();
//                idHakmilik = ocrs.getString(1);
//                accountNo = ocrs.getString(2);
//                statusAkaun = ocrs.getString(3);
//
//                // release the connection
//                db.releaseConnection(ps2, rs1);
//            } else {
//                PreparedStatement ps3 = conn.prepareStatement(
//                        "select ka.ID_HAKMILIK, ka.no_akaun, ka.sts from kew_akaun ka where ka.NO_AKAUN = ? or ka.id_hakmilik = ?");
//                ps3.setString(1, accountNo);
//                ps3.setString(2, idHakmilik);
//                ResultSet rs3 = ps3.executeQuery();
//                while (rs3.next()) {
//                    idHakmilik = rs3.getString(1);
//                    accountNo = rs3.getString(2);
//                    statusAkaun = rs3.getString(3);
//                }
//                // release the connection
//                db.releaseConnection(ps3, rs3);
//            }
//        } catch (SQLException ex) {
//        }
//        if (statusAkaun.equals("B")) {
//            flagBatal = true;
//        }
//        if (!flagBatal) {
//            AccountHakmilikInfo aci = new AccountHakmilikInfo();
//            aci.setAccountNo(accountNo);
//            aci.setIdHakmilik(idHakmilik);
//            listHakmilikInfo.add(aci);
//        }
//        if (flagBatal) {
//            listHakmilikInfo = findHakmilikValid(accountNo, idHakmilik);
//        }
//
//        for (AccountHakmilikInfo ahi : listHakmilikInfo) {
//            OracleDB db = new OracleDB();
//            Connection conn = db.getConnection();
//            AccountInfoAgent aig = new AccountInfoAgent();
//
//            try {
//                // without kod_negeri kn selected (kn.nama, p.kod_negeri = kn.kod)
//                PreparedStatement ps = conn.prepareStatement(
//                        "select ka.no_akaun, ka.id_hakmilik, p.nama, p.no_pengenalan, "
//                        + "p.surat_alamat1, p.surat_alamat2, p.surat_alamat3, p.surat_alamat4, p.surat_poskod, "
//                        + "ka.id_hakmilik, kh.nama, ka.baki, kd.nama, h.no_lot, "
//                        + "ku.nama, h.luas, kkt.nama, kgt.nama, b.nama as lokaliti, kh.nama as jenis_hakmilik, kc.kod, kc.NAMA, ksn.syarat, kl.nama "
//                        + "from kew_akaun ka, pihak p, "
//                        + "hakmilik h, kod_hakmilik kh, kod_daerah kd, kod_uom ku, "
//                        + "kod_katg_tanah kkt, kod_guna_tanah kgt, kod_bpm b, kod_caw kc, KOD_SYARAT_NYATA ksn, kod_lot kl   "
//                        + "where ka.no_akaun = ? and "
//                        + "ka.id_hakmilik = ? and ka.kod_akaun = 'AC' and ka.sts = 'A' and "
//                        + "p.id_pihak = ka.dipegang and h.id_hakmilik = ka.id_hakmilik and "
//                        + "h.kod_hakmilik = kh.kod(+) and h.kod_daerah = kd.kod(+) "
//                        + "and h.kod_uom = ku.kod(+) and h.kod_katg_tanah = kkt.kod(+) and "
//                        + "h.kod_guna_tanah = kgt.kod(+) and b.KOD = h.KOD_BPM(+) and h.KOD_CAW = kc.KOD(+) and h.kod_syarat_nyata = ksn.kod(+) and h.kod_lot = kl.kod(+) and h.KOD_STS_HAKMILIK = 'D'");
//                ps.setString(1, ahi.getAccountNo());
//                ps.setString(2, ahi.getIdHakmilik());
//                ResultSet rs = ps.executeQuery();
//
//                OracleCachedRowSet ocrs = new OracleCachedRowSet();
//                ocrs.populate(rs);
//
//                if (db.getRowCount(ocrs) > 0) {
//                    ocrs.beforeFirst();
//                    ocrs.next();
//
//                    /*ps = conn.prepareStatement(
//                     "select ph.no_pengenalan from pihak ph, hakmilik_pihak hp where "
//                     + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? order by "
//                     + "ph.id_pihak asc");
//                     ps.setString(1, idHakmilik);
//                     rs = ps.executeQuery();
//         
//                     boolean gotPemilik = false;
//                     String icPemilik = "";
//         
//                     while(rs.next()) {
//                     icPemilik = rs.getString(1);
//         
//                     if(icPemilik != null && icPemilik.equals(icNo)) {
//                     gotPemilik = true;
//                     break;
//                     }
//                     }
//         
//                     if(!gotPemilik) {
//                     db.releaseConnection(ps, rs, ocrs);
//                     throwEmptyFaultException("getAccountDetails");
//                     }*/
//                    ps = conn.prepareStatement(
//                            "select ph.no_pengenalan from pihak ph, HAKMILIK_PIHAK hp where "
//                            + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? and rownum = 1");
//                    ps.setString(1, ocrs.getString(2));
//
//                    rs = ps.executeQuery();
//                    rs.next();
//
//                    aig.setNoAkaun(ocrs.getString(1));
//                    aig.setIdHakmilik(ocrs.getString(2));
//                    aig.setNamaPembayar(ocrs.getString(3));
//                    aig.setIcPemilik(ocrs.getString(4));
//                    aig.setIdHakmilikBatal(ahi.getIdHakmilikBatal());
//
//                    String alamat = trimAlamat(ocrs.getString(5), ocrs.getString(6),
//                            ocrs.getString(7), ocrs.getString(8), ocrs.getString(9));
//
//                    aig.setAlamat(alamat);
//
//                    aig.setIdHakmilik(ocrs.getString(10));
//                    aig.setJenisHakmilik(ocrs.getString(20));
//                    aig.setLokaliti(ocrs.getString(19));
//                    System.out.print("lokaliti : " + ocrs.getString(19));
//                    BigDecimal bakiAkaun = ocrs.getBigDecimal(12).setScale(2);
//                    String searchCriteria, status;
//
//                    if (bakiAkaun.equals(BigDecimal.ZERO.setScale(2)) || isNegative(bakiAkaun)) { // already paid for that accountNo
//                        searchCriteria = "no_akaun_dt";
//                        status = "TELAH BAYAR";
//                        if (isNegative(bakiAkaun)) {
//                            bakilebihan = true;
//                        } else {
//                            bakilebihan = false;
//                        }
//                    } else { // have to pay cukai tanah
//                        searchCriteria = "no_akaun_dt";
//                        status = "BELUM BAYAR";
//
//                    }
//
//                    // search the transaction for that accountNo
//                    ps = conn.prepareStatement(
//                            "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
//                            + "from kew_trans where "
//                            + searchCriteria + " = ?");
//                    ps.setString(1, ahi.getAccountNo());
//                    rs = ps.executeQuery();
//
//                    BigDecimal cukaiSemasaTanah = BigDecimal.ZERO.setScale(2);
//                    BigDecimal tunggakanCukaiTanah = BigDecimal.ZERO.setScale(2);
//                    BigDecimal dendaLewatSemasa = BigDecimal.ZERO.setScale(2);
//                    BigDecimal remisyen = BigDecimal.ZERO.setScale(2);
//                    BigDecimal notis6a = BigDecimal.ZERO.setScale(2);
//                    BigDecimal jumlah = BigDecimal.ZERO.setScale(2);
//                    BigDecimal jumlahCukaiSemasa = BigDecimal.ZERO.setScale(2);
//                    BigDecimal jumlahTunggakan = BigDecimal.ZERO.setScale(2);
//                    BigDecimal cukaiSemasaParit = BigDecimal.ZERO.setScale(2);
//                    BigDecimal tunggakanCukaiParit = BigDecimal.ZERO.setScale(2);
//                    BigDecimal tunggakanDendaLewat = BigDecimal.ZERO.setScale(2);
//                    BigDecimal kreditDebit = BigDecimal.ZERO.setScale(2);
//
//                    String idKewDok = "";
//                    Timestamp tarikhMasuk = null;
//                    String utkThn = "";
//                    int currYear = 0;
//                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
//                    System.out.println("tahun :: " + sdf1.format(new Date()));
//                    int recThn = 0;
//                    while (rs.next()) {
//                        String kodTrans = rs.getString(1);
//                        recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;
//                        currYear = Integer.parseInt(sdf1.format(new Date()));
//
//                        if (kodTrans.equals("61401")) {
//                            cukaiSemasaTanah = cukaiSemasaTanah.add(rs.getBigDecimal(2));//cukaisemasaTanah
//
//                        } else if (kodTrans.equals("61402")) {
//                            tunggakanCukaiTanah = tunggakanCukaiTanah.add(rs.getBigDecimal(2));//tunggakan dari tahun cukai tanah
////                            PreparedStatement ps6 = conn.prepareStatement(
////                                    "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
////                                    + "from kew_trans where no_akaun_kr = ? and kod_trans = '61401' and utk_thn = ?");
////                            ps6.setString(1, ahi.getAccountNo());
////                            ps6.setInt(2, recThn);
////                            ResultSet rs6 = ps6.executeQuery();
////                            while (rs6.next()) {
////                                tunggakanCukaiTanah = tunggakanCukaiTanah.subtract(rs6.getBigDecimal(2));
////                            }
//
//                        } else if (kodTrans.equals("76152") && String.valueOf(recThn).equals(sdf1.format(new Date()))) {
//                            dendaLewatSemasa = dendaLewatSemasa.add(rs.getBigDecimal(2));//dendaSemasa
//                        } else if (kodTrans.equals("76152") && !String.valueOf(recThn).equals(sdf1.format(new Date()))) {
//                            tunggakanDendaLewat = tunggakanDendaLewat.add(rs.getBigDecimal(2));//tunggakan denda lewat
//                        } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
//                                || kodTrans.equals("99002") || kodTrans.equals("99003") || kodTrans.equals("99030")) {
//                            remisyen = remisyen.add(rs.getBigDecimal(2));//remisyen
//                            aig.setKodRemisyen(kodTrans);
//                        } else if (kodTrans.equals("72457")) {
//                            notis6a = notis6a.add(rs.getBigDecimal(2));//notis 6A
//                        } else if (kodTrans.equals("61601")) {
//                            cukaiSemasaParit = cukaiSemasaParit.add(rs.getBigDecimal(2));//cukai Semasa Parit
//                        } else if (kodTrans.equals("61602")) {
//                            tunggakanCukaiParit = tunggakanCukaiParit.add(rs.getBigDecimal(2));//tunggakan Cukai Parit
//                        } else if (kodTrans.equals("61611")) {
//                        }
//
//                        idKewDok = rs.getString(3);
//                        tarikhMasuk = rs.getTimestamp(4);
//
//                        if (StringUtils.isEmptyOrWhitespaceOnly(utkThn)) {
//                            if (recThn != currYear) {
//                                utkThn = String.valueOf(recThn);
//                            } else {
//                                utkThn = "";
//                            }
//                        } else {
//                            if (Integer.valueOf(utkThn) < recThn) {
//                                utkThn = String.valueOf(recThn);
//                            }
//
//                        }
//
//                    }
//
//                    if (bakilebihan) {
//                        kreditDebit = bakiAkaun;
//                    }
////
////                    if (!tunggakanCukaiTanah.equals(BigDecimal.ZERO)) {
////                        ps = conn.prepareStatement(
////                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
////                                + "from kew_trans where no_akaun_dt = ? and kod_trans = ?");
////                        ps.setString(1, ahi.getAccountNo());
////                        ps.setString(2, "61402");
////                        rs = ps.executeQuery();
////                        while (rs.next()) {
////                            tunggakanCukaiTanah = tunggakanCukaiTanah.subtract(rs.getBigDecimal(2));
////                        }
////
////                    }
////                    if (!tunggakanDendaLewat.equals(BigDecimal.ZERO)) {
////                               ps = conn.prepareStatement(
////                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
////                                + "from kew_trans where no_akaun_dt = ? and kod_trans = ?");
////                        ps.setString(1, ahi.getAccountNo());
////                        ps.setString(2, "61402");
////                        rs = ps.executeQuery();
////                        while (rs.next()) {
////                            tunggakanCukaiTanah = tunggakanCukaiTanah.subtract(rs.getBigDecimal(2));
////                        }
////                    }
//
//                    if (!bakiAkaun.equals(cukaiSemasaTanah)) {
//                        ps = conn.prepareStatement(
//                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
//                                + "from kew_trans where no_akaun_kr = ? and kod_trans in ('99001','99002','99003','99030')");
//                        ps.setString(1, ahi.getAccountNo());
//
//                        rs = ps.executeQuery();
//                        BigDecimal rem = new BigDecimal(BigInteger.ZERO).setScale(2);
//                        BigDecimal rem2 = new BigDecimal(BigInteger.ZERO).setScale(2);
//                        while (rs.next()) {
//                            currYear = Integer.parseInt(sdf1.format(new Date()));
//                            if (currYear == Integer.parseInt(rs.getString(5))) {
//                                rem = rem.add(rs.getBigDecimal(2));
//                            } else {
//                                rem2 = rem2.add(rs.getBigDecimal(2));
//                            }
//                            aig.setKodRemisyen(rs.getString(1));
//                        }
//                        cukaiSemasaTanah = cukaiSemasaTanah.subtract(rem);
//                        tunggakanCukaiTanah = tunggakanCukaiTanah.subtract(rem2);
//                        //jumlahCukaiSemasa = jumlahCukaiSemasa.subtract(rem);
//                        remisyen = rem.add(rem2);
//                        aig.setRemisyen(remisyen);
//
////                        ps = conn.prepareStatement(
////                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
////                                + "from kew_trans where no_akaun_kr = ? and kod_trans in ('99001','99002','99003','99030')");
////                        ps.setString(1, ahi.getAccountNo());
////
////                        rs = ps.executeQuery();
////                        
////                        while (rs.next()) {
////                            int currYear = Integer.parseInt(sdf1.format(new Date()));
////                            if (currYear == Integer.parseInt(rs.getString(5))) {
////                                rem = rem.add(rs.getBigDecimal(2));
////                            } else {
////                                rem2 = rem2.add(rs.getBigDecimal(2));
////                            }
////
////                        }
//                    }
//
//                    if (bakiAkaun.equals(0) || isNegative(bakiAkaun)) { // already paid for that accountNo
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//                        try {
//                            aig.setTarikhBayaran(sdf.format(tarikhMasuk));
//                        } catch (Exception e) {
//                            aig.setTarikhBayaran("");
//                        }
//                        aig.setNoResit(idKewDok);
//                    }
//
//                    //rebalance
//                    jumlahCukaiSemasa = cukaiSemasaTanah.add(cukaiSemasaParit);
//                    jumlahTunggakan = tunggakanCukaiTanah.add(tunggakanCukaiParit);
//                    if (!jumlahCukaiSemasa.equals(BigDecimal.ZERO.setScale(2))) {
//                        PreparedStatement ps6 = conn.prepareStatement(
//                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
//                                + "from kew_trans where no_akaun_kr = ? and kod_trans = '61401' and utk_thn  = ?");
//                        ps6.setString(1, ahi.getAccountNo());
//                        ps6.setInt(2, currYear);
//                        ResultSet rs6 = ps6.executeQuery();
//                        while (rs6.next()) {
//                            jumlahCukaiSemasa = jumlahCukaiSemasa.subtract(rs6.getBigDecimal(2));
//                            cukaiSemasaTanah = cukaiSemasaTanah.subtract(rs6.getBigDecimal(2));
//                        }
//                    }
//
//                    if (!jumlahTunggakan.equals(BigDecimal.ZERO.setScale(2))) {
//                        PreparedStatement ps6 = conn.prepareStatement(
//                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
//                                + "from kew_trans where no_akaun_kr = ? and (kod_trans = '61401' or kod_trans = '61402') and utk_thn  != ?");
//                        ps6.setString(1, ahi.getAccountNo());
//                        ps6.setInt(2, currYear);
//                        ResultSet rs6 = ps6.executeQuery();
//                        while (rs6.next()) {
//                            jumlahTunggakan = jumlahTunggakan.subtract(rs6.getBigDecimal(2));
//                            tunggakanCukaiTanah = tunggakanCukaiTanah.subtract(rs6.getBigDecimal(2));
//                        }
//                    }
//
//                    if (!dendaLewatSemasa.add(tunggakanDendaLewat).equals(BigDecimal.ZERO.setScale(2))) {
//                        PreparedStatement ps6 = conn.prepareStatement(
//                                "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
//                                + "from kew_trans where no_akaun_kr = ? and kod_trans = '76152'");
//                        ps6.setString(1, ahi.getAccountNo());
////                            ps6.setInt(2, currYear);
//                        ResultSet rs6 = ps6.executeQuery();
//                        while (rs6.next()) {
//                            dendaLewatSemasa = dendaLewatSemasa.subtract(rs6.getBigDecimal(2));
//                        }
//                    }
//
//                    jumlah = jumlahCukaiSemasa.add(jumlahTunggakan).add(dendaLewatSemasa).add(tunggakanDendaLewat).add(notis6a);
//                    // set kod hasil
//                    aig.setKodCukaiSemasa("61401");
//                    aig.setKodTunggakanCukai("61402");
//                    aig.setKodDendaLewat("76152");
//                    aig.setKodTunggakanDendaLewat("76152");
//                    aig.setKodCukaiSemasaParit("61601");
//                    aig.setKodTunggakanCukaiParit("61602");
////                    aig.setKodKreditDebit("61611");
//                    aig.setKodNotis6a("72457");
//                    aig.setKodCarianpersendirian("72488");
//
//                    aig.setCukaiSemasaTanah(cukaiSemasaTanah);//cukaisemasaTanah
//                    aig.setTunggakanCukaiTanah(tunggakanCukaiTanah);//tunggakan dari tahun cukai tanah
//                    aig.setDendaLewatSemasa(dendaLewatSemasa);//dendaSemasa
//                    aig.setTunggakanDendaLewat(tunggakanDendaLewat); //tunggakan denda lewat
//                    aig.setRemisyen(BigDecimal.ZERO); //remisyen
//                    aig.setNotis6a(notis6a);//notis 6A
//                    aig.setCukaiSemasaParit(cukaiSemasaParit); //cukai Semasa Parit
//                    aig.setTunggakanCukaiParit(tunggakanCukaiParit);//tunggakan Cukai Parit
//                    aig.setKodCaw(ocrs.getString(21));
//                    aig.setKodCawValue(ocrs.getString(22));
//                    aig.setSyaratNyata(ocrs.getString(23));
//                    aig.setStatusAkaun("Aktif");
//                    if (aig.getKodCaw().equals("00")) {
//                        aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Galian ");
//                    } else {
//                        aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Daerah");
//                    }
//
////         aig.setCukaiSemasa(cukaiSemasa);
////         aig.setTunggakanCukai(tunggakanCukai);
////         aig.setDendaLewat(dendaLewat);
////         aig.setRemisyen(remisyen);
////         aig.setNotis6a(notis6a);
//                    aig.setJumlahCukaiSemasa(jumlahCukaiSemasa);
//                    aig.setJumlahTunggakan(jumlahTunggakan);
//                    aig.setJumlahBayaran(bakiAkaun);
//                    aig.setStatusBayaran(status);
//                    aig.setKreditDebit(kreditDebit);
//
//                    aig.setBakiAkaun(bakiAkaun);
//
//                    // additional fields for bil cukai
//                    aig.setDaerah(ocrs.getString(13));
//                    aig.setTahun(utkThn);
//                    aig.setNoLot(ocrs.getString(24) + " " + ocrs.getString(14));
//                    aig.setKeluasan(ocrs.getString(16) + " " + ocrs.getString(15));
//                    aig.setKategori(ocrs.getString(17));
//                    aig.setKegunaan(ocrs.getString(18));
//                    aig.setJumlahPecahan(jumlah);
//                    aig.setTunggakanTahun(utkThn); // will decide later
//                    aig.setLebihan(BigDecimal.ZERO.setScale(2)); // will decide later
//                    List<SejarahCukai> listSejarahCukai = new ArrayList<SejarahCukai>();
//                    SejarahTransService sejService = new SejarahTransService();
//                    listSejarahCukai = sejService.findSejarahTrans(accountNo);
//                    aig.setListSejarahCukai(listSejarahCukai);
//                    Listaig.add(aig);
//                } else {
//                    db.releaseConnection(ps, rs, ocrs);
//                    List<AccountHakmilikInfo> aHI = findHakmilikValid(accountNo, idHakmilik);
//                    if (aHI != null && aHI.size() > 0) {
//                        aig.setAkaunBaru("Y");
//                    } else {
//                        throwEmptyFaultException("getAccountDetails");
//                    }
//
//                }
//
//                // release the connection
//                db.releaseConnection(ps, rs, ocrs);
//
//            } catch (SQLException se) {
//                System.out.println("QuitRentAgent.checkAccount: SQLException.");
//                se.printStackTrace();
//
//                throwInternalFaultException("getAccountDetails");
//
//            } catch (NullPointerException npe) {
//                System.out.println("QuitRentAgent.checkAccount: NullPointerException.");
//                npe.printStackTrace();
//
//                throwInternalFaultException("getAccountDetails");
//            }
//        }
//
//        if (listHakmilikInfo.isEmpty()) {
//            try {
//                OracleDB db = new OracleDB();
//                Connection conn = db.getConnection();
//                PreparedStatement ps2 = conn.prepareStatement(
//                        "select ka.ID_HAKMILIK, ka.no_akaun, ka.sts from kew_akaun ka where ka.NO_AKAUN = ? or ka.id_hakmilik = ? and ka.STS = 'B'");
//                ps2.setString(1, accountNo);
//                ps2.setString(2, idHakmilik);
//                ResultSet rs1 = ps2.executeQuery();
//                while (rs1.next()) {
//                    idHakmilik = rs1.getString(1);
//                    accountNo = rs1.getString(2);
//                    statusAkaun = rs1.getString(3);
//                }
//                // release the connection
//                db.releaseConnection(ps2, rs1);
//
//            } catch (SQLException ex) {
//            }
//            AccountInfoAgent aig = new AccountInfoAgent();
//            Listaig = new ArrayList<AccountInfoAgent>();
//            aig.setNoAkaun(accountNo);
//            aig.setIdHakmilik(idHakmilik);
//            aig.setBakiAkaun(BigDecimal.ZERO.setScale(2));
//            aig.setStatusAkaun("Batal");
//            Listaig.add(aig);
//
//        }
//
//        if (Listaig.size() == 1) {
//            if (Listaig.get(0).getBakiAkaun().equals(BigDecimal.ZERO.setScale(2)) || isNegative(Listaig.get(0).getBakiAkaun())) {
//
//            } else {
//                System.out.print("debug:" + Listaig.get(0).getBakiAkaun() + "" + Listaig.get(0).getJumlahPecahan());
//                if (!Listaig.get(0).getBakiAkaun().equals(Listaig.get(0).getJumlahPecahan())) {
//                    throwValFaultException(statusAkaun);
//                }
//            }
//        }
        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        BayaranOnline service12 = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType conn = service12.getBayaranOnlineHttpPort();
        ArrayOfAccountInfo arr = conn.checkAccount(accountNo, idHakmilik);
        for (int i = 0; i < arr.getAccountInfo().size(); i++) {
            AccountInfo info = arr.getAccountInfo().get(i);
            AccountInfoAgent aig = new AccountInfoAgent();
            aig.setNoAkaun(info.getNoAkaun().getValue());
            aig.setIdHakmilik(info.getIdHakmilik().getValue());
            aig.setNamaPembayar(info.getNamaPembayar().getValue());
            aig.setIcPemilik(info.getIcPemilik().getValue());
//                    aig.setIdHakmilikBatal(info.geti);
            aig.setAlamat(info.getAlamat().getValue());
            aig.setJenisHakmilik(info.getJenisHakmilik().getValue());
            aig.setLokaliti(info.getLokaliti().getValue());
            aig.setKodRemisyen(info.getKodRemisyen().getValue());
            aig.setTarikhBayaran(info.getTarikhBayaran().getValue());
            aig.setNoResit(info.getNoResit().getValue());
            aig.setKodCukaiSemasa("61401");
            aig.setKodTunggakanCukai("61402");
            aig.setKodDendaLewat("76152");
            aig.setKodTunggakanDendaLewat("76152");
            aig.setKodCukaiSemasaParit("61601");
            aig.setKodTunggakanCukaiParit("61602");
//                    aig.setKodKreditDebit("61611");
            aig.setKodNotis6a("72457");
            aig.setKodCarianpersendirian("72488");

            aig.setCukaiSemasaTanah(info.getCukaiSemasaTanah().getValue());//cukaisemasaTanah
            aig.setTunggakanCukaiTanah(info.getTunggakanCukaiTanah().getValue());//tunggakan dari tahun cukai tanah
            aig.setDendaLewatSemasa(info.getDendaLewatSemasa().getValue());//dendaSemasa
            aig.setTunggakanDendaLewat(info.getTunggakanDendaLewat().getValue()); //tunggakan denda lewat
            aig.setRemisyen(BigDecimal.ZERO); //remisyen
            aig.setNotis6a(info.getNotis6A().getValue());//notis 6A
            aig.setCukaiSemasaParit(info.getCukaiSemasaParit().getValue()); //cukai Semasa Parit
            aig.setTunggakanCukaiParit(info.getTunggakanCukaiParit().getValue());//tunggakan Cukai Parit
            aig.setKodCaw(info.getKodCaw().getValue());
            aig.setKodCawValue(info.getKodCawValue().getValue());
            aig.setSyaratNyata(info.getSyaratNyata().getValue());
            aig.setStatusAkaun(info.getStatusAkaun().getValue());
            if (aig.getKodCaw().equals("00")) {
                aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Galian ");
            } else {
                aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Daerah");
            }

            aig.setJumlahCukaiSemasa(info.getJumlahCukaiSemasa().getValue());
            aig.setJumlahTunggakan(info.getJumlahTunggakan().getValue());
            aig.setJumlahBayaran(info.getJumlahBayaran().getValue());
            aig.setStatusBayaran(info.getStatusBayaran().getValue());
            aig.setKreditDebit(info.getKreditDebit().getValue());

            aig.setBakiAkaun(info.getBakiAkaun().getValue());

            // additional fields for bil cukai
            aig.setDaerah(info.getDaerah().getValue());
            aig.setTahun(info.getTahun().getValue());
            aig.setNoLot(info.getNoLot().getValue());
            aig.setKeluasan(info.getKeluasan().getValue());
            aig.setKategori(info.getKategori().getValue());
            aig.setKegunaan(info.getKegunaan().getValue());
            aig.setJumlahPecahan(info.getJumlahBayaran().getValue());
            aig.setTunggakanTahun(info.getTunggakanTahun().getValue()); // will decide later
            aig.setLebihan(BigDecimal.ZERO.setScale(2)); // will decide later
            List<SejarahCukai> listSejarahCukai = new ArrayList<SejarahCukai>();
            ArrayOfSejarahCukai sejc = info.getListSejarahCukai().getValue();
            for(int t=0;t<sejc.getSejarahCukai().size();t++){
                etanah.client.bayar.SejarahCukai sc = sejc.getSejarahCukai().get(t);
                SejarahCukai si = new SejarahCukai();
                si.setAmaun(sc.getAmaun().getValue());
                si.setKaedahBayaran(sc.getKaedahBayaran().getValue());
                si.setNoResit(sc.getNoResit().getValue());
                si.setPusatKutipan(sc.getPusatKutipan().getValue());
                si.setTahun(sc.getTahun().getValue());
                XMLGregorianCalendar cal = sc.getTarikh();
                if(cal!=null){
                si.setTarikh(cal.toGregorianCalendar().getTime());
                }
                listSejarahCukai.add(si);
            }
//            SejarahTransService sejService = new SejarahTransService();
//            listSejarahCukai = sejService.findSejarahTrans(accountNo);
            aig.setListSejarahCukai(listSejarahCukai);

            Listaig.add(aig);
        }
        return Listaig;
    }


    /*
     public AccountInfoAgent getAccountDetails(String accountNo, String idHakmilik)
     throws AccountInfoFaultException {
     OracleDB db = new OracleDB();
     Connection conn = db.getConnection();

     AccountInfoAgent aig = new AccountInfoAgent();

     try {
     // without kod_negeri kn selected (kn.nama, p.kod_negeri = kn.kod)
     PreparedStatement ps = conn.prepareStatement(
     "select ka.no_akaun, ka.id_hakmilik, p.nama, p.no_pengenalan, "
     + "p.alamat1, p.alamat2, p.alamat3, p.alamat4, p.poskod, "
     + "ka.id_hakmilik, kh.nama, ka.baki, kd.nama, h.no_lot, "
     + "ku.nama, h.luas, kkt.nama, kgt.nama from kew_akaun ka, pihak p, "
     + "hakmilik h, kod_hakmilik kh, kod_daerah kd, kod_uom ku, "
     + "kod_katg_tanah kkt, kod_guna_tanah kgt "
     + "where ka.no_akaun = ? and "
     + "ka.id_hakmilik = ? and ka.kod_akaun = 'AC' and "
     + "p.id_pihak = ka.dipegang and h.id_hakmilik = ka.id_hakmilik and "
     + "h.kod_hakmilik = kh.kod and h.kod_daerah = kd.kod "
     + "and h.kod_uom = ku.kod and h.kod_katg_tanah = kkt.kod and "
     + "h.kod_guna_tanah = kgt.kod");
     ps.setString(1, accountNo);
     ps.setString(2, idHakmilik);
     ResultSet rs = ps.executeQuery();

     OracleCachedRowSet ocrs = new OracleCachedRowSet();
     ocrs.populate(rs);

     if (db.getRowCount(ocrs) > 0) {
     ocrs.beforeFirst();
     ocrs.next();

     /*ps = conn.prepareStatement(
     "select ph.no_pengenalan from pihak ph, hakmilik_pihak hp where "
     + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? order by "
     + "ph.id_pihak asc");
     ps.setString(1, idHakmilik);
     rs = ps.executeQuery();
                
     boolean gotPemilik = false;
     String icPemilik = "";
                
     while(rs.next()) {
     icPemilik = rs.getString(1);
                
     if(icPemilik != null && icPemilik.equals(icNo)) {
     gotPemilik = true;
     break;
     }
     }
                
     if(!gotPemilik) {
     db.releaseConnection(ps, rs, ocrs);
     throwEmptyFaultException("getAccountDetails");
     }  // have to comment

     ps = conn.prepareStatement(
     "select ph.no_pengenalan from pihak ph, hakmilik_pihak hp where "
     + "ph.id_pihak = hp.id_pihak and hp.id_hakmilik = ? and rownum = 1");
     ps.setString(1, ocrs.getString(2));

     rs = ps.executeQuery();
     rs.next();

     aig.setNoAkaun(ocrs.getString(1));
     aig.setIdHakmilik(ocrs.getString(2));
     aig.setNamaPembayar(ocrs.getString(3));
     aig.setIcPemilik(rs.getString(1));

     String alamat = trimAlamat(ocrs.getString(5), ocrs.getString(6),
     ocrs.getString(7), ocrs.getString(8), ocrs.getString(9));

     aig.setAlamat(alamat);

     aig.setIdHakmilik(ocrs.getString(10));
     aig.setJenisHakmilik(ocrs.getString(11));

     long bakiAkaun = ocrs.getLong(12);
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
     "select kod_trans, amaun, id_kew_dok, trh_masuk, utk_thn "
     + "from kew_trans where "
     + searchCriteria + " = ?");
     ps.setString(1, accountNo);
     rs = ps.executeQuery();

     long cukaiSemasa = 0;
     long tunggakanCukai = 0;
     long dendaLewat = 0;
     long remisyen = 0;
     long notis6a = 0;
     long jumlah = 0;
     long cukaiSemasaParit = 0;
     long tunggakanCukaiParit = 0;
                
     String idKewDok = "";
     Timestamp tarikhMasuk = null;
     String utkThn = "";

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
     aig.setKodRemisyen(kodTrans);
     } else if (kodTrans.equals("61018")) {
     notis6a += rs.getLong(2);
     }else if (kodTrans.equals("61601")) {
     cukaiSemasaParit += rs.getLong(2);
     }else if (kodTrans.equals("61602")) {
     tunggakanCukaiParit += rs.getLong(2);
     }else if (kodTrans.equals("61018")) {
     notis6a += rs.getLong(2);
     }

     idKewDok = rs.getString(3);
     tarikhMasuk = rs.getTimestamp(4);
     utkThn = rs.getString(5);
     }

     if (bakiAkaun <= 0) { // already paid for that accountNo
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

     aig.setTarikhBayaran(sdf.format(tarikhMasuk));
     aig.setNoResit(idKewDok);
     }

     jumlah = cukaiSemasa + tunggakanCukai + dendaLewat + remisyen + notis6a;

     // set kod hasil
     aig.setKodCukaiSemasa("61401");
     aig.setKodTunggakanCukai("61402");
     aig.setKodDendaLewat("76152");

     //                List<String> kodRemisyenList = new LinkedList<String>();
     //                kodRemisyenList.add("99000");
     //                kodRemisyenList.add("99001");
     //                kodRemisyenList.add("99002");
     //                kodRemisyenList.add("99003");
     //                aig.setKodRemisyenList(kodRemisyenList);
     aig.setKodNotis6a("61018");

     aig.setCukaiSemasa(cukaiSemasa);
     aig.setTunggakanCukai(tunggakanCukai);
     aig.setDendaLewat(dendaLewat);
     aig.setRemisyen(remisyen);
     aig.setNotis6a(notis6a);
     aig.setJumlahBayaran(jumlah);
     aig.setStatusBayaran(status);

     aig.setBakiAkaun(bakiAkaun);

     // additional fields for bil cukai
     aig.setDaerah(ocrs.getString(13));
     aig.setTahun(utkThn);
     aig.setNoLot(ocrs.getString(14));
     aig.setKeluasan(ocrs.getString(16) + " " + ocrs.getString(15));
     aig.setKategori(ocrs.getString(17));
     aig.setKegunaan(ocrs.getString(18));
     aig.setTunggakanTahun(""); // will decide later
     aig.setLebihan(BigDecimal.ZERO); // will decide later

     } else {
     db.releaseConnection(ps, rs, ocrs);
     throwEmptyFaultException("getAccountDetails");
     }

     // release the connection
     db.releaseConnection(ps, rs, ocrs);

     } catch (SQLException se) {
     System.out.println("QuitRentAgent.checkAccount: SQLException.");
     se.printStackTrace();

     throwInternalFaultException("getAccountDetails");

     } catch (NullPointerException npe) {
     System.out.println("QuitRentAgent.checkAccount: NullPointerException.");
     npe.printStackTrace();

     throwInternalFaultException("getAccountDetails");
     }

     return aig;
     }

     */
    public static boolean isNegative(BigDecimal b) {
        boolean tru = false;
        if (b.signum() == -1) {
            tru = true;
        } else {
            tru = false;
        }
        return tru;
    }

    private String trimAlamat(String alamat1, String alamat2, String alamat3,
            String alamat4, String poskod) {
        String completeAddress = "";

        String fragments[] = {alamat1, alamat2, alamat3, alamat4, poskod};

        for (String frag : fragments) {
            if (frag != null && !(frag.equals(""))) {
                // remove comma (,) from the fragments if have
                if (frag.charAt(frag.length() - 1) == ',') {
                    frag = frag.substring(0, frag.length() - 1);
                }

                // trim it to make it nice
                frag.trim();

                // combines all
                if (completeAddress.equals("")) {
                    completeAddress += frag;
                } else {
                    completeAddress += ", " + frag;
                }
            }
        }

        return completeAddress;
    }

    public StatusInfo updateAccount(String accountNo, long amaun, String noFPX, String noResit)
            throws AccountInfoFaultException {
        return super.updateAccountByAgency(accountNo, amaun, noFPX, noResit);
    }

    public StatusInfo updateCarianAccount(String noResit)
            throws AccountInfoFaultException {
        return super.updateAccountCarian(20, noResit);
    }

    // for mysql staging database
    public StatusInfo updatePortal(String accountNo, long amaun, String resitNo, String fpxNo,
            String transactionNo, String paymentTime, String paymentType)
            throws AccountInfoFaultException {
        MysqlDB db = new MysqlDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
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

            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date convertedDate = df.parse(paymentTime);

//            ps.setInt(1, Integer.parseInt(nextId));
//            ps.setString(2, "ebill");
//            ps.setLong(3, amaun);
//            ps.setString(4, fpxNo);
//            ps.setString(5, transactionNo);
//            ps.setString(6, accountNo);
//            ps.setString(7, accountNo);
//            ps.setTimestamp(8, new Timestamp(convertedDate.getTime()));
//            ps.setString(9, paymentType);
            ps.setString(1, "");//id_portal_trans
            ps.setString(2, "CUK");//jenis_trans
            String jenisAkaun = ("DL".equals(paymentType)) ? "D" : "K";
            ps.setString(3, jenisAkaun);//jenis_akaun
            ps.setString(4, transactionNo);//id_trans_asal

            ps.setString(6, resitNo);//noresit
            ps.setTimestamp(7, new Timestamp(convertedDate.getTime()));//trh_resit
            ps.setString(8, fpxNo);//no_trans_fpx
            ps.setString(9, accountNo);//no_akaun
            ps.setString(10, accountNo);//no_akaun
            ps.setLong(11, amaun);//amaun
            Date date = new Date();
            ps.setDate(12, new java.sql.Date(date.getTime()));//trh_masuk
            updateAccount(accountNo, amaun, resitNo, fpxNo);
            System.out.println(":::::SSSS:::::");
            System.out.println("idKewDok::::" + idKewDok);
            ps.setString(5, idKewDok);//idKewDok
            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

            si.setFunctionName("updateAccount");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");

        } catch (SQLException se) {
            System.out.println("QuitRentAgent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updatePortal");

        } catch (NullPointerException npe) {
            System.out.println("QuitRentAgent.updateAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("updatePortal");

        } catch (ParseException pe) {
            System.out.println("QuitRentAgent.updateAccount: ParseException.");
            pe.printStackTrace();

            throwInternalFaultException("updatePortal");
        }

        return si;
    }

    public String updateTransaction(String accountNo, long amaun, String userName, String paymentType)
            throws AccountInfoFaultException {
        StagingDB db = new StagingDB();
        Connection conn = db.getConnection();
        String nextId = new String();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "insert into portal_transaction (id, username, amount, fpx_no, "
                    + "transaction_no, account_no, id_hakmilik, payment_date, "
                    + "payment_type,id_kew_dok) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

            nextId = db.getNextSequence("seq_portal_transaction");
            ps.setInt(1, Integer.parseInt(nextId));
            ps.setString(2, userName);
            ps.setLong(3, amaun);
            ps.setString(4, "");
            ps.setString(5, "");
            ps.setString(6, accountNo);
            ps.setString(7, accountNo);
            ps.setTimestamp(8, new Timestamp(new Date().getTime()));
            ps.setString(9, paymentType);
            ps.setString(10, "");
            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

        } catch (SQLException se) {
            System.out.println("QuitRentAgent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updatePortal");

        } catch (NullPointerException npe) {
            System.out.println("QuitRentAgent.updateAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("updatePortal");

        }

        return nextId;
    }
    // for oracle staging database

    public StatusInfo updatePortalStage(String accountNo, BigDecimal amaun, String fpxNo,
            String transactionNo, String paymentTime, String paymentType, String resitNo, String dimasuk)
            throws AccountInfoFaultException, MalformedURLException {
//        StagingDB db = new StagingDB();
//        Connection conn = db.getConnection();
//
        StatusInfo si = new StatusInfo();
//        try {

//             DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//             paymentTime = df.format(new Date());
        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        BayaranOnline service12 = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType conn = service12.getBayaranOnlineHttpPort();

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("accountNo = " + accountNo + " amaun" + amaun + " fpxNo" + fpxNo + " transactionNo" + transactionNo + " paymentTime" + paymentTime + " paymentType" + paymentType + " "
                + "resitNo" + resitNo + " dimasuk" + dimasuk);
        si.setFunctionName("updateAccount");
        si.setStatusCode("SUCCESS");
        si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");
//            String idHakmilik, BigDecimal bd, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String PAYMENT_TRANS_ID1, String idPengguna
        si.setNoresit(conn.updateUserAccount(accountNo, amaun, transactionNo, resitNo, paymentTime, paymentType, fpxNo, dimasuk));
        if ((si.getNoresit() == null) || StringUtils.isNullOrEmpty(si.getNoresit())) {
            si.setFunctionName("updateAccount");
            si.setStatusCode("FAILED");
            si.setStatusMessage("QuitRentAgentService: The account fail to updated");
        }
//        } catch (Exception x) {

//        }
        return si;
    }

    public StatusInfo updateCarianStage(String idHakmilik, String fpxNo,
            String transactionNo, String noResit, String dimasuk)
            throws AccountInfoFaultException, MalformedURLException {
//        StagingDB db = new StagingDB();
//        Connection conn = db.getConnection();
//
        StatusInfo si = new StatusInfo();
        si.setFunctionName("updateAccount");
        si.setStatusCode("SUCCESS");
        si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");
        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        BayaranOnline service = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType port = service.getBayaranOnlineHttpPort();
//            String idHakmilik, BigDecimal bd, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String PAYMENT_TRANS_ID1, String idPengguna
//            si.setNoresit(port.updateUserAccount(accountNo, amaun, transactionNo, "1", paymentTime, paymentType, fpxNo, dimasuk));
//
//        try {
//
//            PreparedStatement ps = conn.prepareStatement(
//                    "insert into portal_trans ("
//                    + "id_portal_trans,"
//                    + "jenis_trans,"
//                    + "jenis_akaun,"
//                    + "id_trans_asal,"
//                    + "id_kew_dok,"
//                    + "no_resit,"
//                    + "trh_resit,"
//                    + "no_trans_fpx,"
//                    + "no_akaun,"
//                    + "id_hakmilik,"
//                    + "amaun,"
//                    + "trh_masuk,dimasuk) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
//
//            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//            Date convertedDate = df.parse(String.valueOf(new Date().getTime()));
//
//            String nextId = db.getNextSequence("seq_portal_trans");
//
//            ps.setInt(1, Integer.parseInt(nextId));//id_portal_trans
//            ps.setString(2, "CHS");//jenis_trans
//            String jenisAkaun = ("DL".equals("DL")) ? "D" : "K";
//            ps.setString(3, jenisAkaun);//jenis_akaun
//            ps.setString(4, transactionNo);//id_trans_asal
//
//            ps.setString(6, noResit);//noresit
//            ps.setTimestamp(7, new Timestamp(convertedDate.getTime()));//trh_resit
//            ps.setString(8, fpxNo);//no_trans_fpx
//            ps.setString(9, "");//no_akaun
//            ps.setString(10, idHakmilik);//id_hakmilik
//            ps.setLong(11, 20);//amaun
//            Date date = new Date();
//            ps.setDate(12, new java.sql.Date(date.getTime()));//trh_masuk
//            ps.setString(13, dimasuk);
//            updateCarianAccount(fpxNo);
//            System.out.println(":::::SSSS:::::");
//            System.out.println("idKewDok::::" + idKewDok);
//            ps.setString(5, idKewDok);//idKewDok
//            int rs = ps.executeUpdate();
//
//            // release the connection
//            db.releaseConnection(ps);
//            updateDok(idHakmilik, Integer.parseInt(nextId), transactionNo, dimasuk, idKewDok);
//
//            si.setFunctionName("updateAccount");
//            si.setStatusCode("SUCCESS");
//            si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");
//
//        } catch (SQLException se) {
//            System.out.println("QuitRentAgent.updateAccount: SQLException.");
//            se.printStackTrace();
//
//            throwInternalFaultException("updatePortal");
//
//        } catch (NullPointerException npe) {
//            System.out.println("QuitRentAgent.updateAccount: NullPointerException.");
//            npe.printStackTrace();
//
//            throwInternalFaultException("updatePortal");
//
//        } catch (ParseException pe) {
//            System.out.println("QuitRentAgent.updateAccount: ParseException.");
//            pe.printStackTrace();
//
//            throwInternalFaultException("updatePortal");
//        }

        return si;
    }

    private byte[] getCarianTxnFileFormCore(String idHakmilik, String transac, String idKewDok) {
        byte[] report = new byte[10 * 1024];  // 1 MB size
        ArrayOfString aos = new ArrayOfString();
        aos.getString().add(idHakmilik);
        CarianEnquiry service = new CarianEnquiry();
        CarianEnquiryPortType port = service.getCarianEnquiryHttpPort();
        ArrayOfAnyType aoat = port.getSijil1(aos, "05", "CSHM", "portal", idKewDok);

        for (int i = 0; i < aoat.getAnyType().size(); i++) {
            report = (byte[]) aoat.getAnyType().get(0);
        }

        System.out.println("[From Core File Bytes :" + report + "]");
        return report;
    }

    public String getReportFormCore(String idHakmilik, String transac) {
        byte[] report = new byte[1000];
        ArrayOfString aos = new ArrayOfString();
        aos.getString().add(idHakmilik);
        CarianEnquiry service = new CarianEnquiry();
        CarianEnquiryPortType port = service.getCarianEnquiryHttpPort();
        ArrayOfAnyType aoat = port.getSijil(aos, "05", "CSHM", "portal");
        String fizikalName = null;
        for (int i = 0; i < aoat.getAnyType().size(); i++) {
            report = (byte[]) aoat.getAnyType().get(0);
//            String str = new String(report);
//
//            System.out.println("Ini adalah stream::" + str);
            OutputStream out = null;
            fizikalName = "/home/oraptl/uploads/" + transac + ".pdf";
            try {
                out = new FileOutputStream(fizikalName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AccountAgentService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out.write(report);
            } catch (IOException ex) {
                Logger.getLogger(AccountAgentService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(AccountAgentService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fizikalName;
    }

    public StatusInfo updateDok(String idHakmilik, int idPortaltrans, String idTransac, String dimasuk, String idKewDok) throws AccountInfoFaultException {
        StagingDB db = new StagingDB();
//        MysqlDB db = new MysqlDB();
        Connection conn = db.getConnection();
        //String path = getReportFormCore(idHakmilik, idTransac);

        StatusInfo si = new StatusInfo();
        InputStream is = null;
        try {
            byte[] s = getCarianTxnFileFormCore(idHakmilik, idTransac, idKewDok);
            if (s != null) {
                is = new ByteArrayInputStream(s);
            }

            PreparedStatement ps = conn.prepareStatement(
                    "insert into portal_trans_dok (id_portal_trans_dok,id_portal_trans,imej, dimasuk, trh_masuk,dikkini,trh_kkini) "
                    + "values (?, ?, ?,?,?,?,?)");
            String nextId = db.getNextSequence("seq_portal_trans_dok");

            ps.setInt(1, Integer.parseInt(nextId));
            ps.setInt(2, idPortaltrans);
            //ps.setBlob(3, null);
            ps.setBinaryStream(3, is, s.length);
            ps.setString(4, dimasuk);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.setString(6, dimasuk);   // update user id
            ps.setDate(7, new java.sql.Date(new Date().getTime()));
            int rs = ps.executeUpdate();

            // release the connection
            db.releaseConnection(ps);

            si.setFunctionName("updateAccount");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");

        } catch (SQLException se) {
            System.out.println("QuitRentAgent.updateAccount: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updatePortal");

        } catch (NullPointerException npe) {
            System.out.println("QuitRentAgent.updateAccount: NullPointerException.");
            npe.printStackTrace();

            throwInternalFaultException("updatePortal");

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return si;
    }

    public StatusInfo updatePortalStageKelompok(List<AkaunForm> listAccountForm, BigDecimal amaun, String fpxNo,
            String transactionNo, String paymentTime, String paymentType, String resitNo, String dimasuk)
            throws AccountInfoFaultException, MalformedURLException {

        StatusInfo si = new StatusInfo();

        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        BayaranOnline service12 = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType conn = service12.getBayaranOnlineHttpPort();
        ArrayOfAkaunForm in0 = new ArrayOfAkaunForm();
        for(AkaunForm a:listAccountForm){
            ObjectFactory obj = new ObjectFactory();
            etanah.client.bayar.AkaunForm f = obj.createAkaunForm();
//            f.getAmaunt().setValue(a.getAmaunt());
            f.setNoAccount(obj.createAkaunFormNoAccount(a.getNoAccount()));
            f.setAmaunt(obj.createAkaunFormAmaunt(a.getAmount()));
            
            in0.getAkaunForm().add(f);
        
        }
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("accountNo = " + listAccountForm + " amaun" + amaun + " fpxNo" + fpxNo + " transactionNo" + transactionNo + " paymentTime" + paymentTime + " paymentType" + paymentType + " "
                + "resitNo" + resitNo + " dimasuk" + dimasuk);
        si.setFunctionName("updateAccount");
        si.setStatusCode("SUCCESS");
        si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");
//            String idHakmilik, BigDecimal bd, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String PAYMENT_TRANS_ID1, String idPengguna
        si.setNoresit(conn.updateAccountPukal(in0, amaun, transactionNo, "1", paymentTime, paymentType, fpxNo, dimasuk));
        if ((si.getNoresit() == null) || StringUtils.isNullOrEmpty(si.getNoresit())) {
            si.setFunctionName("updateAccount");
            si.setStatusCode("FAILED");
            si.setStatusMessage("QuitRentAgentService: The account fail to updated");
        }
//        } catch (Exception x) {

//        }
        return si;}
}
