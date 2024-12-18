package etanah.view.strata;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.IntegrasiPermohonanDokumenDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.view.stripes.common.OutBoundIntegration;
import java.io.*;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DownloadGISFileMlk extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    TransaksiDAO transDAO;
    @Inject
    OutBoundIntegration obi;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrasiPermohonanButirDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    IntegrasiPermohonanDokumenDAO integrasiPermohonanDokumenDAO;
    @Inject
    DokumenDAO dokDAO;
    @Inject
    DokumenService dokServ;
    private static final Logger LOG = Logger.getLogger(GisStrataActionBean.class);
    public Transaksi trans;
    private Permohonan permohonan = new Permohonan();
    private String idPermohonan = new String();
    private Pengguna pguna;
    private String stageId;
    private String path;
    private String idPermohonan1 = new String();
    private String stageId1;
    private String folderZipToBeDownload;
    IntegrasiPermohonan integrasiPermohonan1;
    private boolean extractSuccess = Boolean.FALSE;
    private String[] kodDOC2 = {"STR1", "STR1A", "STR6", "STR7", "AJB", "PBT", "LMB", "SPL", "KJL", "PAB", "PATHS", "JPP"};
    private String[] kodDOC = {"PCSTR", "ULSN"};
    private List<String> fileStore;
    private String fileToDownload;
    private static final int BUFSIZE = 4096;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.info("-----START DOWNLOAD-----");
        stageId = request.getParameter("stageId");
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            folderZipToBeDownload = File.separator + "nfs_apps" + File.separator + "dms" + File.separator + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            folderZipToBeDownload = File.separator + "nfs_apps" + File.separator + "dms" + File.separator + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data

        }

        String filename = request.getParameter("filename");
        String files = getFolderZipToBeDownload() + filename + ".zip";
        File file = new File(files);
        int length = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(files);

        // sets response content type
        if (mimetype == null) {
            mimetype = "application/octet-stream";
        }
        response.setContentType(mimetype);
        response.setContentLength((int) file.length());
        String fileName = (new File(files)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            outStream.write(byteBuffer, 0, length);
        }

        in.close();
        outStream.close();

    }

    public List<String> getFileStore() {
        return fileStore;
    }

    public void setFileStore(List<String> fileStore) {
        this.fileStore = fileStore;
    }

    public boolean isExtractSuccess() {
        return extractSuccess;
    }

    public void setExtractSuccess(boolean extractSuccess) {
        this.extractSuccess = extractSuccess;
    }

    public String[] getKodDOC2() {
        return kodDOC2;
    }

    public void setKodDOC2(String[] kodDOC2) {
        this.kodDOC2 = kodDOC2;
    }

    public String getFileToDownload() {
        return fileToDownload;
    }

    public void setFileToDownload(String fileToDownload) {
        this.fileToDownload = fileToDownload;
    }

    public String getFolderZipToBeDownload() {
        return folderZipToBeDownload;
    }

    public void setFolderZipToBeDownload(String folderZipToBeDownload) {
        this.folderZipToBeDownload = folderZipToBeDownload;
    }

    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PenggunaDAO getPenggunaDAO() {
        return penggunaDAO;
    }

    public void setPenggunaDAO(PenggunaDAO penggunaDAO) {
        this.penggunaDAO = penggunaDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Transaksi getTrans() {
        return trans;
    }

    public void setTrans(Transaksi trans) {
        this.trans = trans;
    }

    public TransaksiDAO getTransDAO() {
        return transDAO;
    }

    public void setTransDAO(TransaksiDAO transDAO) {
        this.transDAO = transDAO;
    }

    public String getIdPermohonan1() {
        return idPermohonan1;
    }

    public void setIdPermohonan1(String idPermohonan1) {
        this.idPermohonan1 = idPermohonan1;
    }

    public String getStageId1() {
        return stageId1;
    }

    public void setStageId1(String stageId1) {
        this.stageId1 = stageId1;
    }

    public IntegrasiPermohonan getIntegrasiPermohonan1() {
        return integrasiPermohonan1;
    }

    public void setIntegrasiPermohonan1(IntegrasiPermohonan integrasiPermohonan1) {
        this.integrasiPermohonan1 = integrasiPermohonan1;
    }
}