package etanah.view.stripes.hasil;

import able.stripes.*;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.*;
import net.sourceforge.stripes.action.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;

/**
 * @author haqqiem 10 March 2015 03:02 PM
 */
@Wizard(startEvents = {"Step1", "filterByDaerah"})
@UrlBinding("/hasil/report_txt")
public class TextFileReportActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(TextFileReportActionBean.class);
    static SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy");
    static SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");
    static SimpleDateFormat sdfWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static DecimalFormat df2 = new DecimalFormat("0.00");
    static DecimalFormat dFormat = new DecimalFormat("#,###,##0.00");
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/report_text_file.jsp";

    private static String kodNegeri;
    private String daerah = "";
    private String reportNo = "";
    private String tarikh = "";
    private String kodBpm = "";
    private String jenisHakmilik = "";
    private String bangsa = "";
    private String downloadLink = "";
    private boolean flagJana = false;
    private BigDecimal amaun = new BigDecimal(0.00);
    
//    static String etanahPath = "/home/haqqiem/";       // local
    static String etanahPath = "/nfs_apps/dms/";       // server 

    private String[] listReport;
    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<KodHakmilik> senaraiJenisHakmilik = new ArrayList<KodHakmilik>();
    private List<KodBangsa> senaraiBangsa = new ArrayList<KodBangsa>();

    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodDaerahDAO kodDaerahDAO;
    @Inject
    private KodBangsaDAO kodBangsaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
            showReports();
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    private String[] showReports() {
        listReport = new String[]{
            "1. Ringkasan Tunggakan Cukai Ikut Kategori (Summary) ",
            "2. Senarai Tunggakan Cukai Ikut Kategori",
            "3. Laporan Tunggakan Sehingga.... ",
            "4. Laporan Induk Cukai Tanah "};
        return listReport;
    }

    public Resolution filterByDaerah() {
        daerah = getContext().getRequest().getParameter("kodDaerah");
        reportNo = getContext().getRequest().getParameter("reportId");
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (daerah == null || daerah.equals("") || daerah.equals("0")) {
            senaraiBPM = new ArrayList<KodBandarPekanMukim>();
        } else {
            sql = "SELECT u FROM etanah.model.KodBandarPekanMukim u "
                    + "WHERE u.daerah.kod = :kodDaerah AND u.bandarPekanMukim <> '00' ORDER BY u.bandarPekanMukim ASC";
            q = s.createQuery(sql);
            q.setString("kodDaerah", daerah);
        }
        senaraiBPM = q.list();
        showForm();
        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");
    }

    @HandlesEvent("Step2")
    public Resolution generateReport() throws ParseException, SQLException {        
        Date year = new Date();
        java.util.Date now = sdfWithTime.parse("01/01/"+(year.getYear()+1900) + " 00:00:00");
        Timestamp startDate = new Timestamp(now.getTime());
        
        downloadLink = getDirPath();
        
        if (reportNo.equals("2")) {
            now = sdfWithTime.parse(tarikh + " 23:59:59");
            Timestamp endDate = new Timestamp(now.getTime());
            if (generateReport02(startDate, endDate, now, endDate, daerah, kodBpm, jenisHakmilik, amaun, bangsa)) {
                flagJana = true;
                LOG.info("downloadLink :" + downloadLink);
                addSimpleMessage("Laporan telah bejaya dijana. Sila klik butang Muat Turun");
            } else {
                addSimpleError("Report tidak dapat dijana.");
            }
        }
        
        if (reportNo.equals("3")) {
            now = sdfWithTime.parse(tarikh + " 23:59:59");
            Timestamp endDate = new Timestamp(now.getTime());
            if (generateReport03(startDate, endDate, now, endDate, daerah, kodBpm, jenisHakmilik)) {;
                flagJana = true;
                LOG.info("downloadLink :" + downloadLink);
                addSimpleMessage("Laporan telah bejaya dijana. Sila klik butang Muat Turun");
            } else {
                addSimpleError("Report tidak dapat dijana.");
            }
        }
        
        if (reportNo.equals("4")) {
            if (generateReport04(startDate, now, daerah, kodBpm)) {;
                flagJana = true;
                LOG.info("downloadLink :" + downloadLink);
                addSimpleMessage("Laporan telah bejaya dijana. Sila klik butang Muat Turun");
            } else {
                addSimpleError("Report tidak dapat dijana.");
            }
        }
        
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public boolean generateReport02(Timestamp startDate, Timestamp endDate, Date now,
            Timestamp dateLast, String daerah, String bpm, String jenis, BigDecimal baki, String kategori) throws ParseException, SQLException {
        
        LOG.info("daerah : "+daerah+" bpm : "+bpm+" jenis : "+jenis);
        LOG.info("baki : "+baki+" kategori : "+kategori);
        Connection conn = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        String cawangan = kodDaerahDAO.findById(daerah).getNama().toUpperCase();
        
        BigDecimal nilaiCukai = new BigDecimal(0.00);
        BigDecimal nilaiTunggakan = new BigDecimal(0.00);
        BigDecimal nilaiDenda = new BigDecimal(0.00);
        BigDecimal nilaiRemisen = new BigDecimal(0.00);
        BigDecimal nilai = new BigDecimal(0.00);

        PrintWriter pw = createFile("HSL_R_23_MLK.txt", now);
        boolean noError = true;

        try {
            //testing
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT DISTINCT hm.id_hakmilik, upper(kb.nama) mukim, kl.nama || ' ' || LPAD(LTRIM(hm.no_lot,'0'),6,' ') no_lot, "
                            + "LPAD(kkt.nama,16,' ') katg_tanah, ka.baki, ka.no_akaun "
                            + "FROM kew_trans kt, kew_akaun ka, hakmilik hm,hakmilik_pihak hp, "
                            + "pihak phk, kod_bangsa kbsa, kod_lot kl, kod_uom kuom, "
                            + "kod_bpm kb, kod_hakmilik kh, kod_daerah kd, kod_katg_tanah kkt, kod_caw kc "
                            + "WHERE kt.kod_trans IN ('61401','61402','76152','99000','99001','99002','99003','99030') "
                            + "AND kt.amaun >= ? "
                            + "AND kt.kod_caw = kc.kod(+) "
                            + "AND kt.no_akaun_dt = ka.no_akaun "
                            + "AND ka.kod_akaun = 'AC' "
                            + "AND ka.id_hakmilik = hm.id_hakmilik "
                            + "AND hm.kod_bpm = kb.kod(+) "
                            + "AND hm.kod_lot = kl.kod(+) "
                            + "AND hm.kod_uom = kuom.kod(+) "
                            + "AND hm.kod_hakmilik = kh.kod(+) "
                            + "AND hm.kod_daerah = kd.kod(+) "
                            + "AND hm.kod_katg_tanah = kkt.kod(+) "
                            + "AND hm.kod_hakmilik = NVL(:p_kod_hakmilik, hm.kod_hakmilik) "
                            + "AND hm.kod_bpm = NVL(:p_kod_bpm, hm.kod_bpm) "
                            + "AND hm.kod_daerah = NVL(:p_kod_daerah, hm.kod_daerah) "
                            + "AND phk.kod_bangsa = NVL(:p_katg_pemilik, phk.kod_bangsa) "
                            + "AND hm.id_hakmilik = hp.id_hakmilik "
                            + "AND hp.id_hakmilik = ka.id_hakmilik "
                            + "AND hp.kod_pb = 'PM' "
                            + "AND hp.aktif = 'Y' "
                            + "AND hp.id_pihak = phk.id_pihak "
                            + "AND phk.kod_bangsa = kbsa.kod(+) AND ka.sts = 'A' "
                            + "AND ka.baki >  0 AND hm.kod_sts_hakmilik = 'D' "
                            + " AND kt.trh_masuk BETWEEN ?"
                            + " AND NVL(?,kt.trh_masuk)",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setBigDecimal(1, baki);
            pst.setString(2, jenis);
            pst.setString(3, bpm);
            pst.setString(4, daerah);
            pst.setString(5, kategori);
            pst.setTimestamp(6, startDate);
            pst.setTimestamp(7, endDate);
            ResultSet rst = pst.executeQuery();

            int count = 0;
            String katg = kodBangsaDAO.findById(kategori).getNama();
            
            // HEADER terima01.txt
            line.append("\t\t\t\t\t\t\t")
                    .append("SISTEM eTANAH NEGERI MELAKA ("+cawangan+")").append("\n")
                    .append("\t\t\t\t\t\t")
                    .append("SENARAI RINGKASAN TUNGGAKAN CUKAI TANAH MENGIKUT KATEGORI PEMILIK").append("\n")
                    .append("\t\t\t\t\t\t\t").append("   ")
                    .append("BAGI TUNGGAKAN LEBIH DARI RM "+baki).append(" ")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n").append("\n")
                    .append("Daerah   : "+cawangan).append("\n")
                    .append("Kategori : "+katg.toUpperCase())
                    .append("\n")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append("BIL").append("\t  ")
                    .append("ID HAKMILIK").append("\t\t   ")
                    .append("MUKIM").append("\t")
                    .append("NO. LOT").append("\t\t   ")
                    .append("JENIS").append("      ")
                    .append("TAHUN").append("\t    ")
                    .append("CUKAI").append("\t")
                    .append("TUNGGAKAN").append("\t")
                    .append("DENDA").append("\t   ")
                    .append("REMISYEN").append("\t  ")
                    .append("BAKI").append("\t   ")
                    .append("NAMA").append("\t")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t     TUNGGAK")
                    .append("\t    SEMASA")
                    .append("\t\t\tLEWAT")
                    .append("\t\t\t  AKHIR")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n");

            pw.print(line.toString());
            line.setLength(0);
            
            while (rst.next()) {
                count++;
                PreparedStatement ppNama = conn.prepareStatement(
                        "SELECT ka.id_hakmilik,phk.nama, phk.alamat1, phk.alamat2, phk.alamat3, phk.alamat4, phk.poskod, kn.nama negeri "
                                + "FROM kew_akaun ka, pihak phk, kod_negeri kn "
                                + "WHERE ka.dipegang = phk.id_pihak AND phk.kod_negeri = kn.kod(+) AND ka.no_akaun =?");
                ppNama.setString(1, rst.getString(6));
                ResultSet rrRow = ppNama.executeQuery();
                rrRow.next();
                
                PreparedStatement min = conn.prepareStatement(
                        "SELECT ka.no_akaun, min(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                min.setString(1, rst.getString(6));
                ResultSet rsMin = min.executeQuery();
                rsMin.next();
                
                PreparedStatement yMax = conn.prepareStatement(
                        "SELECT ka.no_akaun, max(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                yMax.setString(1, rst.getString(6));
                ResultSet rsMax = yMax.executeQuery();
                rsMax.next();
                
                PreparedStatement cukai = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_cukai_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' AND ka.no_akaun =? "
                                + "AND kt.kod_trans = '61401' GROUP BY ka.id_hakmilik");
                cukai.setString(1, rst.getString(6));
                ResultSet rsCukai = cukai.executeQuery();
                rsCukai.next();
                
                BigDecimal tggk = new BigDecimal(0.00);
                PreparedStatement tunggakan = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_tung_cukai "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' "
                                + "AND kt.kod_trans = '61402' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                tunggakan.setString(1, rst.getString(6));
                ResultSet rsTunggakan = tunggakan.executeQuery();
                int c=0;
                while (rsTunggakan.next()) {
                    c++;
                    if(c != 0){
                        tggk = rsTunggakan.getBigDecimal(2);
                    }else{
                        tggk = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal denda = new BigDecimal(0.00);
                PreparedStatement dendaLewat = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_denda_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun "
                                + "AND ka.kod_akaun = 'AC' AND kt.kod_trans = '76152' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                dendaLewat.setString(1, rst.getString(6));
                ResultSet rsDenda = dendaLewat.executeQuery();
                int d=0;
                while (rsDenda.next()) {
                    d++;
                    if(d != 0){
                        denda = rsDenda.getBigDecimal(2);
                    }else{
                        denda = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal remisyen = new BigDecimal(0.00);
                PreparedStatement rem = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_remisyen "
                                + "FROM kew_akaun ka,  kew_trans kt "
                                + "WHERE kt.no_akaun_kr = ka.no_akaun "
                                + "AND kt.kod_trans IN ('99000','99001','99002','99003','99030') "
                                + "AND kt.kod_status = 'A' AND ka.no_akaun=? "
                                + "GROUP BY ka.id_hakmilik");
                rem.setString(1, rst.getString(6));
                ResultSet rsRemisen = rem.executeQuery();
                int r=0;
                while (rsRemisen.next()) {
                    r++;
                    if (r != 0){
                        remisyen = rsRemisen.getBigDecimal(2);
                    }else{
                        remisyen = new BigDecimal(0.00);
                    }
                }
                                
                line.append(count).append("\t")
                        .append(rst.getString(1)).append("\t") 
                        .append(rst.getString(2)).append("  ") 
                        .append(rst.getString(3)).append(" ") 
                        .append(rst.getString(4)).append("  ")
                        .append(rsMin.getInt(2) == 2015 ? "    " : rsMin.getInt(2) == 2014 ? "     " : rsMin.getInt(2)+"-") 
                        .append(rsMin.getInt(2) == 2015 ? "     " : rsMax.getInt(2)-1).append(" ") 
                        .append(StringUtils.leftPad(df2.format(rsCukai.getBigDecimal(2)),12," ")).append("  ") // cukai
                        .append(StringUtils.leftPad(df2.format(tggk),12," ")).append(" ") // tunggakan
                        .append(StringUtils.leftPad(df2.format(denda),12," ")).append("\t") // denda
                        .append(StringUtils.leftPad(df2.format(remisyen),12," ")).append(" ") // remisyen
                        .append(StringUtils.leftPad(dFormat.format(rst.getBigDecimal(5)),12," ")).append("  ") // baki
                        .append(rrRow.getString(2)).append("\t") 
                        .append("\n");
                
                pw.print(line.toString());
                line.setLength(0);
                
                nilaiCukai = nilaiCukai.add(rsCukai.getBigDecimal(2));
                nilaiTunggakan = nilaiTunggakan.add(tggk);
                nilaiDenda = nilaiDenda.add(denda);
                nilaiRemisen = nilaiRemisen.add(remisyen);
                nilai = nilai.add(rst.getBigDecimal(5));
                
            }
            
            line.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t  ")
                    .append("RINGKASAN\t        RM")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("CUKAI SEMASA  :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiCukai),15," "))
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("TUNGGAKAN     :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiTunggakan),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("DENDA LEWAT   :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiDenda),15," ")).append("\t    ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("REMISYEN      :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiRemisen),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("BAKI AKHIR    :")
                    .append(StringUtils.leftPad(dFormat.format(nilai),15," "));
            pw.print(line.toString());
            line.setLength(0);

            releaseObject(pst, rst);
            pst.close();
            rst.close();

        } catch (SQLException e) {
            System.out.println("e" + e);
            noError = false;
        } finally {
            pw.close();
        }

        return noError;
    }
    
    public boolean generateReport03(Timestamp startDate, Timestamp endDate, Date now,
            Timestamp dateLast, String daerah, String bpm, String jenis) throws ParseException, SQLException {
        
        Connection conn = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        String trh_mula = "";
        String trh_akhir = "";
        String cawangan = kodDaerahDAO.findById(daerah).getNama().toUpperCase();
        
        BigDecimal nilaiCukai = new BigDecimal(0.00);
        BigDecimal nilaiTunggakan = new BigDecimal(0.00);
        BigDecimal nilaiDenda = new BigDecimal(0.00);
        BigDecimal nilaiRemisen = new BigDecimal(0.00);
        BigDecimal nilai = new BigDecimal(0.00);

        PrintWriter pw = createFile("HSL_R_24_MLK.txt", now);
        boolean noError = true;

        try {
            //testing
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT DISTINCT LPAD(hm.id_hakmilik,17,' '), upper(kb.nama) mukim, kl.nama || ' ' || LPAD(LTRIM(hm.no_lot,'0'),6,' ') no_lot, ka.baki, ka.no_akaun "
                    + "FROM kew_trans kt, kew_akaun ka, hakmilik hm, hakmilik_pihak hp, pihak phk, kod_bangsa kbsa, "
                    + "kod_negeri kn, kod_lot kl, kod_uom kuom, kod_bpm kb, kod_hakmilik kh, kod_daerah kd, "
                    + "kod_katg_tanah kkt, kod_caw kc "
                    + "WHERE kt.kod_trans in ('61401','61402','76152','99000','99001','99002','99003','99030') "
                    + "AND kt.kod_caw = kc.kod(+) "
                    + "AND kt.no_akaun_dt = ka.no_akaun "
                    + "AND ka.kod_akaun = 'AC' "
                    + "AND ka.id_hakmilik = hm.id_hakmilik "
                    + "AND hm.kod_bpm = kb.kod(+) "
                    + "AND hm.kod_lot = kl.kod(+) "
                    + "AND hm.kod_uom = kuom.kod(+) "
                    + "AND hm.kod_hakmilik = kh.kod(+) "
                    + "AND hm.kod_daerah = kd.kod(+) "
                    + "AND hm.kod_katg_tanah = kkt.kod(+) "
                    + "AND hm.kod_hakmilik = NVL(:p_kod_hakmilik, hm.kod_hakmilik) "
                    + "AND hm.kod_bpm = NVL(:p_kod_bpm, hm.kod_bpm) "
                    + "AND hm.kod_daerah = NVL(:p_kod_daerah, hm.kod_daerah) "
                    + "AND hm.id_hakmilik = hp.id_hakmilik "
                    + "AND hp.id_hakmilik = ka.id_hakmilik "
                    + "AND hp.kod_pb = 'PM' "
                    + "AND hp.aktif = 'Y' "
                    + "AND hp.id_pihak = phk.id_pihak "
                    + "AND phk.kod_negeri = kn.kod(+) "
                    + "AND phk.kod_bangsa = kbsa.kod(+) "
                    + "AND ka.sts = 'A' "
                    + "AND ka.baki >  0 "
                    + "AND hm.kod_sts_hakmilik = 'D' "
                    + "AND kt.trh_masuk BETWEEN ? "
                    + "AND NVL(?,kt.trh_masuk)",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, jenis);
            pst.setString(2, bpm);
            pst.setString(3, daerah);
            pst.setTimestamp(4, startDate);
            pst.setTimestamp(5, endDate);
            ResultSet rst = pst.executeQuery();

            int count = 0;
            
            // HEADER terima01.txt
            line.append("\t\t\t\t\t\t").append("  ")
                    .append("SISTEM eTANAH NEGERI MELAKA ("+cawangan+")").append("\n")
                    .append("\t\t\t\t\t\t  ").append(" ")
                    .append("LAPORAN TUNGGAKAN SEHINGGA").append(" ")
                    .append(sdf2.format(now.getTime())).append(" ")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n").append("\n")
                    .append("Daerah : "+cawangan)
                    .append("\n")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append(" BIL")
                    .append("\t   ID HAKMILIK")
                    .append("\t        MUKIM")
                    .append("\t      NO. LOT")
                    .append("\t   TAHUN")
                    .append("\t    CUKAI")
                    .append("\tTUNGGAKAN")
                    .append("\t   DENDA")
                    .append("\tREMISYEN")
                    .append("\tBAKI")
                    .append("\tNAMA")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t  TUNGGAK")
                    .append("\t    SEMASA")
                    .append("\t\t\t   LEWAT")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n");

            pw.print(line.toString());
            line.setLength(0);
            
            while (rst.next()) {
                count++;
//                System.out.println("resit : "+rst.getString(5));
                PreparedStatement ppNama = conn.prepareStatement(
                        "SELECT ka.id_hakmilik,phk.nama, phk.alamat1, phk.alamat2, phk.alamat3, phk.alamat4, phk.poskod, kn.nama negeri "
                                + "FROM kew_akaun ka, pihak phk, kod_negeri kn "
                                + "WHERE ka.dipegang = phk.id_pihak AND phk.kod_negeri = kn.kod(+) AND ka.no_akaun =?");
                ppNama.setString(1, rst.getString(5));
                ResultSet rrRow = ppNama.executeQuery();
                rrRow.next();
                
                PreparedStatement min = conn.prepareStatement(
                        "SELECT ka.no_akaun, min(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                min.setString(1, rst.getString(5));
                ResultSet rsMin = min.executeQuery();
                rsMin.next();
                
                PreparedStatement yMax = conn.prepareStatement(
                        "SELECT ka.no_akaun, max(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                yMax.setString(1, rst.getString(5));
                ResultSet rsMax = yMax.executeQuery();
                rsMax.next();
                
                PreparedStatement cukai = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_cukai_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' AND ka.no_akaun =? "
                                + "AND kt.kod_trans = '61401' GROUP BY ka.id_hakmilik");
                cukai.setString(1, rst.getString(5));
                ResultSet rsCukai = cukai.executeQuery();
                rsCukai.next();
                
                BigDecimal tggk = new BigDecimal(0.00);
                PreparedStatement tunggakan = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_tung_cukai "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' "
                                + "AND kt.kod_trans = '61402' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                tunggakan.setString(1, rst.getString(5));
                ResultSet rsTunggakan = tunggakan.executeQuery();
                int c=0;
                while (rsTunggakan.next()) {
                    c++;
                    if(c != 0){
                        tggk = rsTunggakan.getBigDecimal(2);
                    }else{
                        tggk = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal denda = new BigDecimal(0.00);
                PreparedStatement dendaLewat = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_denda_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun "
                                + "AND ka.kod_akaun = 'AC' AND kt.kod_trans = '76152' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                dendaLewat.setString(1, rst.getString(5));
                ResultSet rsDenda = dendaLewat.executeQuery();
                int d=0;
                while (rsDenda.next()) {
                    d++;
                    if(d != 0){
                        denda = rsDenda.getBigDecimal(2);
                    }else{
                        denda = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal remisyen = new BigDecimal(0.00);
                PreparedStatement rem = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_remisyen "
                                + "FROM kew_akaun ka,  kew_trans kt "
                                + "WHERE kt.no_akaun_kr = ka.no_akaun "
                                + "AND kt.kod_trans IN ('99000','99001','99002','99003','99030') "
                                + "AND kt.kod_status = 'A' AND ka.no_akaun=? "
                                + "GROUP BY ka.id_hakmilik");
                rem.setString(1, rst.getString(5));
                ResultSet rsRemisen = rem.executeQuery();
                int r=0;
                while (rsRemisen.next()) {
                    r++;
                    if (r != 0){
                        remisyen = rsRemisen.getBigDecimal(2);
                    }else{
                        remisyen = new BigDecimal(0.00);
                    }
                }
                                
                line.append(count).append("\t ")
                        .append(rst.getString(1)).append("    ") 
                        .append(rst.getString(2)).append("  ") 
                        .append(rst.getString(3)).append("  ") 
                        .append(rsMin.getInt(2) == 2015 ? "    " : rsMin.getInt(2) == 2014 ? "     " : rsMin.getInt(2)+"-") 
                        .append(rsMin.getInt(2) == 2015 ? "     " : rsMax.getInt(2)-1).append("    ") 
                        .append(StringUtils.leftPad(df2.format(rsCukai.getBigDecimal(2)),12," ")).append("   ") // cukai
                        .append(StringUtils.leftPad(df2.format(tggk),12," ")).append("   ") // tunggakan
                        .append(StringUtils.leftPad(df2.format(denda),12," ")).append("     ") // denda
                        .append(StringUtils.leftPad(df2.format(remisyen),10," ")).append("\t") // remisyen
                        .append(StringUtils.leftPad(dFormat.format(rst.getBigDecimal(4)),12," ")).append("\t") // baki
                        .append(rrRow.getString(2) == null ? "" : rrRow.getString(2)).append("\t") // nama
                        .append("\n");
                
                pw.print(line.toString());
                line.setLength(0);
                
                nilaiCukai = nilaiCukai.add(rsCukai.getBigDecimal(2));
                nilaiTunggakan = nilaiTunggakan.add(tggk);
                nilaiDenda = nilaiDenda.add(denda);
                nilaiRemisen = nilaiRemisen.add(remisyen);
                nilai = nilai.add(rst.getBigDecimal(4));
                
            }
            System.out.println("count : " + count);
            
            line.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t  ")
                    .append("RINGKASAN\t        RM")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("CUKAI SEMASA  :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiCukai),15," "))
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("TUNGGAKAN     :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiTunggakan),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("DENDA LEWAT   :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiDenda),15," ")).append("\t    ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("REMISYEN      :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiRemisen),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t")
                    .append("BAKI AKHIR    :")
                    .append(StringUtils.leftPad(dFormat.format(nilai),15," "));
            pw.print(line.toString());
            line.setLength(0);

            releaseObject(pst, rst);
            pst.close();
            rst.close();

        } catch (SQLException e) {
            System.out.println("e" + e);
            noError = false;
        } finally {
            pw.close();
        }

        return noError;
    }
    
    public boolean generateReport04(Timestamp startDate, Date now, String daerah, String bpm) throws ParseException, SQLException {
        
        Connection conn = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        String trh_mula = "";
        String trh_akhir = "";
        String cawangan = kodDaerahDAO.findById(daerah).getNama().toUpperCase();
        
        BigDecimal nilaiCukai = new BigDecimal(0.00);
        BigDecimal nilaiTunggakan = new BigDecimal(0.00);
        BigDecimal nilaiDenda = new BigDecimal(0.00);
        BigDecimal nilaiRemisen = new BigDecimal(0.00);
        BigDecimal nilai = new BigDecimal(0.00);

        PrintWriter pw = createFile("HSL_R_34_MLK.txt", now);
        boolean noError = true;

        try {
            //testing
            PreparedStatement pst = conn.prepareStatement(
                    "SELECT distinct upper(kb.nama) mukim, ka.no_akaun, hm.id_hakmilik, "
                            + "kl.nama || LPAD(LTRIM(hm.no_lot,'0'),6,' ') no_lot, "
                            + "LTRIM (TO_CHAR (hm.luas, '9999990D0000')) || ' ' || kuom.nama luas, ka.baki "
                            + "FROM hakmilik hm, kod_lot kl, kod_uom kuom, kod_bpm kb, "
                            + "kod_hakmilik kh, kod_daerah kd,kew_akaun ka,kod_caw kc "
                            + "WHERE hm.kod_bpm = NVL(:p_kod_bpm, hm.kod_bpm) AND hm.kod_caw = kc.kod(+) "
                            + "AND hm.kod_daerah = NVL(:p_kod_daerah, hm.kod_daerah) AND hm.kod_bpm = kb.kod(+) "
                            + "AND hm.kod_lot = kl.kod(+) AND hm.kod_uom = kuom.kod(+) AND hm.kod_hakmilik = kh.kod(+) "
                            + "AND hm.kod_daerah = kd.kod(+) AND hm.id_hakmilik = ka.id_hakmilik AND ka.kod_akaun = 'AC' "
                            + "AND ka.sts = 'A' AND hm.kod_sts_hakmilik = 'D'",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, bpm);
            pst.setString(2, daerah);
            ResultSet rst = pst.executeQuery();

            int count = 0;
            
            // HEADER terima01.txt
            line.append("\t\t\t\t\t\t").append("  ")
                    .append("SISTEM eTANAH NEGERI MELAKA ("+cawangan+")").append("\n")
                    .append("\t\t\t\t\t\t  ").append(" ")
                    .append("\tLAPORAN INDUK CUKAI TANAH").append(" ")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n").append("\n")
                    .append("Daerah : "+cawangan)
                    .append("\n")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append("BIL").append("\t")
                    .append("LOKALITI").append("\t")
                    .append("NO AKAUN").append("       ")
                    .append("ID HAKMILIK").append("\t  ")
                    .append("NO. LOT").append("     ")
                    .append("TAHUN").append("\t ")
                    .append("CUKAI").append("\t  ")
                    .append("TUNGGAKAN").append("\t")
                    .append("DENDA").append("\t  ")
                    .append("REMISYEN").append("\t")
                    .append("BAKI").append("\t   ")
                    .append("NAMA").append("\t")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t     TUNGGAK")
                    .append("\t SEMASA")
                    .append("\t\t\tLEWAT")
                    .append("\t\t\tAKHIR")
                    .append("\n")
                    .append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n");

            pw.print(line.toString());
            line.setLength(0);
            
            while (rst.next()) {
                count++;
                PreparedStatement ppNama = conn.prepareStatement(
                        "SELECT ka.id_hakmilik,phk.nama, phk.alamat1, phk.alamat2, phk.alamat3, phk.alamat4, phk.poskod, kn.nama negeri "
                                + "FROM kew_akaun ka, pihak phk, kod_negeri kn "
                                + "WHERE ka.dipegang = phk.id_pihak AND phk.kod_negeri = kn.kod(+) AND ka.no_akaun =?");
                ppNama.setString(1, rst.getString(2));
                ResultSet rrRow = ppNama.executeQuery();
                rrRow.next();
//                
                PreparedStatement min = conn.prepareStatement(
                        "SELECT ka.no_akaun, min(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                min.setString(1, rst.getString(2));
                ResultSet rsMin = min.executeQuery();
                rsMin.next();
                
                PreparedStatement yMax = conn.prepareStatement(
                        "SELECT ka.no_akaun, max(kt.utk_thn) thn "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.no_akaun =? "
                                + "AND ka.kod_akaun = 'AC' GROUP BY ka.no_akaun ");
                yMax.setString(1, rst.getString(2));
                ResultSet rsMax = yMax.executeQuery();
                rsMax.next();
                
                PreparedStatement cukai = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_cukai_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' AND ka.no_akaun =? "
                                + "AND kt.kod_trans = '61401' GROUP BY ka.id_hakmilik");
                cukai.setString(1, rst.getString(2));
                ResultSet rsCukai = cukai.executeQuery();
                rsCukai.next();
                
                BigDecimal tggk = new BigDecimal(0.00);
                PreparedStatement tunggakan = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_tung_cukai "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun AND ka.kod_akaun = 'AC' "
                                + "AND kt.kod_trans = '61402' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                tunggakan.setString(1, rst.getString(2));
                ResultSet rsTunggakan = tunggakan.executeQuery();
                int c=0;
                while (rsTunggakan.next()) {
                    c++;
                    if(c != 0){
                        tggk = rsTunggakan.getBigDecimal(2);
                    }else{
                        tggk = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal denda = new BigDecimal(0.00);
                PreparedStatement dendaLewat = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_denda_semasa "
                                + "FROM kew_trans kt, kew_akaun ka "
                                + "WHERE kt.no_akaun_dt = ka.no_akaun "
                                + "AND ka.kod_akaun = 'AC' AND kt.kod_trans = '76152' AND ka.no_akaun =? "
                                + "GROUP BY ka.id_hakmilik");
                dendaLewat.setString(1, rst.getString(2));
                ResultSet rsDenda = dendaLewat.executeQuery();
                int d=0;
                while (rsDenda.next()) {
                    d++;
                    if(d != 0){
                        denda = rsDenda.getBigDecimal(2);
                    }else{
                        denda = new BigDecimal(0.00);
                    }
                }
                
                BigDecimal remisyen = new BigDecimal(0.00);
                PreparedStatement rem = conn.prepareStatement(
                        "SELECT ka.id_hakmilik, SUM (kt.amaun) amaun_remisyen "
                                + "FROM kew_akaun ka,  kew_trans kt "
                                + "WHERE kt.no_akaun_kr = ka.no_akaun "
                                + "AND kt.kod_trans IN ('99000','99001','99002','99003','99030') "
                                + "AND kt.kod_status = 'A' AND ka.no_akaun=? "
                                + "GROUP BY ka.id_hakmilik");
                rem.setString(1, rst.getString(2));
                ResultSet rsRemisen = rem.executeQuery();
                int r=0;
                while (rsRemisen.next()) {
                    r++;
                    if (r != 0){
                        remisyen = rsRemisen.getBigDecimal(2);
                    }else{
                        remisyen = new BigDecimal(0.00);
                    }
                }
                                
                line.append(count).append("\t")
                        .append(rst.getString(1)).append("\t") 
                        .append(rst.getString(2)).append("  ") 
                        .append(rst.getString(3)).append("\t ") 
                        .append(rst.getString(4)).append("  ") 
                        .append(rsMin.getInt(2) == 2015 ? "    " : rsMin.getInt(2) == 2014 ? "     " : rsMin.getInt(2)+"-") 
                        .append(rsMin.getInt(2) == 2015 ? "     " : rsMax.getInt(2)-1).append("\t") 
                        .append(StringUtils.leftPad(df2.format(rsCukai.getBigDecimal(2)),8," ")).append("  ") // cukai
                        .append(StringUtils.leftPad(df2.format(tggk),9," ")).append(" ") // tunggakan
                        .append(StringUtils.leftPad(df2.format(denda),9," ")).append("\t") // denda
                        .append(StringUtils.leftPad(df2.format(remisyen),9," ")).append("  ") // remisyen
                        .append(StringUtils.leftPad(dFormat.format(rst.getBigDecimal(6)),12," ")).append("\t   ") // baki
                        .append(rrRow.getString(2) == null ? "" : rrRow.getString(2)).append("\t") 
                        .append("\n");
                
                pw.print(line.toString());
                line.setLength(0);
                
                nilaiCukai = nilaiCukai.add(rsCukai.getBigDecimal(2));
                nilaiTunggakan = nilaiTunggakan.add(tggk);
                nilaiDenda = nilaiDenda.add(denda);
                nilaiRemisen = nilaiRemisen.add(remisyen);
                nilai = nilai.add(rst.getBigDecimal(6));
                
            }
            System.out.println("count : " + count);
            
            line.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t ")
                    .append("RINGKASAN\t        RM")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t")
                    .append("CUKAI SEMASA  :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiCukai),15," "))
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t")
                    .append("TUNGGAKAN     :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiTunggakan),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t")
                    .append("DENDA LEWAT   :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiDenda),15," ")).append("\t    ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t")
                    .append("REMISYEN      :")
                    .append(StringUtils.leftPad(dFormat.format(nilaiRemisen),15," ")).append("     ")
                    .append("\n")
                    .append("\t\t\t\t\t\t\t\t\t")
                    .append("BAKI AKHIR    :")
                    .append(StringUtils.leftPad(dFormat.format(nilai),15," "));
            pw.print(line.toString());
            line.setLength(0);

            releaseObject(pst, rst);
            pst.close();
            rst.close();

        } catch (SQLException e) {
            System.out.println("e" + e);
            noError = false;
        } finally {
            pw.close();
        }

        return noError;
    }
    
    @HandlesEvent("Step3")
    public Resolution downloadFile() {
        String zipname = getNamaReport(reportNo);
        File f = new File(this.getDirPath() + zipname);

        if (f != null) {
            try {
                return new StreamingResolution("text/plain", new FileInputStream(f)).setFilename(zipname);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        
        return null;
    }
    
    public String getNamaReport(String rep) {
        String report = null;
        if (rep.equalsIgnoreCase("1"))
            report = "HSL_R_63_MLK.txt";
        if (rep.equalsIgnoreCase("2"))
            report = "HSL_R_23_MLK.txt";
        if (rep.equalsIgnoreCase("3"))
            report = "HSL_R_24_MLK.txt";
        if (rep.equalsIgnoreCase("4"))
            report = "HSL_R_34_MLK.txt";
            
        return report;
    }
    
    public PrintWriter createFile(String fileName, Date now) {
        String dateFolder = sdfNoSlash.format(now);
        String folderName = "c:\\SPEKS\\" + dateFolder + "\\";

        folderName = getDirPath();

        File f = new File(folderName);

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TextFileReportActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);

        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(TextFileReportActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pw;
    }

    public void createDir(File f) throws IOException {
        if (f.exists()) {
            // do nothing
        } else {
            f.mkdirs();
        }
    }

    private String getDirPath() {
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)
                + getSPEKSPath(null) + File.separator;
        return dirName;
    }

    public void releaseObject(PreparedStatement ps, ResultSet rs) {
        try {
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(TextFileReportActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSPEKSPath(String path) {
        String speksPath = "LAPORAN";

        return speksPath;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String[] getListReport() {
        return listReport;
    }

    public void setListReport(String[] listReport) {
        this.listReport = listReport;
    }

    public List<KodDaerah> getSenaraiDaerah() {
        try {
            String query = "SELECT u from etanah.model.KodDaerah u where u.aktif='Y' order by u.kod";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getBangsa() {
        return bangsa;
    }

    public void setBangsa(String bangsa) {
        this.bangsa = bangsa;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public boolean isFlagJana() {
        return flagJana;
    }

    public void setFlagJana(boolean flagJana) {
        this.flagJana = flagJana;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public List<KodHakmilik> getSenaraiJenisHakmilik() {
        try {
            String query = "SELECT u from etanah.model.KodHakmilik u where u.aktif='Y' order by u.kod";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBangsa> getSenaraiBangsa() {
        try {
            String query = "SELECT u from etanah.model.KodBangsa u where u.aktif='Y' order by u.kod";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
}
