package etanah.view.stripes.hasil;

import java.sql.*;
import etanah.dao.*;
import etanah.model.*;
import java.util.Date;
import java.io.PrintWriter;
import org.hibernate.Session;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;
import com.google.inject.Provider;
import etanah.view.stripes.intg.GenerateSPEKSActionBean;
import java.util.List;

/**
 *
 * @author haqqiem
 * 17 Oct 2012 16:00:35
 *
 */
public class GenerateTerima03 {
    org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GenerateTerima03.class);
    boolean debug = LOG.isDebugEnabled();

    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdfWithSlash = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Inject
    protected Provider<Session> sessionProvider;

    @Inject
    GenerateSPEKSActionBean speks;

    @Inject
    KodBankDAO kodBankDAO;

    public int headerBil(Timestamp startDate, Timestamp endDate, Date now, Pengguna p,
            String otherCriteria, String fileName, Timestamp approxLastDate, List<String> senaraiResit) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();

//        PrintWriter pw = speks.createFile(fileName, now, p);
        int bilangan = 0;
        int m = 0;
        int countb = 0;
        int count = 0;

        boolean noError = true;
        try{
            for (String resit : senaraiResit) {
                LOG.info("::::::::::::::RESIT : "+resit+"::::::::::::::");
                // testing
                PreparedStatement pp = c.prepareStatement(
                        "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                        "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                        "where kd.id_kew_dok = ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_kutip = 'B' and kd.kod_status != 'B' " + //filter resit batal excluded from txt file
                        "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                        "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' " + otherCriteria +
                        "order by cb.id_cara_bayar asc, lpp.id_kew_dok asc", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    pp.setTimestamp(1, startDate);
//                    pp.setTimestamp(2, endDate);
                    pp.setString(1, resit);
                    ResultSet rr = pp.executeQuery();

                    while(rr.next()){
                        count ++;
                        LOG.info("rr.getString(3)  :[Nombor Resit] "+rr.getString(3));
                        LOG.info("rr.getString(10) :[Kod Cara Bayar] "+rr.getString(10));
                    }
                    LOG.info("("+fileName+")rr.count :"+count);

                    //checking working day
                    int loop = 2;
                    Timestamp startDateBefore = startDate;
                    Timestamp endDateBefore = endDate;
                    for(int i=1; i < loop; i++){
                        loop++;
                        long daybeforeS =  startDate.getTime() - ((24*60*60*1000)*i); // 1day = 24hours*60minutes*60seconds*1000
                        Timestamp startDateC = new Timestamp(daybeforeS);
                         long daybeforeF =  endDate.getTime() - ((24*60*60*1000)*i);
                        Timestamp endDateC = new Timestamp(daybeforeF);

                        PreparedStatement ppc = c.prepareStatement(
                            "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                            "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                            "where kd.id_kew_dok = ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_status != 'B' " + //and kd.kod_kutip = 'B' " + //filter resit batal excluded from txt file
                            "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                            "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' " + otherCriteria +
                            "order by cb.id_cara_bayar asc, lpp.id_kew_dok asc");
//                        ppc.setTimestamp(1, startDateC);
//                        ppc.setTimestamp(2, endDateC);
                        ppc.setString(1, resit);
                        ResultSet rrc = ppc.executeQuery();

                        int countc = 0;
                        while(rrc.next()){
                            countc++;
                        }
                        LOG.info("("+fileName+")rrc.count :"+countc+", date : "+startDateC.getDate()+"/"+(startDateC.getMonth()+1)+"/"+(1900+startDateC.getYear()));
                        if(countc  > 0){
                            startDateBefore = startDateC;
                            endDateBefore = endDateC;
                            break;
                        }
                        if(endDateC.toString().equals(approxLastDate.toString())){
                            LOG.info("endDateC == approxLastDate");
                            break;
                        }
                        speks.releaseObject(ppc, rrc);
                    }
                    // finish check working day before

                    PreparedStatement ppb = c.prepareStatement(
                        "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                        "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                        "where kd.id_kew_dok = ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_kutip = 'L' and kd.kod_status != 'B' " + //filter resit batal excluded from txt file
                        "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                        "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' " + otherCriteria +
                        "order by cb.id_cara_bayar asc, lpp.id_kew_dok asc", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    ppb.setTimestamp(1, startDateBefore);
//                    ppb.setTimestamp(2, endDateBefore);
                    ppb.setString(1, resit);
                    ResultSet rrb = ppb.executeQuery();

                    while(rrb.next()){
                        countb ++;
                    }
                    LOG.info("("+fileName+")rrb.countb :"+countb);

                // testing
                // HEADER terima details
//                    if(m ==(senaraiResit.size()-1)){
//                        line.append("0").append(" ")
//                                .append(sdfNoSlash.format(now.getTime())).append(" ")
//                                .append(count+countb).append("\n");
//
//                        pw.print(line.toString());
//                        line.setLength(0);
//                    }
//
//                    if(rr.first()){  //checking if ResultSet is null or not for mod=Biasa
//                        LOG.info("("+fileName+")rr has row");
//                    }
//
//                    if(rrb.first()){ //checking if ResultSet is null or not for mod=Lewat
//                        LOG.info("("+fileName+")rrb has row");
//                    }

                    long currentIdCaraBayar = 0;
                    int bil = 1;

                    rr.beforeFirst();
//                    while (rr.next()) {
//                        long idCaraBayar = rr.getLong(1);
//
//                        // check if has same id_cara_bayar
//                        if(currentIdCaraBayar == idCaraBayar) {}
//                        else {
//                            currentIdCaraBayar = idCaraBayar;
//                            bil = 1;
//                        }
//
//                             //terima03
//                            line.append(startDate.getYear() + 1900).append("|")             // tahun
//                                .append(getJabPenerima(rr.getString(2))).append("|")    // kod jabatan penerima
//                                .append(getPTJPenerima(rr.getString(2))).append("|")    // kod ptj penerima
//                                .append(rr.getString(3)).append("|")                                         // no resit
//                                .append(bil).append("|")                                                                     // bil
//                                .append(rr.getString(5)).append("|")                                        // no rujukan
//                                .append(rr.getDouble(7)).append("|")                                        // amaun
//                                .append(rr.getString(10).equals("T") ? "1" : (rr.getString(10).equals("KW") ? "3" : (rr.getString(10).equals("WP") ? "4": ""))).append("|")
//                                .append(sdfWithSlash.format(rr.getDate(9))).append("\n");// tarikh
//                        }
//
//                        pw.print(line.toString());
//                        line.setLength(0);
//
//                        bil++;

                    //untuk mode lewat
                    rrb.beforeFirst();
//                    while (rrb.next()) {
//                        long idCaraBayar = rrb.getLong(1);
//
//                        // check if has same id_cara_bayar
//                        if(currentIdCaraBayar == idCaraBayar) {}
//                        else {
//                            currentIdCaraBayar = idCaraBayar;
//                            bil = 1;
//                        }
//
//                            // terima03
//                            line.append(startDate.getYear() + 1900).append("|")
//                                .append(getJabPenerima(rrb.getString(2))).append("|")
//                                .append(getPTJPenerima(rrb.getString(2))).append("|")
//                                .append(rrb.getLong(1)).append("|")
//                                .append(bil).append("|")
//                                .append(rrb.getString(5)).append("|")
//                                .append(rrb.getDouble(7)).append("\n")
//                                .append(rr.getString(10).equals("T") ? "1" : (rr.getString(10).equals("KW") ? "3" : (rr.getString(10).equals("WP") ? "4": ""))).append("|")
//                                .append(sdfWithSlash.format(rr.getDate(9))).append("\n");// tarikh
//
//                        pw.print(line.toString());
//                        line.setLength(0);
//
//                        bil++;
//                    }
//
//                    releaseObject(ppb, rrb);
                    m = count+countb;
            }
            bilangan = m;
//            c.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        } finally {
//            pw.close();
        }
        LOG.info("-----------------------bilangan :"+bilangan);
        return bilangan;
    }

    private boolean generateTerima01(Timestamp startDate, Timestamp endDate, Date now, Pengguna p,
            String otherCriteria, String fileName, Timestamp approxLastDate) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("startDate : "+startDate);
        LOG.info("endDate : "+endDate);

        PrintWriter pw = speks.createFile("terima01.txt", now, p);
        boolean noError = true;

        try {
            //testing
            LOG.info("-------here-------");
             PreparedStatement pst = c.prepareStatement(
                     "SELECT DISTINCT kdb.id_kew_dok, SUM(kdb.amaun) AS total, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "FROM kew_dokumen_bayar kdb, kew_dokumen kd WHERE kdb.trh_masuk BETWEEN ? AND ? AND kd.kod_kutip = 'B' AND kdb.id_kew_dok = kd.id_kew_dok " +
                     "GROUP BY kdb.id_kew_dok, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "ORDER BY kdb.trh_masuk ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            ResultSet rst = pst.executeQuery();

            int count = 0;
            while(rst.next()){
                count ++;
            }
            LOG.info("rst.count :"+count);

            //checking working day
            int loop = 2;
            Timestamp startDateBefore = startDate;
            Timestamp endDateBefore = endDate;
            for(int i=1; i < loop; i++){
                loop++;
                long daybeforeS =  startDate.getTime() - ((24*60*60*1000)*i); // 1day = 24hours*60minutes*60seconds*1000
                Timestamp startDateC = new Timestamp(daybeforeS);
                 long daybeforeF =  endDate.getTime() - ((24*60*60*1000)*i);
                Timestamp endDateC = new Timestamp(daybeforeF);

                PreparedStatement ppc = c.prepareStatement(
                    "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                    "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                    "where cb.trh_masuk between ? and ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_status != 'B' " + //and kd.kod_kutip = 'B' " + //filter resit batal excluded from txt file
                    "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                    "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' " + otherCriteria +
                    "order by cb.id_cara_bayar asc, lpp.id_kew_dok asc");
                ppc.setTimestamp(1, startDateC);
                ppc.setTimestamp(2, endDateC);
                ResultSet rrc = ppc.executeQuery();

                int countc = 0;
                while(rrc.next()){
                    countc++;
                }
                LOG.info("("+fileName+")rrc.count :"+countc+", date : "+startDateC.getDate()+"/"+(startDateC.getMonth()+1)+"/"+(1900+startDateC.getYear()));
                if(countc  > 0){
                    startDateBefore = startDateC;
                    endDateBefore = endDateC;
                    break;
                }
                if(endDateC.toString().equals(approxLastDate.toString())){
                    LOG.info("endDateC == approxLastDate");
                    break;
                }
                speks.releaseObject(ppc, rrc);
            }
            // finish check working day before
            
            PreparedStatement psb = c.prepareStatement(
                    "SELECT DISTINCT kdb.id_kew_dok, SUM(kdb.amaun) AS total, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "FROM kew_dokumen_bayar kdb, kew_dokumen kd " +
                     "WHERE kdb.trh_masuk BETWEEN ? AND ? AND kdb.id_kew_dok = kd.id_kew_dok AND kd.kod_kutip = 'L' " +
                     "GROUP BY kdb.id_kew_dok, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw ORDER BY kdb.trh_masuk ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            psb.setTimestamp(1, startDateBefore);
            psb.setTimestamp(2, endDateBefore);
            ResultSet rsb = psb.executeQuery();
            int countb = 0;
            while(rsb.next()){
                countb ++;
            }
            LOG.info("rsb.count :"+countb);
            // HEADER terima01.txt

            line.append("0").append(" ")
                    .append(sdfNoSlash.format(now.getTime())).append(" ")
                    .append(count+countb).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            // CONTENTS terima01.txt
            if(rst.first()){  //checking if ResultSet is null or not for mod=Biasa
                LOG.info("rst has row");
            }

            if(rsb.first()){ //checking if ResultSet is null or not for mod=Lewat
                LOG.info("rsb has row");
            }

            rst.beforeFirst();
            while (rst.next()) {
                String resit = rst.getString(1);
                int row = 0;
                LOG.info("resit : "+resit);

                // row
                PreparedStatement ppRow = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar, cb.kod_cara_bayar " +
                        "FROM cara_bayar cb, kew_dokumen_bayar kdb, kew_dokumen kd WHERE kd.id_kew_dok = ? AND " +
                        "kdb.id_cara_bayar = cb.id_cara_bayar AND kdb.id_kew_dok = kd.id_kew_dok");

                ppRow.setString(1, resit);
                ResultSet rrRow = ppRow.executeQuery();

                boolean flag = true;
                String checking = "";
                String checking1 = "";
                while(rrRow.next()){
                    LOG.info("------------------------------START------------------------------");
                    LOG.info("cara bayar : "+rrRow.getString(2));
                    LOG.info("id cara bayar : "+rrRow.getString(1));
                    LOG.info("flag : "+flag+" :Checking : "+checking);
                    LOG.info("1 - checking : "+checking);
                    LOG.info("2 - checking1 : "+checking1);
                    if(checking.equals(rrRow.getString(2))){
                        LOG.info("----SINI----");
                        checking = rrRow.getString(2);
                        LOG.info("3 - checking : "+checking);
                        flag = false;
                    }

                    checking = rrRow.getString(2);
                    LOG.info("4 - checking : "+checking);
                    LOG.info("5 - checking1 : "+checking1);
                    row++;
                    LOG.info("------------------------------FINISH------------------------------");
                }
                LOG.info("-------------- ROW : "+row);
                if(row==1){flag = false;}

                LOG.info("-------------- FINAL FLAG!!!! -------------- "+flag);

                // detail
                PreparedStatement pp = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar, cb.trh_masuk, cb.kod_cara_bayar, cb.kod_bank, cb.no_ruj, " +
                        "cb.trh_cek, cb.kod_cara_bayar, cb.bank_caw, cb.amaun, cb.kod_caw " +
                        "FROM cara_bayar cb, kew_dokumen_bayar kdb, kew_dokumen kd " +
                        "WHERE kd.id_kew_dok = ? AND cb.kod_cara_bayar != 'J' and kdb.id_cara_bayar = cb.id_cara_bayar AND kdb.id_kew_dok = kd.id_kew_dok " +
                        "ORDER BY cb.id_cara_bayar ASC");

                pp.setString(1, resit);
                ResultSet rr = pp.executeQuery();

                rr.next();

                if(flag){
                    LOG.info("rr.getString(4) : "+rr.getString(4));
                    String kodBankSPEKS = null;
                    if(rr.getString(4) != null){
                        KodBank kb = kodBankDAO.findById(rr.getString(4));
                        kodBankSPEKS = kb.getKeyBank();
                    }
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rst.getString(1)).append("|")       // no resit
                            .append(sdfWithSlash.format(rst.getDate(3))).append("|")    // tarikh urusniaga
                            .append("234").append("|")  // jenis_terimaan
                            .append(speks.getJabPenerima(rst.getString(5))).append("|")   // kod jabatan penerima
                            .append(speks.getPTJPenerima(rst.getString(5))).append("|")   // kod ptj penerima
                            .append("-").append("|")    // no syarikat --etanah xpakai--
                            .append(rst.getString(4) == null ? "" : rst.getString(4)).append("|")   // nama pembayar
                            .append("|")    // alamat 1
                            .append("|")    // alamat 2
                            .append("11").append("|")
//                            .append(rr.getString(4) == null ? "" : kodBankSPEKS).append("|")   // kod bank
                            .append("|")   // kod bank (18/10/2012 POS xpayah kod bank)
                            .append("|")    // jenis kad
                            .append("|")    // no kad
//                            .append(rr.getString(5) == null ? "" : rr.getString(5)).append("|")   // no rujukan
                            .append("|")   // no rujukan (18/10/2012 POS xpayah no rujukan)
                            .append("|")    // cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   //kod modul --eTanah = ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|") // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // tempat
                            .append(rst.getDouble(2)).append("\n"); // amaun
                }else{
                    LOG.info("rr.getString(4) : "+rr.getString(4));
                    String kodBankSPEKS = null;
                    if(rr.getString(4) != null){
                        KodBank kb = kodBankDAO.findById(rr.getString(4));
                        kodBankSPEKS = kb.getKeyBank();
                    }
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rst.getString(1)).append("|")       // no resit
                            .append(sdfWithSlash.format(rst.getDate(3))).append("|")    // tarikh urusniaga
                            .append("234").append("|")  // jenis_terimaan
                            .append(speks.getJabPenerima(rst.getString(5))).append("|")   // kod jabatan penerima
                            .append(speks.getPTJPenerima(rst.getString(5))).append("|")   // kod ptj penerima
                            .append("-").append("|")    // no syarikat --etanah xpakai--
                            .append(rst.getString(4) == null ? "" : rst.getString(4)).append("|")   // nama pembayar
                            .append("|")    // alamat 1
                            .append("|")    // alamat 2
                            .append(rr.getString(3).equals("T") ? "1" : (rr.getString(3).equals("CT") || rr.getString(3).equals("CB")|| rr.getString(3).equals("CL") ? "2" : (rr.getString(3).equals("KW") ? "3" : (rr.getString(3).equals("WP") ? "4" : (rr.getString(3).equals("DB") ? "5" : (rr.getString(3).equals("VS")||rr.getString(3).equals("KK") ? "6" : (rr.getString(3).equals("DK") ? "7" : ""))))))).append("|")
//                            .append(rr.getString(4) == null ? "" : kodBankSPEKS).append("|")   // kod bank
                            .append(rr.getString(4) == null ? "":rr.getString(4).equals("PMB") ? "" : kodBankSPEKS).append("|")   // kod bank (18/10/2012)
                            .append("|")    // jenis kad
                            .append("|")    // no kad
//                            .append(rr.getString(5) == null ? "" : rr.getString(5)).append("|")   // no rujukan
                            .append(rr.getString(5) == null ? "":rr.getString(3).equals("T") ? "" :rr.getString(3).equals("KW") ? "" :rr.getString(3).equals("WP") ? "" : rr.getString(5)).append("|")   // no rujukan (18/10/2012)
                            .append("|")    // cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   //kod modul --eTanah = ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|") // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // tempat
                            .append(rst.getDouble(2)).append("\n"); // amaun
                }

                pw.print(line.toString());
                line.setLength(0);

                speks.releaseObject(pp, rr);
            }

            rsb.beforeFirst();
            while (rsb.next()) {
                String resit = rsb.getString(1);
                int row = 0;

                // row
                PreparedStatement ppRow = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar " +
                        "FROM cara_bayar cb, kew_dokumen_bayar kdb, kew_dokumen kd WHERE kd.id_kew_dok = ? AND " +
                        "kdb.id_cara_bayar = cb.id_cara_bayar AND kdb.id_kew_dok = kd.id_kew_dok");

                ppRow.setString(1, resit);
                ResultSet rrRow = ppRow.executeQuery();

