/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.account;

import com.mysql.jdbc.StringUtils;
import etanah.ws.agent.SejarahCukai;
import etanah.ws.utility.OracleDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faidzal
 */
public class SejarahTransService {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public List<SejarahCukai> findSejarahTrans(String accountNo) throws ParseException {
        List<SejarahCukai> listSej = new ArrayList<SejarahCukai>();
        try {
            OracleDB db = new OracleDB();
            Connection conn = db.getConnection();
            String thn;
            String idKewDok;
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement ps2 = conn.prepareStatement(
                    "select distinct skt.UTK_THN, skt.ID_SEJ_KEW_DOK "
                    + "from sej_kew_trans skt "
                    + "where skt.NO_AKAUN_KR = ?"
                    + " and skt.ID_SEJ_KEW_DOK is not null"
                    + " and skt.KOD_STATUS = 'T' order by skt.UTK_THN desc");
            ps2.setString(1, accountNo);
            ResultSet rs1 = ps2.executeQuery();
            while (rs1.next()) {
                thn = rs1.getString(1);
                idKewDok = rs1.getString(2);
                ps = conn.prepareStatement(
                        "select distinct skdb.ID_SEJ_KEW_DOK, sjd.TRH_TRANS, sjd.AMAUN_BAYAR, kcb.NAMA, sjd.TRH_MASUK, kakc.nama, kc.nama "
                        + "from "
                        + "SEJ_KEW_DOKUMEN_BAYAR skdb, "
                        + "SEJ_CARA_BAYAR sjb,SEJ_KEW_DOKUMEN sjd,kod_agensi_kutipan_caw kakc, "
                        + "kod_cara_bayar kcb, pguna pg, kod_caw kc "
                        + "where skdb.ID_SEJ_KEW_DOK = ? "
                        + "and skdb.ID_SEJ_CARA_BAYAR = sjb.ID_SEJ_CARA_BAYAR "
                        + "and skdb.ID_SEJ_KEW_DOK = sjd.ID_SEJ_KEW_DOK and sjb.KOD_CARA_BAYAR = kcb.KOD and sjd.KOD_AGENSI_KUTIPAN_CAW = kakc.KOD(+) and pg.kod_caw = kc.kod"
                                + " and pg.ID_PGUNA = sjd.DIMASUK and EXTRACT(YEAR FROM sjd.TRH_MASUK) > (EXTRACT(YEAR FROM sysdate)-6)");
                ps.setString(1, idKewDok);
                rs = ps.executeQuery();
                while (rs.next()) {
//                    thn = rs.getString(1);
//                    idKewDok = rs.getString(2);
                    SejarahCukai sej = new SejarahCukai();
                    sej.setNoResit(idKewDok);
                    sej.setTarikh(rs.getDate(2));
                    sej.setAmaun(rs.getString(3));
                    sej.setKaedahBayaran(rs.getString(4));
                    sej.setTahun(thn);
                    sej.setTarikh(sdf.parse(sdf.format(rs.getDate(5))));
                    String kaunter = rs.getString(6);
                    if (StringUtils.isNullOrEmpty(kaunter)) {
                        sej.setPusatKutipan("Kaunter Pejabat Tanah Daerah");
                        
                    } else {
                        
                        sej.setPusatKutipan(rs.getString(6));
                    }
                    listSej.add(sej);
                }
                
            }
            // release the connection
            if (ps != null) {
                db.releaseConnection(ps, rs);
            }
            db.releaseConnection(ps2, rs1);
            
        } catch (SQLException ex) {
            Logger.getLogger(SejarahTransService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSej;
    }
    
}
