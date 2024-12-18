package etanah.view.stripes.intg;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import etanah.model.*;
import java.util.logging.*;
import com.google.inject.*;
import etanah.Configuration;
import able.stripes.AbleActionBean;
import etanah.view.etanahContextListener;
import etanah.view.stripes.hasil.JanaFailPBT;
import javax.activation.MimetypesFileTypeMap;

/**
 * @author haqqiem
 * Fri May 25 2012 10:35:54 PM
 **/


public class GenerateLaporanSPEKSActionBean extends AbleActionBean {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GenerateLaporanSPEKSActionBean.class);
//    Logger LOG = Logger.getLogger(GenerateLaporanSPEKSActionBean.class);
    String pathDir = "";
    String typeOfPdfFile = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");

    @Inject
    etanah.Configuration conf;
    
    @Inject JanaFailPBT jana;

     /***** start to Create PDF file ****/
    public String LaporanGenerator(String dateBiasa, String dateLewat, Pengguna user) throws ParseException{
        Injector inject = etanahContextListener.getInjector();
        Configuration conf1 = inject.getInstance(Configuration.class);
        String url = conf1.getProperty("report.server.location");
        String dblink = conf1.getProperty("report.key");
        LOG.info("-------------------------------- url : "+url+" dblink : "+dblink);

//        Date from = sdf.parse(tarikhDari);
        Date untill = sdf.parse(dateLewat);
        Calendar c = Calendar.getInstance();
            c.setTime(untill);
            c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();
//        String dateHingga = sdf.format(newDate);              //testing 14/10/2014 xnk +1 
        String dateHingga = dateLewat;
        LOG.info("date Biasa : "+dateLewat+" dateHingga : "+dateHingga);

        String result = "fail";
//        String url = "http://bris4:9001/reports/rwservlet?";
//        String url = "http://10.66.130.14:9003/reports/rwservlet?";
//        String dblink = "etml";
        String reportname = "";
        if("04".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_SPEKS_MLK.rdf";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_SPEKS_NS.rdf";
        }
        String urlSource = url+"?"+dblink+"&"+reportname+"&p_trh_mula="+dateLewat+"&p_trh_tamat="+dateHingga;
        LOG.info("(LaporanGenerator) urlSource :"+urlSource);

//        String namaLaporan = dateBiasa.replaceAll("/","")+"-Laporan.pdf";   
        String namaLaporan = "Terimaan.pdf";
//        PdfWriter pw = createFile(dateBiasa, namaLaporan);

        byte[] ba1 = new byte[1024];
        int baLength;
        
        try {
            if(createFile(dateBiasa,namaLaporan, user)){
                FileOutputStream fos1 = new FileOutputStream(pathDir);

                URL urlReport = new URL(urlSource);
                URLConnection connURL = urlReport.openConnection();
                LOG.info("urlReport : "+urlReport);
                LOG.info("connURL : "+connURL);
                LOG.info("connURL.getContentType() : "+connURL.getContentType());
                LOG.info("pathDir : "+pathDir);

                if (!connURL.getContentType().equalsIgnoreCase("application/pdf")) {
                      LOG.error("FAILED.\n[Sorry. This is not a PDF.]");
                  } else {
                        // Read the PDF from the URL and save to a local file
                      InputStream is1 = urlReport.openStream();
                      while ((baLength = is1.read(ba1)) != -1)
                      {
                          fos1.write(ba1, 0, baLength);
                      }
                      fos1.flush();
                      fos1.close();
                      is1.close();
                  }
            }
        } catch (MalformedURLException ex) {
            LOG.error("(LaporanGenerator) MURLE :"+ex);
            ex.printStackTrace(); // for development only
        }catch(IOException ioEX){
            LOG.error("(LaporanGenerator) IOE :"+ioEX);
            ioEX.printStackTrace(); // for development only
        }
        return result;
    }
    /***** finish to Create PDF file ****/

    /***** start to Create PDF file ****/
    public String LaporanGeneratorBaucar(String dateBiasa, String dateLewat, Pengguna user) throws ParseException{
        LOG.info("----------LaporanGeneratorBaucar----------");
        Injector inject = etanahContextListener.getInjector();
        Configuration conf1 = inject.getInstance(Configuration.class);
        String url = conf1.getProperty("report.server.location");
        String dblink = conf1.getProperty("report.key");
        LOG.info("-------------------------------- url : "+url+" dblink : "+dblink);

//        Date from = sdf.parse(tarikhDari);
        Date untill = sdf.parse(dateBiasa);
        Calendar c = Calendar.getInstance();
            c.setTime(untill);
            c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();
        String dateHingga = sdf.format(newDate);
        LOG.info("date Biasa : "+dateBiasa+" dateHingga : "+dateHingga);

        String result = "fail";
//        String url = "http://bris4:9001/reports/rwservlet?";
        String reportname = "";
        if("04".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_BSPEKS_MLK.rdf";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_BSPEKS_NS.rdf";
        }
        String urlSource = url+"?"+dblink+"&"+reportname+"&p_trh_masuk="+dateBiasa;
        LOG.info("(LaporanGenerator) urlSource :"+urlSource);

        String namaLaporan = "Baucar.pdf";

        byte[] ba1 = new byte[1024];
        int baLength;

        try {
            if(createFile(dateBiasa,namaLaporan, user)){
                FileOutputStream fos1 = new FileOutputStream(pathDir);

                URL urlReport = new URL(urlSource);
                URLConnection connURL = urlReport.openConnection();
                LOG.info("urlReport : "+urlReport);
                LOG.info("connURL : "+connURL);
                LOG.info("connURL.getContentType() : "+connURL.getContentType());

                if (!connURL.getContentType().equalsIgnoreCase("application/pdf")) {
                      LOG.error("FAILED.\n[Sorry. This is not a PDF.]");
                  } else {
                        // Read the PDF from the URL and save to a local file
                      InputStream is1 = urlReport.openStream();
                      while ((baLength = is1.read(ba1)) != -1)
                      {
                          fos1.write(ba1, 0, baLength);
                      }
                      fos1.flush();
                      fos1.close();
                      is1.close();
                  }
            }
        } catch (MalformedURLException ex) {
            LOG.error("(LaporanGenerator) MURLE :"+ex);
            ex.printStackTrace(); // for development only
        }catch(IOException ioEX){
            LOG.error("(LaporanGenerator) IOE :"+ioEX);
            ioEX.printStackTrace(); // for development only
        }
        return result;
    }
    /***** finish to Create PDF file ****/

    /***** start to Create PDF file ****/
    public String LaporanGeneratorJurnal(String dateBiasa, String dateLewat, Pengguna user) throws ParseException{
        LOG.info("----------LaporanGeneratorJurnal----------");
        Injector inject = etanahContextListener.getInjector();
        Configuration conf1 = inject.getInstance(Configuration.class);
        String url = conf1.getProperty("report.server.location");
        String dblink = conf1.getProperty("report.key");
        LOG.info("-------------------------------- url : "+url+" dblink : "+dblink);

//        Date from = sdf.parse(tarikhDari);
        Date untill = sdf.parse(dateBiasa);
        Calendar c = Calendar.getInstance();
            c.setTime(untill);
            c.add(Calendar.DATE, 1);
        Date newDate = c.getTime();
        String dateHingga = sdf.format(newDate);
        LOG.info("date Biasa : "+dateBiasa+" dateHingga : "+dateHingga);

        String result = "fail";
//        String url = "http://bris4:9001/reports/rwservlet?";
        String reportname = "";
        if("04".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_JSPEKS_MLK.rdf";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSL_R_JSPEKS_NS.rdf";
        }
        String urlSource = url+"?"+dblink+"&"+reportname+"&p_trh_masuk="+dateBiasa;
        LOG.info("(LaporanGenerator) urlSource :"+urlSource);

//        String namaLaporan = dateBiasa.replaceAll("/","")+"-Laporan.pdf";
        String namaLaporan = "Jurnal.pdf";
//        PdfWriter pw = createFile(dateBiasa, namaLaporan);

        byte[] ba1 = new byte[1024];
        int baLength;

        try {
            if(createFile(dateBiasa,namaLaporan, user)){
                FileOutputStream fos1 = new FileOutputStream(pathDir);

                URL urlReport = new URL(urlSource);
                URLConnection connURL = urlReport.openConnection();
                LOG.info("urlReport : "+urlReport);
                LOG.info("connURL : "+connURL);
                LOG.info("connURL.getContentType() : "+connURL.getContentType());

                if (!connURL.getContentType().equalsIgnoreCase("application/pdf")) {
                      LOG.error("FAILED.\n[Sorry. This is not a PDF.]");
                  } else {
                        // Read the PDF from the URL and save to a local file
                      InputStream is1 = urlReport.openStream();
                      while ((baLength = is1.read(ba1)) != -1)
                      {
                          fos1.write(ba1, 0, baLength);
                      }
                      fos1.flush();
                      fos1.close();
                      is1.close();
                  }
            }
        } catch (MalformedURLException ex) {
            LOG.error("(LaporanGenerator) MURLE :"+ex);
            ex.printStackTrace(); // for development only
        }catch(IOException ioEX){
            LOG.error("(LaporanGenerator) IOE :"+ioEX);
            ioEX.printStackTrace(); // for development only
        }
        return result;
    }
    /***** finish to Create PDF file ****/
    
    /***** start to Create PDF file ****/
    public String LaporanGeneratorKutipanCukaiTaksiran(Pengguna user, String namaFail, String trhMula, String trhHingga, String kodTrans, String kodPbt) throws ParseException{
        LOG.info("----------LaporanGeneratorKutipanCukaiTaksiran----------");
        Injector inject = etanahContextListener.getInjector();
        Configuration conf1 = inject.getInstance(Configuration.class);
        String url = conf1.getProperty("report.server.location");
        String dblink = conf1.getProperty("report.key");

        Date now = new Date();

        String result = "fail";
        String reportname = "";
        if("04".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSLLaporanTerimaan.rdf";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            reportname = "HSLLaporanTerimaan_NS.rdf";
        }
        String urlSource = url+"?"+dblink+"&"+reportname+"&p_trh_mula="+trhMula+"&p_trh_akhir="+trhHingga+"&p_kod_trans="+kodTrans;
        LOG.info("(LaporanGenerator) urlSource :"+urlSource);

        String namaLaporan = namaFail;

        byte[] ba1 = new byte[1024];
        int baLength;

        try {
            if(createPBTFile(namaLaporan, now, user, kodPbt)){
                FileOutputStream fos1 = new FileOutputStream(pathDir);

                URL urlReport = new URL(urlSource);
                URLConnection connURL = urlReport.openConnection();
                LOG.info("urlReport : "+urlReport);
                LOG.info("connURL : "+connURL);
                LOG.info("connURL.getContentType() : "+connURL.getContentType());

                if (!connURL.getContentType().equalsIgnoreCase("application/pdf")) {
                      LOG.error("FAILED.\n[Sorry. This is not a PDF.]");
                  } else {
                        // Read the PDF from the URL and save to a local file
                      InputStream is1 = urlReport.openStream();
                      while ((baLength = is1.read(ba1)) != -1)
                      {
                          fos1.write(ba1, 0, baLength);
                      }
                      fos1.flush();
                      fos1.close();
                      is1.close();
                  }
                result = connURL.getContentType();
            }
        } catch (MalformedURLException ex) {
            LOG.error("(LaporanGenerator) MURLE :"+ex);
            ex.printStackTrace(); // for development only
        }catch(IOException ioEX){
            LOG.error("(LaporanGenerator) IOE :"+ioEX);
            ioEX.printStackTrace(); // for development only
        }
        return result;
    }
    /***** finish to Create PDF file ****/
    
    
    /***** start to Create dir path for Cukai Taksiran****/
    private String getDirPBTPath(Pengguna p, String kodPbt) {
        String etanahPath = conf.getProperty("document.path");
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)+"SPEKS"+ File.separator
                + getPBTPath(p, null, kodPbt) + File.separator;

        LOG.info(dirName);
        return dirName;
    }

    private String getPBTPath(Pengguna pengguna, String path, String kodPbt) {
        if(pengguna != null) {
            Date now = new Date();
            String dateFolder = sdfNoSlash.format(now);

            String bilCukaiPath = "PBT" + File.separator + kodPbt +File.separator+ pengguna.getKodCawangan().getKod() +File.separator + dateFolder;

            if(org.apache.commons.lang.StringUtils.isNotBlank(path)) {
                bilCukaiPath = bilCukaiPath + File.separator + path;
            }
            LOG.info(bilCukaiPath);
            return bilCukaiPath;
        }
        return "";
    }

    /***** start to Create dir path ****/
    private String getDirPath(String date, Pengguna p) {
        String etanahPath = conf.getProperty("document.path");
        String dirName = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator)
                + getSPEKSPath(p, null, date) + File.separator;
        LOG.info("(getDirPath) path:"+dirName);
        return dirName;
    }

    private String getSPEKSPath(Pengguna pengguna, String path, String date) {
        if(pengguna != null) {
            String kodCawangan = pengguna.getKodCawangan().getKod();

//            String speksPath = "SPEKS" + File.separator + kodCawangan + File.separator+ date.replaceAll("/", "");
            String speksPath = "SPEKS" + File.separator + date.replaceAll("/", "");

            if(org.apache.commons.lang.StringUtils.isNotBlank(path)) {
                speksPath = speksPath + File.separator + path;
            }

            return speksPath;
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

    public boolean  createFile(String date, String fileName, Pengguna p) {
        boolean result = false;
        String folderName = "c:\\SPEKS\\" + date.replaceAll("/", "") + "\\";

        folderName = this.getDirPath(date, p);

        File f = new File(folderName);

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);
            pathDir = f.getAbsolutePath();
            LOG.info("(createFile) pathDir:"+pathDir);
            result = true;

        } catch (IOException ex) {
            LOG.error("(createFile) IOE:"+ex);
            ex.printStackTrace(); // for development only
        }

        File fTxt = new File("/nfs_apps/dms/SPEKS/path.txt");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fTxt);
            pw.print(pathDir);
        } catch (FileNotFoundException Fex) {
            LOG.error("(createFile) Fex:"+Fex);
            Fex.printStackTrace(); // for development only
        }finally{
            pw.close();
        }

        return result;
    }
    /***** finish to Create dir path ****/
    
    public boolean createPBTFile(String fileName, Date now, Pengguna user, String kodPbt) {
        LOG.info(" ------------------------ createFile ------------------------");
        boolean result = false;
        String dateFolder = sdfNoSlash.format(now);
        String folderName = "c:\\SPEKS\\" + dateFolder + "\\";
        folderName = getDirPBTPath(user, kodPbt);
        File f = new File(folderName);
        
        MimetypesFileTypeMap mimeypeMap = new MimetypesFileTypeMap();
        String a = mimeypeMap.getContentType(f);
        LOG.info("File type : "+a);
        typeOfPdfFile = a;

        // create directory and file
        try {
            createDir(f);
            f = new File(folderName + fileName);
            pathDir = f.getAbsolutePath();
            LOG.info("(createFile) pathDir:"+pathDir);
            result = true;

        } catch (IOException ex) {
            Logger.getLogger(GenerateLaporanSPEKSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateLaporanSPEKSActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getPathDir() {
        return pathDir;
    }

    public void setPathDir(String pathDir) {
        this.pathDir = pathDir;
    }

    public String getTypeOfPdfFile() {
        return typeOfPdfFile;
    }

    public void setTypeOfPdfFile(String typeOfPdfFile) {
        this.typeOfPdfFile = typeOfPdfFile;
    }


}
