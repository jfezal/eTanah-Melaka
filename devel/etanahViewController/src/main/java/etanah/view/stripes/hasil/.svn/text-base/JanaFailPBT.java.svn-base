package etanah.view.stripes.hasil;

import java.io.*;
import java.util.*;
import java.text.*;
import etanah.dao.*;
import java.util.zip.*;
import etanah.model.*;
import java.sql.ResultSet;
import java.util.logging.*;
import java.sql.Timestamp;
import java.sql.Connection;
import org.hibernate.Session;
import java.sql.SQLException;
import com.google.inject.Inject;
import java.sql.PreparedStatement;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.intg.GenerateLaporanSPEKSActionBean;
import javax.activation.FileDataSource;
import javax.activation.MimetypesFileTypeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author abdul.hakim
 * 20-Apr-2011
 *
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/jana_fail_pbt")
public class JanaFailPBT extends AbleActionBean {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JanaFailPBT.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/jana_fail_pbt.jsp";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
    DecimalFormat df2 = new DecimalFormat("00.00");
    SimpleDateFormat sdfWithTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sdfWithTime1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat sdfWithoutSlash = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
    private String kodNegeri = "";
    private String downloadLink = "";
    private String tarikhMula;
    private String tarikhHingga;
    private String pbt;
    private String tarikhMasuk;
    private String title = "";
    private String typeOfTextFile = "";
    private String typeOfPdfFile = "";
    private String namaTextFile = "";
    private String namaPdfFile = "";
    private String pathDir = "";
    private String agensi = "";
    private String[] senaraiBayaran;
    private String[] dokumenUpload = new String[2];
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject private etanah.Configuration conf;
    @Inject KodTransaksiDAO kodTransaksiDAO;
    @Inject KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject GenerateLaporanSPEKSActionBean intg;
    @Inject KutipanHasilManager manager;
    @Inject KodDokumenDAO kodDokumenDAO;
    @Inject KodAgensiKutipanDAO kodAgensiKutipanDAO;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeriSembilan";
        }

        int x = showReports().length;
        logger.info("x : " + x);
        for (int i = 0; i < x; i++) {
            String kod = senaraiBayaran[i];
            KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
            logger.info("kodTransaksi.getKod() : " + kodTransaksi.getKod());
            senaraiKodTransaksi.add(kodTransaksi);
        }
        logger.info("senaraiKodTransaksi.size() : " + senaraiKodTransaksi.size());

        return new ForwardResolution(DEFAULT_VIEW);
    }

    private String[] showReports() {
        logger.info("showReport:start");

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = new String[]{"51530", "51531", "51532"};

            logger.info("senaraiBayaran.length : " + senaraiBayaran.length);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = new String[]{"99020", "99021", "99022", "99023", "99024", "99025", "99026", "99027"};

            logger.info("senaraiBayaran.length : " + senaraiBayaran.length);
        }
        return senaraiBayaran;
    }

    @HandlesEvent("Step2")
    public Resolution generateFiles() throws ParseException, IOException {
        logger.info("----------------------------------- step 2 -----------------------------------");
        logger.info("kod Transaksi : "+pbt);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna user = ctx.getUser();

        Date now = sdfWithTime1.parse(tarikhMula + " 00:00:00");
        Timestamp startDate = new Timestamp(now.getTime());
        Date to = sdfWithTime1.parse(tarikhHingga + " 23:59:59");
        Timestamp endDate = new Timestamp(to.getTime());
        Date now1 = new Date();
        downloadLink = getDirPath(user);
        
        logger.info("downloadLink : "+ pathDir);
        title = "Cukai Taksiran "+tajukFile(pbt);
        
        if (generateTerima01(now1, pbt, user, startDate, endDate)) {
            logger.info("dokumenUpload.length : "+dokumenUpload.length);
            for (String string : dokumenUpload) {
                logger.info("==================="+string);
                String[] arr = string.split("-");
                logger.info("arr.length : "+arr.length);
                String frmt = "";
                logger.info("filename : "+arr[0]);
                if(arr[0].endsWith("txt")){
                    frmt = "TXTPB";
                    title = "Text Fail "+title;
                }
                if(arr[0].endsWith("pdf")){
                    frmt = "LPBT";
                    title = "Laporan "+title;
                }
                
                logger.info("arr[1] : "+arr[1]);
                if(arr[1].equalsIgnoreCase("application/octet")){
                    arr[1] = "text/plain";
                }
                logger.info("arr[1] : "+arr[1]);
                    Dokumen d = storeDokumen(user, arr[1], title, pathDir, arr[0], frmt);
                    storeAgensiKutipanDokumen(user, pbt, d, agensi);
            }
            addSimpleMessage("Penjanaan berjaya.");
        } else {
            addSimpleError("Penjanaan tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
        }
        showForm();
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    private String kodResit (String kodTrans){
        String kod = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if(kodTrans.equals("51530"))
                kod = "225";
            if(kodTrans.equals("51531"))
                kod = "235";
            if(kodTrans.equals("51532"))
                kod = "230";
        }
        logger.info(kod);
        return kod;
    }
    
    private String tajukFile (String kodTrans){
        String tajuk = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if(kodTrans.equals("51530"))
                tajuk = "MBMB";
            if(kodTrans.equals("51531"))
                tajuk = "MPAG";
            if(kodTrans.equals("51532"))
                tajuk = "MPJ";
        }
        logger.info(tajuk);
        return tajuk;
    }

    private boolean generateTerima01(Date now,  String kodTrans, Pengguna pguna, Timestamp startDate, Timestamp endDate) throws ParseException {
        logger.info("kodTrans : "+kodTrans);
        logger.info("startDate : "+startDate);
        logger.info("endDate : "+endDate);
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String trh_file = sdfWithoutSlash.format(now);
        logger.info("trh_file : "+trh_file);

        String kod = kodResit(kodTrans);
        String filename = kod+trh_file;
        namaTextFile = filename+".txt";
        namaPdfFile = filename+".pdf";
        logger.info("filename : "+filename);
        PrintWriter pw = createFile(namaTextFile, now, pguna);
        
        StringBuilder files = new StringBuilder();
            files.append(namaTextFile).append("-").append(typeOfTextFile);
        dokumenUpload [0] = files.toString();
        
        boolean noError = true;
        try {
            // CONTENTS terima01.txt
            PreparedStatement ps = c.prepareStatement(
                    "SELECT kt.trh_masuk, kt.id_kew_dok, kt.kod_trans, kt.amaun, kd.no_ruj  "
                    + "FROM kew_trans kt, kew_dokumen kd WHERE kt.kod_trans = ? AND kt.id_kew_dok = kd.id_kew_dok AND kt.trh_masuk BETWEEN ? AND ?");
            ps.setString(1, kodTrans);
            ps.setTimestamp(2, startDate);
            ps.setTimestamp(3, endDate);
            ResultSet rs = ps.executeQuery();
            String id ="";
            while (rs.next()) {
                String m = df2.format(rs.getBigDecimal(4));
                String siri = StringUtils.leftPad(m.replace(".", ""),12,"0");
//                String siri = String.format("%s%0" + 12 + "d", id, m.replace(".", ""));
                line.append(kod)
                        .append(sdfWithoutSlash.format(rs.getDate(1)))
                        .append(sdfWithTime.format(rs.getTimestamp(1)))
                        //                        .append("\t")
                        .append("     ")
                        .append(rs.getString(2))
                        .append("0000"+ rs.getString(3))
                        //                        .append("\t")
                        .append("        ")
                        .append(rs.getString(5)) // nama pembayar
//                        .append("00000000" + rs.getInt(4)).append("\n");
                        .append(siri).append("\n");
//                  line.append(newLine);

                pw.print(line.toString());
                line.setLength(0);
            }

            releaseObject(ps, rs);
            c.close();
            
            
            agensi = tajukFile(pbt);
            typeOfPdfFile = intg.LaporanGeneratorKutipanCukaiTaksiran(pguna, namaPdfFile, tarikhMula, tarikhHingga, pbt, agensi);
            files = new StringBuilder();
                files.append(namaPdfFile).append("-").append(typeOfPdfFile);
            dokumenUpload [1] = files.toString();
            
            logger.info("dokumenUpload.length : "+dokumenUpload.length);
            for (String string : dokumenUpload) {
                logger.info("==================="+string);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }
    
    public Resolution downloadFile() {
//        String zipname = tarikhHingga.replaceAll("/", "")+ ".zip";
        Date now = new Date();
        String kod = kodResit(pbt);
        String trh_file = sdfWithoutSlash.format(now);
        String filename = kod+trh_file;
        String zipname = sdfNoSlash.format(new Date())+ ".zip";
        
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna user = ctx.getUser();
        downloadLink = getDirPath(user);
        this.createZip(this.getDownloadLink(), filename);
//        this.createZip(zipname);
//        File f = new File(this.getDownloadLink() + "speks.zip");
        
        logger.info("zipname : "+zipname);
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
    
    private boolean createZip(String currentPath, String file) {
        logger.info("====================== currentPath : "+currentPath);
        String[] filenames = new String[]{currentPath + file +".txt", currentPath + file + ".pdf"};
        
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            String t = sdfNoSlash.format(new Date());
            // Create the ZIP file
//            String outFilename = currentPath + "speks.zip";
//            String outFilename =currentPath + tarikhHingga.replaceAll("/", "")+".zip";
            String outFilename =currentPath + t +".zip";
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

            // Compress the files
            for (int i=0; i < filenames.length; i++) {
                FileInputStream in = new FileInputStream(filenames[i]);

                int lengthName = filenames[i].lastIndexOf(File.separator);
                String name = filenames[i].substring(lengthName+1);
                logger.info("(createZip) name :"+name);
                String zipName =  t+File.separator+name;
                logger.info("(createZip) zipName :"+zipName);

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

//    public PrintWriter createFile(String fileName, Date now) {
//        String dateFolder = sdfNoSlash.format(now);
//
//        String folderName = "c:\\PBT\\" + dateFolder + "\\";
//        File f = new File(folderName);
//
//        // create directory and file
//        try {
//            createDir(f);
//            f = new File(folderName + fileName);
//
//        } catch (IOException ex) {
//            Logger.getLogger(JanaFailPBT.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(f);
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JanaFailPBT.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return pw;
//    }

    public PrintWriter createFile(String fileName, Date now, Pengguna user) {
        logger.info(" ------------------------ createFile ------------------------");
        String dateFolder = sdfNoSlash.format(now);

        String folderName = "c:\\SPEKS\\" + dateFolder + "\\";

        folderName = this.getDirPath(user);

        File f = new File(folderName);
        
        MimetypesFileTypeMap mimeypeMap = new MimetypesFileTypeMap();
        String a = mimeypeMap.getContentType(f);
        logger.info("File type : "+a);
        typeOfTextFile = a;
        
        FileDataSource ds = new FileDataSource(f);  
        logger.info("ds.getContentType() : "+ds.getContentType());

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);

        } catch (IOException ex) {
            Logger.getLogger(JanaFailPBT.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JanaFailPBT.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pw;
    }

    public String getDirPath(Pengguna p) {
        String etanahPath = conf.getProperty("document.path");
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)+"SPEKS"+ File.separator
                + getPBTPath(p, null) + File.separator;

        logger.info(dirName);
        return dirName;
    }

    public String getPBTPath(Pengguna pengguna, String path) {
        if(pengguna != null) {
            Date now = new Date();
            String dateFolder = sdfNoSlash.format(now);
            String kodPbt = tajukFile(pbt);
            String bilCukaiPath = "PBT" + File.separator + kodPbt +File.separator+ pengguna.getKodCawangan().getKod() +File.separator + dateFolder;

            if(org.apache.commons.lang.StringUtils.isNotBlank(path)) {
                bilCukaiPath = bilCukaiPath + File.separator + path;
            }
            logger.info(bilCukaiPath);
            pathDir = "SPEKS"+File.separator+bilCukaiPath;
            return bilCukaiPath;
        }
        return "";
    }

    public void createDir(File f) throws IOException {
        if (f.exists()) {
            // do nothing

        } else {
            f.mkdirs();
        }
    }
    
    private void releaseObject(PreparedStatement ps, ResultSet rs) {
        try {
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(JanaFailPBT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Dokumen storeDokumen(Pengguna user, String type, String tajuk, String path, String filename, String kodDokumen){
        Dokumen doc = new Dokumen();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        
        doc.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
        doc.setTajuk(tajuk);
        doc.setNoRujukan(null);
        doc.setKlasifikasi(kodKlasifikasiDAO.findById(1));
        doc.setPerihal(null);
        doc.setNoVersi("1.0");
        doc.setFormat(type);
        doc.setSaiz(0);
        doc.setNamaFizikal(path+File.separator+filename);
        doc.setNamaTandatangan(null);
        doc.setBaru('Y');
            ia.setDimasukOleh(user);
            ia.setTarikhMasuk(now);
        doc.setInfoAudit(ia);
    
        manager.save(doc);
        return doc;
    }
    
    public AgensiKutipanDokumen storeAgensiKutipanDokumen(Pengguna user, String type, Dokumen doc, String agensi){
        AgensiKutipanDokumen akd = new AgensiKutipanDokumen();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        
        akd.setKodAgensiKutipan(kodAgensiKutipanDAO.findById(agensi));
        akd.setKodDokumen(doc.getKodDokumen());
        akd.setCatatan(null);
        akd.setIdDokumen(doc);
            ia.setDimasukOleh(user);
            ia.setTarikhMasuk(now);
        akd.setInfoAudit(ia);
        
        manager.save(akd);
        return akd;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String[] getSenaraiBayaran() {
        return senaraiBayaran;
    }

    public void setSenaraiBayaran(String[] senaraiBayaran) {
        this.senaraiBayaran = senaraiBayaran;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public String getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(String tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(String tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public String getTypeOfTextFile() {
        return typeOfTextFile;
    }

    public void setTypeOfTextFile(String typeOfTextFile) {
        this.typeOfTextFile = typeOfTextFile;
    }

    public String getTypeOfPdfFile() {
        return typeOfPdfFile;
    }

    public void setTypeOfPdfFile(String typeOfPdfFile) {
        this.typeOfPdfFile = typeOfPdfFile;
    }

    public String getNamaTextFile() {
        return namaTextFile;
    }

    public void setNamaTextFile(String namaTextFile) {
        this.namaTextFile = namaTextFile;
    }

    public String getNamaPdfFile() {
        return namaPdfFile;
    }

    public void setNamaPdfFile(String namaPdfFile) {
        this.namaPdfFile = namaPdfFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDokumenUpload() {
        return dokumenUpload;
    }

    public void setDokumenUpload(String[] dokumenUpload) {
        this.dokumenUpload = dokumenUpload;
    }

    public String getPathDir() {
        return pathDir;
    }

    public void setPathDir(String pathDir) {
        this.pathDir = pathDir;
    }

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }
}
