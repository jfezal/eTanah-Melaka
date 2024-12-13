package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.IntegrasiPermohonanDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanBangunan;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.common.OutBoundIntegration;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.hibernate.Session;

/*
 * @author faidzal
 * edit by zadirul for pelan_lokas_GIS - 15 august 2011
 */
@UrlBinding("/strata/gis/mlk")
public class GisMLKStrataActionBean extends AbleActionBean {

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
    KandunganFolderService kfServ;
    @Inject
    KandunganFolderDAO kfDAO;
    @Inject
    FolderDokumenDAO folderDokDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokServ;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodBandarPekanMukimDAO kodMukimDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(GisMLKStrataActionBean.class);
    public Transaksi trans;
    private Permohonan permohonan = new Permohonan();
    private String idPermohonan = new String();
    private Pengguna pguna;
    private String stageId;
    private String path;
    private String idPermohonan1 = new String();
    private String stageId1;
    IntegrasiPermohonan integrasiPermohonan1;
    private boolean isDokumenDeleted = Boolean.FALSE;
    private boolean isDokumenExist = Boolean.FALSE;
    private boolean isDokumenExist2 = Boolean.FALSE;
    private boolean extractSuccess = Boolean.FALSE;
    private boolean showKomms = Boolean.FALSE;
    private boolean updateSuccess = Boolean.FALSE;
    private boolean checked = Boolean.FALSE;
    private boolean strataXML = Boolean.FALSE;
    private boolean attachments = Boolean.FALSE;
    private boolean dwnldDisabled = Boolean.FALSE;
    private boolean muatTurunBtn = Boolean.FALSE;
    private boolean selesaiBtn = Boolean.TRUE;
    private boolean enabledHantar = Boolean.FALSE;
    private List<PelanGIS> pelanGIS;
    private String[] kodDOC2 = {"STR1", "STR1A", "STR6", "STR7", "AJB", "PBT", "LMB", "SPL", "KJL", "PAB", "PATHS", "JPP"};
    private String[] kodDOC = {"PCSTR", "ULSN"};
    private String kodNegeri;
    private List<String> fileStore;
    private String fileToDownload;
    private static final int BYTES_DOWNLOAD = 1024;
    private String arahan;
    private String kaedah;
    private String hantar;
    private String hantarfile;
    private String hiddenHantar;
    private String gisStage;
    private List<Dokumen> dokumen;
    private List<Dokumen> listDok;
    private List<UrusanDokumen> dokUrusan;
    private List<String> listDokUrusan;
    private List<String> checkedList = new ArrayList();
    private Dokumen checkedDok;

    public boolean isIsDokumenDeleted() {
        return isDokumenDeleted;
    }

    public void setIsDokumenDeleted(boolean isDokumenDeleted) {
        this.isDokumenDeleted = isDokumenDeleted;
    }

    public boolean isIsDokumenExist() {
        return isDokumenExist;
    }

    public void setIsDokumenExist2(boolean isDokumenExist2) {
        this.isDokumenExist2 = isDokumenExist2;
    }

    public boolean isIsDokumenExist2() {
        return isDokumenExist2;
    }

    public void setIsDokumenExist(boolean isDokumenExist) {
        this.isDokumenExist = isDokumenExist;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info(" -------- 1 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        this.pguna = peng;
        String[] name = {"permohonan"};
        Object[] object = {permohonan};
        List<Transaksi> listTrans = transDAO.findByEqualCriterias(name, object, null);
        if (listTrans.size() > 0) {
            trans = listTrans.get(0);
        }
        PelanGIS pelanGIS = strService.findPelanByIdPermohonan(permohonan.getIdPermohonan());
        if (pelanGIS != null) {
            getContext().getRequest().setAttribute("adaPelan", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("adaPelan", Boolean.FALSE);
        }
        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            BPelService serviceBpel = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semakkemasukan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                } else if (stageId.equals("g_cetakpelan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("2")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                }
            }
        }

    }

    @DefaultHandler
    public Resolution showForm() throws ParserConfigurationException {
        LOG.info(" -------- 2 ----------");
        getPathJPP();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService serviceBpel = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        getContext().getRequest().setAttribute("pengguna", peng.getIdPengguna());
        return new JSP("strata/pbbm/gisStrata.jsp").addParameter("tab", "true");
    }

    public Resolution Jupem() throws IOException, ParserConfigurationException, SAXException {
        LOG.info(" -------- 3 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        listDok = new ArrayList();
        dokUrusan = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());

        if (!mhnBngn.isEmpty()) {
            if (mhnBngn.get(0).getNamaLain() != null) {
                getContext().getRequest().setAttribute("manual", Boolean.TRUE);
//                selesaiBtn = true;
            }
        }
        for (int i = 0; i < dokUrusan.size(); i++) {
            listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
        }
        for (String kod : listDokUrusan) {
            Dokumen dokumenToBeZip = strService.findDok(idPermohonan, kod);
            // Dapatkan data utk kod dokumen terlibat
            if (dokumenToBeZip != null) {
                for (int a = 0; a < kodDOC2.length; a++) {
                    String kodDok = kodDOC2[a];
                    if (dokumenToBeZip.getKodDokumen().getKod().equals(kodDok)) {
                        dokumenToBeZip.setNoRujukan("2");
                    }
                }
                if (kod.equals("JPP")) {
                    LOG.info("----listDok----::" + dokumenToBeZip.getIdDokumen());
                    listDok.add(dokumenToBeZip);
                    // masukkan path baru dalam zip file. get name file, cth : 281.xml
                } else {
                    LOG.info("----listDok----::" + dokumenToBeZip.getIdDokumen());
                    listDok.add(dokumenToBeZip);
                }
            }
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            gisStage = "Senarai Fail untuk di Hantar ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            gisStage = "Senarai Fail untuk di Hantar ke JUPEM (Peringkat 2)";
        }
        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semakkemasukan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                } else if (stageId.equals("g_cetakpelan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("2")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                }
            }
        }
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public void checkDocExist(String idMohon) {
        LOG.info(" -------- 4 ----------");
        LOG.info("----lalu checkDocExist----");
        permohonan = permohonanDAO.findById(idMohon);
        dokumen = new ArrayList();
        listDok = new ArrayList();
        dokUrusan = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
        }
        for (String kod : listDokUrusan) {
            Dokumen dokumenToBeZip = strService.findDok(idMohon, kod);
            // Dapatkan data utk kod dokumen terlibat
            if (dokumenToBeZip != null) {
                if (dokumenToBeZip.getNamaFizikal() != null) {
                    if (kod.equals("JPP")) {
                        //Add to StrataXML directory
                        LOG.info("----dokumen----::" + dokumenToBeZip.getIdDokumen());
                        dokumen.add(dokumenToBeZip);
                        // masukkan path baru dalam zip file. get name file, cth : 281.xml
                    } else {
                        //Add to Attachment directory
                        LOG.info("----dokumen----::" + dokumenToBeZip.getIdDokumen());
                        dokumen.add(dokumenToBeZip);
                    }


                }
                LOG.info("-------hidval" + dokumenToBeZip.getKodDokumen().getKod());
                String checkDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                dokumenToBeZip.setCatatanMinit(checkDok);
                listDok.add(dokumenToBeZip);
            }
        }
        checked = true;
    }