//                while(rrRow.next()){row++;}
                boolean flag = true;
                String checking = "";
                String checking1 = "";
                while(rrRow.next()){
                    LOG.info("------------------------------START------------------------------");
                    LOG.info("cara bayar : "+rrRow.getString(2));
                    LOG.info("id cara bayar : "+rrRow.getString(1));
                    LOG.info("flag : "+flag+" :Checking : "+checking);
                    LOG.info("1 - checking : "+checking);
                    LOG.info("2 - checking1 : "+checking1);
                    if(checking.equals(rrRow.getString(2))){
                        LOG.info("----SINI----");
                        checking = rrRow.getString(2);
                        LOG.info("3 - checking : "+checking);
                        flag = false;
                    }

                    checking = rrRow.getString(2);
                    LOG.info("4 - checking : "+checking);
                    LOG.info("5 - checking1 : "+checking1);
                    row++;
                    LOG.info("------------------------------FINISH------------------------------");
                }
                LOG.info("-------------- ROW : "+row);
                if(row==1){flag = false;}

                LOG.info("-------------- FINAL FLAG!!!! -------------- "+flag);

                //detail
                PreparedStatement pp = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar, cb.trh_masuk, cb.kod_cara_bayar, cb.kod_bank, cb.no_ruj, " +
                        "cb.trh_cek, cb.kod_cara_bayar, cb.bank_caw, cb.amaun, cb.kod_caw " +
                        "FROM cara_bayar cb, kew_dokumen_bayar kdb, kew_dokumen kd " +
                        "WHERE kd.id_kew_dok = ? AND cb.kod_cara_bayar != 'J' and kdb.id_cara_bayar = cb.id_cara_bayar AND kdb.id_kew_dok = kd.id_kew_dok " +
                        "ORDER BY cb.id_cara_bayar ASC");

                pp.setString(1, resit);
                ResultSet rr = pp.executeQuery();

                rr.next();

                if(flag){
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rsb.getString(1)).append("|")     // no resit
                            .append(sdfWithSlash.format(rsb.getDate(3))).append("|")    // tarikh urusniaga
                            .append("234").append("|")  // jenis terimaan
                            .append(speks.getJabPenerima(rsb.getString(5))).append("|")  // kod jabatan penerima
                            .append(speks.getPTJPenerima(rsb.getString(5))).append("|") // kod ptj penerima
                            .append("-").append("|")    // no syarikat
                            .append(rsb.getString(4) == null ? "" : rsb.getString(4)).append("|") // nama pembayar
                            .append("|")    // alamat 1
                            .append("|")    // alamat 2
                            .append("11").append("|")
