package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.*;
import org.hibernate.*;

/**
 *
 * @author abdul.hakim
 * 08-April-2011
 * 
 */
@Wizard(startEvents = {"Step1", "popupCarianKumpulan"})
@UrlBinding("/hasil/bil_cukai")
public class BilCukaiNSActionBean extends AbleActionBean {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BilCukaiNSActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/bil_cukai.jsp";
    private String daerah;
    private String kategori;
    private String idKumpulan = "";
    private String namaKumpulan = "";
    private String kodNegeri;
    private int tahunHantar = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdfWithTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sdfWithTime1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat sdfWithoutSlash = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
    private KodDaerah kodDaerah;
    private static KodDaerah kd;
    private String downloadLink = "";
    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();
    private List<KumpulanAkaun> senaraiKumpulan = new ArrayList<KumpulanAkaun>();
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodDaerahDAO kodDaerahDAO;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }
        
        tahunHantar = Integer.parseInt(sdfYear.format(new Date()));
        logger.info(tahunHantar);
        String sql = "select d from KodDaerah d where d.aktif = 'Y' order by d.nama";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiDaerah = q.list();
        logger.info("senaraiDaerah.size() : " + senaraiDaerah.size());

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution jana() throws SQLException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        Date now = new Date();
        int year = Integer.parseInt(sdfYear.format(now));
        String tahun = Integer.toString(year);
        String baki = null;
        String hantar = null;

        kodDaerah = kodDaerahDAO.findById(daerah);

        Connection con = null;
        con = sessionProvider.get().connection();
//        con = sessionProvider.get().;
        logger.info("idKumpulan : " + idKumpulan+" year : "+tahun);
        setBilCukai(kodDaerah.getKod(), tahun, con, pengguna.getIdPengguna(), idKumpulan, baki, hantar);
//        setBilCukai(kodDaerah.getKod(), tahun, con, pengguna.getIdPengguna());

        test(kodDaerah);
        showForm();
        kd = kodDaerah;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution janaBil(String baki, String hantar) throws SQLException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        Date now = new Date();
