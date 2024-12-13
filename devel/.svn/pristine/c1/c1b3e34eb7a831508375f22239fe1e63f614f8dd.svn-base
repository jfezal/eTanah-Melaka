package etanah.view.dokumen;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;

import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.lowagie.text.*;
import java.io.*;
import com.lowagie.text.pdf.*;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.util.FileUtil;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import etanah.view.utility.PdfImageUtil;
import java.awt.Color;
import java.util.*;
//import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Class for viewing document. This request MUST NOT be decorated by Sitemesh.
 * @author hishammk
 *
 */
@HttpCache(allow = true)
@UrlBinding("/dokumen/view/{idDokumen}")
public class ViewDocumentAction extends AbleActionBean {

    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private Dokumen dokumen;

    private long idDokumen;
    private String idFolder;
    private Pengguna pengguna;
    private static final Logger LOGGER = Logger.getLogger(ViewDocumentAction.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long id) {
        this.idDokumen = id;
    }

    @DefaultHandler
    public Resolution view() {
        LOGGER.info("MASUK VIEW");
        
        idDokumen= Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOGGER.info("idDokumen" + idDokumen);
        
        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (p.getTahapSekuriti() == null) {
            return new ErrorResolution(401, "Pengguna tiada kod klasifikasi.");
        } else if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }

        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        if (d.getBaru() == 'Y') {
            ia = d.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pengguna);
            d.setInfoAudit(ia);
            d.setBaru('T');
            dokumenDAO.update(d);
        }
        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        InputStream fis = null;
        File f = new File(path);       
        
        boolean flag = false;
        
        try {
            
            if (!f.exists()) {
                WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
                String[] path2 = d.getNamaFizikal().split("/");
                path = d.getNamaFizikal();
                if (path2.length > 0) {
                    String daerah = path2[3];
                    String bpm = path2[4];
                    String jenisHm = path2[5];
                    String versi = path2[6];
                    String idHakmilik = path2[7];
                    fis = worm.get(idHakmilik, daerah, bpm, versi, jenisHm, versi);
                    InputStream sign = worm.get(idHakmilik + ".sig", daerah, bpm, versi, jenisHm, versi);
                    if (sign != null) flag = true;
                    
                }
            } else {
                fis = new FileInputStream(f);
            }
            
            
            if (fis != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
                    if (flag) {
                         createWatermark = false;
                    } else {
                         File signFile = new File(path + ".sig");
                         if (signFile.exists()) {
                             createWatermark = false;
                         }
                    }
                    
                }
                
                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHDK")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHKK")) {
                    createWatermark = false;
                }
                if(isDebug) LOGGER.debug("creating watermark :" + createWatermark);

                BufferedInputStream bis = new BufferedInputStream(fis);

                if (createWatermark) {
                    ByteArrayOutputStream bous = FileUtil.createWaterMark(fis);
                    InputStream inputStream = new ByteArrayInputStream(bous.toByteArray());
                    bis = new BufferedInputStream(inputStream);
                }

                PdfImageUtil pdfImageUtil = new PdfImageUtil();

                bis = new BufferedInputStream(
                                new ByteArrayInputStream(pdfImageUtil.pdfToImageToPdf(bis)));

                final BufferedInputStream b = bis;

                String filename = null;
                int sep = d.getNamaFizikal().lastIndexOf(File.separator);
                if (sep >= 0) d.getNamaFizikal().substring(sep);
                else filename = d.getNamaFizikal();
                final String fn = filename;
                
                getContext().getResponse().setContentType("application/octet-stream" );
                
                return new StreamingResolution (d.getFormat(), bis).setFilename(filename);