//                            .append(rr.getString(4) == null ? "" : rr.getString(4)).append("|")   // kod bank
                            .append("|")   // kod bank (18/10/2012 POS xpayah kod bank)
                            .append("|")    // jenis kad
                            .append("|")    // no kad
//                            .append(rr.getString(5) == null ? "" : rr.getString(5)).append("|")   // no rujukan
                            .append("|")   // no rujukan (18/10/2012 POS xpayah no rujukan)
                            .append("|")    //cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   // kod modul --eTanah=ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|")   // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // cawangan
                            .append(rsb.getDouble(2)).append("\n"); // amaun
                }else{
                    LOG.info("rr.getString(4) : "+rr.getString(4));
                    String kodBankSPEKS = null;
                    if(rr.getString(4) != null){
                        KodBank kb = kodBankDAO.findById(rr.getString(4));
                        kodBankSPEKS = kb.getKeyBank();
                    }
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rsb.getString(1)).append("|")     // no resit
                            .append(sdfWithSlash.format(rsb.getDate(3))).append("|")    // tarikh urusniaga
                            .append("234").append("|")  // jenis terimaan
                            .append(speks.getJabPenerima(rsb.getString(5))).append("|")  // kod jabatan penerima
                            .append(speks.getPTJPenerima(rsb.getString(5))).append("|") // kod ptj penerima
                            .append("-").append("|")    // no syarikat
                            .append(rsb.getString(4) == null ? "" : rsb.getString(4)).append("|") // nama pembayar
                            .append("|")    // alamat 1
                            .append("|")    // alamat 2
                            .append(rr.getString(3).equals("T") ? "1" : (rr.getString(3).equals("CT") || rr.getString(3).equals("CB")|| rr.getString(3).equals("CL") ? "2" : (rr.getString(3).equals("KW") ? "3" : (rr.getString(3).equals("WP") ? "4" : (rr.getString(3).equals("DB") ? "5" : (rr.getString(3).equals("VS")||rr.getString(3).equals("KK") ? "6" : (rr.getString(3).equals("DK") ? "7" : ""))))))).append("|")
//                            .append(rr.getString(4) == null ? "" : rr.getString(4)).append("|")   // kod bank
                            .append(rr.getString(4) == null ? "":rr.getString(4).equals("PMB") ? "" : kodBankSPEKS).append("|")   // kod bank (18/10/2012)
                            .append("|")    // jenis kad
                            .append("|")    // no kad
//                            .append(rr.getString(5) == null ? "" : rr.getString(5)).append("|")   // no rujukan
                            .append(rr.getString(5) == null ? "":rr.getString(3).equals("T") ? "" :rr.getString(3).equals("KW") ? "" :rr.getString(3).equals("WP") ? "" : rr.getString(5)).append("|")   // no rujukan (18/10/2012)
                            .append("|")    //cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   // kod modul --eTanah=ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|")   // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // cawangan
                            .append(rsb.getDouble(2)).append("\n"); // amaun
                }

                pw.print(line.toString());
                line.setLength(0);

                speks.releaseObject(pp, rr);
            }

            speks.releaseObject(pst, rst);
            speks.releaseObject(psb, rsb);
            c.close();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }
}