//        int year = Integer.parseInt(sdfYear.format(now)) + 1;
        int year = Integer.parseInt(sdfYear.format(now));
        String tahun = Integer.toString(tahunHantar);

        kodDaerah = kodDaerahDAO.findById(daerah);

        Connection con = null;
        con = sessionProvider.get().connection();
        idKumpulan = "";
        logger.info("idKumpulan : " + idKumpulan+" year : "+tahun);
        setBilCukai(kodDaerah.getKod(), tahun, con, pengguna.getIdPengguna(), idKumpulan, baki, hantar);

        test(kodDaerah);
        showForm();
        kd = kodDaerah;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step4")
    public Resolution passParameter1() throws SQLException {
        String baki = "0";
        String hantar = null;
        janaBil(baki, hantar);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step5")
    public Resolution passParameter2() throws SQLException {
//        String baki = null;
        String baki = "1";
//        String hantar = "Y";
        String hantar = null;
        janaBil(baki, hantar);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public static void setBilCukai(String kodCaw, String tahun, Connection con1, String user, String kump, String baki, String hantar) throws SQLException {
//    public static void setBilCukai(String kodCaw, String tahun, Connection con1, String user) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;

        try {
            con = con1;
//            proc = con.prepareCall("{call et_bil_cukai(?, ?, ?)}");
            proc = con.prepareCall("{call et_bil_cukai_landed(?, ?, ?, ?, ?, ?)}");
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
            proc.setString(3, user);
            proc.setString(4, kump);
            proc.setString(5, baki);
            proc.setString(6, hantar);
            logger.info("proc : " + proc);
            proc.executeUpdate();
        } finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public Resolution test(KodDaerah kod){
        BilCukai b = searchLatest();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna user = ctx.getUser();

        Date now1 = new Date();

        if (generateTerima01(now1, b, user, kod)) {
            addSimpleMessage("Maklumat telah berjaya dijana");
        } else {
            addSimpleError("Penjanaan tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
        }

        return new ForwardResolution(DEFAULT_VIEW);
    }

    public BilCukai searchLatest(){
        BilCukai bil = new BilCukai();

        String sql = "SELECT bc from etanah.model.BilCukai bc where bc.jenisCukai = 'L' order by bc.infoAudit.tarikhMasuk DESC";         // FIXME : catatan
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        List<BilCukai> senaraiBil = new ArrayList<BilCukai>();
        senaraiBil  = q.list();
        bil = senaraiBil.get(0);

        return bil;
    }

private boolean generateTerima01(Date now,  BilCukai bil, Pengguna user, KodDaerah kod) {
        Connection c = sessionProvider.get().connection();
        StringBuilder line = new StringBuilder();

        PrintWriter pw = createFile("bilCukai.txt", now, user, kod);

        boolean noError = true;
        try {
            // CONTENTS terima01.txt

                PreparedStatement pp = c.prepareStatement(
                    "select bc.bilcukai "
                    + "from bil_cukai bc where bc.id_bil = ?");

                pp.setLong(1, bil.getId());
                ResultSet rr = pp.executeQuery();

                rr.next();

                 line.append(rr.getString(1)).append("\n");

                pw.print(line.toString());
                line.setLength(0);

            releaseObject(pp, rr);
            c.close();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            noError = false;

        } finally {
            pw.close();
        }

        return noError;
    }

public void createDir(File f) throws IOException {
        if (f.exists()) {
            // do nothing
        }
        else {
            f.mkdirs();
        }
    }

    public PrintWriter createFile(String fileName, Date now, Pengguna user, KodDaerah kod) {
        String dateFolder = sdfNoSlash.format(now);

        String folderName = "c:\\BilCukai\\" + kod.getKod() + "\\"+ dateFolder + "\\";
        logger.info("folderName1 : "+folderName);
        folderName = this.getDirPath(user, kod);
        logger.info("folderName2 : "+folderName);

        File f = new File(folderName);

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);

        } catch (IOException ex) {
            Logger.getLogger(BilCukaiNSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BilCukaiNSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pw;
    }

    private String getDirPath(Pengguna p, KodDaerah kod) {
        String etanahPath = conf.getProperty("document.path");
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)+"SPEKS"+ File.separator
                + getBilCukaiPath(p, null, kod) + File.separator;

        return dirName;
    }

    private String getBilCukaiPath(Pengguna pengguna, String path, KodDaerah kod) {
        if(pengguna != null) {
            Date now = new Date();
            String dateFolder = sdfNoSlash.format(now);

            String bilCukaiPath = "BilCukai" + File.separator + kod.getKod() + File.separator+ dateFolder;

            if(org.apache.commons.lang.StringUtils.isNotBlank(path)) {
                bilCukaiPath = bilCukaiPath + File.separator + path;
            }

            return bilCukaiPath;
        }
        return "";
    }


    @HandlesEvent("Step3")
    public Resolution downloadFile() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        downloadLink = getDirPath(pengguna, kd);
        Date now = new Date();
        String zipname = "bilCukai.zip";
        this.createZip(this.getDownloadLink());
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

     private void releaseObject(PreparedStatement ps, ResultSet rs) {
        try {
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(BilCukaiNSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private boolean createZip(String currentPath) {
        String[] filenames = new String[]{currentPath + "bilCukai.txt"};

        Date now = new Date();
        String dateFolder = sdfNoSlash.format(now);

        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
            String outFilename =currentPath + "bilCukai.zip";
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

            // Compress the files
            for (int i=0; i < filenames.length; i++) {
                FileInputStream in = new FileInputStream(filenames[i]);

                int lengthName = filenames[i].lastIndexOf(File.separator);
                String name = filenames[i].substring(lengthName+1);
                logger.info("(createZip) name :"+name);
                String zipName =  dateFolder+File.separator+name;
                logger.info("(createZip) zipName :"+zipName);

                // Add ZIP entry to output stream.
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

     public Resolution popupCarianKumpulan(){
        String sql = "select d from KodDaerah d where d.aktif = 'Y' order by d.nama";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiDaerah = q.list();
        logger.info("senaraiDaerah.size() : " + senaraiDaerah.size());
        return new ForwardResolution("/WEB-INF/jsp/hasil/popup_kumpulan.jsp").addParameter("popup", "true");
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodDaerah> getSenaraiDaerah() {
        return senaraiDaerah;
    }

    public void setSenaraiDaerah(List<KodDaerah> senaraiDaerah) {
        this.senaraiDaerah = senaraiDaerah;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        this.namaKumpulan = namaKumpulan;
    }

    public List<KumpulanAkaun> getSenaraiKumpulan() {
        return senaraiKumpulan;
    }

    public void setSenaraiKumpulan(List<KumpulanAkaun> senaraiKumpulan) {
        this.senaraiKumpulan = senaraiKumpulan;
    }

    public int getTahunHantar() {
        return tahunHantar;
    }

    public void setTahunHantar(int tahunHantar) {
        this.tahunHantar = tahunHantar;
    }
}
