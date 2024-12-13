/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.intg;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.*;
import etanah.model.*;
import etanah.util.DateUtil;
import java.io.FileNotFoundException;
import java.text.ParseException;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.GenerateTerima03;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import java.util.logging.Logger;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author amir.muhaimin
 * @modified by Izam
 * @modified by haqqiem
 *
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/intg/generate_speks")
public class GenerateSPEKSActionBean extends AbleActionBean {

    private String tarikhMasuk;
    private static Timestamp dateLast;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");

    SimpleDateFormat sdfWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat sdfWithSlash = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");

    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");

    @Inject protected Provider<Session> sessionProvider;
    @Inject etanah.Configuration conf;
    @Inject etanah.view.stripes.intg.GenerateLaporanSPEKSActionBean intg;
    @Inject etanah.kodHasilConfig khConf;
    @Inject PenggunaDAO penggunaDAO;
    @Inject KodBankDAO kodBankDAO;
    @Inject CaraBayaranDAO carabayaranDAO;

    @Inject
    GenerateTerima03 gt3;

    org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GenerateSPEKSActionBean.class);
    boolean debug = LOG.isDebugEnabled();

    private static final String VIEWPAGE = "intg/speks.jsp";
    
    private String downloadLink = "";
    List<String> senaraiResit = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP(VIEWPAGE);
    }

    public String GenerateFilesCron(String dateMasuk){
        String Result = "fail";
//        Pengguna user = penggunaDAO.findById(khConf.getProperty("speks.pengguna.cron")); // hard code for pengguna. make sure in db have user admin
        Pengguna user = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //ikut user yg login
        try{
            Date now = sdfWithTime.parse(tarikhMasuk + " 00:00:00");
            Timestamp startDate = new Timestamp(now.getTime());

            now = sdfWithTime.parse(tarikhMasuk + " 23:59:59");
            Timestamp endDate = new Timestamp(now.getTime());
            LOG.info("start generate txt file.");
            downloadLink = getDirPath(user);
            if(generateTerima01(startDate, endDate, now, user) &&
                generateTerima02(startDate, endDate, now, user) &&
                generateTerima03(startDate, endDate, now, user) &&
                generateJournal01(startDate, endDate, now, user) &&
                generateJournal02(startDate, endDate, now, user) &&
                generateBill01(startDate, endDate, now, user) &&
                generateBill02(startDate, endDate, now, user) &&
                generateBill03(startDate, endDate, now, user)) {

                System.out.println("Penjanaan berjaya. Sila rujuk fail SPEKS.");
                System.out.println("downloadLink :"+downloadLink);

                Result = "success";
            } else {
                System.out.println("Penjanaan tidak berjaya. Sila hubungi pentadbir "
                        + "sistem untuk maklumat lanjut.");
            }
            System.out.println("finish generate txt file.");
        }catch(ParseException pex){
            System.out.println("(GenerateFilesCron) pex:"+pex);
            pex.printStackTrace(); // for development only
        }
        return Result;
    }

    public Resolution generateFiles() throws ParseException, IOException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna user = ctx.getUser();

        if (tarikhMasuk == null) {
            addSimpleError("Sila Masukkan Tarikh Berkenaan.");
            
        } else {
            Date now = sdfWithTime.parse(tarikhMasuk + " 00:00:00");
            Timestamp startDate = new Timestamp(now.getTime());

            now = sdfWithTime.parse(tarikhMasuk + " 23:59:59");
            Timestamp endDate = new Timestamp(now.getTime());
            LOG.info("start generate txt file.");
            downloadLink = getDirPath(user);
            if(generateTerima01(startDate, endDate, now, user) &&
                generateTerima02(startDate, endDate, now, user) &&
                generateTerima03(startDate, endDate, now, user) &&
                generateJournal01(startDate, endDate, now, user) &&
                generateJournal02(startDate, endDate, now, user) &&
                generateBill01(startDate, endDate, now, user) &&
                generateBill02(startDate, endDate, now, user) &&
                generateBill03(startDate, endDate, now, user)) {

                addSimpleMessage("Penjanaan berjaya. Sila rujuk fail SPEKS.");
                LOG.info("downloadLink :"+downloadLink);
                LOG.info("senaraiResit.size() :"+senaraiResit.size());

                for (String resit : senaraiResit) {
                    LOG.info("resit : "+resit);
                }

            } else {
                addSimpleError("Penjanaan tidak berjaya. Sila hubungi pentadbir "
                        + "sistem untuk maklumat lanjut.");
            }
            LOG.info("finish generate txt file.");
        }

        return new JSP(VIEWPAGE);
    }

    private boolean generateTerima01(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) throws ParseException {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("startDate : "+startDate);
        LOG.info("endDate : "+endDate);
        String trh_mula ="";
        String trh_akhir ="";

        PrintWriter pw = createFile("terima01.txt", now, p);
        boolean noError = true;

        try {
            //testing
            LOG.info("-------here-------");
             PreparedStatement pst = c.prepareStatement(
                     "SELECT DISTINCT kdb.id_kew_dok, SUM(kdb.amaun) AS total, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "FROM kew_dokumen_bayar kdb, kew_dokumen kd WHERE kdb.trh_masuk BETWEEN ? AND ? " +
                     "AND kd.kod_kutip = 'B' AND kdb.id_kew_dok = kd.id_kew_dok AND kdb.dimasuk != 'portal' " +
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
            
            //check working day before
            int loop = 2;
            Timestamp startDateBefore = startDate;
            Timestamp endDateBefore = endDate;
            for(int i=1; i < loop; i++){
                loop++;
                long daybeforeS =  startDate.getTime() - ((24*60*60*1000)*i); // 1day = 24hours*60minutes*60seconds*1000
                Timestamp startDateC = new Timestamp(daybeforeS);
                 long daybeforeF =  endDate.getTime() - ((24*60*60*1000)*i);
                Timestamp endDateC = new Timestamp(daybeforeF);

                PreparedStatement pstf = c.prepareStatement(
                     "SELECT DISTINCT kdb.id_kew_dok, SUM(kdb.amaun) AS total, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "FROM kew_dokumen_bayar kdb, kew_dokumen kd " +
                     "WHERE kdb.trh_masuk BETWEEN ? AND ? AND kdb.id_kew_dok = kd.id_kew_dok AND kdb.dimasuk != 'portal' " +
                     "GROUP BY kdb.id_kew_dok, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw ORDER BY kdb.trh_masuk ASC");
                pstf.setTimestamp(1, startDateC);
                pstf.setTimestamp(2, endDateC);
                ResultSet rstf = pstf.executeQuery();
                int countf = 0;
                while(rstf.next()){
                    countf++;
                }
                LOG.info("rstf.count :"+countf+", date : "+startDateC.getDate()+"/"+(startDateC.getMonth()+1)+"/"+(1900+startDateC.getYear()));
                LOG.info("daybeforeS : "+startDateC);
                LOG.info("daybeforeF : "+endDateC);
                trh_mula = sdf.format(startDate);
                trh_akhir = sdf.format(endDateC);
                LOG.info("trh_mula : "+trh_mula+" trh_akhir : "+trh_akhir);
                if(countf  > 0){
                    LOG.info("hahahaha");
                    startDateBefore = startDateC;
                    endDateBefore = endDateC;
                    dateLast = endDateBefore;
                    break;
                }
                releaseObject(pstf, rstf);
            }
            // finish check working day before
            PreparedStatement psb = c.prepareStatement(
                    "SELECT DISTINCT kdb.id_kew_dok, SUM(kdb.amaun) AS total, kdb.trh_masuk, kd.isu_kpd, kd.kod_caw " +
                     "FROM kew_dokumen_bayar kdb, kew_dokumen kd " +
                     "WHERE kdb.trh_masuk BETWEEN ? AND ? AND kdb.id_kew_dok = kd.id_kew_dok AND kd.kod_kutip = 'L' AND kdb.dimasuk != 'portal' " +
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
                PreparedStatement pp = null;
                ResultSet rr = null;
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
                    }else{flag = true;}
                    
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
                pp = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar, cb.trh_masuk, cb.kod_cara_bayar, cb.kod_bank, cb.no_ruj, " +
                        "cb.trh_cek, cb.kod_cara_bayar, cb.bank_caw, cb.amaun, cb.kod_caw " +
                        "FROM cara_bayar cb, kew_dokumen_bayar kdb, kew_dokumen kd " +
                        "WHERE kd.id_kew_dok = ? AND cb.kod_cara_bayar != 'J' and kdb.id_cara_bayar = cb.id_cara_bayar AND kdb.id_kew_dok = kd.id_kew_dok " +
                        "ORDER BY cb.id_cara_bayar ASC");

                pp.setString(1, resit);
                rr = pp.executeQuery();

                rr.next();

                String kodBankSPEKS = null;
                String kodTerimaan = "234";
                if(rr.getString(4) != null){
                    PreparedStatement pSPEKS = c.prepareStatement("SELECT kb.bank_key FROM kod_bank kb WHERE kb.kod = ?");
                    pSPEKS.setString(1, rr.getString(4));
                    ResultSet rSPEKS = pSPEKS.executeQuery();
                    rSPEKS.next();
                    kodBankSPEKS = rSPEKS.getString(1);
                    LOG.info("dalam ni : "+kodBankSPEKS);
                }
                if(rst.getString(1) != null){
                    PreparedStatement pAmanah = c.prepareStatement("SELECT kkt.amanah FROM kew_trans kt, kod_kew_trans kkt WHERE kt.id_kew_dok = ?"
                            + "AND kt.kod_trans = kkt.kod");
                    pAmanah.setString(1, rst.getString(1));
                    ResultSet rAmanah = pAmanah.executeQuery();
                    rAmanah.next();
                    String aa = rAmanah.getString(1);
                    if(aa.equals("Y")){
                        LOG.info("==HERE==");
                        kodTerimaan = "240";
                    }
                }
                LOG.info("------------------- kodTerimaan : "+kodTerimaan);
                if(flag){
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rst.getString(1)).append("|")       // no resit
                            .append(sdfWithSlash.format(rst.getDate(3))).append("|")    // tarikh urusniaga
//                            .append("234").append("|")  // jenis_terimaan
                            .append(kodTerimaan).append("|")  // jenis_terimaan       --19.03.2014
                            .append(getJabPenerima(rst.getString(5))).append("|")   // kod jabatan penerima
                            .append(getPTJPenerima(rst.getString(5))).append("|")   // kod ptj penerima
                            .append("-").append("|")    // no syarikat --etanah xpakai--    26/03/2014 -ic mandatory
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
                        senaraiResit.add(resit);

                    LOG.info("--sini--");
                }else{
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rst.getString(1)).append("|")       // no resit
                            .append(sdfWithSlash.format(rst.getDate(3))).append("|")    // tarikh urusniaga
//                            .append("234").append("|")  // jenis_terimaan
                            .append(kodTerimaan).append("|")  // jenis_terimaan       --19.03.2014
                            .append(getJabPenerima(rst.getString(5))).append("|")   // kod jabatan penerima
                            .append(getPTJPenerima(rst.getString(5))).append("|")   // kod ptj penerima
                            .append("-").append("|")    // no syarikat --etanah xpakai--    26/03/2014 -ic mandatory
                            .append(rst.getString(4) == null ? "" : rst.getString(4)).append("|")   // nama pembayar
                            .append("|")    // alamat 1
                            .append("|")    // alamat 2
                            .append(rr.getString(3).equals("T") ? "1" : (rr.getString(3).equals("CT") || rr.getString(3).equals("CB")|| rr.getString(3).equals("CL") ? "2" : (rr.getString(3).equals("KW") ? "3" : (rr.getString(3).equals("WP") ? "4" : (rr.getString(3).equals("DB") ? "5" : (rr.getString(3).equals("VS")||rr.getString(3).equals("KK") ? "6" : (rr.getString(3).equals("DK") ? "7" : ""))))))).append("|")
//                            .append(rr.getString(4) == null ? "" : kodBankSPEKS).append("|")   // kod bank
                            .append(rr.getString(4) == null ? "":rr.getString(4).equals("PMB") ? "" : kodBankSPEKS).append("|")   // kod bank (18/10/2012)
                            .append("|")    // jenis kad
                            .append("|")    // no kad
//                            .append(rr.getString(5) == null ? "" : rr.getString(5)).append("|")   // no rujukan
                            .append(rr.getString(3).equals("T") ? "" :rr.getString(3).equals("KW") ? "" :rr.getString(3).equals("WP") ? "":rr.getString(3).equals("CT") ? "":rr.getString(3).equals("CL") ? "" : rr.getString(5)).append("|")   // no rujukan (18/10/2012)
                            .append(rr.getString(7).equals("CT") ? rr.getString(5) : (rr.getString(7).equals("CL") ? rr.getString(5) : "")).append("|")    // cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   //kod modul --eTanah = ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|") // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // tempat
                            .append(rst.getDouble(2)).append("\n"); // amaun

                    LOG.info("--situ--");
                    if((rr.getString(3).equals("KW"))||rr.getString(3).equals("WP")){
                        senaraiResit.add(resit);
                    }
                }

                pw.print(line.toString());
                line.setLength(0);

