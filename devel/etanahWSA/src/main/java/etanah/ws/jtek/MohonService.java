/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.jtek;

import etanah.ws.account.WebService;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.utility.OracleDB;
import etanah.ws.utility.StatusInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 *
 * @author Izam
 */
public class MohonService extends WebService {

    public MohonInfo getMohonDetails(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        MohonInfo mi = new MohonInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select vjm.id_jtek_ulas, vjm.id_mohon, vjm.keterangan_modul, "
                    + "vjm.trh_permohonan, vjm.trh_jtek_terima, vjm.sts_mohon_jtek, "
                    + "vjm.tajuk from v_jtek_mohon vjm where vjm.id_jtek_ulas = ?");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                mi.setIdMohon(ocrs.getString(2));
                mi.setKategori(ocrs.getString(3));
                mi.setTrhMohon(ocrs.getString(4));
                mi.setTrhTerima(ocrs.getString(5));
                mi.setTindakan(ocrs.getString(6));
                mi.setTajuk(ocrs.getString(7));

                UlasInfo ui = getUlasDetails(idUlas);

                mi.setUlasInfo(ui);

            } else {
               throwEmptyFaultException("getMohonDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getMohonDetails: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getMohonDetails");
        }

        return mi;
    }

    public List<MohonInfo> getMohonList(String kodAgensi) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<MohonInfo> miList = new LinkedList<MohonInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select vjm.id_jtek_ulas, vjm.id_mohon, vjm.keterangan_modul, "
                    + "vjm.trh_permohonan, vjm.trh_jtek_terima, vjm.sts_mohon_jtek, "
                    + "vjm.tajuk from v_jtek_mohon vjm where vjm.kod_jtek = ? and "
                    + "vjm.sts_mohon_jtek != 'Selesai'");
            ps.setString(1, kodAgensi);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    MohonInfo mi = new MohonInfo();

                    mi.setIdMohon(ocrs.getString(2));
                    mi.setKategori(ocrs.getString(3));
                    mi.setTrhMohon(ocrs.getString(4));
                    mi.setTrhTerima(ocrs.getString(5));
                    mi.setTindakan(ocrs.getString(6));
                    mi.setTajuk(ocrs.getString(7));

                    UlasInfo ui = new UlasInfo();
                    ui.setIdUlas(ocrs.getLong(1));

                    mi.setUlasInfo(ui);

                    miList.add(mi);
                }
            } else {
               throwEmptyFaultException("getMohonList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getMohonList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getMohonList");
        }

        return miList;
    }

    private UlasInfo getUlasDetails(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        UlasInfo ui = new UlasInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select ju.trh_sedia, ju.nama_penyelia, ju.trh_masuk, ju.kod_syor, "
                    + "ju.ulasan from jtek_ulas ju where ju.id_jtek_ulas = ?");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();
                ocrs.next();

                ui.setIdUlas(idUlas);
                ui.setTrhSedia(ocrs.getString(1));
                ui.setNamaPenyelia(ocrs.getString(2));
                ui.setNoRujukan(ocrs.getString(3));
                ui.setKodSyor(ocrs.getString(4));
                ui.setUlasan(ocrs.getString(5));

                List<PemohonInfo> pemohonInfo = getPemohonList(idUlas);
                List<TanahInfo> tanahInfo = getTanahList(idUlas);
                List<DokInfo> dokInfo = getDokList(idUlas);
                List<DokHantarInfo> dokHantarInfo = getDokHantarList(idUlas);

                ui.setPemohonInfo(pemohonInfo);
                ui.setTanahInfo(tanahInfo);
                ui.setDokInfo(dokInfo);
                ui.setDokHantarInfo(dokHantarInfo);

            } else {
               //throwEmptyFaultException("getUlasDetails");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getUlasDetails: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getUlasDetails");
        }

        return ui;
    }

    private List<PemohonInfo> getPemohonList(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<PemohonInfo> piList = new LinkedList<PemohonInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select vjip.nama, vjip.alamat1, vjip.alamat2, vjip.alamat3, "
                    + "vjip.alamat4, vjip.poskod, vjip.negeri, vjip.no_tel1, vjip.email "
                    + "from v_jtek_info_pemohon vjip where vjip.id_jtek_ulas = ?");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    PemohonInfo pi = new PemohonInfo();

                    String alamat = ocrs.getString(2) + ", " + ocrs.getString(3)
                            + ", " + ocrs.getString(4) + ", " + ocrs.getString(5)
                            + ", " + ocrs.getString(6) + ", " + ocrs.getString(7);

                    pi.setNama(ocrs.getString(1));
                    pi.setAlamat(alamat);
                    pi.setNoTel(ocrs.getString(8));
                    pi.setEmail(ocrs.getString(9));

                    piList.add(pi);
                }
            } else {
               //throwEmptyFaultException("getPemohonList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getPemohonList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getPemohonList");
        }

        return piList;
    }

    private List<TanahInfo> getTanahList(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<TanahInfo> tiList = new LinkedList<TanahInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select vjit.id_hakmilik, vjit.keterangan_bpm, vjit.no_lot, vjit.luas, "
                    + "vjit.luas_uom, vjit.keterangan_syarat, vjit.keterangan_sekatan, "
                    + "vjit.keterangan_guna_tanah from v_jtek_info_tanah vjit "
                    + "where vjit.id_jtek_ulas = ?");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    TanahInfo ti = new TanahInfo();

                    String luas = String.valueOf(ocrs.getLong(4)) + " " + ocrs.getString(5);

                    ti.setIdHakmilik(ocrs.getString(1));
                    ti.setBpm(ocrs.getString(2));
                    ti.setNoLot(ocrs.getString(3));
                    ti.setLuas(luas);
                    ti.setSyaratNyata(ocrs.getString(6));
                    ti.setSekatan(ocrs.getString(7));
                    ti.setPajakan(ocrs.getString(8));

                    tiList.add(ti);
                }
            } else {
               //throwEmptyFaultException("getTanahList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getTanahList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getTanahList");
        }

        return tiList;
    }

    private List<DokInfo> getDokList(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<DokInfo> diList = new LinkedList<DokInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select vjdh.keterangan_dokumen, vjdh.nama_fizikal "
                    + "from v_jtek_dok_hantar vjdh where vjdh.id_jtek_ulas = ?");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    DokInfo di = new DokInfo();

                    di.setKeterangan(ocrs.getString(1));
                    di.setNamaFizikal(ocrs.getString(2));

                    diList.add(di);
                }
            } else {
               //throwEmptyFaultException("getDokList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getDokList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getDokList");
        }

        return diList;
    }

    private List<DokHantarInfo> getDokHantarList(long idUlas) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<DokHantarInfo> dhiList = new LinkedList<DokHantarInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select jdt.id_jtek_dok_terima, jdt.kod_caw, jdt.kod_dok, jdt.kod_klas, "
                    + "jdt.perihal, jdt.nama_fizikal, jdt.dimasuk, jdt.trh_masuk, jdt.dikkini, "
                    + "jdt.trh_kkini, kd.nama from jtek_dok_terima jdt, kod_dokumen kd where "
                    + "jdt.id_jtek_ulas = ? and jdt.kod_dok = kd.kod");
            ps.setLong(1, idUlas);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if(db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while(ocrs.next()) {
                    DokHantarInfo dhi = new DokHantarInfo();

                    dhi.setIdDokHantar(ocrs.getLong(1));
                    dhi.setKodCaw(ocrs.getString(2));
                    dhi.setKodDok(ocrs.getString(3));
                    dhi.setKodKlas(ocrs.getLong(4));
                    dhi.setCatatan(ocrs.getString(5));
                    dhi.setNamaFizikal(ocrs.getString(6));
                    dhi.setDiMasuk(ocrs.getString(7));
                    dhi.setTrhMasuk(ocrs.getString(8));
                    dhi.setDiKkini(ocrs.getString(9));
                    dhi.setTrhKkini(ocrs.getString(10));
                    dhi.setKeteranganDok(ocrs.getString(11));

                    dhiList.add(dhi);
                }
            } else {
               //throwEmptyFaultException("getDokList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch(SQLException se) {
            System.out.println("MohonService.getDokHantarList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getDokHantarList");
        }

        return dhiList;
    }

    public StatusInfo updateJTekUlas(long idUlas, String stsUlas, String namaPenyelia,
            String noRuj, String ulasan, String kodSyor, String username)
            throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        String updateDate = "";
        if(stsUlas.equals("SED")) updateDate = "trh_sedia = ?";
        else if(stsUlas.equals("SEL")) updateDate = "trh_selesai = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "update jtek_ulas set kod_sts = ?, nama_penyelia = ?, no_ruj = ?, "
                    + "ulasan = ?, kod_syor = ?, " + updateDate + ", dikkini = ?, "
                    + "trh_kkini = ? where id_jtek_ulas = ?");

            ps.setString(1, stsUlas);
            ps.setString(2, namaPenyelia);
            ps.setString(3, noRuj);
            ps.setString(4, ulasan);
            ps.setString(5, kodSyor);
            ps.setDate(6, new Date(new java.util.Date().getTime()));
            ps.setString(7, username);
            ps.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));
            ps.setLong(9, idUlas);

            int rs = ps.executeUpdate();

            si.setFunctionName("updateJTekUlas");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentService the application has been updated successfully");

            // release the connection
            db.releaseConnection(ps);

        } catch(SQLException se) {
            System.out.println("MohonService.updateJTekUlas: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateJTekUlas");
        }

        return si;
    }

    public StatusInfo addJTekDokHantar(long idUlas, String kodCaw, String kodDok, long kodKlas,
            String perihal, String username) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            String idDokHantar = db.getNextSequence("seq_jtek_dok_terima");

            PreparedStatement ps = conn.prepareStatement(
                    "insert into jtek_dok_terima (id_jtek_dok_terima, kod_caw, id_jtek_ulas, "
                    + "kod_dok, kod_klas, perihal, dimasuk, trh_masuk) values (?, ?, ?, ?, ?, "
                    + "?, ?, ?)");

            ps.setString(1, idDokHantar);
            ps.setString(2, kodCaw);
            ps.setLong(3, idUlas);
            ps.setString(4, kodDok);
            ps.setLong(5, kodKlas);
            ps.setString(6, perihal);
            ps.setString(7, username);
            ps.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));

            int rs = ps.executeUpdate();

            si.setFunctionName("addJTekDokHantar");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentService the document has been added successfully");

            // release the connection
            db.releaseConnection(ps);

        } catch(SQLException se) {
            System.out.println("MohonService.addJTekDokHantar: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("addJTekDokHantar");
        }

        return si;
    }

    public StatusInfo updateJTekDokHantar(long idDokHantar, String kodDok, long kodKlas,
            String perihal, String username) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "update jtek_dok_terima set kod_dok = ?, kod_klas = ?, perihal = ?, "
                    + "dikkini = ?, trh_kkini = ? where id_jtek_dok_terima = ?");

            ps.setString(1, kodDok);
            ps.setLong(2, kodKlas);
            ps.setString(3, perihal);
            ps.setString(4, username);
            ps.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
            ps.setLong(6, idDokHantar);

            int rs = ps.executeUpdate();

            si.setFunctionName("updateJTekDokHantar");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentService the document has been updated successfully");

            // release the connection
            db.releaseConnection(ps);

        } catch(SQLException se) {
            System.out.println("MohonService.updateJTekDokHantar: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("updateJTekDokHantar");
        }

        return si;
    }

    public StatusInfo deleteJTekDokHantar(long idDokHantar) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        StatusInfo si = new StatusInfo();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "delete from jtek_dok_terima where id_jtek_dok_terima = ?");

            ps.setLong(1, idDokHantar);

            int rs = ps.executeUpdate();

            si.setFunctionName("deleteJTekDokHantar");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentService the document has been deleted successfully");

            // release the connection
            db.releaseConnection(ps);

        } catch(SQLException se) {
            System.out.println("MohonService.deleteJTekDokHantar: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("deleteJTekDokHantar");
        }

        return si;
    }
    
    public  List<EmailInfo> getEmailList(String  kod_agency ) throws AccountInfoFaultException {
        OracleDB db = new OracleDB();
        Connection conn = db.getConnection();

        List<EmailInfo> emailList = new LinkedList<EmailInfo>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select email from portal_users where kod_agency = ?  ");
           // la_sp.id_lapor_tanah_spdn = ?
            ps.setString(1, kod_agency);
            ResultSet rs = ps.executeQuery();

            OracleCachedRowSet ocrs = new OracleCachedRowSet();
            ocrs.populate(rs);

            if (db.getRowCount(ocrs) > 0) {
                ocrs.beforeFirst();

                while (ocrs.next()) {
                	EmailInfo emailinfo = new EmailInfo();
                	System.out.println("======ocrs.getString(1)======="+ocrs.getString(1));
                	emailinfo.setEmailId(ocrs.getString(1));
                	
                	emailList.add(emailinfo);
                }
            } else {
                //throwEmptyFaultException("getDokList");
            }

            // release the connection
            db.releaseConnection(ps, rs, ocrs);

        } catch (SQLException se) {
            System.out.println("MohonService.getEmailList: SQLException.");
            se.printStackTrace();

            throwInternalFaultException("getEmailList");
        }

        return emailList;
    }

}
