/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pelan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;

import com.google.inject.Inject;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import com.sun.media.jai.codec.FileSeekableStream;
//import com.sun.media.jai.codec.ImageCodec;
//import com.sun.media.jai.codec.ImageDecoder;
//import com.sun.media.jai.codec.JPEGEncodeParam;
//import com.sun.media.jai.codec.SeekableStream;
//import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.JPEGEncodeParam;
import etanah.dao.KodSeksyenDAO;
import etanah.model.KodSeksyen;
import etanah.model.gis.PelanGIS;
import etanah.service.RegService;
import etanah.util.StringUtils;
//import java.awt.image.RenderedImage;
//import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@HttpCache(allow = true)
@UrlBinding("/pelan/view/{idPelan}")
public class ViewPelanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(ViewPelanActionBean.class);
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    RegService regService;
    @Inject
    private etanah.Configuration conf;
    private String idPelan;
    private String path2;

    public String getIdPelan() {
        return idPelan;
    }

    public void setIdPelan(String idPelan) {
        this.idPelan = idPelan;
    }

    @DefaultHandler
    public Resolution view() throws FileNotFoundException, IOException {
        if (StringUtils.isBlank(idPelan)) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }
        logger.info("idPelan byte = " + idPelan.length());
        if (idPelan.length() == 17) {
            logger.info("idPelan byte 16= " + idPelan.getBytes());
            logger.debug("id Pelan " + idPelan);
            logger.debug("id Pelan " + idPelan);
            String kodNegeri = conf.getProperty("kodNegeri");
            String kodDaerah = idPelan.substring(2, 4);
            String kodBpm = idPelan.substring(4, 6);
            String kodSeksyen = idPelan.substring(6, 9);
            logger.debug("kodSeksyen: " + kodSeksyen);
            String kodPelan = idPelan.substring(9, 10);
            logger.debug("kodPelan: " + kodPelan);
            String jenisPelan = "";
            if (kodPelan.equals("1")) {
                jenisPelan = "B1";
            } else {
                jenisPelan = "B2";
            }
            logger.debug("jenisPelan: " + jenisPelan);
            String noLot = idPelan.substring(10);
            String path = regService.cariPathPelan(kodNegeri, kodDaerah, kodBpm, kodSeksyen, noLot, jenisPelan);
            if (path == null) {
                return new ErrorResolution(500, "Dokumen tidak dijumpai");
            }
            String docPath = conf.getProperty("pelan.path");
            if (docPath == null) {
                return new ErrorResolution(500,
                        "Konfigurasi \"document.path\" tidak dijumpai");
            }
            path2 = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + jenisPelan + File.separator + path;
        }

        if (idPelan.length() == 16) {
            logger.info("idPelan byte 16= " + idPelan.getBytes());
            logger.debug("id Pelan " + idPelan);

            String kodNegeri = conf.getProperty("kodNegeri");
            String kodDaerah = idPelan.substring(2, 4);
            String kodBpm = idPelan.substring(4, 6);
            String kodSeksyen = idPelan.substring(6, 8);
            if (!kodSeksyen.equals(null)) {
                KodSeksyen seksyen = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
                kodSeksyen = seksyen.getSeksyen();
            }
            logger.debug("kodSeksyen: " + kodSeksyen);
            String kodPelan = idPelan.substring(8, 9);
            logger.debug("kodPelan: " + kodPelan);
            String jenisPelan = "";
            if (kodPelan.equals("1")) {
                jenisPelan = "B1";
            } else {
                jenisPelan = "B2";
            }
            logger.debug("jenisPelan: " + jenisPelan);
            String noLot = idPelan.substring(9);
            String path = regService.cariPathPelan(kodNegeri, kodDaerah, kodBpm, kodSeksyen, noLot, jenisPelan);
            if (path == null) {
                return new ErrorResolution(500, "Dokumen tidak dijumpai");
            }
            String docPath = conf.getProperty("pelan.path");
            if (docPath == null) {
                return new ErrorResolution(500,
                        "Konfigurasi \"document.path\" tidak dijumpai");
            }
            path2 = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + jenisPelan + File.separator + path;

        }
        if (idPelan.length() == 15) {
            logger.info("idPelan byte 15= " + idPelan.getBytes());
            logger.debug("id Pelan " + idPelan);

            String kodNegeri = conf.getProperty("kodNegeri");
            String kodDaerah = idPelan.substring(2, 4);
            String kodBpm = idPelan.substring(4, 6);
            String kodSeksyen = idPelan.substring(6, 8);
            kodSeksyen = 0 + kodSeksyen ;
             logger.debug("id kodSeksyen " + kodSeksyen);
             logger.debug("id kodDaerah " + kodDaerah);
             logger.debug("id kodBpm " + kodBpm);
            if (!kodSeksyen.equals(null)) {
               KodSeksyen seksyen1 = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
                kodSeksyen = seksyen1.getSeksyen();
//                seksyen2 = seksyen1.getSeksyen();
//                KodSeksyen seksyen = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
//                kodSeksyen = seksyen.getSeksyen();
            }
            logger.debug("kodSeksyen: " + kodSeksyen);
            String kodPelan = idPelan.substring(8, 9);
            logger.debug("kodPelan: " + kodPelan);
            String jenisPelan = "";
            if (kodPelan.equals("1")) {
                jenisPelan = "B1";
            } else {
                jenisPelan = "B2";
            }
            logger.debug("jenisPelan: " + jenisPelan);
            String noLot = idPelan.substring(8);
            logger.debug("id noLot " + noLot);
            String path = regService.cariPathPelan(kodNegeri, kodDaerah, kodBpm, kodSeksyen, noLot, jenisPelan);
            if (path == null) {
                return new ErrorResolution(500, "Dokumen tidak dijumpai");
            }
            String docPath = conf.getProperty("pelan.path");
            if (docPath == null) {
                return new ErrorResolution(500,
                        "Konfigurasi \"document.path\" tidak dijumpai");
            }
            path2 = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + jenisPelan + File.separator + path;

        }
        File f = new File(path2);
        FileInputStream fis = null;
        fis = new FileInputStream(f);
        String format = "image/tiff";
        return new StreamingResolution(format, fis);
    }

    public void tiffToJpg(String tiff, String output) throws FileNotFoundException, IOException {
        // Load the input image.
        RenderedOp src = JAI.create("fileload", tiff);

        //Adjust the compression quality
        JPEGEncodeParam encodeParam = new JPEGEncodeParam();
        encodeParam.setQuality(1.0f);

        // Encode the file as a JPEG image.
        FileOutputStream stream = new FileOutputStream(output);
        JAI.create("encode", src, stream, "JPEG", encodeParam);

        // Store the image in the JPEG format.
        JAI.create("filestore", src, output, "JPEG", encodeParam);
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

}