//                return new StreamingResolution(d.getFormat()) {
//
//                    @Override
//                    public void stream(HttpServletResponse response) throws Exception {
//                        response.setHeader("Cache-Control", "no-cache");
//                        response.setHeader("Pragma", "no-cache");
//                        response.setDateHeader("Expires", 0);
//                        response.setHeader("Content-disposition", "inline; filename=" + fn);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        int ch;
//                        while ((ch = b.read()) != -1) {
//                            baos.write(ch);
//                        }
//                        baos.writeTo(response.getOutputStream());
//                    }
//                };
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }

        getContext().getResponse().setContentType("application/octet-stream" );

        String filename = null;
        int sep = d.getNamaFizikal().lastIndexOf(File.separator);
        if (sep >= 0) d.getNamaFizikal().substring(sep);
        else filename = d.getNamaFizikal();

        return new StreamingResolution(d.getFormat(), fis).setFilename(filename);

    }

    public Resolution viewLogDocument() {

        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        dokumen = dokumenDAO.findById(idDokumen);

        if (dokumen == null || dokumen.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        return new JSP("dokumen/view_log.jsp").addParameter("popup", "true");
    }

     public Resolution showEditForm() {

        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        dokumen = dokumenDAO.findById(idDokumen);

        if (dokumen == null || dokumen.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        if (getContext().getRequest().getParameter("param") != null) {
            getContext().getRequest().setAttribute("param", getContext().getRequest().getParameter("param"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }

        return new JSP("dokumen/edit_doc.jsp").addParameter("popup", "true");
    }

     public Resolution saveEditDoc() {

         if (isDebug) {
             LOGGER.debug("dokumen =" + dokumen.getTajuk());
             LOGGER.debug("dokumen =" + dokumen.getNoRujukan());
             LOGGER.debug("dokumen =" + dokumen.getTarikhDokumen());
         }

         if (dokumen != null) {
             pengguna = ((etanahActionBeanContext) getContext()).getUser();

             Dokumen d = dokumenDAO.findById(dokumen.getIdDokumen());
             d.setTajuk(dokumen.getTajuk());
             d.setNoRujukan(dokumen.getNoRujukan());
             d.setTarikhDokumen(dokumen.getTarikhDokumen());

             Session s = sessionProvider.get();
             Transaction tx = s.beginTransaction();

             DokumenCapaian dc = new DokumenCapaian();
             dc.setDokumen(d);
             dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("KD"));
             dc.setAlasan("Kemaskini Dokumen");
             InfoAudit ia = new InfoAudit();
             ia.setDimasukOleh(pengguna);
             ia.setTarikhMasuk(new Date());
             dc.setInfoAudit(ia);

             dokumenDAO.save(d);
             dokumenCapaianDAO.save(dc);

             tx.commit();
             addSimpleMessage("Kemaskini Dokumen Berjaya.");
         }

         if (getContext().getRequest().getParameter("param") != null) {
            getContext().getRequest().setAttribute("param", getContext().getRequest().getParameter("param"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }

         return new JSP("dokumen/edit_doc.jsp").addParameter("popup", "true");
     }

    private File createWatermark(InputStream is, String path) throws Exception {
        PdfReader reader = new PdfReader(is);
        int n = reader.getNumberOfPages();

        // Create a stamper that will copy the document to a new file

        File f = new File(TMP_DIR + (TMP_DIR.endsWith(File.separator) ? "" : File.separator) + System.nanoTime() + "_watermark");
        f.deleteOnExit();
        if(isDebug)
            LOGGER.debug("file tmp path = " + f.getAbsolutePath());
//        File f = File.createTempFile(TMP_DIR + (TMP_DIR.endsWith(File.separator) ? "" : File.separator) + System.nanoTime() + "_watermark", null);
        FileOutputStream fos = new FileOutputStream(f);
        PdfStamper stamp = new PdfStamper(reader, fos);
        int i = 0;
        PdfContentByte under;
        PdfContentByte over;

//        Image img = Image.getInstance("/home/fikri/baharu.gif"); //tmp
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.WINANSI, BaseFont.EMBEDDED);

//        img.setAbsolutePosition(200, 400);

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

            // Text over the existing page
//            over = stamp.getOverContent(i);
//            over.saveState();
//            over.setColorFill(Color.LIGHT_GRAY);
//            over.beginText();
//            over.setFontAndSize(bf, 20);
//            over.showTextAligned(Element.ALIGN_LEFT, "HANYA UNTUK PAPARAN", 150, 400, 45);
//            over.showTextAligned(Element.ALIGN_LEFT, "TIDAK SAH UNTUK SEBARANG URUSNIAGA", 110, 310, 45);
//            over.endText();
//            over.restoreState();
        }

        stamp.close();
        return f;
    }

    //for temporary use. should recode
    public Resolution signForm() {
        pengguna = ((etanahActionBeanContext) getContext()).getUser();
        Dokumen d = dokumenDAO.findById(idDokumen);
        System.out.println("d.getNamaFizikal() ::" + d.getNamaFizikal() + "::" + File.separator);
        idFolder = d.getNamaFizikal().substring(0, d.getNamaFizikal().indexOf(File.separator));
        String afterSign = getContext().getRequest().getParameter("afterSign");
        String vDoc = getContext().getRequest().getParameter("vdoc");
        if (StringUtils.isNotBlank(afterSign)) {
            getContext().getRequest().setAttribute("afterSign", "true");
        }
        if (StringUtils.isNotBlank(vDoc)) {
            getContext().getRequest().setAttribute("vDoc", "true");
        }
        return new JSP("dokumen/dokumen_sign.jsp").addParameter("popup", "true");
    }

    public Resolution viewPdf() {
        Logger.getLogger(ViewDocumentAction.class).info("idDokumen :" + idDokumen);
        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }
        String docPath = conf.getProperty("document.path");
        String afterSign = getContext().getRequest().getParameter("afterSign");
        String fn = d.getNamaFizikal();
//        if(StringUtils.isNotBlank(afterSign) && afterSign.equals("true")){
//            docPath = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + "sig";
//            fn = d.getNamaFizikal().substring(d.getNamaFizikal().indexOf(File.separator)+1);
//        }
        System.out.println("fn ::" + fn);
        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
        if (f != null) {
            try {
                return new StreamingResolution(d.getFormat(), new FileInputStream(f));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
}
