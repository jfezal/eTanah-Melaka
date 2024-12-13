package etanah.view.lelong.dokumen;

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
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.util.FileUtil;
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
@UrlBinding("/lelong/dokumen/view")
public class ViewDocumentAction extends AbleActionBean {

    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    private long idDokumen;
    private String idFolder;
    private Pengguna pengguna;
    private static final Logger LOGGER = Logger.getLogger(ViewDocumentAction.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    Permohonan permohonan;
    private String idPermohonan;
    private Permohonan p;
    private FolderDokumen folderDokumen;
    private Dokumen dokumen;
    private String namaFizikal;
    private KandunganFolder kandunganFolder;

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

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getNamaFizikal() {
        return namaFizikal;
    }

    public void setNamaFizikal(String namaFizikal) {
        this.namaFizikal = namaFizikal;
    }

    public KandunganFolder getKandunganFolder() {
        return kandunganFolder;
    }

    public void setKandunganFolder(KandunganFolder kandunganFolder) {
        this.kandunganFolder = kandunganFolder;
    }

    @DefaultHandler
    public Resolution view() {
        LOGGER.info("--------------view-----------");
        String idDokumen2 = getContext().getRequest().getParameter("idDokumen");
        LOGGER.info("idDokumen : "+ idDokumen2);

        if (idDokumen2 == null) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(Long.valueOf(idDokumen2));
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }

        // log the view
        LOGGER.info("test");
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
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
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            if (f != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                if (isDebug) {
                    LOGGER.debug("creating watermark..");
                }
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2")) {
                    File signFile = new File(path + ".sig");
                    if (signFile.exists()) {
                        createWatermark = false;
                    }
                }

                if (createWatermark) {
//                    f = createWatermark(fis, path);
//                    fis = new FileInputStream(f);
//                    pdf = FileUtil.createWaterMark(fis);
                    final InputStream is = fis;
                    final String format = d.getFormat();
                    return new StreamingResolution(d.getFormat()) {

                        @Override
                        public void stream(HttpServletResponse response) throws Exception {
                            response.setContentType(format);
//                            response.setHeader("Content-disposition","attachment; filename=" + "eTanah.pdf" );

                            ByteArrayOutputStream bous = FileUtil.createWaterMark(is);
                            bous.writeTo(response.getOutputStream());

                        }
                    }.setFilename(f.getName());
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }

        getContext().getResponse().setContentType("application/octet-stream");

        return new StreamingResolution(d.getFormat(), fis);

    }

    private File createWatermark(InputStream is, String path) throws Exception {
        PdfReader reader = new PdfReader(is);
        int n = reader.getNumberOfPages();

        // Create a stamper that will copy the document to a new file

        File f = new File(TMP_DIR + (TMP_DIR.endsWith(File.separator) ? "" : File.separator) + System.nanoTime() + "_watermark");
        f.deleteOnExit();
        if (isDebug) {
            LOGGER.debug("file tmp path = " + f.getAbsolutePath());
        }
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
        return new JSP("lelong/dokumen/dokumen_sign.jsp").addParameter("popup", "true");
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
}
