/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Izam
 */
public class UtilityService {
    OracleDB db = new OracleDB();

    List<Daerah> daerahList = new LinkedList<Daerah>();
    List<Mukim> mukimList = new LinkedList<Mukim>();
    List<JenisHakmilik> jenisHakmilikList = new LinkedList<JenisHakmilik>();
    List<KodLot> kodLotList = new LinkedList<KodLot>();
    List<JenisDok> jenisDokList = new LinkedList<JenisDok>();

    public Daerah getDaerahById(String idDaerah) {
        Connection conn = db.getConnection();

        Daerah daerah = new Daerah();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_daerah where aktif = 'Y' "
                    + "and kod = ?");
            ps.setString(1, idDaerah);
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                rs.next();
                daerah.setKod(rs.getString(1));
                daerah.setNama(rs.getString(2));
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getDaerahById: SQLException.");
        }

        return daerah;
    }

    public List<Daerah> getDaerahList() {
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_daerah where aktif = 'Y'");
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                while(rs.next()) {
                    Daerah daerah = new Daerah();
                    daerah.setKod(rs.getString(1));
                    daerah.setNama(rs.getString(2));

                    daerahList.add(daerah);
                }
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getDaerahList: SQLException.");
        }

        return daerahList;
    }

    public List<Mukim> getMukimListByIdDaerah(String idDaerah) {
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_bpm where aktif = 'Y' "
                    + "and kod_daerah = ?");
            ps.setString(1, idDaerah);
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                while(rs.next()) {
                    Mukim mukim = new Mukim();
                    mukim.setKod(rs.getString(1));
                    mukim.setNama(rs.getString(2));
                    mukim.setDaerah(getDaerahById(idDaerah));

                    mukimList.add(mukim);
                }
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getMukimListByIdDaerah: SQLException.");
        }

        return mukimList;
    }

    public List<JenisHakmilik> getJenisHakmilikList() {
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_hakmilik where aktif = 'Y' "
                    + "and (kod = 'GM' or kod = 'GRN' or kod = 'HSD' or "
                    + "kod = 'HSM' or kod = 'PM' or kod = 'PN')");
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                while(rs.next()) {
                    JenisHakmilik jh = new JenisHakmilik();
                    jh.setKod(rs.getString(1));
                    jh.setNama(rs.getString(2));

                    jenisHakmilikList.add(jh);
                }
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getJenisHakmilikList: SQLException.");
        }

        return jenisHakmilikList;
    }

    public List<KodLot> getKodLotList() {
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_lot where aktif = 'Y' "
                    + "and (kod = '1' or kod = '2' or kod = '3' or kod = '4')");
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                while(rs.next()) {
                    KodLot kodLot = new KodLot();
                    kodLot.setKod(rs.getString(1));
                    kodLot.setNama(rs.getString(2));

                    kodLotList.add(kodLot);
                }
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getKodLotList: SQLException.");
        }

        return kodLotList;
    }

    public List<JenisDok> getJenisDokList() {
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select kod, nama from kod_dokumen where aktif = 'Y' and kod != 'LB1' "
                    + "and kod != 'PBT' and kod != 'SSM' and kod != 'PRU' and kod != 'LMB' "
                    + "and kod != 'AJB' order by nama asc");
            ResultSet rs = ps.executeQuery();

            if (rs.getFetchSize() > 0) {
                while(rs.next()) {
                    JenisDok jenisDok = new JenisDok();
                    jenisDok.setKod(rs.getString(1));
                    jenisDok.setNama(rs.getString(2));

                    jenisDokList.add(jenisDok);
                }
            }

            db.releaseConnection(ps, rs);

        } catch (SQLException se) {
            System.out.println("UtilityService.getJenisDokList: SQLException.");
        }

        return jenisDokList;
    }
}
