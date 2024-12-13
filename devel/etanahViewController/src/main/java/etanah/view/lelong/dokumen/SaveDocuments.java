package etanah.view.lelong.dokumen;

import com.google.inject.Injector;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dokumen.scan.SerializableImage;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;

import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;

import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import javax.media.jai.JAI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * SaveDocument is a servlet to perform saving a binary content (document) to the database
 * (in a BLOB column).
 * Requires JAI library from Sun.
 */
public class SaveDocuments extends HttpServlet {

    public static boolean COMPRESSED_OUTPUT = false;

    public static final int TIFF_DEFAULT_COMPRESSION =
                TIFFEncodeParam.COMPRESSION_PACKBITS;
                //TIFFEncodeParam.COMPRESSION_LZW;

    public static final String TIFF_EXTENSION = ".TIFF";

    public static final String TIFF_CONTENT_TYPE = "image/tiff";

    private final static Logger LOG = Logger.getLogger(SaveDocuments.class);
    private final static boolean debug = LOG.isDebugEnabled();
    private final static boolean info = LOG.isInfoEnabled();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest req, 
                       HttpServletResponse res) throws ServletException, 
                                                       IOException {
        LOG.info("request received");

        if (debug){
            Cookie [] cookies = req.getCookies();
            if (cookies.length == 0) LOG.warn("No cookie submitted!");
            for (Cookie c : cookies){
                LOG.debug(c.getName() + "=" + c.getValue());
            }
        }
        
        Pengguna p = (Pengguna) req.getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPengguna = p.getIdPengguna();
        
        Injector injector = etanahContextListener.getInjector();
        
        Configuration conf = injector.getInstance(Configuration.class); 
        Session sess  = injector.getProvider(Session.class).get();
        DokumenDAO dokumenDAO = injector.getInstance(DokumenDAO.class);
        
        String documentPath = conf.getProperty("document.path");
        	
        InputStream is = null;
        try {
            ObjectInputStream ois = null;
            is = req.getInputStream();
            if (COMPRESSED_OUTPUT) {
                ZipInputStream iiz = new ZipInputStream(is);
                ois = new ObjectInputStream(iiz);
            } else {
                ois = new ObjectInputStream(is);
            }
            ArrayList f1 = (ArrayList) ois.readObject();

            LOG.debug("received " + (f1.size() - 1) + " images");

            // at position-0 should be the headerInfo (id of row to be inserted + format)
            String headerInfo = (String) f1.get(0);
            String [] infos = headerInfo.split(",");
            Long idDokumen = Long.valueOf(infos[0]);
            boolean isFormatPdf = infos.length > 1 && "PDF".equalsIgnoreCase(infos[1]);
            
            Dokumen dokumen = dokumenDAO.findById(idDokumen);
            if (dokumen == null){
            	LOG.fatal("Dokumen " + idDokumen + "tidak dijumpai");
            	throw new RuntimeException("Dokumen " + idDokumen + "tidak dijumpai");
            }

            // save into filesystem
            StringBuilder outputPath = new StringBuilder(documentPath);
            if (!documentPath.endsWith(File.separator)) outputPath.append(File.separator);
            // append the folder id
            StringBuilder relativePath = new StringBuilder();
            List<KandunganFolder> folder =  dokumen.getSenaraiFolder();
            if (folder != null && folder.size() > 0){
            	relativePath.append(folder.get(0).getFolder().getFolderId()).append(File.separator);
            }
            relativePath.append(idDokumen);
            outputPath.append(relativePath);
            File outputFile = new File(outputPath.toString());
            File fldr = outputFile.getParentFile();
            fldr.mkdirs();
            if (debug) LOG.debug("saving to " + outputPath.toString());

            if (debug) LOG.debug("start processing images");
            Vector<BufferedImage> imgs = new Vector<BufferedImage>();
            for (int i = 1; i < f1.size(); i++) {
                SerializableImage si = (SerializableImage) f1.get(i);
                /*
                MemoryImageSource mis = new MemoryImageSource(si.width, si.height,
                        si.pixels, 0, si.width);
                */
                BufferedImage bimg = 
                    new BufferedImage(si.width, si.height, BufferedImage.TYPE_INT_RGB);
                bimg.setRGB(0, 0, si.width, si.height, si.getPixels(), 0, 
                            si.width);
                imgs.add(bimg);
            }
            if (imgs.size() == 1) {
                //saveAsTiffImage(imgs.get(0), outputFile);
                //dokumen.setFormat(TIFF_CONTENT_TYPE);
            	if (!isFormatPdf){
            		saveAsJpegImage(imgs.get(0), outputFile);
            		dokumen.setFormat("image/jpeg");
            	} else{
                    saveAsPfdImage(imgs, outputFile);
                    dokumen.setFormat("application/pdf");
            	}
            } else {
                //saveAsTiffImage(imgs, outputFile);
                //dokumen.setFormat(TIFF_CONTENT_TYPE);
                saveAsPfdImage(imgs, outputFile);
                dokumen.setFormat("application/pdf");
            }
            if (debug) LOG.debug("completed processing images");

            dokumen.setNamaFizikal(relativePath.toString());
            dokumen.setSaiz(outputFile.length());
            InfoAudit ia = dokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new Date());
            dokumen.setInfoAudit(ia);
            
            Transaction txn = sess.beginTransaction();
            dokumenDAO.update(dokumen);
            txn.commit();

            res.setStatus(HttpServletResponse.SC_OK);

        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e1) {
                }
            }
        }

    }


    private static void saveAsTiffImage(BufferedImage bimg, 
                                        File fi) throws IOException {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(bimg);
        TIFFEncodeParam encodeParam = new TIFFEncodeParam();
        encodeParam.setCompression(TIFF_DEFAULT_COMPRESSION);
        OutputStream os = new FileOutputStream(fi);
        pb.add(os);
        pb.add("TIFF");
        pb.add(encodeParam);
        JAI.create("encode", pb);
        os.close();
    }

    private static void saveAsJpegImage(BufferedImage bimg, 
                                        File fi) throws IOException {
        ImageIO.write(bimg, "jpeg", fi);
    }

    private static void saveAsTiffImage(Vector<BufferedImage> imgs, 
                                        File fi) throws IOException {
        OutputStream out = new FileOutputStream(fi);
        TIFFEncodeParam param = new TIFFEncodeParam();
        param.setCompression(TIFF_DEFAULT_COMPRESSION);
        ImageEncoder encoder = 
            ImageCodec.createImageEncoder("TIFF", out, param);
        List l = imgs.subList(1, imgs.size());
        param.setExtraImages(l.iterator());
        encoder.encode(imgs.get(0));
        out.close();
    }

    private static void saveAsPfdImage(Vector<BufferedImage> imgs,
                                        File fi) throws IOException{
        try{
            Document document = new Document();
            document.setMargins(0f, 0f, 0f, 0f);
            PdfWriter.getInstance(document, new FileOutputStream(fi));
            document.open();
            Rectangle rect = document.getPageSize();

            int sz = imgs.size();
            for (int i = 0; i < sz; i++){
                if (i > 0){
                    document.newPage();
                }
                BufferedImage bi = imgs.get(i);                
				com.lowagie.text.Image img =
					com.lowagie.text.Image.getInstance(bi, null);
				img.scaleToFit(rect.getWidth(), rect.getHeight());
				document.add(img);
            }
            document.close();
        } catch (DocumentException e){
            e.printStackTrace();
            IOException eio = new IOException(e.getMessage());
            eio.initCause(e);
            throw eio;
        } catch (FileNotFoundException e1){
            e1.printStackTrace();
            IOException eio = new IOException(e1.getMessage());
            eio.initCause(e1);
            throw eio;
        }
    }
}