    public Resolution simpanDoc() throws IOException, ParserConfigurationException, SAXException {
        LOG.info(" -------- 5 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        dokUrusan = new ArrayList();
        listDok = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            LOG.info("dokUrusan.get(i).getKodDokumen().getKod()-----:" + dokUrusan.get(i).getKodDokumen().getKod());
            Dokumen dokumenToBeZip = strService.findDok(permohonan.getIdPermohonan(), dokUrusan.get(i).getKodDokumen().getKod());
            if (dokumenToBeZip != null) {
                LOG.info("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                String checkedDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("----checkedDok----:" + checkedDok);
                if (checkedDok.equals("2")) {
                    checkedList.add(checkedDok);
                }
                dokumenToBeZip.setCatatanMinit(checkedDok);
                listDok.add(dokumenToBeZip);
                listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
            }
        }
        if (!checkedList.isEmpty()) {
            LOG.info("PENGGUNA : " + peng.getIdPengguna());
            LOG.info("TASK ID : " + taskId);
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("stageId>>>>>>" + stageId);
            LOG.info("----hantarfile----::" + hantarfile);
            // Create new zip file
            dokumen = new ArrayList();
            String fileNameToBeUpload = "";
            String fileNameToBeUpload2 = "";
            String folderPath = "";
            String folderZipToBeUpload = "";
            String docPath = conf.getProperty("document.path");
            if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
                folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
                folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
                folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
                folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            }
            if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                if (d != null) {
                    //Baca file XML JPP
                    Document doc = null;
                    File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = null;
                    db = dbf.newDocumentBuilder();
                    LOG.info("-----db-----::" + db);
                    doc = db.parse(f);
                    LOG.info("-----doc-----::" + doc);
                    NodeList n1 = doc.getElementsByTagName("Scheme");
                    LOG.info("-----n1-----::" + n1);
                    String pengukur_noic = "";
                    String negeri = "";
                    String daerah = "";
                    String mukim = "";
                    String seksyen = "";
                    String lot_no = "";
                    String skim = "";
                    for (int i = 0; i < n1.getLength(); i++) {
                        pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                        negeri = (n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue());
                        daerah = (n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue());
                        mukim = (n1.item(i).getAttributes().getNamedItem("mukim").getNodeValue());
                        seksyen = (n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue());
                        lot_no = (n1.item(i).getAttributes().getNamedItem("lot").getNodeValue());
                        skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                        fileNameToBeUpload = negeri + daerah + mukim + seksyen + lot_no + skim + ".zip";
                        fileNameToBeUpload2 = negeri + daerah + mukim + seksyen + lot_no + skim;
                        //cth : 05001023044501(S)281.zip
                    }
                    fileToDownload = fileNameToBeUpload2;
                    //create directory
                    final File file = new File(folderPath);
                    final File parent_directory = file.getParentFile();
                    if (null != parent_directory) {
                        file.setReadable(true);
                        file.setWritable(true);
                        file.mkdirs();
                        LOG.info("-----SUCCESS DIRECTORY-----");
                    }

                }
            }
            // Create new zip file
            dokumen = new ArrayList();
            byte[] buffer = new byte[100 * 1024];
            FileOutputStream fileOutputStream = new FileOutputStream(new File(folderZipToBeUpload + fileNameToBeUpload));
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            for (String kod : listDokUrusan) {
                Dokumen dokumenToBeZip = strService.findDok(permohonan.getIdPermohonan(), kod);
                if (dokumenToBeZip != null) {
                    String dokKod = getContext().getRequest().getParameter(dokumenToBeZip.getKodDokumen().getKod());
                    LOG.info("----dokKod----" + dokKod);

                    if (dokKod != null) {
                        Dokumen dok = dokDAO.findById(Long.parseLong(dokKod));
                        LOG.info("----dok----" + dok);
                        LOG.info("-----dokumenToBeZip.getNamaFizikal-----" + dok.getNamaFizikal());
                        FileInputStream fileInputStream = new FileInputStream(docPath + dok.getNamaFizikal());

                        String filepath = dok.getNamaFizikal(); //Dapatkan nama fizikal, cth : 00/05/11/99/281.xml
                        String filepathnew = filepath.replace(File.separator, ":"); // cth : 00:05:11:99:281.xml
                        String filename[] = filepathnew.split(":");

                        if (dok.getKodDokumen().getKod().equals("JPP")) {
                            strataXML = true;
                            dokumen.add(dok);
                            //Add to StrataXML directory
                            zipOutputStream.putNextEntry(new ZipEntry("StrataXML" + File.separator + filename[filename.length - 1]));
                        } else if (!dok.getKodDokumen().getKod().equals("JPP")) {
                            //Add to Attachment directory
                            attachments = true;
                            dokumen.add(dok);
                            zipOutputStream.putNextEntry(new ZipEntry("Attachment" + File.separator + filename[filename.length - 1]));
                        }

                        int length;

                        while ((length = fileInputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, length);
                        }

                        fileInputStream.close();
                        fileInputStream = null;
//                }
//            }
                    }

                }
            }
            LOG.info("-----PASS-----::");
            if (zipOutputStream != null) {
                zipOutputStream.closeEntry();
            }
            zipOutputStream.close();
            zipOutputStream = null;
            hiddenHantar = "2";
            dwnldDisabled = false;
            checked = true;
            showKomms = true;
            muatTurunBtn = true;
        } else {
            hiddenHantar = "2";
            checked = true;
            dwnldDisabled = false;
            showKomms = false;
        }
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution zipfiles() throws IOException, ParserConfigurationException, SAXException {
        LOG.info(" -------- 6 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        dokUrusan = new ArrayList();
        listDok = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            LOG.info("dokUrusan.get(i).getKodDokumen().getKod()-----:" + dokUrusan.get(i).getKodDokumen().getKod());
            Dokumen dokumenToBeZip = strService.findDok(permohonan.getIdPermohonan(), dokUrusan.get(i).getKodDokumen().getKod());
            if (dokumenToBeZip != null) {
                LOG.info("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                String checkedDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("----checkedDok----:" + checkedDok);
                if (checkedDok.equals("2")) {
                    checkedList.add(checkedDok);
                }
                dokumenToBeZip.setCatatanMinit(checkedDok);
                listDok.add(dokumenToBeZip);
                listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
            }
        }
        if (!checkedList.isEmpty()) {
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            LOG.info("PENGGUNA : " + peng.getIdPengguna());
            LOG.info("TASK ID : " + taskId);
            BPelService serviceBpel = new BPelService();
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("stageId>>>>>>" + stageId);
            LOG.info("----hantarfile----::" + hantarfile);
            if (hantarfile.equalsIgnoreCase("1")) {
                hiddenHantar = "1";
                hantar = "1";
            } else if (hantarfile.equalsIgnoreCase("2")) {
                hiddenHantar = "2";
                hantar = "2";
            }
            String fileNameToBeUpload = "";
            String fileNameToBeUpload2 = "";
            String folderPath = "";
            String folderZipToBeUpload = "";
            String docPath = conf.getProperty("document.path");
            if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
                folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
                folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
                folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
                folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            }
            if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                if (d != null) {
                    //Baca file XML JPP
                    Document doc = null;
                    File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = null;
                    db = dbf.newDocumentBuilder();
                    LOG.info("-----db-----::" + db);
                    doc = db.parse(f);
                    LOG.info("-----doc-----::" + doc);
                    NodeList n1 = doc.getElementsByTagName("Scheme");
                    LOG.info("-----n1-----::" + n1);
                    String pengukur_noic, negeri, daerah, mukim, seksyen, lot_no, skim;

                    for (int i = 0; i < n1.getLength(); i++) {
                        pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                        negeri = (n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue());
                        daerah = (n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue());
                        mukim = (n1.item(i).getAttributes().getNamedItem("mukim").getNodeValue());
                        seksyen = (n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue());
                        lot_no = (n1.item(i).getAttributes().getNamedItem("lot").getNodeValue());
                        skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                        fileNameToBeUpload = negeri + daerah + mukim + seksyen + lot_no + skim + ".zip";
                        fileNameToBeUpload2 = negeri + daerah + mukim + seksyen + lot_no + skim;
                        //cth : 05001023044501(S)281.zip
                    }
                    fileToDownload = fileNameToBeUpload2;
                    //create directory
                    final File file = new File(folderPath);
                    final File parent_directory = file.getParentFile();
                    if (null != parent_directory) {
                        file.setReadable(true);
                        file.setWritable(true);
                        file.mkdirs();
                        LOG.info("-----SUCCESS DIRECTORY-----");
                    }

                }
            }
            // Create new zip file
            dokumen = new ArrayList();
            byte[] buffer = new byte[100 * 1024];
            FileOutputStream fileOutputStream = new FileOutputStream(new File(folderZipToBeUpload + fileNameToBeUpload));
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            for (String kod : listDokUrusan) {
                Dokumen dokumenToBeZip = strService.findDok(permohonan.getIdPermohonan(), kod);
                if (dokumenToBeZip != null) {
                    String dokKod = getContext().getRequest().getParameter(dokumenToBeZip.getKodDokumen().getKod());
                    LOG.info("----dokKod----" + dokKod);

                    if (dokKod != null) {
                        Dokumen dok = dokDAO.findById(Long.parseLong(dokKod));
                        LOG.info("----dok----" + dok);
                        LOG.info("-----dokumenToBeZip.getNamaFizikal-----" + dok.getNamaFizikal());
                        FileInputStream fileInputStream = new FileInputStream(docPath + dok.getNamaFizikal());

                        String filepath = dok.getNamaFizikal(); //Dapatkan nama fizikal, cth : 00/05/11/99/281.xml
                        String filepathnew = filepath.replace(File.separator, ":"); // cth : 00:05:11:99:281.xml
                        String filename[] = filepathnew.split(":");

                        if (dok.getKodDokumen().getKod().equals("JPP")) {
                            strataXML = true;
                            dokumen.add(dok);
                            //Add to StrataXML directory
                            zipOutputStream.putNextEntry(new ZipEntry("StrataXML" + File.separator + filename[filename.length - 1]));
                        } else if (!dok.getKodDokumen().getKod().equals("JPP")) {
                            //Add to Attachment directory
                            attachments = true;
                            dokumen.add(dok);
                            zipOutputStream.putNextEntry(new ZipEntry("Attachment" + File.separator + filename[filename.length - 1]));
                        }

                        int length;

                        while ((length = fileInputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, length);
                        }

                        fileInputStream.close();
                        fileInputStream = null;
//                }
//            }
                    }

                }
            }
            LOG.info("-----PASS-----::");
            if (zipOutputStream != null) {
                zipOutputStream.closeEntry();
            }
            zipOutputStream.close();
            zipOutputStream = null;
            dwnldDisabled = false;
            checked = true;
            showKomms = true;
            muatTurunBtn = true;
        } else {
            checked = true;
            dwnldDisabled = true;
            showKomms = false;
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution transferFile() {
        LOG.info(" -------- 7 ----------");
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        obi = new OutBoundIntegration();
        obi.setTaskId(taskId);
        error = obi.copyfile();
        addSimpleError(error);
        addSimpleMessage(error);
        return new JSP("strata/pbbm/gisStrata.jsp").addParameter("tab", "true");
    }

    //Tambah function checking di sini - IDA
    public Resolution CheckJupem() {
        LOG.info(" -------- 8 ----------");
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);


        //Test for Retrive ToJupem
        try {

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                /**
                 * Hafifi 18/06/2013 : Create Zip File from list of document and
                 * send it to Koms Server PTG*
                 */
                String gisPath = conf.getProperty("pelan.path");
                String docPath = conf.getProperty("document.path");
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                kaedah = d.getCatatanMinit();
                PermohonanStrata mohonStrata = strService.findIDNamaSkim(permohonan.getIdPermohonan());
                String fileNameToBeUpload = mohonStrata.getNamaSkim() + ".zip"; //return no skim

                //Ida 20/06/2013 : File to download to Kom Server
                String ftpUrl = "10.66.130.67"; //TODO: Need to change to necessary data
                String username = "etadmin"; //TODO: Need to change to necessary data
                String password = "q1w2e3r4-etanah"; //TODO: Need to change to necessary data
                FTPClient client = new FTPClient();
                InputStream fis = null;
                String folderZipToBeUpload = gisPath + "B4" + File.separator + "05" + File.separator + mohonStrata.getNamaSkim();
                //fileNameToBeUpload = "-1.zip";
                FileOutputStream fos = null;

                File file = new File(folderZipToBeUpload);
                if (!file.exists()) {
                    file.setReadable(true);
                    file.setWritable(true);
                    file.mkdirs();
                }

                try {
                    client.connect(ftpUrl, 21);
                    client.login(username, password);
                    client.enterLocalPassiveMode();
                    client.setFileType(FTP.BINARY_FILE_TYPE);

                    //akan masukan zip file di dir ini
                    BPelService serviceBpel = new BPelService();
                    Task t = null;
                    t = serviceBpel.getTaskFromBPel(taskId, peng);
                    stageId = t.getSystemAttributes().getStage();
                    LOG.info("stageId>>>>>>" + stageId);
                    if (stageId.equalsIgnoreCase("g_sedialaporan")) {
                        client.changeWorkingDirectory("ToPTG" + File.separator + "00" + File.separator + "Strata" + File.separator + "B4StrataTitlePlan");  //TODO: Need to change to necessary data
                    } else if (stageId.equalsIgnoreCase("g_keputusan2")) {
                        client.changeWorkingDirectory("ToPTG" + File.separator + "00" + File.separator + "Strata" + File.separator + "StrataTitlePlan");  //TODO: Need to change to necessary data
                    }
                    LOG.info("---Server Directory ---" + client.printWorkingDirectory());

                    fos = new FileOutputStream(new File(folderZipToBeUpload, fileNameToBeUpload)); //directory local to be copied

                    fis = client.retrieveFileStream(client.printWorkingDirectory() + File.separator + fileNameToBeUpload);

                    int length;
                    byte[] buffer = new byte[100 * 1024];
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }

                    client.retrieveFile(client.printWorkingDirectory() + fileNameToBeUpload, fos);

                } catch (Exception ex) {
                    LOG.info("---Error 1---" + ex.getMessage());
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                        client.disconnect();
                    } catch (IOException ex) {
                        LOG.info("---Error 2---" + ex.getMessage());
                    }
                }