//                releaseObject(pp, rr);
                pp.close();
                rr.close();
//                rst.close();
            }

            rsb.beforeFirst();
            while (rsb.next()) {
                String resit = rsb.getString(1);
                int row = 0;

                // row
                PreparedStatement ppRow = c.prepareStatement(
                        "SELECT DISTINCT cb.id_cara_bayar, cb.kod_cara_bayar " +
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
                String kodBankSPEKS = null;
                String kodTerimaan = "234";
                if(rr.getString(4) != null){
                    PreparedStatement pSPEKS = c.prepareStatement("SELECT kb.bank_key FROM kod_bank kb WHERE kb.kod = ?");
                    pSPEKS.setString(1, rr.getString(4));
                    ResultSet rSPEKS = pSPEKS.executeQuery();
                    rSPEKS.next();
                    kodBankSPEKS = rSPEKS.getString(1);
                    LOG.info("dalam ni : "+kodBankSPEKS);
                }
                
                
                if(rsb.getString(1) != null){
                    PreparedStatement pAmanah = c.prepareStatement("SELECT kkt.amanah FROM kew_trans kt, kod_kew_trans kkt WHERE kt.id_kew_dok = ?"
                            + "AND kt.kod_trans = kkt.kod");
                    pAmanah.setString(1, rsb.getString(1));
                    ResultSet rAmanah = pAmanah.executeQuery();
                    rAmanah.next();
                    String aa = rAmanah.getString(1);
                    if(aa.equals("Y")){
                        LOG.info("==HERE==");
                        kodTerimaan = "240";
                    }
                }
                LOG.info("kodTerimaan : "+kodTerimaan);

                if(flag){
                    senaraiResit.add(resit);
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rsb.getString(1)).append("|")     // no resit
                            .append(sdfWithSlash.format(rsb.getDate(3))).append("|")    // tarikh urusniaga
                            .append(kodTerimaan).append("|")  // jenis terimaan
                            .append(getJabPenerima(rsb.getString(5))).append("|")  // kod jabatan penerima
                            .append(getPTJPenerima(rsb.getString(5))).append("|") // kod ptj penerima
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
                    line.append(startDate.getYear() + 1900).append("|")     // tahun
                            .append(rsb.getString(1)).append("|")     // no resit
                            .append(sdfWithSlash.format(rsb.getDate(3))).append("|")    // tarikh urusniaga
                            .append(kodTerimaan).append("|")  // jenis terimaan
                            .append(getJabPenerima(rsb.getString(5))).append("|")  // kod jabatan penerima
                            .append(getPTJPenerima(rsb.getString(5))).append("|") // kod ptj penerima
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
                            .append(rr.getString(3).equals("T") ? "" :rr.getString(3).equals("KW") ? "" :rr.getString(3).equals("WP") ? "":rr.getString(3).equals("CT") ? "":rr.getString(3).equals("CL") ? "" : rr.getString(5)).append("|")   // no rujukan (18/10/2012)
                            .append(rr.getString(7).equals("CT") ? rr.getString(5) : (rr.getString(7).equals("CL") ? rr.getString(5) : "")).append("|")    //cek pos
                            .append(rr.getDate(6) == null ? "" : sdf.format(rr.getDate(6))).append("|")   // tarikh cek
                            .append("ET").append("|")   // kod modul --eTanah=ET--
                            .append(rr.getString(7).equals("CT") ? "1" : (rr.getString(7).equals("CL") ? "2" : "")).append("|")   // jenis cek
                            .append(rr.getString(8) == null ? "" : rr.getString(8)).append("|")   // cawangan
                            .append(rsb.getDouble(2)).append("\n"); // amaun
                    if((rr.getString(3).equals("KW"))||rr.getString(3).equals("WP")){
                        senaraiResit.add(resit);
                    }
                }
                LOG.info("senaraiResit.size() : "+senaraiResit);

                pw.print(line.toString());
                line.setLength(0);

                releaseObject(pp, rr);
            }

            releaseObject(pst, rst);
            releaseObject(psb, rsb);
            c.close();
