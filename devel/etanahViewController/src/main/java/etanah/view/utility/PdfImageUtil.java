package etanah.view.utility;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
//import org.jpedal.PdfDecoder;
//import org.jpedal.exception.PdfException;

/**
 *
 * @author fikri
 */
public class PdfImageUtil {

    private static Logger LOGGER = Logger.getLogger(PdfImageUtil.class);

    public byte[] pdfToImageToPdf(InputStream is) throws IOException, DocumentException {
        return imageToPdf( pdfToImage(is) );
    }

    public List<BufferedImage> pdfToImage(InputStream is) {

        LOGGER.debug("Parsing image from PDF file ....");
        List<BufferedImage> result = new ArrayList<BufferedImage>();
//        PdfDecoder decodePdf = new PdfDecoder(true);
 //       PdfDecoder.setFontReplacements(decodePdf);

//        try {
////            decodePdf.openPdfFileFromInputStream(is, true);
////            for (int i = 1; i <= decodePdf.getPageCount(); i++) {
////                BufferedImage buffImage = decodePdf.getPageAsImage(i);
////                result.add(buffImage);
//            }
//
//        } catch (PdfException e) {
//            e.printStackTrace();
//        } finally {
//            decodePdf.closePdfFile();
//        }

        return result;
    }

    public byte[] imageToPdf(List<BufferedImage> bufferedImages)
            throws IOException, DocumentException {

        byte[] myBytes = null;
        PdfWriter pdf = null;
        ByteArrayOutputStream bytearrayos = new ByteArrayOutputStream();
        try {

            Document document = new Document();

            pdf = PdfWriter.getInstance(document, bytearrayos);
            document.open();
            for (BufferedImage bufferedImage : bufferedImages) {
                com.lowagie.text.Image image = com.lowagie.text.Image.getInstance(bufferedImage, null);
//                image.scalePercent(70);
                image.setAbsolutePosition(0, 0);
                document.add(image);
                document.newPage();
            }
            document.close();
            myBytes = bytearrayos.toByteArray();

        } finally {
            if (pdf != null) {
                pdf.flush();
                pdf.close();
            }
        }

        LOGGER.info("convertIntoPDF() ...... End");
        return (myBytes);
    }
}