                extractZip();

            } //closed kod negeri


        } catch (Exception e) {
            LOG.error(e);
        }
        //End Test
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_sedialaporan")) {
            gisStage = "Terima Fail Dari JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_keputusan2")) {
            gisStage = "Terima Fail Dari JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisPabStrataMlk.jsp").addParameter("tab", "true");
    }

    /**
     * ** 27/06/2013: SYAFIQ AZ: Extract Zip File ***
     */
    public void extractZip() throws FileNotFoundException {
        LOG.info(" -------- 9 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        try {

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                String docPath = conf.getProperty("document.path");
                String gisPath = conf.getProperty("pelan.path");
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                byte[] buffer = new byte[1024];
                try {
                    if (d != null) {
                        String string = d.getNamaFizikal();
                        string = string.replace(File.separator, ":");
                        String parts[] = string.split(":");
                        String path = docPath + parts[0] + File.separator + parts[1] + File.separator + parts[2] + File.separator + parts[3] + File.separator + parts[4] + File.separator;
                        String path2 = docPath + parts[0] + File.separator + parts[1] + File.separator + parts[2] + File.separator + parts[3] + File.separator + parts[4];
                        String path3 = parts[0] + File.separator + parts[1] + File.separator + parts[2] + File.separator + parts[3] + File.separator + parts[4] + File.separator;
                        PermohonanStrata mohonStrata = strService.findIDNamaSkim(permohonan.getIdPermohonan());
                        String fileNameToBeUpload = mohonStrata.getNamaSkim() + ".zip"; //return no skim
                        LOG.info("-----fileNameToBeUpload-----" + fileNameToBeUpload);
                        LOG.info("-----path+fileNameToBeUpload-----" + path + fileNameToBeUpload);
                        //HAFIFI 30/06/2013 : Read from correct zip file with correct path
                        String folderZipToBeUpload = gisPath + "B4" + File.separator + "05" + File.separator + mohonStrata.getNamaSkim() + File.separator + mohonStrata.getNamaSkim() + ".zip";
                        ZipInputStream zis = new ZipInputStream(new FileInputStream(folderZipToBeUpload));
                        ZipEntry ze = zis.getNextEntry();
                        while (ze != null) {

                            String fileName = ze.getName();
                            LOG.info("-----path + fileName-----" + path + fileName);
                            File newFile = new File(path + fileName);


                            //create all non exists folders
                            //else you will hit FileNotFoundException for compressed folder
                            new File(newFile.getParent()).mkdirs();

                            FileOutputStream fos = new FileOutputStream(newFile);

                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }

                            fos.close();
                            ze = zis.getNextEntry();
                        }

                        zis.closeEntry();
                        zis.close();
                        LOG.info("-----SUCCESSFULLY UNZIP-----::" + fileNameToBeUpload);
                        String newGisPath = gisPath + "B4" + File.separator + "05" + File.separator + mohonStrata.getNamaSkim();
                        final File file = new File(newGisPath);
                        final File parent_directory = file.getParentFile();
                        LOG.info("-----file-----::" + file);
                        if (null != parent_directory) {
                            file.setReadable(true);
                            file.setWritable(true);
                            file.mkdirs();
                            LOG.info("-----SUCCESS DIRECTORY-----");
                        }
                        File file2 = new File(path + fileNameToBeUpload);

                        if (file2.delete()) {
                            System.out.println("-----" + file2.getName() + " is deleted!-----");
                        } else {
                            LOG.info("-----Delete operation is failed.------");
                        }
                        copyZipUpdatePath(path2, path3, ".tif", newGisPath, permohonan.getIdPermohonan());
                        LOG.info("-----DONE-----");


                    }

                } catch (Exception e) {
                    LOG.error(e);
                }

            }
        } catch (Exception e2) {
            LOG.error(e2);
        }
    }

    public void copyZipUpdatePath(String path, String newPath, String ext, String gisPath, String idPermohonan) {
        LOG.info(" -------- 10 ----------");
        PermohonanStrata mohonStrata = strService.findIDNamaSkim(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        Dokumen d = new Dokumen();
        if (stageId.equalsIgnoreCase("g_sedialaporan")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PLK");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
            }
        } else if (stageId.equalsIgnoreCase("g_keputusan2")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PAB");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
            }
        }
        try {

            byte[] buf = new byte[1024];
            FileOutputStream fos = new FileOutputStream(path + File.separator + mohonStrata.getNamaSkim() + ".zip");
            ZipOutputStream out = new ZipOutputStream(fos);
//
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            if (folder.isDirectory() == false) {
                LOG.info("Directory does not exists : " + path);
                return;
            }
            String fileName = "";
            String folderName = "";
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    LOG.info("----File----:: " + listOfFiles[i].getName());
                    fileName = listOfFiles[i].getName();
                } else if (listOfFiles[i].isDirectory()) {
                    LOG.info("----Directory----::" + listOfFiles[i].getName());
                    folderName = listOfFiles[i].getName();
                }
                String extension = FilenameUtils.getExtension(fileName);
                if (extension.equalsIgnoreCase("xml") || extension.equalsIgnoreCase("tif")) {
                    ZipEntry ze = new ZipEntry(fileName);
                    out.putNextEntry(ze);

                    FileInputStream in = new FileInputStream(path + File.separator + fileName);

                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();

                }
            }
            out.closeEntry();
            out.close();
            LOG.info("-----DONE COMPRESS TO-----" + path + File.separator + mohonStrata.getNamaSkim() + ".zip");
            LOG.info("-----UPDATE TABLE DOKUMEN KOD: PAB-----");
            InfoAudit ia = new InfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new Date());
            Dokumen dok = dokDAO.findById(d.getIdDokumen());
            if (stageId.equalsIgnoreCase("g_sedialaporan")) {
                dok.setCatatanMinit("1");
            } else if (stageId.equalsIgnoreCase("g_keputusan2")) {
                dok.setCatatanMinit("2");
            }
            dok.setFormat("application/x-zip-compressed");
            dok.setNamaFizikal(newPath + mohonStrata.getNamaSkim() + ".zip");
            dok.setInfoAudit(ia);
            dokServ.saveOrUpdate(dok);
            LOG.info("-----UPDATE SUCCESS-----");