//            intg.LaporanGenerator(trh_akhir, trh_mula, p);

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateTerima02(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        LOG.info("(generateTerima02)dateLast :"+dateLast.toString());
        return generateTerimaDetails(startDate, endDate, now, p, "terima02.txt",dateLast);
    }
    
    private boolean generateTerima03(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        LOG.info("(generateTerima03)dateLast :"+dateLast.toString());
        return generateTerimaDetails03(startDate, endDate, now, p,
                "and (cb.kod_cara_bayar = 'KW' or cb.kod_cara_bayar = 'WP' or cb.kod_cara_bayar = 'T')", "terima03.txt",dateLast);
    }

    private boolean generateTerimaDetails(Timestamp startDate, Timestamp endDate, Date now, Pengguna p, String fileName, Timestamp approxLastDate) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();

        PrintWriter pw = createFile(fileName, now, p);

        boolean noError = true;
        try{
        // testing
        PreparedStatement pp = c.prepareStatement(
                "SELECT kd.id_kew_dok, kkt.kod, lpp.amaun, kkt.nama, kd.kod_caw, kkt.amanah " +
                "FROM kew_dokumen kd, lapor_pnyata_pmungut lpp, kod_kew_trans kkt, kew_trans kt " +
                "WHERE kd.trh_masuk BETWEEN ? AND ? " +
                "AND kd.id_kew_dok = lpp.id_kew_dok AND lpp.id_trans = kt.id_trans AND kt.kod_trans = kkt.kod AND kd.kod_kutip = 'B' AND kt.AMAUN > 0 " +
                "ORDER BY kd.id_kew_dok ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pp.setTimestamp(1, startDate);
            pp.setTimestamp(2, endDate);
            ResultSet rr = pp.executeQuery();

            int count = 0;
            while(rr.next()){
                count ++;
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
                    "SELECT kd.id_kew_dok, kkt.kod, lpp.amaun, kkt.nama, kd.kod_caw, kkt.amanah " +
                    "FROM kew_dokumen kd, lapor_pnyata_pmungut lpp, kod_kew_trans kkt, kew_trans kt " +
                    "WHERE kd.trh_masuk BETWEEN ? AND ? " +
                    "AND kd.id_kew_dok = lpp.id_kew_dok AND lpp.id_trans = kt.id_trans AND kt.kod_trans = kkt.kod AND kt.AMAUN > 0 " +
                    "ORDER BY kd.id_kew_dok ASC");
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
                releaseObject(ppc, rrc);
            }
            // finish check working day before

            PreparedStatement ppb = c.prepareStatement(
                "SELECT kd.id_kew_dok, kkt.kod, lpp.amaun, kkt.nama, kd.kod_caw, kkt.amanah " +
                "FROM kew_dokumen kd, lapor_pnyata_pmungut lpp, kod_kew_trans kkt, kew_trans kt " +
                "WHERE kd.trh_masuk BETWEEN ? AND ? " +
                "AND kd.id_kew_dok = lpp.id_kew_dok AND lpp.id_trans = kt.id_trans AND kt.kod_trans = kkt.kod AND kd.kod_kutip = 'L' AND kt.AMAUN > 0 " +
                "ORDER BY kd.id_kew_dok ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppb.setTimestamp(1, startDateBefore);
            ppb.setTimestamp(2, endDateBefore);
            ResultSet rrb = ppb.executeQuery();

            int countb = 0;
            while(rrb.next()){
                countb ++;
            }
            LOG.info("("+fileName+")rrb.countb :"+countb);

        // testing
        // HEADER terima details
            line.append("0").append(" ")
                    .append(sdfNoSlash.format(now.getTime())).append(" ")
                    .append(count+countb).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            if(rr.first()){  //checking if ResultSet is null or not for mod=Biasa
                LOG.info("("+fileName+")rr has row");
            }

            if(rrb.first()){ //checking if ResultSet is null or not for mod=Lewat
                LOG.info("("+fileName+")rrb has row");
            }

            long currentIdCaraBayar = 0;
            int bil = 1;

            rr.beforeFirst();
            while (rr.next()) {
                long idCaraBayar = rr.getLong(1);

                // check if has same id_cara_bayar
                if(currentIdCaraBayar == idCaraBayar) {}
                else {
                    currentIdCaraBayar = idCaraBayar;
                    bil = 1;
                }

                     // terima02
                    line.append(startDate.getYear() + 1900).append("|")             // tahun
                        .append(getJabPenerima(rr.getString(5))).append("|")    // kod jabatan penerima
                        .append(getPTJPenerima(rr.getString(5))).append("|")    // kod ptj penerima
                        .append(rr.getString(1)).append("|")                                         // no resit
                        .append(bil).append("|")                                                                     // bil
                        .append("|")                                                                                                 // vot
                        .append(getJabPenerima(rr.getString(5))).append("|")    // kod jabatan
                        .append(getPTJPenerima(rr.getString(5))).append("|")    // kod ptj
                        .append("|")                                                                                                 // prog_akt
//                        .append("|")                                                                                                 // amanah
                        .append(rr.getString(6).equals("Y") ? rr.getString(2) : "").append("|") // amanah
                        .append("|")                                                                                                 // projek
                        .append("|")                                                                                                 // setia
                        .append("|")                                                                                                 // cp
//                        .append(rr.getString(2)).append("|")                                         // kod hasil
                        .append((rr.getString(6).equals("T") ? rr.getString(2) : "")).append("|") // kod hasil
                        .append(rr.getDouble(3)).append("|")                                         // amaun
                        .append(rr.getString(4)).append("\n");                                    // perihal

                pw.print(line.toString());
                line.setLength(0);

                bil++;
            }

            //untuk mode lewat
            rrb.beforeFirst();
            while (rrb.next()) {
                long idCaraBayar = rrb.getLong(1);

                // check if has same id_cara_bayar
                if(currentIdCaraBayar == idCaraBayar) {}
                else {
                    currentIdCaraBayar = idCaraBayar;
                    bil = 1;
                }

                    // terima02
                    line.append(startDate.getYear() + 1900).append("|")                 // tahun
                        .append(getJabPenerima(rrb.getString(5))).append("|")     // kod jabatan penerima
                        .append(getPTJPenerima(rrb.getString(5))).append("|")    // kod ptj penerima
                        .append(rrb.getString(1)).append("|")                                              // no resit
                        .append(bil).append("|")                                                                       // bil
                        .append("|")                                                                                                   // vot
                        .append(getJabPenerima(rrb.getString(5))).append("|")   // kod jabatan
                        .append(getPTJPenerima(rrb.getString(5))).append("|")   // kod ptj
                        .append("|")                                                                                                  // prog_akt
//                        .append("|")                                                                                                  // amanah
                        .append(rrb.getString(6).equals("Y") ? rrb.getString(2) : "").append("|") // amanah
                        .append("|")                                                                                                  // projek
                        .append("|")                                                                                                  // setia
                        .append("|")                                                                                                  // cp
//                        .append(rrb.getString(2)).append("|")                                       // kod hasil
                        .append(rrb.getString(6).equals("T") ? rrb.getString(2) : "").append("|") // kod hasil
                        .append(rrb.getDouble(3)).append("|")                                       // amaun
                        .append(rrb.getString(4)).append("\n");                                  // perihal

                pw.print(line.toString());
                line.setLength(0);

                bil++;
            }

            releaseObject(ppb, rrb);
            c.close();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateTerimaDetails03(Timestamp startDate, Timestamp endDate, Date now, Pengguna p,
            String otherCriteria, String fileName, Timestamp approxLastDate) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();

        PrintWriter pw = createFile(fileName, now, p);
        int m = 0;
        int countb = 0;
        int count = 0;

        boolean noError = true;
        try{
            int bilangan = gt3.headerBil(startDate, endDate, now, p,
                "and (cb.kod_cara_bayar = 'KW' or cb.kod_cara_bayar = 'WP' or cb.kod_cara_bayar = 'T')", "terima03.txt",dateLast,senaraiResit);
            LOG.info("bilangan -----------------------------"+bilangan);
            if(bilangan==0){
                LOG.info("---------------SINI---------------");
                line.append("0").append(" ")
                                .append(sdfNoSlash.format(now.getTime())).append(" ")
                                .append(bilangan).append("\n");

                        pw.print(line.toString());
                        line.setLength(0);
            }
            for (String resit : senaraiResit) {
                LOG.info("::::::::::::::RESIT : "+resit+"::::::::::::::");
                // testing
                PreparedStatement pp = c.prepareStatement(
                        "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                        "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                        "where kd.id_kew_dok = ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_kutip = 'B' and kd.kod_status != 'B' " + //filter resit batal excluded from txt file
                        "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                        "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' and kt.AMAUN > 0 " + otherCriteria +
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
                            "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' and kt.AMAUN > 0 " + otherCriteria +
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
                        releaseObject(ppc, rrc);
                    }
                    // finish check working day before

                    PreparedStatement ppb = c.prepareStatement(
                        "select cb.id_cara_bayar, cb.kod_caw, lpp.id_kew_dok, kdb.id_kdb, cb.no_ruj, kt.kod_trans, lpp.amaun, kkt.nama, cb.trh_masuk, cb.kod_cara_bayar " +
                        "from lapor_pnyata_pmungut lpp, cara_bayar cb, kew_dokumen_bayar kdb, kew_trans kt, kod_kew_trans kkt, kew_dokumen kd " +
                        "where kd.id_kew_dok = ? and cb.id_cara_bayar = kdb.id_cara_bayar and kdb.id_kew_dok = kd.id_kew_dok and kd.kod_kutip = 'L' and kd.kod_status != 'B' " + //filter resit batal excluded from txt file
                        "and kt.id_trans = lpp.id_trans and kkt.kod = kt.kod_trans and " +
                        "kdb.id_kdb = lpp.id_kdb and cb.kod_cara_bayar != 'J' and kt.AMAUN > 0 " + otherCriteria +
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
                    if(m==0){
                        line.append("0").append(" ")
                                .append(sdfNoSlash.format(now.getTime())).append(" ")
                                .append(bilangan).append("\n");

                        pw.print(line.toString());
                        line.setLength(0);
                    }

                    if(rr.first()){  //checking if ResultSet is null or not for mod=Biasa
                        LOG.info("("+fileName+")rr has row");
                    }

                    if(rrb.first()){ //checking if ResultSet is null or not for mod=Lewat
                        LOG.info("("+fileName+")rrb has row");
                    }

                    long currentIdCaraBayar = 0;
                    int bil = 1;

                    rr.beforeFirst();
                    while (rr.next()) {
                    long idCaraBayar = rr.getLong(1);

                    // check if has same id_cara_bayar
//                    if (currentIdCaraBayar == idCaraBayar) {}
//                    else {
//                        currentIdCaraBayar = idCaraBayar;
//                        bil = 1;
//                    }

                    //terima03
                    line.append(startDate.getYear() + 1900).append("|") // tahun
                            .append(getJabPenerima(rr.getString(2))).append("|") // kod jabatan penerima
                            .append(getPTJPenerima(rr.getString(2))).append("|") // kod ptj penerima
                            .append(rr.getString(3)).append("|") // no resit
                            .append(bil).append("|") // bil
                            .append(rr.getString(5) == null ? "-" : rr.getString(5)).append("|") // no rujukan
                            .append(rr.getDouble(7)).append("|") // amaun
                            .append(rr.getString(10).equals("T") ? "1" : (rr.getString(10).equals("KW") ? "3" : (rr.getString(10).equals("WP") ? "4" : ""))).append("|").append(sdfWithSlash.format(rr.getDate(9))).append("\n");// tarikh

                    pw.print(line.toString());
                    line.setLength(0);

                    bil++;
                }

                    //untuk mode lewat
                    rrb.beforeFirst();
                    while (rrb.next()) {
                        long idCaraBayar = rrb.getLong(1);

                        // check if has same id_cara_bayar
//                        if(currentIdCaraBayar == idCaraBayar) {}
//                        else {
//                            currentIdCaraBayar = idCaraBayar;
//                            bil = 1;
//                        }

                            // terima03
                            line.append(startDate.getYear() + 1900).append("|")
                                .append(getJabPenerima(rrb.getString(2))).append("|")
                                .append(getPTJPenerima(rrb.getString(2))).append("|")
                                .append(rrb.getString(3)).append("|")
                                .append(bil).append("|")
                                .append(rrb.getString(5) == null ? "-":rrb.getString(5)).append("|")
                                .append(rrb.getDouble(7)).append("|")
                                .append(rrb.getString(10).equals("T") ? "1" : (rrb.getString(10).equals("KW") ? "3" : (rrb.getString(10).equals("WP") ? "4": ""))).append("|")
                                .append(sdfWithSlash.format(rrb.getDate(9))).append("\n");// tarikh

                        pw.print(line.toString());
                        line.setLength(0);

                        bil++;
                    }

                    releaseObject(ppb, rrb);
                    m++;
            }
            c.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }
//        reportSPEKS.LaporanGenerator("19/10/2012", "20/10/2012");
        return noError;
    }

    private boolean generateBill01(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("%%%%%% INSIDE generateBill01 %%%%%%");
        LOG.info("startDate : "+startDate);
        LOG.info("endDate : "+endDate);
        String trh_mula ="";
        String trh_akhir ="";
        
        PrintWriter pw = createFile("bill01.txt", now, p);

        boolean noError = true;

        try {
//            commnet tarikh masuk 18/12/2013
//            PreparedStatement pst = c.prepareStatement(
//                    "SELECT DISTINCT mhp.id_mohon, mhp.trh_masuk, mhp.cara_bayar, SUM(mhp.amaun), kc.kod_ptj, kc.kod_jab_spek, mhp.catatan "
//                    + "FROM mohon_pihak_pendeposit mhp, kod_caw kc "
//                    + "WHERE mhp.trh_masuk BETWEEN ? AND ? AND mhp.kod_caw = kc.kod "
//                    + "GROUP BY mhp.id_mohon, mhp.trh_masuk, mhp.cara_bayar, kc.kod_ptj, kc.kod_jab_spek, mhp.catatan "
//                    + "ORDER BY mhp.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            PreparedStatement pst = c.prepareStatement(
                    "SELECT DISTINCT mhp.id_mohon, mhp.cara_bayar, SUM(mhp.amaun), kc.kod_ptj, kc.kod_jab_spek, mhp.catatan, mhp.kod_deposit_speks  "
                    + "FROM mohon_pihak_pendeposit mhp, kod_caw kc "
                    + "WHERE mhp.trh_masuk BETWEEN ? AND ? AND mhp.kod_caw = kc.kod "
                    + "GROUP BY mhp.id_mohon, mhp.cara_bayar, kc.kod_ptj, kc.kod_jab_spek, mhp.catatan, mhp.kod_deposit_speks  "
                    , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            ResultSet rst = pst.executeQuery();//get unique id_mohon

                trh_mula = sdf.format(startDate);
                trh_akhir = sdf.format(endDate);

            int count = 0;
            while (rst.next()) {
                count++;
            }
            LOG.info("rst.count :" + count);
            // HEADER bill01.txt
            line.append("0").append(" ").append(sdfNoSlash.format(now.getTime())).append(" ").append(count).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            // CONTENTS bill01.txt
            if (rst.first()) {  //checking if ResultSet is null or not for mod=Biasa
                LOG.info("rst has row");
            }

            rst.beforeFirst();
            while (rst.next()) {
                String resit = rst.getString(1);
                int row = 0;
                LOG.info("id_mohon : " + resit);

                // row
                PreparedStatement ppRow = c.prepareStatement(
                        "SELECT mpp.amaun " +
                        "FROM mohon_pihak_pendeposit mpp " +
                        "WHERE mpp.id_mohon = ?");

                ppRow.setString(1, resit);
                ResultSet rrRow = ppRow.executeQuery();

                while(rrRow.next()){row++;}
                LOG.info("row : "+row);

                line.append(startDate.getYear() + 1900).append("|") //  1.tahun
//                        .append("231").append("|") //  2.Transaction Type 211: Bill Biasa 231:Pulangan Deposit ::ASAl::
                        .append(rst.getString(7)).append("|") //  2.Transaction Type 211: Bill Biasa 231:Pulangan Deposit
                        .append(rst.getString(1)).append("|") //  3.Bill No.
                        .append(sdfNoSlash.format(startDate)).append("|") //  4.Transaction Date
                        .append(rst.getDouble(3)).append("|") //  5.Transaction Amount
                        .append(rst.getString(5)).append("|") //  6.Jab Bayar
                        .append(rst.getString(4)).append("|") //  7.PTJ Bayar
                        .append(sdfNoSlash.format(startDate)).append("|") //  8.Invoice Date
                        .append(sdfNoSlash.format(startDate)).append("|") //  9.Invoice Date
                        .append(sdfNoSlash.format(startDate)).append("|") // 10.Invoice Date
                        .append("N").append("|") // 11.AP58 Flag
                        .append("").append("|") // 12.AP58 Year
                        .append(rst.getString(6)).append("|") // 13.Voucher Desc.
                        .append(rst.getString(2).equals("CT") ? "1" : (rst.getString(2).equals("EF") ? "3" : (rst.getString(2).equals("DB") ? "4": ""))).append("|") // 14.Payment Flag
                        .append(row).append("|") // 15.Sum of Cheque
                        .append("N").append("\n");// 16.AP96 Flag

                pw.print(line.toString());
                line.setLength(0);

            }
                releaseObject(pst, rst);
                intg.LaporanGeneratorBaucar(trh_akhir, trh_mula, p);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        }finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateBill02(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("%%%%%% INSIDE generateBill02 %%%%%%");
        LOG.info("startDate : " + startDate);
        LOG.info("endDate : " + endDate);
        String trh_mula ="";
        String trh_akhir ="";

        PrintWriter pw = createFile("bill02.txt", now, p);

        boolean noError = true;

        try {
            PreparedStatement pst = c.prepareStatement(
                    "SELECT mhp.id_mohon, mhp.trh_masuk, mhp.cara_bayar, kc.kod_ptj, kc.kod_jab_spek, mhp.catatan, mhp.amaun, kt.kod_trans "
                    + "FROM mohon_pihak_pendeposit mhp, kod_caw kc, kew_trans kt "
                    + "WHERE mhp.trh_masuk BETWEEN ? AND ? AND mhp.kod_caw = kc.kod AND mhp.id_mohon = kt.no_akaun_kr "
                    + "ORDER BY mhp.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            ResultSet rst = pst.executeQuery();//get unique id_mohon
                trh_mula = sdf.format(startDate);
                trh_akhir = sdf.format(endDate);
                LOG.info("trh_mula : "+trh_mula+" trh_akhir : "+trh_akhir);

            int count = 0;
            int bil = 1;
            while (rst.next()) {
                count++;
            }
            // HEADER bill02.txt
            line.append("0").append(" ").append(sdfNoSlash.format(now.getTime())).append(" ").append(count).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            // CONTENTS bill02.txt
            if (rst.first()) {  //checking if ResultSet is null or not for mod=Biasa
                LOG.info("rst has row");
            }

            String currentResit = "";
            rst.beforeFirst();
            while (rst.next()) {
                String resit = rst.getString(1);
                int row = 0;
                LOG.info("id_mohon : " + resit);

                // check if has same id_cara_bayar
                if(currentResit.equals(resit)) {}
                else {
                    currentResit = resit;
                    bil = 1;
                }

                line.append(startDate.getYear() + 1900).append("|") //  1.tahun
                        .append(rst.getString(1)).append("|") //  2.Bill No.
                        .append(bil).append("|") //  3.Counter
                        .append("").append("|") //  4.Vot
                        .append(rst.getString(5)).append("|") //  5.Jab Bayar
                        .append(rst.getString(4)).append("|") //  6.PTJ Bayar
                        .append("").append("|") //  7.Program Act.
//                        .append("79501").append("|") //  8.Amanah
                        .append(rst.getString(8)).append("|") //  8.Amanah
                        .append("").append("|") // 9.Projek
                        .append("").append("|") // 10.Setia
                        .append("").append("|") // 11.CP
                        .append("").append("|") // 12.kod Barang
                        .append(rst.getDouble(7)).append("|") // 13.Amaun Bil
                        .append(rst.getString(5)).append("|") // 14.Jab_Bayar
                        .append(rst.getString(4)).append("\n");// 15.PTJ_Bayar

                pw.print(line.toString());
                line.setLength(0);

                bil++;

            }
                releaseObject(pst, rst);

            intg.LaporanGenerator(trh_akhir, trh_mula, p);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        }finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateBill03(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("%%%%%% INSIDE generateBill03 %%%%%%");
        LOG.info("startDate : " + startDate);
        LOG.info("endDate : " + endDate);

        PrintWriter pw = createFile("bill03.txt", now, p);

        boolean noError = true;

        try {
            PreparedStatement pst = c.prepareStatement(
                    "SELECT mhp.id_mohon, mhp.trh_masuk, mhp.cara_bayar, kc.kod_ptj, kc.kod_jab_spek, mhp.catatan, mhp.amaun, p.nama, p.no_pengenalan, "
                    + "mhp.no_akaun_bank, mhp.alamat_bank1, mhp.alamat_bank2, mhp.kod_bank, kb.nama "
                    + "FROM mohon_pihak_pendeposit mhp, kod_caw kc, pihak p, kod_bank kb "
                    + "WHERE mhp.trh_masuk BETWEEN ? AND ? AND mhp.id_pihak = p.id_pihak "
                    + "AND mhp.kod_caw = kc.kod AND mhp.kod_bank = kb.kod ORDER BY mhp.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            ResultSet rst = pst.executeQuery();//get unique id_mohon

            int count = 0;
            int bil = 1;
            while (rst.next()) {
                count++;
            }
            // HEADER bill03.txt
            line.append("0").append(" ").append(sdfNoSlash.format(now.getTime())).append(" ").append(count).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            // CONTENTS bill03.txt
            if (rst.first()) {  //checking if ResultSet is null or not for mod=Biasa
                LOG.info("rst has row");
            }

            String currentResit = "";
            rst.beforeFirst();
            while (rst.next()) {
                String resit = rst.getString(1);
                int row = 0;
                LOG.info("id_mohon : " + resit);

                // check if has same id_cara_bayar
                if(currentResit.equals(resit)) {}
                else {
                    currentResit = resit;
                    bil = 1;
                }

                line.append(startDate.getYear() + 1900).append("|") //  1.tahun
                        .append(rst.getString(1)).append("|") //  2.Bill No.
                        .append(bil).append("|") //  3.Counter
                        .append(rst.getString(8)).append("|") //  4.Penerima
                        .append(rst.getDouble(7)).append("|") //  5.Amaun
                        .append(rst.getString(13)).append("|") //  6.Bank Code
                        .append("").append("|") //  7.Cek Opt
                        .append(rst.getString(9)).append("|") //  8.IC / No.Syarikat
                        .append(rst.getString(10)).append("|") // 9.No. Account
//                        .append(rst.getString(1)).append("|") // 10.Bank Address 1                21/02/12 SPEKS
                        .append(rst.getString(14)).append("|") // 10.Bank Address 1
//                        .append(rst.getString(12)).append("|") // 11.Bank Address 2
                        .append(rst.getString(12) == null ? "" : rst.getString(12)).append("|") // 11.Bank Address 2
                        .append(rst.getString(6)).append("|") // 12.Perihal
                        .append(rst.getString(5)).append("|") // 13.Jab_Bayar
                        .append(rst.getString(4)).append("\n");// 14.PTJ_Bayar

                pw.print(line.toString());
                line.setLength(0);

                bil++;

            }
                releaseObject(pst, rst);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        }finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateJournal01(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) throws ParseException {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("%%%%%% INSIDE generateJournal01 %%%%%%");
        LOG.info("startDate : " + startDate);
        LOG.info("endDate : " + endDate);
        String trh_mula ="";
        String trh_akhir ="";
        
        trh_mula = sdf.format(startDate);
        trh_akhir = sdf.format(endDate);

        PrintWriter pw = createFile("jurnal01.txt", now, p);

        boolean noError = true;

        try {
            // HEADER jurnal01.txt
            PreparedStatement ps = c.prepareStatement(
                    "SELECT to_char(kt.trh_masuk, 'yyyy') AS Tahun, to_char(kt.trh_masuk, 'mm') AS Bulan, '160' as Urusniaga, kt.id_kew_dok, "
                    + "to_char(kt.trh_masuk, 'ddmmyyyy') AS tarikh, kt.amaun, kc.kod_jab_spek, kc.kod_ptj "
                    + "FROM kew_trans kt, kod_caw kc "
                    + "WHERE kt.trh_masuk BETWEEN ? AND ? AND kt.kod_status='J' AND kt.kod_caw = kc.kod", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
            }

            line.append("0").append(" ")
                    .append(sdfNoSlash.format(now.getTime())).append(" ")
                    .append(count).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            releaseObject(ps, rs);
            intg.LaporanGeneratorJurnal(trh_akhir, trh_mula, p);

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        }

        try {
            // CONTENTS jurnal01.txt
            PreparedStatement ps = c.prepareStatement(
                    "SELECT to_char(kt.trh_masuk, 'yyyy') AS Tahun, to_char(kt.trh_masuk, 'mm') AS Bulan, '160' as Urusniaga, kt.id_kew_dok, "
                    + "to_char(kt.trh_masuk, 'ddmmyyyy') AS tarikh, kt.amaun, kt.kod_caw, kc.kod_jab_spek, kc.kod_ptj "
                    + "FROM kew_trans kt, kod_caw kc "
                    + "WHERE kt.trh_masuk BETWEEN ? AND ? AND kt.kod_status='J' AND kt.kod_caw = kc.kod ORDER BY kt.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                line.append(rs.getString(1)).append("|")        // tahun
                        .append(rs.getString(2)).append("|")     //bulan
                        .append(rs.getString(3)).append("|")      //jenis urusniaga
                        .append(rs.getString(4)).append("|")        //no. baucar
                        .append(rs.getString(5)).append("|")     //tarikh baucar
                        .append(rs.getDouble(6)).append("|")        //amaun
                        .append(getJabPenerima(rs.getString(7))).append("|")        //jabatan
                        .append(getPTJPenerima(rs.getString(7))).append("\n");  //ptj

                pw.print(line.toString());
                line.setLength(0);
            }

            releaseObject(ps, rs);
            c.close();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }

    private boolean generateJournal02(Timestamp startDate, Timestamp endDate, Date now, Pengguna p) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        LOG.info("%%%%%% INSIDE generateJournal02 %%%%%%");
        LOG.info("startDate : " + startDate);
        LOG.info("endDate : " + endDate);

        PrintWriter pw = createFile("jurnal02.txt", now, p);

        boolean noError = true;

        try {
            // HEADER jurnal02.txt
            PreparedStatement ps = c.prepareStatement(
                    "SELECT to_char(kt.trh_masuk, 'yyyy') AS Tahun, to_char(kt.trh_masuk, 'mm') AS Bulan, '160' as Urusniaga, kt.id_kew_dok, "
                    + "to_char(kt.trh_masuk, 'ddmmyyyy') AS tarikh, kt.amaun, kc.kod_jab_spek, kc.kod_ptj "
                    + "FROM kew_trans kt, kod_caw kc "
                    + "WHERE kt.trh_masuk BETWEEN ? AND ? AND kt.kod_status='J' AND kt.kod_caw = kc.kod ORDER BY kt.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
            }

            line.append("0").append(" ")
                    .append(sdfNoSlash.format(now.getTime())).append(" ")
                    .append(count*2).append("\n");

            pw.print(line.toString());
            line.setLength(0);

            releaseObject(ps, rs);

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;
        }

        try {
            // CONTENTS jurnal02.txt
            PreparedStatement ps = c.prepareStatement(
                    "SELECT to_char(kt.trh_masuk, 'yyyy') AS Tahun, to_char(kt.trh_masuk, 'mm') AS Bulan, '160' as Urusniaga, kt.id_kew_dok, "
                    + "to_char(kt.trh_masuk, 'ddmmyyyy') AS tarikh, kt.amaun, kc.kod_jab_spek, kc.kod_ptj "
                    + "FROM kew_trans kt, kod_caw kc "
                    + "WHERE kt.trh_masuk BETWEEN ? AND ? AND kt.kod_status='J' AND kt.kod_caw = kc.kod ORDER BY kt.trh_masuk", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);
            ResultSet rs = ps.executeQuery();

            String currentIdKewDok = "";
            int bil = 1;

            while (rs.next()) {
                String idKewDok = rs.getString(4);
                LOG.info("idKewDok : "+idKewDok);

                // row
                PreparedStatement ppRow = c.prepareStatement(
                        "SELECT to_char(kt.trh_masuk, 'yyyy') as Tahun, kt.id_kew_dok, rownum as Bil, kt.kod_caw, kt.kod_trans, kt.amaun, kt.no_akaun_kr "
                        + "FROM kew_trans kt, kod_caw kc "
                        + "WHERE kt.id_kew_dok =? AND kt.kod_caw = kc.kod ORDER BY kt.trh_masuk");

                ppRow.setString(1, idKewDok);
                ResultSet rrRow = ppRow.executeQuery();

                // check if has same id_kew_dok
                if(currentIdKewDok.equals(idKewDok)) {}
                else {
                    currentIdKewDok = idKewDok;
                    bil = 1;}

                while(rrRow.next()){
                    if(rrRow.getString(7)==null){
                        line.append(rrRow.getString(1)).append("|") //tahun
                                .append(rrRow.getString(2)).append("|") //no. baucar
                                .append(bil).append("|") //bil.
                                .append("|") //vot
                                .append(getJabPenerima(rrRow.getString(4))).append("|") // jabatan
                                .append(getPTJPenerima(rrRow.getString(4))).append("|") // ptj
                                .append("|") //program akt.
                                .append("|") //amanah
                                .append("|") //projek
                                .append("|") //setia
                                .append("|") //cp
                                .append(rrRow.getString(5)).append("|") //sodo
                                .append(rs.getDouble(6)).append("|") //amaun debit
                                .append("|") //amaun kredit
                                .append(getJabPenerima(rrRow.getString(4))).append("|") // jabatan
                                .append(getPTJPenerima(rrRow.getString(4))).append("\n");      // ptj

                        pw.print(line.toString());
                        line.setLength(0);

                        bil++;
                    }else{
                        line.append(rrRow.getString(1)).append("|") //tahun
                                .append(rrRow.getString(2)).append("|") //no. baucar
                                .append(bil).append("|") //bil.
                                .append("|") //vot
                                .append(getJabPenerima(rrRow.getString(4))).append("|") // jabatan
                                .append(getPTJPenerima(rrRow.getString(4))).append("|") // ptj
                                .append("|") //program akt.
                                .append("|") //amanah
                                .append("|") //projek
                                .append("|") //setia
                                .append("|") //cp
                                .append(rrRow.getString(5)).append("|") //sodo
                                .append("|") //amaun debit
                                .append(rs.getDouble(6)).append("|") //amaun kredit
                                .append(getJabPenerima(rrRow.getString(4))).append("|") // jabatan
                                .append(getPTJPenerima(rrRow.getString(4))).append("\n");      // ptj

                        pw.print(line.toString());
                        line.setLength(0);

                        bil++;
                    }
                }
            }

            releaseObject(ps, rs);
            c.close();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }

    public void createDir(File f) throws IOException {
        if (f.exists()) {
            // do nothing
            
        } else {
            f.mkdirs();
        }
    }

    public PrintWriter createFile(String fileName, Date now, Pengguna user) {
        String dateFolder = sdfNoSlash.format(now);

        String folderName = "c:\\SPEKS\\" + dateFolder + "\\";

        folderName = this.getDirPath(user);

        File f = new File(folderName);

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);

        } catch (IOException ex) {
            Logger.getLogger(GenerateSPEKSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateSPEKSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pw;
    }

    private String getDirPath(Pengguna p) {
        String etanahPath = conf.getProperty("document.path");
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)
                + getSPEKSPath(p, null) + File.separator;

        return dirName;
    }

    public String getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(String tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public static Timestamp getDateLast() {
        return dateLast;
    }

    public static void setDateLast(Timestamp dateLast) {
        GenerateSPEKSActionBean.dateLast = dateLast;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    // Kod Jabatan for ns & mlk
    public String getJabPenerima(String kodCawangan) {
        String kodNegeri = conf.getProperty("kodNegeri");

        if(kodNegeri.equals("05")) {
            if(kodCawangan.equals("00")) return "0050";
            else if (kodCawangan.equals("01")) return "0200";
            else if (kodCawangan.equals("02")) return "0220";
            else if (kodCawangan.equals("03")) return "0230";
            else if (kodCawangan.equals("04")) return "0240";
            else if (kodCawangan.equals("05")) return "0250";
            else if (kodCawangan.equals("06")) return "0260";
            else if (kodCawangan.equals("07")) return "0210";
            else if (kodCawangan.equals("08")) return "0270";
            else return "";

        } else if (kodNegeri.equals("04")) {
            if(kodCawangan.equals("00")) return "0050";
            else if (kodCawangan.equals("01")) return "0060";
            else if (kodCawangan.equals("02")) return "0080";
            else if (kodCawangan.equals("03")) return "0070";
            else return "";

        } else return "";
    }

    // KOd PTJ for ns & mlk
    public String getPTJPenerima(String kodCawangan) {
        String kodNegeri = conf.getProperty("kodNegeri");

        if(kodNegeri.equals("05")) {
            if(kodCawangan.equals("00")) return "50000100";
            else if (kodCawangan.equals("01")) return "20000200";
            else if (kodCawangan.equals("02")) return "22000200";
            else if (kodCawangan.equals("03")) return "23000200";
            else if (kodCawangan.equals("04")) return "24000200";
            else if (kodCawangan.equals("05")) return "25000200";
            else if (kodCawangan.equals("06")) return "26000200";
            else if (kodCawangan.equals("07")) return "21000200";
            else if (kodCawangan.equals("08")) return "27000200";
            else return "";
            
        } else if(kodNegeri.equals("04")) {
            if(kodCawangan.equals("00")) return "01000100";
            else if (kodCawangan.equals("01")) return "01000100";
            else if (kodCawangan.equals("02")) return "01000100";
            else if (kodCawangan.equals("03")) return "01000100";
            else return "";

        } else return "";
    }

    // for mlk
    /*private String getJabPenerima(String kodCawangan) {
        if(kodCawangan.equals("00")) return "0050";
        else if (kodCawangan.equals("01")) return "0060";
        else if (kodCawangan.equals("02")) return "0080";
        else if (kodCawangan.equals("03")) return "0070";
        else return "";
    }*/

    //for mlk
    /*private String getPTJPenerima(String kodCawangan) {
        if(kodCawangan.equals("00")) return "01000100";
        else if (kodCawangan.equals("01")) return "01000100";
        else if (kodCawangan.equals("02")) return "01000100";
        else if (kodCawangan.equals("03")) return "01000100";
        else return "";
    }*/

    // include kod cawangan and id kaunter for the path
    private String getSPEKSPath(Pengguna pengguna, String path) {
        if(pengguna != null) {
            String kodCawangan = pengguna.getKodCawangan().getKod();
            String idKaunter = pengguna.getIdKaunter();

//            String speksPath = "SPEKS" + File.separator + kodCawangan + File.separator+ tarikhMasuk.replaceAll("/", "");
            String speksPath = "SPEKS" + File.separator + tarikhMasuk.replaceAll("/", "");
//                    + idKaunter + File.separator + tarikhMasuk.replaceAll("/", "");

            if(org.apache.commons.lang.StringUtils.isNotBlank(path)) {
                speksPath = speksPath + File.separator + path;
            }

            return speksPath;
        }
        return "";
    }

    public Resolution downloadFile() {
        String zipname = tarikhMasuk.replaceAll("/", "")+ ".zip";
        this.createZip(this.getDownloadLink());
//        this.createZip(zipname);
//        File f = new File(this.getDownloadLink() + "speks.zip");
        File f = new File(this.getDownloadLink() + zipname);

        if (f != null) {
            try {
                return new StreamingResolution("application/zip", new FileInputStream(f)).setFilename(zipname);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        
        return null;
    }

    private boolean createZip(String currentPath) {
        String[] filenames = new String[]{currentPath + "terima01.txt", currentPath + "terima02.txt",
                                            currentPath + "terima03.txt", currentPath + "jurnal01.txt",
                                            currentPath + "jurnal02.txt", currentPath + "bill01.txt",
                                            currentPath + "bill02.txt", currentPath + "bill03.txt", currentPath + "Terimaan.pdf",
                                            currentPath + "Baucar.pdf", currentPath + "Jurnal.pdf"};
        
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
//            String outFilename = currentPath + "speks.zip";
            String outFilename =currentPath + tarikhMasuk.replaceAll("/", "")+".zip";
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

            // Compress the files
            for (int i=0; i < filenames.length; i++) {
                FileInputStream in = new FileInputStream(filenames[i]);

                int lengthName = filenames[i].lastIndexOf(File.separator);
                String name = filenames[i].substring(lengthName+1);
                LOG.info("(createZip) name :"+name);
                String zipName =  tarikhMasuk.replaceAll("/", "")+File.separator+name;
                LOG.info("(createZip) zipName :"+zipName);

                // Add ZIP entry to output stream.
//                out.putNextEntry(new ZipEntry(filenames[i]));
                out.putNextEntry(new ZipEntry(zipName));

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                // Complete the entry
                out.closeEntry();
                in.close();
            }

            // Complete the ZIP file
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void releaseObject(PreparedStatement ps, ResultSet rs) {
        try {
            LOG.info("------------------------------------releaseObject------------------------------------");
            ps.close();
            rs.close();
            LOG.info("------------------------------------releaseObject------------------------------------");

        } catch (SQLException ex) {
            Logger.getLogger(GenerateSPEKSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}