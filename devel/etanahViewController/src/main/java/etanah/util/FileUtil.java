/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.util;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import org.apache.log4j.Logger;

/**
 *
 * @author md.nurfikri
 */
public class FileUtil {

    String uploadLocation;
    private static Logger LOG = Logger.getLogger(FileUtil.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    public FileUtil() {
    }

    public FileUtil(String uploadLocation) {
        this.uploadLocation = uploadLocation;
    }

    public String saveFile(String fileName, InputStream inputStream) throws Exception {
//        String genFileName = System.nanoTime() + "_" + fileName;
        File dir = new File(uploadLocation);
        if (!dir.exists()) {
            dir.mkdirs();
            dir.setReadable(true, false);
            dir.setExecutable(true, false);
        }
        File file = new File(uploadLocation + (uploadLocation.endsWith(File.separator) ? "" : File.separator) + fileName);
        FileOutputStream out = new FileOutputStream(file);
        FileUtil.copyStream(inputStream, out);
        out.close();
        file.setReadable(true, false);
        return file.getName();
    }

    public File getFile(String fileName) {
        File file = new File(uploadLocation + (uploadLocation.endsWith(File.separator) ? "" : File.separator) + fileName);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public void deleteFile(String fileName) {
        File file = new File(uploadLocation + (uploadLocation.endsWith(File.separator) ? "" : File.separator) + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public String writeAsString(File file, String contents) {
        Writer out = null;
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                return "unable to make parent dir for " + file;
            }
            Reader in = new StringReader(contents);
            out = new FileWriter(file);
            FileUtil.copyStream(in, out);
            return null;
        } catch (IOException e) {
            return "[FileUtil] ERROR writing file : " + e.getMessage();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                } // ignored
            }
        }
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        final int MAX = 4096;
        byte[] buf = new byte[MAX];
        for (int bytesRead = in.read(buf, 0, MAX);
                bytesRead != -1;
                bytesRead = in.read(buf, 0, MAX)) {
            out.write(buf, 0, bytesRead);
        }
    }

    public static void copyStream(Reader in, Writer out) throws IOException {
        final int MAX = 4096;
        char[] buf = new char[MAX];
        for (int bytesRead = in.read(buf, 0, MAX);
                bytesRead != -1;
                bytesRead = in.read(buf, 0, MAX)) {
            out.write(buf, 0, bytesRead);
        }
    }

    public static ByteArrayOutputStream createWaterMark(InputStream is) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();       

        try {
//            copyStream(is, out);
            

            PdfReader reader = new PdfReader(is);
            int n = reader.getNumberOfPages();

            PdfStamper stamp = new PdfStamper(reader, out);

            int i = 0;
            PdfContentByte under;
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.WINANSI, BaseFont.EMBEDDED);
            while (i < n) {
                i++;
                // Watermark under the existing page
//            under = stamp.getUnderContent(i);
//            under.addImage(img);
                under = stamp.getUnderContent(i);
                under.beginText();
                under.setColorFill(Color.LIGHT_GRAY);
                under.setFontAndSize(bf, 30);
                under.showTextAligned(Element.ALIGN_LEFT, "HANYA UNTUK PAPARAN", 150, 430, 45);
                under.showTextAligned(Element.ALIGN_LEFT, "TIDAK SAH UNTUK SEBARANG URUSAN", 110, 310, 45);
                under.endText();
            }

            stamp.close();

        } catch (Exception ex) {
            LOG.error(ex);
        }
        return out;
    }
    
    public byte[] getByteOfFile(String path) throws FileNotFoundException, IOException{
        
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileUtil.copyStream(is, out);
        
        return out.toByteArray();
    }
}