//            extractSuccess = true;
            isDokumenExist = true;
//            updateSuccess = true;
            if (stageId.equals("g_sedialaporan")) {
                KodDokumen kd = dokServ.findByKodDokumen("PLK");
                addSimpleMessage("Fail Telah Berjaya Dimuat Turun. Sila semak " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'");
            } else if (stageId.equals("g_keputusan2")) {
                KodDokumen kd = dokServ.findByKodDokumen("PAB");
                addSimpleMessage("Fail Telah Berjaya Dimuat Turun. Sila semak " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'");
            }
        } catch (IOException e) {
            e.printStackTrace();
//            extractSuccess = true;
            isDokumenExist = true;
//            updateSuccess = false;
            addSimpleError("Gagal Memuat Turun Fail");
        }
    }

    public Resolution showkommms() {
        LOG.info(" -------- 11 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        dokumen = new ArrayList();
        listDok = new ArrayList();
        dokUrusan = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
        }
        for (String kod : listDokUrusan) {
            Dokumen dokumenToBeZip = strService.findDok(idPermohonan, kod);
            // Dapatkan data utk kod dokumen terlibat
            if (dokumenToBeZip != null) {
                String checkDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                String dokKod = getContext().getRequest().getParameter(dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("----dokKod----" + dokKod);
                if (dokKod != null) {
                    Dokumen dok = dokDAO.findById(Long.parseLong(dokKod));
                    LOG.info("----dok----" + dok);
                    LOG.info("-----dokumenToBeZip.getNamaFizikal-----" + dok.getNamaFizikal());
//                if (dokumenToBeZip.getNamaFizikal() != null) {
                    if (kod.equals("JPP")) {
                        //Add to StrataXML directory
                        LOG.info("----dokumen----::" + dok.getIdDokumen());
                        strataXML = true;
                        dokumen.add(dok);
                        // masukkan path baru dalam zip file. get name file, cth : 281.xml
                    } else {
                        //Add to Attachment directory
                        LOG.info("----dokumen----::" + dok.getIdDokumen());
                        attachments = true;
                        dokumen.add(dok);
                    }
                }

//                }
                LOG.info("-------hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                dokumenToBeZip.setCatatanMinit(checkDok);
                listDok.add(dokumenToBeZip);
            }
        }
        if (hantarfile.equalsIgnoreCase("1")) {
            hiddenHantar = "1";
            hantar = "1";
        } else if (hantarfile.equalsIgnoreCase("2")) {
            hiddenHantar = "2";
            hantar = "2";
        }
        showKomms = true;
        checked = true;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution hantarKeKomms() throws IOException, ParserConfigurationException, SAXException {
        LOG.info(" -------- 12 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String folderPath = "";
        String folderZipToBeUpload = "";
        String fileNameToBeUpload = "";
        String docPath = conf.getProperty("document.path");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
            folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            folderPath = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
            folderZipToBeUpload = docPath + "04" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
        }
        LOG.info("----folderPath----::" + folderPath);
        LOG.info("----folderZipToBeUpload----::" + folderZipToBeUpload);
        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
            if (d != null) {
                //Baca file XML JPP
                Document doc = null;
                File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
                LOG.info("-----f-----::" + f);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                LOG.info("-----db-----::" + db);
                doc = db.parse(f);
                LOG.info("-----doc-----::" + doc);
                NodeList n1 = doc.getElementsByTagName("Scheme");
                LOG.info("-----n1-----::" + n1);
                String pengukur_noic = "";
                String negeri = "";
                String daerah = "";
                String mukim = "";
                String seksyen = "";
                String lot_no = "";
                String skim = "";
                LOG.info("-----n1 length------::" + n1.getLength());
                for (int i = 0; i < n1.getLength(); i++) {
                    pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                    negeri = (n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue());
                    daerah = (n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue());
                    mukim = (n1.item(i).getAttributes().getNamedItem("mukim").getNodeValue());
                    seksyen = (n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue());
                    lot_no = (n1.item(i).getAttributes().getNamedItem("lot").getNodeValue());
                    skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                    fileNameToBeUpload = negeri + daerah + mukim + seksyen + lot_no + skim + ".zip";
                    //cth : 05001023044501(S)281.zip
                    LOG.info("-----fileNameToBeUpload-----::" + fileNameToBeUpload);
                }
                FasaPermohonan mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakkemasukan");
                String stage = "";
                if (mohonFasa != null) {
                    stage = "H1";
                }

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_cetakpelan");
                if (mohonFasa != null) {
                    stage = "H2";
                }
                LOG.info("negeri-----::" + negeri);
                LOG.info("daerah-----::" + daerah);
                LOG.info("mukim-----::" + mukim);
                LOG.info("seksyen-----::" + seksyen);
                LOG.info("lot_no-----::" + lot_no);
                LOG.info("permohonan-----::" + permohonan.getIdPermohonan());
                LOG.info("skim-----::" + skim.substring(3));
                LOG.info("Jenis Pelan----::" + stage);
                PelanGIS pelanGisStrata = strService.findPelanGIS(permohonan.getIdPermohonan(), stage);
                if (pelanGisStrata == null) {
                    if (stageId.equals("g_cetakpelan")) {
                        Session session = sessionProvider.get();
                        session.clear();
                    }
                    PelanGIS pelanGis = new PelanGIS();
                    PelanGISPK pelanGisPK = new PelanGISPK();
                    KodNegeri kodNeg = new KodNegeri();
                    KodDaerah kodDaerah = new KodDaerah();
                    kodNeg = kodNegeriDAO.findById(negeri);
                    kodDaerah = kodDaerahDAO.findById(daerah);
                    pelanGisPK.setKodNegeri(kodNeg);
                    pelanGisPK.setKodDaerah(kodDaerah);
                    pelanGisPK.setKodMukim(mukim);
                    pelanGisPK.setKodSeksyen(seksyen);
                    pelanGisPK.setNoLot(lot_no);
                    pelanGisPK.setPermohonan(permohonan);
                    pelanGis.setPelanGISPK(pelanGisPK);
                    pelanGis.setPelanTif(skim.substring(3));
                    pelanGis.setJenisPelan(stage);
                    strService.simpanPelanGIS(pelanGis);
                }

                //create directory
                final File file = new File(folderPath);
                final File parent_directory = file.getParentFile();
                LOG.info("-----file-----::" + file);
                if (null != parent_directory) {
                    file.setReadable(true);
                    file.setWritable(true);
                    file.mkdirs();
                    LOG.info("-----SUCCESS DIRECTORY-----");
                }

                FileOutputStream fileOutputStream = new FileOutputStream(new File(folderZipToBeUpload + fileNameToBeUpload));
                //cth : C:/EGIS/Strata/B4StrataTitle/05001023044501(S)281.zip

                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                // Create new zip file

                byte[] buffer = new byte[100 * 1024];
                //length memory
                listDok = new ArrayList();
                dokumen = new ArrayList();
                dokUrusan = new ArrayList();
                listDokUrusan = new ArrayList();
                dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
                for (int i = 0; i < dokUrusan.size(); i++) {
                    listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
                }
                for (String kod : listDokUrusan) {
                    Dokumen dokumenToBeZip = strService.findDok(permohonan.getIdPermohonan(), kod);
                    // Dapatkan data utk kod dokumen terlibat
                    if (dokumenToBeZip != null) {
//                        if (dokumenToBeZip.getNamaFizikal() != null) {
                        String checkDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                        LOG.info("---checkDok---" + checkDok);
                        String dokKod = getContext().getRequest().getParameter(dokumenToBeZip.getKodDokumen().getKod());
                        LOG.info("----dokKod----" + dokKod);
                        if (dokKod != null) {
                            Dokumen dok = dokDAO.findById(Long.parseLong(dokKod));
                            LOG.info("----dok----" + dok);
                            FileInputStream fileInputStream = new FileInputStream(docPath + dok.getNamaFizikal());
                            // Location document untuk di copy, cth : D:/nfs_app/00/05/11/99/281.xml

                            String filepath = dok.getNamaFizikal(); //Dapatkan nama fizikal, cth : 00/05/11/99/281.xml
                            String filepathnew = filepath.replace(File.separator, ":"); // cth : 00:05:11:99:281.xml
                            String filename[] = filepathnew.split(":");

                            if (kod.equals("JPP")) {
                                //Add to StrataXML directory
                                zipOutputStream.putNextEntry(new ZipEntry("StrataXML" + File.separator + filename[filename.length - 1]));
                                LOG.info("----strataXML----::" + dok.getTajuk());
                                strataXML = true;
                                dokumen.add(dok);
                                // masukkan path baru dalam zip file. get name file, cth : 281.xml
                            } else {
                                //Add to Attachment directory
                                zipOutputStream.putNextEntry(new ZipEntry("Attachment" + File.separator + filename[filename.length - 1]));
                                LOG.info("----attachments----::" + dok.getTajuk());
                                attachments = true;
                                dokumen.add(dok);
                            }

                            int length;

                            while ((length = fileInputStream.read(buffer)) > 0) {
                                zipOutputStream.write(buffer, 0, length);
                            }

                            fileInputStream.close();
                            fileInputStream = null;
                        }
                        LOG.info("-------hidval" + dokumenToBeZip.getKodDokumen().getKod());
                        LOG.info("---checkDok---" + checkDok);
                        dokumenToBeZip.setCatatanMinit(checkDok);
                        listDok.add(dokumenToBeZip);
                    }
//                    }
                }
                LOG.info("-----PASS-----::");
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                zipOutputStream = null;

                //Hafifi 14/06/2013 : File to upload to Kom Server
                LOG.info("-----ENTER FTP-----::");
                String ftpUrl = "10.66.130.67"; //TODO: Need to change to necessary data
                String username = "etadmin"; //TODO: Need to change to necessary data
                String password = "q1w2e3r4-etanah"; //TODO: Need to change to necessary data
                FTPClient client = new FTPClient();
                FileInputStream fis = null;

                try {
                    LOG.info("-----FTP CONNECTION-----::");
                    client.connect(ftpUrl, 21);
                    client.login(username, password);
                    client.enterLocalPassiveMode();
                    client.setFileType(FTP.BINARY_FILE_TYPE);

                    if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
                        client.changeWorkingDirectory("FromPTG" + File.separator + "00" + File.separator + "Strata" + File.separator + "B4StrataTitlePlan");
                        LOG.info(client.printWorkingDirectory());
                    } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
                        client.changeWorkingDirectory("FromPTG" + File.separator + "00" + File.separator + "Strata" + File.separator + "StrataTitlePlan");
                    }
                    fis = new FileInputStream(folderZipToBeUpload + fileNameToBeUpload);
                    LOG.info(fis);
                    client.storeFile(fileNameToBeUpload, fis);
                    client.logout();
                    LOG.info("-----SUCCESSFULLY TRANSFER-----::");
                    addSimpleMessage("Fail telah Dihantar");
                } catch (Exception ex) {
                    addSimpleError("Gagal Menghantar Fail");
                    LOG.error(ex.getMessage());
                } finally {

                    try {
                        if (fis != null) {
                            fis.close();
                        }
                        client.disconnect();
                    } catch (IOException ex) {
                        LOG.error(ex.getMessage());
                    }
                }

            }
        }
        if (hantarfile.equalsIgnoreCase("1")) {
            hiddenHantar = "1";
            hantar = "1";
        } else if (hantarfile.equalsIgnoreCase("2")) {
            hiddenHantar = "2";
            hantar = "2";
        }
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);

        Dokumen d = new Dokumen();

        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PLK");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                dokPLK.setCatatanMinit("1");
                dokPLK.setPerihal("1");
                dokServ.saveOrUpdate(dokPLK);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                dokPLK.setCatatanMinit("1");
                dokPLK.setPerihal("1");
                dokServ.saveOrUpdate(dokPLK);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
            }
            ///
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PAB");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                Dokumen dokPAB = dokDAO.findById(d.getIdDokumen());
                dokPAB.setCatatanMinit("2");
                dokPAB.setPerihal("2");
                dokServ.saveOrUpdate(dokPAB);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                Dokumen dokPAB = dokDAO.findById(d.getIdDokumen());
                dokPAB.setCatatanMinit("2");
                dokPAB.setPerihal("2");
                dokServ.saveOrUpdate(dokPAB);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
            }

        }
        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semakkemasukan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                } else if (stageId.equals("g_cetakpelan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("2")) {
                        LOG.info("selesaiBtn-----::True");
                        selesaiBtn = true;
                    } else {
                        LOG.info("selesaiBtn-----::False");
                        selesaiBtn = false;
                    }
                }
            }
        }
        Dokumen dlk = strService.findDok(permohonan.getIdPermohonan(), "JPP");
        dlk.setCatatanMinit("2");
        dokServ.saveOrUpdate(dlk);
        checked = true;
        showKomms = true;
        enabledHantar = true;
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution updateVal() {
        LOG.info(" -------- 13 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        Dokumen d = new Dokumen();
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PLK");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                dokPLK.setCatatanMinit("1");
                dokServ.saveOrUpdate(dokPLK);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                dokPLK.setCatatanMinit("1");
                dokServ.saveOrUpdate(dokPLK);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
            }
            ///
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            Dokumen dd = new Dokumen();
            dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
            if (dd == null) {
                Dokumen dk = new Dokumen();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                KodDokumen kd = kodDokumenDAO.findById("PAB");
                dk.setKodDokumen(kd);
                dk.setTajuk(kd.getNama());
                dk.setInfoAudit(infoAudit);
                dk.setNoVersi("1.0");
                dk.setKlasifikasi(klasifikasiAm);
                dokServ.save(dk);
                KandunganFolder kf = new KandunganFolder();
                kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                kf.setDokumen(dk);
                kf.setInfoAudit(infoAudit);
                kfServ.save(kf);
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                Dokumen dokPAB = dokDAO.findById(d.getIdDokumen());
                dokPAB.setCatatanMinit("2");
                dokServ.saveOrUpdate(dokPAB);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
            } else {
                d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                Dokumen dokPAB = dokDAO.findById(d.getIdDokumen());
                dokPAB.setCatatanMinit("2");
                dokServ.saveOrUpdate(dokPAB);
                gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
            }

        }
        listDok = new ArrayList();
        dokUrusan = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
        }
        for (String kod : listDokUrusan) {
            Dokumen dokumenToBeZip = strService.findDok(idPermohonan, kod);
            if (dokumenToBeZip != null) {
                String checkDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("-------hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                dokumenToBeZip.setCatatanMinit(checkDok);
                listDok.add(dokumenToBeZip);
            }
        }
        LOG.info("hantarfile----:" + hantarfile);
        if (hantarfile.equalsIgnoreCase("1")) {
            hiddenHantar = "1";
            hantar = "1";
        } else if (hantarfile.equalsIgnoreCase("2")) {
            hiddenHantar = "2";
            hantar = "2";
        }
        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semakkemasukan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        selesaiBtn = true;
                    } else {
                        selesaiBtn = false;
                    }
                } else if (stageId.equals("g_cetakpelan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("2")) {
                        selesaiBtn = true;
                    } else {
                        selesaiBtn = false;
                    }
                }
            }
        }
        Dokumen dlk = strService.findDok(permohonan.getIdPermohonan(), "JPP");
        dlk.setCatatanMinit("1");
        dokServ.saveOrUpdate(dlk);
        dwnldDisabled = false;
        checked = true;
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution tutupHantar() {
        LOG.info(" -------- 15 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listDok = new ArrayList();
        dokUrusan = new ArrayList();
        listDokUrusan = new ArrayList();
        dokUrusan = strService.getListDokByUrusan(permohonan.getKodUrusan().getKod());
        for (int i = 0; i < dokUrusan.size(); i++) {
            listDokUrusan.add(dokUrusan.get(i).getKodDokumen().getKod());
        }
        for (String kod : listDokUrusan) {
            Dokumen dokumenToBeZip = strService.findDok(idPermohonan, kod);
            // Dapatkan data utk kod dokumen terlibat
            if (dokumenToBeZip != null) {
                for (int a = 0; a < kodDOC2.length; a++) {
                    String kodDok = kodDOC2[a];
                    if (dokumenToBeZip.getKodDokumen().getKod().equals(kodDok)) {
                        dokumenToBeZip.setNoRujukan("2");
                    }
                }
                String checkDok = getContext().getRequest().getParameter("hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                LOG.info("-------hidval" + dokumenToBeZip.getKodDokumen().getKod());
                LOG.info("---checkDok---" + checkDok);
                dokumenToBeZip.setCatatanMinit(checkDok);
                listDok.add(dokumenToBeZip);
            }
        }
        if (hantarfile.equalsIgnoreCase("1")) {
            hiddenHantar = "1";
//            hantar = "1";
        } else if (hantarfile.equalsIgnoreCase("2")) {
            hiddenHantar = "2";
//            hantar = "2";
        }
//        checkDocExist(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semakkemasukan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_cetakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turunMlk.jsp").addParameter("tab", "true");
    }

    public Resolution ToJupem() throws ParserConfigurationException, SAXException, IOException {
        LOG.info(" -------- 16 ----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        //check sama ada JUPEM telah hantar dokumen

        List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);

        String docPath = conf.getProperty("document.path");
        String fileNameToBeUpload = "";

        if (!mhnBngn.isEmpty()) {
            if (mhnBngn.get(0).getNamaLain() == null) {
                if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {

                    Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                    kaedah = d.getCatatanMinit();
                    LOG.info("d+++"+d);
                    if (kaedah != null) {
                        if (kaedah.equals("1")) {
                            if (stageId.equals("g_sedialaporan")) {
//                    Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "PLK");
//                    if (d.getNamaFizikal() == null) {
                                KodDokumen kd = dokServ.findByKodDokumen("PLK");
                                arahan = "Arahan: Fail dari JUPEM telah diterima. Sila muat naik dokumen " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'";
//                    } else {
//                        arahan =
//                    }
                            } else if (stageId.equals("g_keputusan2")) {
//                    Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "PAB");
//                    if (d.getNamaFizikal() == null) {
                                KodDokumen kd = dokServ.findByKodDokumen("PAB");
                                arahan = "Arahan: Fail dari JUPEM telah diterima. Sila muat naik dokumen " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'";
//                    } else {
//                    }
                            }
                        }

                    if (d != null) {
                        //Baca file XML JPP
                        Document doc = null;
                        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
                        LOG.info("-----f-----" + f);
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        LOG.info("-----dbf-----" + dbf);
                        DocumentBuilder db = null;
                        db = dbf.newDocumentBuilder();
                        LOG.info("-----db-----" + db);
                        doc = db.parse(f);
                        LOG.info("-----doc-----" + doc);
                        NodeList n1 = doc.getElementsByTagName("Scheme");
                        LOG.info("-----n1-----" + n1);
                        String pengukur_noic, negeri, daerah, mukim, seksyen, lot_no, skim;

                        for (int i = 0; i < n1.getLength(); i++) {
                            pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                            negeri = (n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue());
                            daerah = (n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue());
                            mukim = (n1.item(i).getAttributes().getNamedItem("mukim").getNodeValue());
                            seksyen = (n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue());
                            lot_no = (n1.item(i).getAttributes().getNamedItem("lot").getNodeValue());
                            skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());

                            fileNameToBeUpload = negeri + daerah + mukim + seksyen + lot_no + skim;
                            LOG.info("-----fileNameToBeUpload-----" + fileNameToBeUpload);
                            //cth : 05001023044501(S)281.zip
                        }
                    }
                    }
                }
                // pelanGIS = new PelanGIS() ;
                String stage = "";
                FasaPermohonan mohonFasa = new FasaPermohonan();

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakkemasukan");
                if (mohonFasa != null) {
                    stage = "H1";
                }

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_sedialaporan");
                if (mohonFasa != null) {
                    stage = "T1";
                }

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_cetakpelan");
                if (mohonFasa != null) {
                    stage = "H2";
                }

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_keputusan2");
                if (mohonFasa != null) {
                    stage = "T2";
                }

                PelanGIS p;
                PermohonanStrata mohonStrata = strService.findIDNamaSkim(permohonan.getIdPermohonan());
                LOG.info("-----mohonStrata.getNamaSkim()-----" + mohonStrata.getNamaSkim());
                LOG.info("-----stage-----" + stage);
                List<PelanGIS> lp = strService.findListPelanGISPKByNoSkim(mohonStrata.getNamaSkim(), stage);
                //Perlu tanya gis apa untuk refer
                if (!lp.isEmpty()) {
                    isDokumenExist = true;
                    LOG.info("-----isDokumenExist-----" + isDokumenExist);
                }
                LOG.info("Dokumen exist KOMMS: " + mohonStrata.getNamaSkim());
                LOG.info("Dokumen exist KOMMS: " + isDokumenExist);
                Dokumen d = new Dokumen();
                Dokumen dok = new Dokumen();
                if (stageId.equalsIgnoreCase("g_sedialaporan")) {
                    Dokumen dd = new Dokumen();
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd == null) {
                        Dokumen dk = new Dokumen();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                        KodDokumen kd = kodDokumenDAO.findById("PLK");
                        dk.setKodDokumen(kd);
                        dk.setTajuk(kd.getNama());
                        dk.setInfoAudit(infoAudit);
                        dk.setNoVersi("1.0");
                        dk.setKlasifikasi(klasifikasiAm);
                        dokServ.save(dk);
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                        kf.setDokumen(dk);
                        kf.setInfoAudit(infoAudit);
                        kfServ.save(kf);
                        d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                        gisStage = "Terima Fail Dari JUPEM (Peringkat 1)";
                    } else {
                        d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                        gisStage = "Terima Fail Dari JUPEM (Peringkat 1)";
                    }
                } else if (stageId.equalsIgnoreCase("g_keputusan2")) {
                    Dokumen dd = new Dokumen();
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd == null) {
                        Dokumen dk = new Dokumen();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                        KodDokumen kd = kodDokumenDAO.findById("PAB");
                        dk.setKodDokumen(kd);
                        dk.setTajuk(kd.getNama());
                        dk.setInfoAudit(infoAudit);
                        dk.setNoVersi("1.0");
                        dk.setKlasifikasi(klasifikasiAm);
                        dokServ.save(dk);
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(folderDokDAO.findById(permohonan.getFolderDokumen().getFolderId()));
                        kf.setDokumen(dk);
                        kf.setInfoAudit(infoAudit);
                        kfServ.save(kf);
                        d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                        gisStage = "Terima Fail Dari JUPEM (Peringkat 2)";
                    } else {
                        d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                        gisStage = "Terima Fail Dari JUPEM (Peringkat 2)";
                    }
                }
                if (dok != null) {
                    isDokumenExist2 = true;
                    LOG.info("Dokumen exist JUPEM2U: " + dok.getNamaFizikal());
                }

                LOG.info("Dokumen exist JUPEM2U: " + isDokumenExist2);
            } else {
                getContext().getRequest().setAttribute("manual", Boolean.TRUE);
            }
        }
        return new JSP("strata/pbbm/gisPabStrataMlk.jsp").addParameter("tab", "true");
    }

    public String getPathJPP() throws ParserConfigurationException {
        //String path = "";
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        if (d != null) {
            String docPath = conf.getProperty("document.path");
            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            path = f.getAbsolutePath() + ".xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
        }
        return path;
    }

    /*
     *  papar pelan lokasi GIS
     */
    public Resolution showPelanLokasi() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService serviceBpel = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.debug("STAGE ID : " + stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("P14A") || permohonan.getKodUrusan().getKod().equals("P22B") || permohonan.getKodUrusan().getKod().equals("PNB")) {
            if (permohonan.getPermohonanSebelum() != null) {
                idPermohonan1 = permohonan.getPermohonanSebelum().getIdPermohonan();
                stageId1 = "g_semakkemasukan";
            }
        } else {
            integrasiPermohonan1 = strService.findIDMohon(permohonan.getIdPermohonan());
            LOG.debug("integrasiPermohonan1: " + integrasiPermohonan1);
            idPermohonan1 = permohonan.getIdPermohonan();
            stageId1 = "g_sedialaporan";
        }

        //check if user alredy upload xml for PLKTA
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("P8")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("P22A")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTHS")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTPS")) {
            Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLKTA");
            if (d == null) {
                isDokumenDeleted = true;
            } else {
                if (d.getNamaFizikal() == null || d.getNamaFizikal().length() == 0) {
                    isDokumenDeleted = true;
                }
            }
        }
        /*
        if (permohonan != null) {
        LOG.info("--- permohonan not null, kod urusan : " + permohonan.getKodUrusan().getKod() + " ---- ");
        if (permohonan.getKodUrusan().getKod().equals("P8") || permohonan.getKodUrusan().getKod().equals("P40A")) {
        getContext().getRequest().setAttribute("KuatkuasaStrata", Boolean.TRUE);
        } else {
        getContext().getRequest().setAttribute("KuatkuasaStrata", Boolean.FALSE);
        }
        }
        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        getContext().getRequest().setAttribute("pengguna", peng.getIdPengguna());
         */

        return new JSP("strata/common/pelan_lokasi_GIS.jsp").addParameter("tab", "true");
    }

    /*
     *  simpan pelan lokasi GIS dalam Folder_Dok
     */
    public Resolution simpanPelanLokasi() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // save into dokumen and folder_dok for viewing purpose
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(peng);
        /*  PelanGIS pelanGIS = strService.findPelanByIdPermohonan(permohonan.getIdPermohonan());
        if (pelanGIS != null) {
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + pelanGIS.getPelanTif();
        LOG.info("path : " + dokumenPath);
        comm.savePelanLokasi(dokumenPath, permohonan.getFolderDokumen().getFolderId(), ia, permohonan); */

        // Ida update on 13 may 2013


        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLKTA");

        /* Insert in Stage g_semakkemasukan */

        /*saving mohon_integ*/
        LOG.info("---saving in mohon_integ---");
        IntegrasiPermohonan integrasiPermohonan = new IntegrasiPermohonan();
        integrasiPermohonan.setPermohonan(permohonan);
        integrasiPermohonan.setKodUrusan(permohonan.getKodUrusan());
        integrasiPermohonan.setIdAliran("g_sedialaporan"); //dapat dari mana?
        integrasiPermohonan.setIdAliranTerima("semaklaporan"); //dapat dari mana? buat mana ni hardcode dulu
        integrasiPermohonan.setInfoAudit(infoAudit);
        integrasiPermohonan = integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);

        /*saving mohon_integ_butir*/
        LOG.info("---saving in mohon_integ_butir---");
        IntegrasiPermohonanButir integrasiPermohonanButir = new IntegrasiPermohonanButir();
        integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
        integrasiPermohonanButir.setNamaFolderHantar(integrasiPermohonan.getIdAliran());
        integrasiPermohonanButir.setNamaFolderTerima(integrasiPermohonan.getIdAliranTerima());
        integrasiPermohonanButir.setInfoAudit(infoAudit);
        integrasiPermohonanButir = integrasiPermohonanButirDAO.saveOrUpdate(integrasiPermohonanButir);

        /*saving mohon_integ_dokumen*/
        LOG.info("---saving in mohon_integ_dokumen---");
        IntegrasiPermohonanDokumen integrasiPermohonanDokumen = new IntegrasiPermohonanDokumen();
        integrasiPermohonanDokumen.setIntegrasiPermohonanButir(integrasiPermohonanButir);
        integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
        integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
        integrasiPermohonanDokumen.setInfoAudit(infoAudit);
        integrasiPermohonanDokumen = integrasiPermohonanDokumenDAO.saveOrUpdate(integrasiPermohonanDokumen);



        // end insert in table GIS
        addSimpleMessage("Pelan Lokasi Telah Disimpan. Sila Tekan Tab Dokumen Untuk Semak Pelan Lokasi");

        return new JSP("strata/common/pelan_lokasi_GIS.jsp").addParameter("tab", "true");
    }

    public static int getBYTES_DOWNLOAD() {
        return BYTES_DOWNLOAD;
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

    public boolean isShowKomms() {
        return showKomms;
    }

    public void setShowKomms(boolean showKomms) {
        this.showKomms = showKomms;
    }

    public String getHantarfile() {
        return hantarfile;
    }

    public void setHantarfile(String hantarfile) {
        this.hantarfile = hantarfile;
    }

    public String getHantar() {
        return hantar;
    }

    public void setHantar(String hantar) {
        this.hantar = hantar;
    }

    public String getHiddenHantar() {
        return hiddenHantar;
    }

    public void setHiddenHantar(String hiddenHantar) {
        this.hiddenHantar = hiddenHantar;
    }

    public boolean isUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
    }

    public List<Dokumen> getDokumen() {
        return dokumen;
    }

    public void setDokumen(List<Dokumen> dokumen) {
        this.dokumen = dokumen;
    }

    public List<Dokumen> getListDok() {
        return listDok;
    }

    public void setListDok(List<Dokumen> listDok) {
        this.listDok = listDok;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Dokumen getCheckedDok() {
        return checkedDok;
    }

    public void setCheckedDok(Dokumen checkedDok) {
        this.checkedDok = checkedDok;
    }

    public List<UrusanDokumen> getDokUrusan() {
        return dokUrusan;
    }

    public void setDokUrusan(List<UrusanDokumen> dokUrusan) {
        this.dokUrusan = dokUrusan;
    }

    public List<String> getListDokUrusan() {
        return listDokUrusan;
    }

    public void setListDokUrusan(List<String> listDokUrusan) {
        this.listDokUrusan = listDokUrusan;
    }

    public boolean isStrataXML() {
        return strataXML;
    }

    public void setStrataXML(boolean strataXML) {
        this.strataXML = strataXML;
    }

    public boolean isAttachments() {
        return attachments;
    }

    public void setAttachments(boolean attachments) {
        this.attachments = attachments;
    }

    public boolean isDwnldDisabled() {
        return dwnldDisabled;
    }

    public void setDwnldDisabled(boolean dwnldDisabled) {
        this.dwnldDisabled = dwnldDisabled;
    }

    public List<String> getCheckedList() {
        return checkedList;
    }

    public void setCheckedList(List<String> checkedList) {
        this.checkedList = checkedList;
    }

    public String getgisStage() {
        return gisStage;
    }

    public void setgisStage(String gisStage) {
        this.gisStage = gisStage;
    }

    public boolean isMuatTurunBtn() {
        return muatTurunBtn;
    }

    public void setMuatTurunBtn(boolean muatTurunBtn) {
        this.muatTurunBtn = muatTurunBtn;
    }

    /**
     * @return the selesaiBtn
     */
    public boolean isSelesaiBtn() {
        return selesaiBtn;
    }

    /**
     * @param selesaiBtn the selesaiBtn to set
     */
    public void setSelesaiBtn(boolean selesaiBtn) {
        this.selesaiBtn = selesaiBtn;
    }

    public String getKaedah() {
        return kaedah;
    }

    public void setKaedah(String kaedah) {
        this.kaedah = kaedah;
    }

    public String getArahan() {
        return arahan;
    }

    public void setArahan(String arahan) {
        this.arahan = arahan;
    }

    /**
     * @return the enabledHantar
     */
    public boolean isEnabledHantar() {
        return enabledHantar;
    }

    /**
     * @param enabledHantar the enabledHantar to set
     */
    public void setEnabledHantar(boolean enabledHantar) {
        this.enabledHantar = enabledHantar;
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
