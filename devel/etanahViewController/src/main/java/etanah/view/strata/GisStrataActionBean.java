package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BangunanTingkatDAO;
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
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriBangunanDAO;
import etanah.dao.KodKegunaanBangunanDAO;
import etanah.dao.KodKegunaanPetakAksesoriDAO;
import etanah.dao.KodTujuanUkurDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodHartaBersama;
import etanah.model.KodKategoriBangunan;
import etanah.model.KodKegunaanBangunan;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodKegunaanPetakAksesori;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodTujuanUkur;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanHartaBersama;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.model.strata.BadanPengurusan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.common.OutBoundIntegration;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
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
@UrlBinding("/strata/gis")
public class GisStrataActionBean extends AbleActionBean {

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    KodTujuanUkurDAO kodTujuanUkurDAO;
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
    PihakDAO pihakDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(GisStrataActionBean.class);
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
    private int documentExist;
    private PermohonanStrata permohonanstrata;
    private List<HakmilikPermohonan> listHakPermohon;
    private HakmilikPermohonan HakPermohon;
    private List<PermohonanSkim> listMohonSkim;
    private PermohonanSkim mohonSkim;
    private BadanPengurusan badanUrus;
    private static Document doc = null;
    private BadanPengurusan mc;
    private int jumPetak = 0;
    private int hv = 0;
    private BangunanTingkat bangunanTingkat;
    private int sum4 = 0;
    private List<PermohonanBangunan> pBangunanL;
    private List<PermohonanBangunan> pBangunanLandParcel;
    private List<BangunanPetak> petakL;
    private List<PermohonanBangunan> bgnList;
    private List<BangunanTingkat> bgnTgktList;
    private List<BangunanPetak> bgnPetakList;
    private List<BangunanPetakAksesori> bgnPetakAksrList;
    private List<BangunanPetakAksesori> bgnPetakAksrList2;
    private String tempIdBangunan;
    private String tempIdTingkat;
    private String tempIdPetak;

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

    public Resolution selectedPitak() {

        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");
        tempIdPetak = getContext().getRequest().getParameter("idPetak");

        LOG.info("----------selectedPitak---------" + tempIdBangunan);
        LOG.info("----------selectedPitak---------" + tempIdTingkat);
        LOG.info("----------selectedPitak---------" + tempIdPetak);
        try {
            ToJupem();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_PBBD.jsp").addParameter("tab", "true");
    }

    public Resolution selectedTingkat() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");

        LOG.info("----------selectedTingkat---------" + tempIdBangunan);
        LOG.info("----------selectedTingkat---------" + tempIdTingkat);
        try {
            ToJupem();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_PBBD.jsp").addParameter("tab", "true");
    }

    public Resolution selectedBangunan() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        String ekstrak = getContext().getRequest().getParameter("ekstrak");
        LOG.info("----------tempIdBangunan---------" + tempIdBangunan);
        LOG.info("----------ekstrak---------" + ekstrak);
        try {
            ToJupem();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_PBBD.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listHakPermohon = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        this.pguna = peng;

        if (kodNegeri.equals("05")) {
            bgnList = new ArrayList<PermohonanBangunan>();
            bgnTgktList = new ArrayList<BangunanTingkat>();
            bgnPetakList = new ArrayList<BangunanPetak>();
            bgnPetakAksrList = new ArrayList<BangunanPetakAksesori>();
            bgnPetakAksrList2 = new ArrayList<BangunanPetakAksesori>();
            BangunanPetakAksesori namaPetakAksr = new BangunanPetakAksesori();
            BangunanPetak bgPetak1 = new BangunanPetak();

            pBangunanL = strService.findByIDPermohonan(permohonan.getIdPermohonan());
            pBangunanLandParcel = strService.findByIDPermohonanByLandparcel(permohonan.getIdPermohonan());
            for (PermohonanBangunan pBgn : pBangunanLandParcel) {
                List<BangunanTingkat> bgTgktList2 = strService.findByIDBangunanList(pBgn.getIdBangunan());
                for (BangunanTingkat bgTgkt2 : bgTgktList2) {
                    bgnTgktList.add(bgTgkt2);
                }
            }
            for (BangunanTingkat bgTgkt3 : bgnTgktList) {
                List<BangunanPetak> bgPetakL = new ArrayList<BangunanPetak>();
                bgPetakL = strService.findByIDPetak2(bgTgkt3.getIdTingkat());
                for (BangunanPetak bgPetaks : bgPetakL) {
                    bgnPetakList.add(bgPetaks);
                }
            }
            for (BangunanPetak bgPetak : bgnPetakList) {
                bgnPetakAksrList2 = strService.findByIDPetakAksr2(bgPetak.getIdPetak());
                if (bgnPetakAksrList2.size() == 0) {
                    BangunanPetakAksesori bgPetakAksr = new BangunanPetakAksesori();
                    bgPetakAksr.setPetak(bgPetak);
                    bgnPetakAksrList.add(bgPetakAksr);
                }

                for (int a = 0; a < bgnPetakAksrList2.size(); a++) {
                    BangunanPetakAksesori bgPetakAksr = new BangunanPetakAksesori();
                    bgPetakAksr = bgnPetakAksrList2.get(a);
                    bgPetakAksr = strService.findByIDPetakAksr(bgPetakAksr.getPetak().getIdPetak(), bgPetakAksr.getNama());
                    bgnPetakAksrList.add(bgPetakAksr);
                }
            }
        }

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
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            BPelService serviceBpel = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
//            stageId = "g_sediakertasptg";
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semaklaporan")) {
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
                } else if (stageId.equals("g_semakpelan")) {
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

            List<PermohonanBangunan> bgnList = new ArrayList<PermohonanBangunan>();
            bgnList = strService.findByIDMohonBlok2(idPermohonan);
            documentExist = bgnList.size();
            System.out.println("Doc Exist : " + documentExist);
        }

    }

    @DefaultHandler
    public Resolution showForm() throws ParserConfigurationException {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            gisStage = "Senarai Fail untuk di Hantar ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
            gisStage = "Senarai Fail untuk di Hantar ke JUPEM (Peringkat 2)";
        }
        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semaklaporan")) {
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
                } else if (stageId.equals("g_semakpelan")) {
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
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public void checkDocExist(String idMohon) {
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
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
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
            if (stageId.equalsIgnoreCase("g_semaklaporan")) {
                folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
                folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
                folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
                folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            }
            if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
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
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution zipfiles() throws IOException, ParserConfigurationException, SAXException {
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
            if (stageId.equalsIgnoreCase("g_semaklaporan")) {
                folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
                folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
                folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
                folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
            }
            if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution transferFile() {
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

            if ("05".equals(conf.getProperty("kodNegeri"))) {
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
                String ftpUrl = "10.66.128.67"; //TODO: Need to change to necessary data
                String username = "etanahN9"; //TODO: Need to change to necessary data
                String password = "etanah123"; //TODO: Need to change to necessary data
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
                    if (stageId.equalsIgnoreCase("g_sediakertasptg")) {
                        client.changeWorkingDirectory("ToPTG" + File.separator + "99" + File.separator + "Strata" + File.separator + "B4StrataTitlePlan");  //TODO: Need to change to necessary data
                    } else if (stageId.equalsIgnoreCase("g_terimapab")) {
                        client.changeWorkingDirectory("ToPTG" + File.separator + "99" + File.separator + "Strata" + File.separator + "StrataTitlePlan");  //TODO: Need to change to necessary data
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
        if (stageId.equalsIgnoreCase("g_sediakertasptg")) {
            gisStage = "Terima Fail Dari JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_terimapab")) {
            gisStage = "Terima Fail Dari JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisPabStrata.jsp").addParameter("tab", "true");
    }

    /**
     * ** 27/06/2013: SYAFIQ AZ: Extract Zip File ***
     */
    public void extractZip() throws FileNotFoundException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        try {

            if ("05".equals(conf.getProperty("kodNegeri"))) {
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
        PermohonanStrata mohonStrata = strService.findIDNamaSkim(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, peng);
        stageId = t.getSystemAttributes().getStage();
        LOG.info("stageId>>>>>>" + stageId);
        Dokumen d = new Dokumen();
        if (stageId.equalsIgnoreCase("g_sediakertasptg")) {
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
        } else if (stageId.equalsIgnoreCase("g_terimapab")) {
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
            if (stageId.equalsIgnoreCase("g_sediakertasptg")) {
                dok.setCatatanMinit("1");
            } else if (stageId.equalsIgnoreCase("g_terimapab")) {
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
            if (stageId.equals("g_sediakertasptg")) {
                KodDokumen kd = dokServ.findByKodDokumen("PLK");
                addSimpleMessage("Fail Telah Berjaya Dimuat Turun. Sila semak " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'");
            } else if (stageId.equals("g_terimapab")) {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution hantarKeKomms() throws IOException, ParserConfigurationException, SAXException {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan";
            folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "B4StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
            folderPath = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan";
            folderZipToBeUpload = docPath + "05" + File.separator + "EGIS" + File.separator + "StrataTitlePlan" + File.separator; //TODO: Need to change to necessary data
        }
        LOG.info("----folderPath----::" + folderPath);
        LOG.info("----folderZipToBeUpload----::" + folderZipToBeUpload);
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
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
                FasaPermohonan mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semaklaporan");
                String stage = "";
                if (mohonFasa != null) {
                    stage = "H1";
                }

                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakpelan");
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
                    if (stageId.equals("g_semakpelan")) {
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
                String ftpUrl = "10.66.128.67"; //TODO: Need to change to necessary data
                String username = "etanahN9"; //TODO: Need to change to necessary data
                String password = "etanah123"; //TODO: Need to change to necessary data
                FTPClient client = new FTPClient();
                FileInputStream fis = null;

                try {
                    LOG.info("-----FTP CONNECTION-----::");
                    client.connect(ftpUrl, 21);
                    client.login(username, password);
                    client.enterLocalPassiveMode();
                    client.setFileType(FTP.BINARY_FILE_TYPE);
                    if (stageId.equalsIgnoreCase("g_semaklaporan")) {
                        client.changeWorkingDirectory(File.separator + "FromPTG" + File.separator + "99" + File.separator + "Strata" + File.separator + "B4StrataTitlePlan");  //TODO: Need to change to necessary data
                    } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
                        client.changeWorkingDirectory(File.separator + "FromPTG" + File.separator + "99" + File.separator + "Strata" + File.separator + "StrataTitlePlan");
                    }
                    fis = new FileInputStream(folderZipToBeUpload + fileNameToBeUpload);
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

        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
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
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
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
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semaklaporan")) {
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
                } else if (stageId.equals("g_semakpelan")) {
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
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution updateVal() {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
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
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
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
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semaklaporan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        selesaiBtn = true;
                    } else {
                        selesaiBtn = false;
                    }
                } else if (stageId.equals("g_semakpelan")) {
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
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution tutupHantar() {
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
        if (stageId.equalsIgnoreCase("g_semaklaporan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 1)";
        } else if (stageId.equalsIgnoreCase("g_semakpelan")) {
            gisStage = "Hantar Fail Ke JUPEM (Peringkat 2)";
        }
        return new JSP("strata/pbbm/gisStrata_muat_turun.jsp").addParameter("tab", "true");
    }

    public Resolution ToJupem() throws ParserConfigurationException, SAXException, IOException {
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
//        stageId = "g_sediakertasptg";
        LOG.info("stageId>>>>>>" + stageId);
        //check sama ada JUPEM telah hantar dokumen 

        String docPath = conf.getProperty("document.path");
        String fileNameToBeUpload = "";

        // class to compare the name of Petak & sort accordingly
        Comparator c = new Comparator<BangunanPetak>() {

            @Override
            public int compare(BangunanPetak o1, BangunanPetak o2) {
//                String[] namaPetak1Pecahan = o1.getNama().split(" ");
//                String[] namaPetak2Pecahan = o2.getNama().split(" ");
                return Integer.parseInt(o1.getNama())
                        - Integer.parseInt(o1.getNama());
            }
        };

        Comparator c2 = new Comparator<BangunanTingkat>() {

            @Override
            public int compare(BangunanTingkat u1, BangunanTingkat u2) {
//                String[] namaTgkt1Pecahan = u1.getNama().split(" ");
//                String[] namaTgkt2Pecahan = u2.getNama().split(" ");
//                return Integer.parseInt(namaTgkt1Pecahan[1])
//                        - Integer.parseInt(namaTgkt2Pecahan[1]);

                return (u1.getTingkat() - u2.getTingkat());
            }
        };

        Comparator c3 = new Comparator<BangunanPetakAksesori>() {

            @Override
            public int compare(BangunanPetakAksesori p1, BangunanPetakAksesori p2) {
                String namaAksr1Pecahan = p1.getNama();
                String namaAksr2Pecahan = p2.getNama();
                return Integer.parseInt(namaAksr1Pecahan)
                        - Integer.parseInt(namaAksr2Pecahan);
            }
        };

        Comparator c4 = new Comparator<PermohonanBangunan>() {

            @Override
            public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {

//                String namaBngn1Pecahan = b1.getNama();
//                String namaBngn2Pecahan = b2.getNama();
//                return Integer.parseInt(namaBngn1Pecahan)
//                        - Integer.parseInt(namaBngn2Pecahan);

                String namaBngn1Pecahan = b1.getNama().length() > 1 ? b1.getNama().substring(1) : "0";
                LOG.info("---namaBngn1Pecahan---" + namaBngn1Pecahan);
                String namaBngn2Pecahan = b2.getNama().length() > 1 ? b2.getNama().substring(1) : "0";
                LOG.info("---namaBngn2Pecahan---" + namaBngn2Pecahan);
                return Integer.parseInt(namaBngn1Pecahan)
                        - Integer.parseInt(namaBngn2Pecahan);
            }
        };

        if (pBangunanL != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            LOG.info("---permohonan---" + permohonan.getIdPermohonan());
//            if (permohonan.getPermohonanSebelum() != null) {
//                permohonan = permohonan.getPermohonanSebelum();
//            }
            Collections.sort(pBangunanL, c4);
            for (PermohonanBangunan p : pBangunanL) {
                permohonan.setSenaraiBangunan(pBangunanL);
                if (p.getSenaraiTingkat() != null) {
                    List<BangunanTingkat> listTingkat = p.getSenaraiTingkat();
                    Collections.sort(listTingkat, c2);
                    p.setSenaraiTingkat(listTingkat);
                    for (BangunanTingkat tgkt : p.getSenaraiTingkat()) {
                        List<BangunanPetak> listPetak = strService.getSenaraiPetak(tgkt.getIdTingkat());
                        Collections.sort(listPetak, c);
                        tgkt.setSenaraiPetak(listPetak);
                        for (BangunanPetak ptk1 : tgkt.getSenaraiPetak()) {
                            List<BangunanPetakAksesori> listPetakaksr = new ArrayList();
//                            listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            listPetakaksr = strService.findPtkAksrByIDPetak(ptk1.getIdPetak());
                            LOG.info("--listPetakaksr--" + listPetakaksr);
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

        if (pBangunanLandParcel != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            LOG.info("---permohonan---" + permohonan.getIdPermohonan());
//            if (permohonan.getPermohonanSebelum() != null) {
//                permohonan = permohonan.getPermohonanSebelum();
//            }
            Collections.sort(pBangunanLandParcel, c4);
            for (PermohonanBangunan p : pBangunanLandParcel) {
                permohonan.setSenaraiBangunan(pBangunanLandParcel);
                if (p.getSenaraiTingkat() != null) {
                    List<BangunanTingkat> listTingkat = p.getSenaraiTingkat();
                    Collections.sort(listTingkat, c2);
                    p.setSenaraiTingkat(listTingkat);
                    for (BangunanTingkat tgkt : p.getSenaraiTingkat()) {
                        List<BangunanPetak> listPetak = strService.getSenaraiPetak(tgkt.getIdTingkat());
                        Collections.sort(listPetak, c);
                        tgkt.setSenaraiPetak(listPetak);
                        for (BangunanPetak ptk1 : tgkt.getSenaraiPetak()) {
                            List<BangunanPetakAksesori> listPetakaksr = new ArrayList();
//                            listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            listPetakaksr = strService.findPtkAksrByIDPetak(ptk1.getIdPetak());
                            LOG.info("--listPetakaksr--" + listPetakaksr);
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {

            Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
            kaedah = d.getCatatanMinit();
            if (kaedah != null) {
                if (kaedah.equals("1")) {
                    if (stageId.equals("g_sediakertasptg")) {
//                    Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "PLK");
//                    if (d.getNamaFizikal() == null) {
                        KodDokumen kd = dokServ.findByKodDokumen("PLK");
                        arahan = "Arahan: Fail dari JUPEM telah diterima. Sila muat naik dokumen " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'";
//                    } else {
//                        arahan = 
//                    }
                    } else if (stageId.equals("g_terimapab")) {
//                    Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "PAB");
//                    if (d.getNamaFizikal() == null) {
                        KodDokumen kd = dokServ.findByKodDokumen("PAB");
                        arahan = "Arahan: Fail dari JUPEM telah diterima. Sila muat naik dokumen " + kd.getKod() + "(" + kd.getNama() + ") di tab 'Dokumen'";
//                    } else {
//                    }
                    }
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
        // pelanGIS = new PelanGIS() ;
        String stage = "";
        FasaPermohonan mohonFasa = new FasaPermohonan();

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semaklaporan");
        if (mohonFasa != null) {
            stage = "H1";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_sediakertasptg");
        if (mohonFasa != null) {
            stage = "T1";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakpelan");
        if (mohonFasa != null) {
            stage = "H2";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_terimapab");
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
        if (stageId.equalsIgnoreCase("g_sediakertasptg")) {
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
        } else if (stageId.equalsIgnoreCase("g_terimapab")) {
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

        return new JSP("strata/pbbm/gisPabStrata.jsp").addParameter("tab", "true");
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
                stageId1 = "g_semaklaporan";
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

        /* Insert in Stage g_semaklaporan */

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

    public Resolution HapusXml() {
        LOG.debug("---------Hapus Xml Started--------::");
        String msg = "";
        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        String docPath = conf.getProperty("document.path");
        /*
        if (d != null) {
        LOG.debug("---------Xml Document deleting from table--------::");
        //            strService.deleteAll2(d);
        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
        d.setNamaFizikal("");
        strService.saveDokumen(d);
        LOG.debug("---------Xml physical path deleting--------::");
        f.delete();
        }*/

        LOG.debug("---------Data deleting from Mohon_bngn table--------::");
//        List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
        List<PermohonanBangunan> pb1 = strService.findByIDPermohonan1(idPermohonan);
        LOG.debug("---------PermohonanBangunan--------::" + pb1);

        for (PermohonanBangunan pb2 : pb1) {

            List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
            LOG.info("BANGUNAN:: " + pb2.getNama());

            for (BangunanTingkat bt2 : bt1) {
                LOG.info("--------Tingkat deleting::");
                List<BangunanPetak> p1 = bt2.getSenaraiPetak();

                LOG.info("Tingkat:: " + bt2.getIdTingkat());

                for (BangunanPetak p2 : p1) {

//                    List<BangunanPetakAksesori> pk1 = p2.getSenaraiPetakAksesori();
                    List<BangunanPetakAksesori> pk1 = strService.findPtkAksrByIDPetak(p2.getIdPetak());

                    for (BangunanPetakAksesori pk2 : pk1) {
                        LOG.info("--------petak aksesori deleting---------::");
                        strService.deleteAksesori(pk2);
                    }
                    LOG.info("--------petak deleting--------::");
                    strService.deletePetak(p2);
                }
                LOG.info("--------Tingkat deleting::");
                strService.deleteTgkt(bt2);
            }
            LOG.info("--------Permohonan Bangunan deleting::");
            LOG.info("BANGUNAN:: " + pb2.getNama());
            strService.deleteBngn(pb2);
        }

        /*
        LOG.debug("--Data deleting from Mohon_strata table--::");
        permohonanstrata = strService.findID(idPermohonan);
        LOG.debug("--permohonanstrata--::" + permohonanstrata);
        strService.deletemohonStrataByIDMohon(idPermohonan);
        LOG.debug("--permohonanstrata deleted--::");
         */
        /*
        LOG.debug("--Data deleting from Mohon_skim table--::");
        listHakPermohon = strService.getMaklumatTan(idPermohonan);
        LOG.debug("--listHakPermohon--::" + listHakPermohon);
        if (listHakPermohon.size() > 0) {
        HakPermohon = listHakPermohon.get(0);
        LOG.debug("--HakPermohon--::" + HakPermohon);
        }
        listMohonSkim = strService.findPermohonanSkimByIdMh(Long.valueOf(HakPermohon.getId()));
        LOG.debug("--mohonSkim list- based on date-::" + listMohonSkim);
        if (listMohonSkim.size() > 0) {
        mohonSkim = listMohonSkim.get(0);
        //added
        Long mhb = mohonSkim.getIdSkim();
        LOG.debug("--getIdSkim--::" + mhb);
        LOG.debug("--mohonSkim--::" + mohonSkim.getIdSkim());
        LOG.debug("--Data deleting from Mohon_Harta_Bersama--::");
        if (mhb != null) {
        strService.deleteHartaBersamaByIdSkim(mhb);
        }
        strService.deletemohonskim(mohonSkim.getIdSkim());
        LOG.debug("--mohonSkim Deleted--::");
        }
         */

        /*
        LOG.debug("--Data deleting from BDN URS n Pihak--::");
        badanUrus = strService.findBdn(idPermohonan);
        LOG.debug("--BDN URS--::" + badanUrus);
        Long pk = badanUrus.getPihak().getIdPihak();
        LOG.debug("--pihak--::" + pk);
        strService.deleteBadanUrus(badanUrus);
        LOG.debug("--BDN URS n pihak Deleted--::");
        
        LOG.debug("--pihak-from BDN URS--::" + pk);
        Pihak pk1 = strService.findByIdPihak(pk);
        LOG.debug("--pihak--from--findBy Id--::" + pk1);
        strService.deletePihak(pk1.getIdPihak());
        LOG.debug("--pihak Deleted--::");
         */

        msg = "Maklumat telah berjaya disimpan.";

        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("message", msg);
    }

    public Resolution extrakBangunan() throws ParserConfigurationException, IOException, SAXException, ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";

        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500, "Konfigurasi \"document.path\" tidak dijumpai");
        }

        if (d == null) {
            error = "Dokumen tidak Dijumpai. Sila Muat Naik Jadual Petak.";
            return new RedirectResolution(MaintainBangunanActionBeanPBBD.class, "showForm1").addParameter("error", error).addParameter("message", msg);
        } else {

            // List<PermohonanBangunan> pb = permohonan.getSenaraiBangunan();
            List<PermohonanBangunan> pb = strService.findByIDPermohonan(permohonan.getIdPermohonan());
            for (PermohonanBangunan bngn : pb) {
                if (bngn != null) {
                    strService.deleteBngn(bngn);
                }
            }


            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            LOG.info(f);
            LOG.info(d.getNamaFizikal());
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(f);

                NodeList n1 = doc.getElementsByTagName("Scheme");
                NodeList n2 = doc.getElementsByTagName("Block");
                NodeList n3 = doc.getElementsByTagName("Tingkat");
                NodeList n4 = doc.getElementsByTagName("Petak");
                NodeList n5 = doc.getElementsByTagName("Ruang");
                NodeList n6 = doc.getElementsByTagName("Aksesori");
                NodeList n7 = doc.getElementsByTagName("CommonArea");
                NodeList n8 = doc.getElementsByTagName("LandParcel");
                NodeList n9 = doc.getElementsByTagName("ProvisionalBlock");

                List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
                //if (validateXML(n1)) {
                //}
                String diukur_oleh,
                        pengukur_noic,
                        ulasan_jupem,
                        nama_pemaju,
                        nama_perbadanan_pengurusan,
                        alamat_perbadanan_pengurusan,
                        nama_projek,
                        no_ruj_jubl,
                        no_ruj_ljt,
                        no_fail_ukursemula,
                        kodtujuanukur, no_fail_ukur,
                        skim;
                int bilangan_petak;
                int bilangan_aksesori;
                Date tarikh_terima_sijil_akuan;
                Date tarikh_lulus_permohonan;
                Date tarikh_siap;
                BigDecimal bayaran_ukur;
                BigDecimal bayaran_pelan;

//                BigDecimal jumlah_unit_syer;
                //added
                BigDecimal jumlah_unit_syer = new BigDecimal(0);
                int bilpetak = 0;

                HakmilikPermohonan hk = new HakmilikPermohonan();
                PermohonanSkim ps = new PermohonanSkim();

                hk = strService.findMohonHakmilik(idPermohonan);
                System.out.println("Id Skim Dari Mohon Hakmilik : " + hk.getId());
                ps = strService.findIDSkimByIDMH(hk.getId());
                System.out.println("Mohon Skim : " + ps.getIdSkim());

                for (int i = 0; i < n1.getLength(); i++) {
                    if (ps != null) {
                        System.out.println("Mohon Skim Not Null");
                        mohonSkim = ps;
                    } else {
                        System.out.println("Mohon Skim Null");
                        mohonSkim = new PermohonanSkim();
                        mohonSkim.setHakmilikPermohonan(listHakPermohon.get(0));
                    }

                    KodTujuanUkur kodTujuanUkur = new KodTujuanUkur();
                    diukur_oleh = (n1.item(i).getAttributes().getNamedItem("diukur_oleh").getNodeValue());
                    pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                    ulasan_jupem = (n1.item(i).getAttributes().getNamedItem("ulasan_jupem").getNodeValue());
                    nama_pemaju = (n1.item(i).getAttributes().getNamedItem("nama_pemaju").getNodeValue());
                    nama_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("nama_perbadanan_pengurusan").getNodeValue());
                    alamat_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("alamat_perbadanan_pengurusan").getNodeValue());
                    nama_projek = (n1.item(i).getAttributes().getNamedItem("nama_projek").getNodeValue());
                    no_ruj_jubl = (n1.item(i).getAttributes().getNamedItem("no_ruj_jubl").getNodeValue());
                    no_ruj_ljt = (n1.item(i).getAttributes().getNamedItem("no_ruj_ljt").getNodeValue());
                    no_fail_ukur = (n1.item(i).getAttributes().getNamedItem("no_fail_ukursemula").getNodeValue());
                    no_fail_ukursemula = (n1.item(i).getAttributes().getNamedItem("no_fail_ukur").getNodeValue());
                    kodtujuanukur = (n1.item(i).getAttributes().getNamedItem("kodtujuanukur").getNodeValue());
                    skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                    bilangan_petak = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue());
                    bilangan_aksesori = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_aksesori").getNodeValue());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    tarikh_terima_sijil_akuan = sdf.parse((n1.item(i).getAttributes().getNamedItem("tarikh_terima_sijil_akuan").getNodeValue()));
                    tarikh_lulus_permohonan = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_lulus_permohonan").getNodeValue());
                    tarikh_siap = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_siap").getNodeValue());
                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue())) {
                        bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                    }

                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue())) {
                        bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                    }

                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue())) {
                        jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue().equals("")) {
                        bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue().equals("")) {
                        bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue().equals("")) {
                        jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue().equals("")) {
                        bilpetak = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue());
                        LOG.info("---bilangan petak for Landparcel---" + bilpetak);
                    }



                    /*saving in Pihak */
                    LOG.info("---saving in Pihak---");
                    Pihak pihak = new Pihak();
                    InfoAudit ia = strService.getInfo(peng);
                    LOG.info("---nama_perbadanan_pengurusan---::" + nama_perbadanan_pengurusan);
                    pihak.setNama(nama_perbadanan_pengurusan);
                    LOG.info("---alamat_perbadanan_pengurusan---::" + alamat_perbadanan_pengurusan);
                    /* using string tokenizer for alamat_perbadanan_pengurusan coz
                     * only one string is there at XML file
                     * for saving in alamat1, alamat2, and so on using string tokenizer
                     */

                    String str = alamat_perbadanan_pengurusan;
                    LOG.info("---str---::" + alamat_perbadanan_pengurusan);
                    String[] rs = str.split(",");
                    LOG.info("-Alamat1--rs[0]---::" + rs[0]);
                    pihak.setAlamat1(rs[0]);
                    LOG.info("-Alamat2--rs[1].trim()---::" + rs[1].trim());
                    pihak.setAlamat2(rs[1].trim());
                    /*in XML dont have alamat3 and alamat4 thats y here setting emtpy string*/
                    pihak.setAlamat3("");
                    pihak.setAlamat4("");
                    String str1 = rs[2];
                    String[] rs1 = str1.trim().split(" ");
                    LOG.info("---str1.trim()--::" + str1.trim());
                    LOG.info("---rs1--::" + rs1);
                    LOG.info("---rs1[0]--::" + rs1[0]);
                    pihak.setPoskod(rs1[0]);
                    if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                        LOG.info("---Melaka--::");
                        if (str.endsWith("Melaka")) {
                            LOG.info("---rs1[1]--::" + rs1[1]);
                            KodNegeri kd = kodNegeriDAO.findById("04");
                            LOG.info("---kd---::" + kd);
                            pihak.setNegeri(kd);
                        }
                    } else {
                        LOG.info("---Seremban--::");
                        if (rs1[1].endsWith("SEREMBAN")) {
                            LOG.info("---rs1[1]--::" + rs1[1]);
                            KodNegeri kd = kodNegeriDAO.findById("05");
                            LOG.info("---kd---::" + kd);
                            pihak.setNegeri(kd);
                        }
                    }
                    pihak.setInfoAudit(ia);
                    pihak = strService.savePihak(pihak);
                    LOG.info("---ID PIHAK---::" + pihak.getIdPihak());
                    pihak = pihakDAO.findById(pihak.getIdPihak());
                    /* end pihak */

                    /*saving in Strata_BDN_URUS */
                    LOG.info("---saving BadanPengurusan---");

                    BadanPengurusan mc1 = new BadanPengurusan();
                    mc1 = strService.findbyId(idPermohonan);

                    if (mc1 == null) {
                        System.out.println("Bandan Pengurusan Null");
                        mc = new BadanPengurusan();
                    } else {
                        System.out.println("Bandan Pengurusan Not Null");
                        mc = mc1;
                    }
                    InfoAudit ia1 = strService.getInfo(peng);
                    mc.setPihak(pihak);
                    mc.setPermohonan(permohonan);
                    mc.setCawangan(permohonan.getCawangan());
                    mc.setInfoAudit(ia1);
                    strService.simpanMaklumatBangunan(mc);
                    LOG.info("---ID BDN---::" + mc.getIdBadan());
                    /* end BadanPengurusan */

                    PermohonanSkim mohonSkim = new PermohonanSkim();
                    mohonSkim.setBadanPengurusan(mc);
                    mohonSkim.setHakmilikPermohonan(listHakPermohon.get(0));
                    mohonSkim.setCawangan(peng.getKodCawangan());
                    mohonSkim.setInfoAudit(infoAudit);
                    mohonSkim.setNamaPemaju(nama_pemaju);
                    mohonSkim.setNamaProjek(nama_projek);
                    mohonSkim.setDiukur(diukur_oleh);
                    mohonSkim.setNoKpPengukur(pengukur_noic);
                    mohonSkim.setNoFailUkur(no_ruj_ljt);
                    mohonSkim.setNoSkim(getIntegerValue1(skim));
                    mohonSkim.setNoRujJubl(no_ruj_jubl);
                    mohonSkim.setNoFailUkurSemula(no_fail_ukursemula);
                    mohonSkim.setNoFailPt(no_fail_ukur);
                    //added 16-05-2012 by murali                    
                    mohonSkim.setJumlahUnitSyer(jumlah_unit_syer.longValue());

                    kodTujuanUkur = kodTujuanUkurDAO.findById(kodtujuanukur);
                    mohonSkim.setKodTujuanUkur(kodTujuanUkur);
                    if (!StringUtils.isEmpty(String.valueOf(bilangan_petak))) {
                        mohonSkim.setBilPetak(Long.parseLong(String.valueOf(bilangan_petak)));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(bilangan_aksesori))) {
                        mohonSkim.setBilAksr(Long.parseLong(String.valueOf(bilangan_aksesori)));
                    }
                    mohonSkim.setTrhSiap(tarikh_siap);
                    mohonSkim.setTrhLulusCf(tarikh_terima_sijil_akuan);
                    mohonSkim = strService.saveSkim(mohonSkim);

                    PermohonanStrata mohonStrata = strService.findID(idPermohonan);
                    if (mohonStrata != null) {
                    } else {
                        mohonStrata = new PermohonanStrata();
                    }

                    mohonStrata.setPermohonan(permohonan);
                    mohonStrata.setInfoAudit(infoAudit);
                    mohonStrata.setCawangan(peng.getKodCawangan());
//                    mohonStrata.setNama("-");
                    mohonStrata.setNama(nama_projek);
                    mohonStrata.setPemilikNama("-");
                    mohonStrata.setNamaSkim(getIntegerValue1(skim));
                    strService.simpanPemilik(mohonStrata);
                    int a;
                    for (a = 0; a < n2.getLength(); a++) {
                        KodKategoriBangunan kodKatBangunan = new KodKategoriBangunan();
                        String dl = "";
                        PermohonanBangunan bgn = new PermohonanBangunan();
                        jumPetak = 0;
                        bgn.setPermohonan(permohonan);
                        bgn.setInfoAudit(infoAudit);
                        bgn.setCawangan(peng.getKodCawangan());
                        kodKatBangunan = kodKategoriBangunanDAO.findById((n2.item(a).getAttributes().getNamedItem("blocktype").getNodeValue()));
                        if (kodKatBangunan.getKod().equals("B")) {
                            bgn.setNama("M" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        if (kodKatBangunan.getKod().equals("P")) {
                            bgn.setNama("P" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        if (kodKatBangunan.getKod().equals("L")) {
                            bgn.setNama(getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        bgn.setPermohonanSkim(mohonSkim);
//                        bgn.setNama(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue());
                        bgn.setNama("M" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        bgn.setBilanganTingkat(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("no_of_tingkat").getNodeValue()));
                        bgn.setKodKategoriBangunan(kodKatBangunan);
                        bgn.setSyerBlok(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("unitsyer").getNodeValue()));
                        KodKegunaanBangunan kodkegunan = new KodKegunaanBangunan();
                        kodkegunan = kodKegunaanBangunanDAO.findById((n2.item(a).getAttributes().getNamedItem("kodkegunaanbangunan").getNodeValue()));
                        LOG.info("---kodkegunan---" + kodkegunan);
                        bgn.setKodKegunaanBangunan(kodkegunan);
                        strService.simpanBangunan(bgn);
                        msg = "Maklumat telah berjaya disimpan.";
//                        extrakTgkt(n3, n4, bgn.getIdBangunan());
                        extrakTgkt(n3, n4, n6, n7, bgn.getIdBangunan(), "S", n8);
                        bgn.setBilanganPetak(jumPetak);
                        strService.simpanBangunan(bgn);
                    }
                }

                /*saving in Mohon_Harta_Bersama */
                LOG.info("---saving in Mohon_Harta_Bersama---");
                int ca;

                PermohonanHartaBersama phb1 = new PermohonanHartaBersama();

                //hk = strService.findMohonHakmilik(idPermohonan);
                //phb1 = strService.find

                for (ca = 0; ca < n7.getLength(); ca++) {
                    PermohonanHartaBersama phb = new PermohonanHartaBersama();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    phb.setInfoAudit(infoAudit);
                    phb.setCawangan(peng.getKodCawangan());
                    phb.setIdSkim(mohonSkim);
                    String nama = n7.item(ca).getAttributes().getNamedItem("commonareano").getNodeValue();
                    LOG.debug("--nama--::" + nama);
                    String temp = nama.substring(3, nama.length());
                    LOG.debug("--substring nama--::" + temp);
                    phb.setNama(temp);
                    if (n7.item(ca).getAttributes().getNamedItem("petaktype").getNodeValue() != null) {
                        String petaktype1 = n7.item(ca).getAttributes().getNamedItem("petaktype").getNodeValue();
                        LOG.debug("--petak type--::" + petaktype1);
                        KodHartaBersama khb = strService.findKodHartaBersamaByNama(petaktype1);
                        LOG.debug("--khb--::" + khb);
                        LOG.debug("--khb.nama--::" + khb.getNama());
                        phb.setJenisHartaBersama(khb);
                        LOG.debug("--saved in jenisHartaBersama coloum--::" + khb.getNama());
                    }
                    strService.simpanPermohonanHartaBersama(phb);
                }


                /*saving LandParcels in Mohon_BNG */
                LOG.info("---saving LandParcels in Mohon_BNGN---");
                int LP;
                for (LP = 0; LP < 1; LP++) {

                    KodKategoriBangunan kodKatBangunan = new KodKategoriBangunan();
                    String dl = "";
                    PermohonanBangunan bgn = new PermohonanBangunan();
                    jumPetak = 0;
                    bgn.setPermohonan(permohonan);
                    bgn.setInfoAudit(infoAudit);
                    bgn.setCawangan(peng.getKodCawangan());
                    kodKatBangunan = kodKategoriBangunanDAO.findById("L");
                    bgn.setPermohonanSkim(mohonSkim);
                    LOG.info("---landparcelno---" + n8.item(LP).getAttributes().getNamedItem("landparcelno").getNodeValue());
                    LOG.info("---landparcelno--after remove brackets-" + getIntegerValue(n8.item(LP).getAttributes().getNamedItem("landparcelno").getNodeValue()));
                    bgn.setNama("L" + getIntegerValue(n8.item(LP).getAttributes().getNamedItem("landparcelno").getNodeValue()));
                    bgn.setBilanganTingkat(0);
                    bgn.setKodKategoriBangunan(kodKatBangunan);
                    LOG.info("---unitysyor---" + n8.item(LP).getAttributes().getNamedItem("unitsyer").getNodeValue());
                    bgn.setSyerBlok(Integer.valueOf(n8.item(LP).getAttributes().getNamedItem("unitsyer").getNodeValue()));

                    LOG.info("---bilangan_petak-from schema for landparcel to save--" + bilpetak);
                    bgn.setBilanganPetak(bilpetak);
                    //temporary
                    LOG.info("---area---" + n8.item(LP).getAttributes().getNamedItem("a_area").getNodeValue());
                    BigDecimal bd = new BigDecimal(n8.item(LP).getAttributes().getNamedItem("a_area").getNodeValue());
                    bgn.setUntukKegunaanLain(bd.toString());
                    strService.simpanBangunan(bgn);
                    System.out.println("LandParcel ExtraTgkt");
                    extrakTgkt(n3, n4, n6, n7, bgn.getIdBangunan(), "L", n8);
                }


                /*saving ProvisionalBlock in Mohon_BNG */
                LOG.info("---saving ProvisionalBlock in Mohon_BNGN---");
                int PB;
                for (PB = 0; PB < n9.getLength(); PB++) {
                    KodKategoriBangunan kodKatBangunan = new KodKategoriBangunan();
                    String dl = "";
                    PermohonanBangunan bgn = new PermohonanBangunan();
                    jumPetak = 0;
                    bgn.setPermohonan(permohonan);
                    bgn.setInfoAudit(infoAudit);
                    bgn.setCawangan(peng.getKodCawangan());
                    kodKatBangunan = kodKategoriBangunanDAO.findById("P");
                    bgn.setPermohonanSkim(mohonSkim);
                    LOG.info("---PB bangunanno---" + n9.item(PB).getAttributes().getNamedItem("bangunanno").getNodeValue());
                    // bgn.setNama("P" + getIntegerValue(n9.item(PB).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                    bgn.setNama(getIntegerValue(n9.item(PB).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                    bgn.setBilanganTingkat(Integer.valueOf(n9.item(PB).getAttributes().getNamedItem("no_of_tingkat").getNodeValue()));
                    bgn.setKodKategoriBangunan(kodKatBangunan);
                    bgn.setSyerBlok(Integer.valueOf(n9.item(PB).getAttributes().getNamedItem("unitsyer").getNodeValue()));
                    strService.simpanBangunan(bgn);
                    if (!kodKatBangunan.getKod().equals("P")) {
                        extrakTgkt(n3, n4, n6, n7, bgn.getIdBangunan(), "S", n8);
                    }
                    bgn.setBilanganPetak(jumPetak);
                    strService.simpanBangunan(bgn);
                    msg = "Maklumat telah berjaya disimpan.";
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorResolution(500,
                        "Fail " + docPath + d.getNamaFizikal() + " tidak dijumpai");
            }
        }
        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakTgkt(NodeList n3, NodeList n4, NodeList n6, NodeList n7, long IdBangunan, String codek, NodeList n8) {

        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        ArrayList<BangunanTingkat> listTingkat = new ArrayList<BangunanTingkat>();
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        int bilTgkt = 0;
        BangunanTingkat tgkt;

        if (codek.equalsIgnoreCase("L")) {
            bilTgkt = 1;
            hv = 0;
        } else if (codek.equalsIgnoreCase("S")) {
            bilTgkt = bgn.getBilanganTingkat();
        }

        int i = 0;
        int petakIndex = 0;
        int currNoOfPetak = 0;
        int prevNoOfPetak = 0;
        LOG.info("-----------hv-------------:" + hv);
        for (int b = hv; b < bilTgkt + hv; b++) {

            LOG.debug("Bilangan Tingkat = " + i);
            tgkt = new BangunanTingkat();
            tgkt.setBangunan(bgn);
            tgkt.setNama(String.valueOf(i + 1));
            tgkt.setBilanganPetak(Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue()));
            if (codek.equalsIgnoreCase("L")) {
                tgkt.setTingkat(0);
            } else if (codek.equalsIgnoreCase("S")) {
                tgkt.setTingkat(i + 1);
            }

            currNoOfPetak = Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue());
            petakIndex = petakIndex + prevNoOfPetak;
            prevNoOfPetak = currNoOfPetak;
            //added
//            tgkt.setBilanganPetakAks(Integer.parseInt(n6.item(b).getAttributes().getNamedItem("no_of_aksesori").getNodeValue()));
            String bilanganPetakAksTemp = (n3.item(b).getAttributes().getNamedItem("no_of_aksesori").getNodeValue());

            int bilanganPetakAks = 0;
            LOG.info("-----------bilanganPetakAksTemp-------------:" + bilanganPetakAksTemp);
            if (bilanganPetakAksTemp != null) {
                bilanganPetakAks = Integer.parseInt(bilanganPetakAksTemp);
            }
            LOG.info("-----------bilanganPetakAks-------------:" + bilanganPetakAks);
            jumPetak = jumPetak + tgkt.getBilanganPetak();
            tgkt.setInfoAudit(infoAudit);
            tgkt.setBilanganPetakAksesori(bilanganPetakAks);
            listTingkat.add(tgkt);
            strService.simpanBangunanTingkat(tgkt);

            /*
            String petakAksesoriL = (n6.item(b).getAttributes().getNamedItem("petakupi").getNodeValue());
            String petakAksesoriNo = (n6.item(b).getAttributes().getNamedItem("aksesorino").getNodeValue());
            String petakAksesoriType = (n6.item(b).getAttributes().getNamedItem("petaktype").getNodeValue());
            String petakAksesoriSyer = (n6.item(b).getAttributes().getNamedItem("unitsyer").getNodeValue());

            int legthP = petakAksesoriL.length();
            LOG.info("Petak Aksesori : " + petakAksesoriL);
            petakAksesoriL.substring(legthP - 1, legthP);
            LOG.info("Landed apa : " + petakAksesoriL.substring(legthP - 1, legthP));
            LOG.info("Aks No : " + petakAksesoriNo);
            LOG.info("Petak Type : " + petakAksesoriType);
            LOG.info("Petak Syer : " + petakAksesoriSyer);
             */
//            String bilanganPetakAksTemp2 = (n1.item(b).getAttributes().getNamedItem("bilangan_aksesori").getNodeValue());
//            
//            int bilanganPetakAks = 0;
//            LOG.info("-----------bilanganPetakAksTemp-------------:" + bilanganPetakAksTemp);
//            if (bilanganPetakAksTemp != null) {
//                bilanganPetakAks = Integer.parseInt(bilanganPetakAksTemp);
//            }



//            extrakPetak(n4, n6, n7, tgkt.getIdTingkat(), bgn.getIdBangunan());\

            if (codek.equals("S")) {
                extrakPetak(n4, n6, n7, tgkt.getIdTingkat(), bgn.getIdBangunan(), petakIndex, codek);
            } else if (codek.equals("L")) {
                extrakPetak2(n4, n6, n7, tgkt.getIdTingkat(), bgn.getIdBangunan(), petakIndex, codek, n8);
            }

            i++;
        }
        hv = bgn.getBilanganTingkat();
        bgn.setSenaraiTingkat(listTingkat);
        strService.updateBangunan(bgn);

        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetak(NodeList n4, NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int petakIndex, String codek) {
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        int looping = 0;

        bangunanTingkat = bangunanTingkatDAO.findById(idTingkat);
        looping = bangunanTingkat.getBilanganPetak();

        System.out.println("------>penting : " + looping);
        int c;
        ArrayList<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        for (c = 0; c < looping; c++) {
            LOG.info("-----------petakIndex-------------:" + petakIndex);
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            LOG.debug("Bilangan Petak = " + c);
            BangunanPetak petak = new BangunanPetak();
            petak.setNama(String.valueOf(sum4 + c + 1));
            petak.setInfoAudit(infoAudit1);

            BangunanTingkat tgkt = new BangunanTingkat();
            petak.setTingkat(bangunanTingkat);

            BigDecimal bd = new BigDecimal(n4.item(petakIndex).getAttributes().getNamedItem("a_area").getNodeValue());
            petak.setLuas(bd);
            KodKegunaanPetak k = new KodKegunaanPetak();
            k.setKod(n4.item(petakIndex).getAttributes().getNamedItem("kodkegunaanpetak").getNodeValue());
            petak.setKegunaan(k);
//            petak.setSyer(Integer.valueOf(n4.item(c).getAttributes().getNamedItem("unitsyer").getNodeValue()));

            //added murali
            int uSyer = Integer.valueOf(n4.item(petakIndex).getAttributes().getNamedItem("unitsyer").getNodeValue());
            LOG.info("-----------uSyer-------------:" + uSyer);
            petak.setSyer(uSyer);

            //added by murali 31/07/2012
            String petakpab = n4.item(petakIndex).getAttributes().getNamedItem("pab").getNodeValue();
            LOG.info("-----------PAB-------------:" + petakpab);
            if (petakpab != null) {
                petak.setPabPetak(petakpab);
            }

            // added by zadirul for unit luas (Kod_UOM)
            String unitluas = n4.item(petakIndex).getAttributes().getNamedItem("a_unit").getNodeValue();
            if (unitluas != null) {
                KodUOM kodUOM = kodUOMDAO.findById(unitluas);
                petak.setLuasUom(kodUOM);
            }

            strService.simpanPetak(petak);

            listPetak.add(petak);
            System.out.println("List Petak : " + listPetak.size());

            petakIndex++;
        }
        sum4 = sum4 + bangunanTingkat.getBilanganPetak();
        int bilanganPetakAks = bangunanTingkat.getBilanganPetakAksesori();

        LOG.debug("--bilanganPetakAks--::" + bilanganPetakAks);
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            System.out.println("Codek : " + codek);
            if (codek.equalsIgnoreCase("S")) {
                if (bilanganPetakAks >= 0) {
                    extrakPetakAksesori(n6, n7, bangunanTingkat.getIdTingkat(), bgn.getIdBangunan(), bilanganPetakAks);
                }
            }
        } else {
            if (bilanganPetakAks > 0) {
                extrakPetakAksesori(n6, n7, bangunanTingkat.getIdTingkat(), bgn.getIdBangunan(), bilanganPetakAks);
            }
        }

        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetak2(NodeList n4, NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int petakIndex, String codek, NodeList n8) {
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        int looping = 0;
        looping = n8.getLength();

        System.out.println("------>penting : " + looping);
        int c;
        ArrayList<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        for (c = 0; c < looping; c++) {

            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            LOG.debug("Bilangan Petak = " + c);
            BangunanPetak petak = new BangunanPetak();
            petak.setInfoAudit(infoAudit1);

            BangunanTingkat tgkt = new BangunanTingkat();
            System.out.println("ID Bangunan : " + IdBangunan);
            tgkt = strService.findTinketByIdBngnNama2(IdBangunan);
            petak.setTingkat(tgkt);
            System.out.println("Tingkat : " + tgkt.getTingkat());



            BigDecimal bd = new BigDecimal(n8.item(c).getAttributes().getNamedItem("a_area").getNodeValue());
            petak.setLuas(bd);
            //KodKegunaanPetak k = new KodKegunaanPetak();
            String landparcelno = n8.item(c).getAttributes().getNamedItem("landparcelno").getNodeValue();
            String landparcelno1 = landparcelno.substring(landparcelno.indexOf("L") + 2);
            System.out.println("-->LandParcelno : " + landparcelno1);
            //k.setKod("L" + landparcelno1);
            //petak.setKegunaan(k);

            petak.setNama(landparcelno1);
            //petak.setSyer(Integer.valueOf(n4.item(c).getAttributes().getNamedItem("unitsyer").getNodeValue()));

            //added murali
            int uSyer = Integer.valueOf(n8.item(c).getAttributes().getNamedItem("unitsyer").getNodeValue());
            LOG.info("-----------uSyer-------------:" + uSyer);
            petak.setSyer(uSyer);

            //added by murali 31/07/2012
            String petakpab = n8.item(c).getAttributes().getNamedItem("pab").getNodeValue();
            LOG.info("-----------PAB-------------:" + petakpab);
            if (petakpab != null) {
                petak.setPabPetak(petakpab);
            }

            // added by zadirul for unit luas (Kod_UOM)
            String unitluas = n8.item(c).getAttributes().getNamedItem("a_unit").getNodeValue();
            if (unitluas != null) {
                KodUOM kodUOM = kodUOMDAO.findById(unitluas);
                petak.setLuasUom(kodUOM);
            }

            strService.simpanPetak(petak);

            listPetak.add(petak);
            System.out.println("List Petak : " + listPetak.size());

        }
        sum4 = sum4 + bangunanTingkat.getBilanganPetak();
        int bilanganPetakAks = bangunanTingkat.getBilanganPetakAksesori();

        LOG.debug("--bilanganPetakAks--::" + bilanganPetakAks);
        if (kodNegeri.equals("05")) {
            System.out.println("Codek : " + codek);
            extrakPetakAksesori2(n6, n7, bangunanTingkat.getIdTingkat(), bgn.getIdBangunan(), bilanganPetakAks, n8);
        } else {
            if (bilanganPetakAks > 0) {
                extrakPetakAksesori(n6, n7, bangunanTingkat.getIdTingkat(), bgn.getIdBangunan(), bilanganPetakAks);
            }
        }

        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetakAksesori(NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int bilanganPetakAks) {
        LOG.debug("--in extrakPetakAksesori--::");
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        LOG.debug("--bgn--::" + bgn);

        /*Saving in BGN_PTK_AKS*/
        LOG.debug("--saving in BNGN_PTK_AKS--::" + bilanganPetakAks);
        int c;
        BangunanPetakAksesori bpk = new BangunanPetakAksesori();
        ArrayList<BangunanPetakAksesori> listPetakAks = new ArrayList<BangunanPetakAksesori>();
        StringBuffer sb = new StringBuffer();
        List<String> aksnameList = new ArrayList<String>();

        for (c = 0; c < bilanganPetakAks; c++) {
            LOG.debug("--aksesorino Bangunan--::" + n6.item(c).getAttributes().getNamedItem("aksesorino").getNodeValue());
            String aksnama = n6.item(c).getAttributes().getNamedItem("aksesorino").getNodeValue();

            String petakupi = n6.item(c).getAttributes().getNamedItem("petakupi").getNodeValue();
            LOG.debug("--petakupi Bangunan--::" + petakupi);
            String tinketNama = "";
            String petakNama = "";

            System.out.println("Inside Aksesori Bangunan");
            int tinketIndex = petakupi.lastIndexOf("T");

            System.out.println("Tinket Index:" + tinketIndex);

            int petakIndex = petakupi.lastIndexOf("P");

            System.out.println("Petak Index:" + petakIndex);

            tinketNama = petakupi.substring(tinketIndex + 2, petakIndex - 1);

            System.out.println("Tinket Value:" + tinketNama);

            petakNama = petakupi.substring(petakIndex + 2);

            System.out.println("Petak Value:" + petakNama);


            BangunanTingkat bgnTkt = new BangunanTingkat();
            BangunanPetak ptk = new BangunanPetak();
            System.out.println("----->" + IdBangunan);
            bgnTkt = strService.findTinketByIdBngnNama(IdBangunan, tinketNama);
            LOG.debug("--bgnTkt--::" + bgnTkt.getIdTingkat());
            ptk = strService.findPetakByIdBngnIdTinketNama(bgnTkt.getIdTingkat(), petakNama);
            //LOG.debug("--ptk--::" + ptk.getIdPetak());

            bpk = new BangunanPetakAksesori();
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            if (aksnama != null) {
                LOG.debug("--aksnama--::" + aksnama);
                String temp = aksnama.substring(3, aksnama.length());
                bpk.setNama(temp);
            }
            bpk.setInfoAudit(infoAudit1);
            bpk.setCawangan(peng.getKodCawangan());
            bpk.setTingkat(bgnTkt);
            bpk.setBangunan(bgn);
            bpk.setPetak(ptk);
            String aksesorino = n6.item(c).getAttributes().getNamedItem("aksesorino").getNodeValue();
            LOG.debug("--aksesorino--::" + aksesorino);
            if (aksnama.charAt(0) == 'A') {
                bpk.setLokasi("Luar Bangunan");
            } else {
                bpk.setLokasi("Dalam Bangunan");
            }
            String petaktype = n6.item(c).getAttributes().getNamedItem("petaktype").getNodeValue();
            LOG.debug("--petak type--::" + petaktype);
            KodKegunaanPetakAksesori kodguna1 = kodKegunaanPetakAksesoriDAO.findById(petaktype);
            LOG.info("--kodguna1--" + kodguna1);
            if (kodguna1 != null) {
                bpk.setKegunaan(kodguna1);
            }
            //added
            String petakaksrpab = n6.item(c).getAttributes().getNamedItem("pab").getNodeValue();
            LOG.info("--petakaksrpab--" + petakaksrpab);
            if (petakaksrpab != null) {
                bpk.setPabAksesori(petakaksrpab);
            }
            strService.simpanPetakAksesori(bpk);
        }

        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetakAksesori2(NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int bilanganPetakAks, NodeList n8) {
        LOG.debug("..:: extrakPetakAksesori2 ::..");
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);


        /*Saving in BGN_PTK_AKS*/
        int c;
        BangunanPetakAksesori bpk = new BangunanPetakAksesori();
        ArrayList<BangunanPetakAksesori> listPetakAks = new ArrayList<BangunanPetakAksesori>();
        StringBuffer sb = new StringBuffer();
        List<String> aksnameList = new ArrayList<String>();

        for (int a = 0; a < n8.getLength(); a++) {
            String landparcelno = n8.item(a).getAttributes().getNamedItem("landparcelno").getNodeValue();
            String landparcelno1 = landparcelno.substring(landparcelno.indexOf("L") + 2);

            for (c = 0; c < n6.getLength(); c++) {
                String aksnama = n6.item(c).getAttributes().getNamedItem("aksesorino").getNodeValue();
                System.out.println("Aksesori No : " + aksnama);

                String petakupi = n6.item(c).getAttributes().getNamedItem("petakupi").getNodeValue();
                System.out.println("Petak UPI : " + petakupi);

                String petaktype = n6.item(c).getAttributes().getNamedItem("petaktype").getNodeValue();
                System.out.println("Petak Type :" + petaktype);

                String petakaksrpab = n6.item(c).getAttributes().getNamedItem("pab").getNodeValue();
                System.out.println("PAB : " + petakaksrpab);

                String petakNama = "";
                petakNama = petakupi.substring(petakupi.indexOf("L") + 2);
                System.out.println("Petak UPI After Substring :" + petakNama);

                //String namaBangunan = bgn.getNama().substring(bgn.getNama().length() - 1);
                System.out.println("Bangunan No Di Land Parcel :" + landparcelno1);

                if (petakupi.matches("(.*)L(.*)")) {
                    if (petakNama.equals(landparcelno1)) {
                        System.out.println("Match Aksesori No With LandParcel No  :: id Bangunan-->" + IdBangunan);

                        BangunanTingkat bgnTkt = new BangunanTingkat();
                        BangunanPetak ptk = new BangunanPetak();

                        bgnTkt = strService.findTinketByIdBngnNama2(IdBangunan);
                        System.out.println("Id TIngkat : " + bgnTkt.getIdTingkat());
                        ptk = strService.findBangunanPetak(landparcelno1, bgnTkt.getIdTingkat());

                        System.out.println("Tingkat No : " + ptk.getTingkat());
                        System.out.println("Petak No : " + ptk.getIdPetak());

                        bpk = new BangunanPetakAksesori();
                        InfoAudit infoAudit1 = new InfoAudit();
                        infoAudit1.setDimasukOleh(peng);
                        infoAudit1.setTarikhMasuk(new java.util.Date());

                        if (aksnama != null) {
                            String temp = aksnama.substring(aksnama.indexOf("A") + 2);
                            bpk.setNama(temp);
                            System.out.println("Aksesori No : " + temp);
                        }

                        bpk.setInfoAudit(infoAudit1);
                        bpk.setCawangan(peng.getKodCawangan());
                        bpk.setTingkat(ptk.getTingkat());
                        bpk.setBangunan(bgn);
                        bpk.setPetak(ptk);

                        if (aksnama.charAt(0) == 'A') {
                            bpk.setLokasi("Luar Bangunan");
                        } else {
                            bpk.setLokasi("Dalam Bangunan");
                        }

                        KodKegunaanPetakAksesori kodguna1 = kodKegunaanPetakAksesoriDAO.findById(petaktype);
                        System.out.println("Kod Kegunaan Aksesori : " + kodguna1);
                        if (kodguna1 != null) {
                            bpk.setKegunaan(kodguna1);
                        }

                        if (petakaksrpab != null) {
                            bpk.setPabAksesori(petakaksrpab);
                        }
                        strService.simpanPetakAksesori(bpk);
                    }
                }
            }
        }
        System.out.println("..:: Tamat extrakPetakAksesori2 ::..");
        return new RedirectResolution(GisStrataActionBean.class, "ToJupem").addParameter("error", error).addParameter("message", msg);
    }

    public boolean validateXML(NodeList n1) {
        boolean pass = false;
        String hakmilik = new String();
        for (int i = 0; i < n1.getLength(); i++) {
            hakmilik = n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue();
            System.out.println("Hakmilik : " + hakmilik);
//
//            "no_fail_ptg"
//                    "diukur_oleh"
//                    "pengukur_noic"
//                    ="123456789012" tarikh_siap="2008-05-10" bilangan_petak="291" bilangan_aksesori="291" tarikh_terima_sijil_akuan="2010-04-13" tarikh_lulus_permohonan="2010-06-10" bayaran_ukur="" bayaran_pelan="" ulasan_jupem="" nama_pemaju="" nama_perbadanan_pengurusan="" nama_projek="" no_ruj_jubl="JX597-2" no_ruj_ljt="338/00066" no_fail_ukursemula="PUBLWP260_2008" kodtujuanukur="18" jumlah_unit_syer="0">



            /**
             * negeri="14" daerah="00" mukim="06" seksyen="000" lot="43905"
             * skim="(S)-1" pa="PA113103" no_hakmilik="PN46013"
             *
             */
            System.out.println("Id Hakmilik-->" + (listHakPermohon.get(0)).getHakmilik().getIdHakmilik());
            if (hakmilik.equals((listHakPermohon.get(0)).getHakmilik().getIdHakmilik())) {
                pass = true;
                break;
            } else {
                pass = false;
            }
        }
        return pass;
    }

    public String getIntegerValue1(String s) {
        String remove = "(S)";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
    }

    public String getIntegerValue(String s) {
        String remove = "(B)M";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        remove = "(B)";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        remove = "(L)";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
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

    public int getDocumentExist() {
        return documentExist;
    }

    public void setDocumentExist(int documentExist) {
        this.documentExist = documentExist;
    }

    public List<HakmilikPermohonan> getListHakPermohon() {
        return listHakPermohon;
    }

    public void setListHakPermohon(List<HakmilikPermohonan> listHakPermohon) {
        this.listHakPermohon = listHakPermohon;
    }

    public PermohonanSkim getMohonSkim() {
        return mohonSkim;
    }

    public void setMohonSkim(PermohonanSkim mohonSkim) {
        this.mohonSkim = mohonSkim;
    }

    public BadanPengurusan getBadanUrus() {
        return badanUrus;
    }

    public void setBadanUrus(BadanPengurusan badanUrus) {
        this.badanUrus = badanUrus;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public List<PermohonanBangunan> getpBangunanLandParcel() {
        return pBangunanLandParcel;
    }

    public void setpBangunanLandParcel(List<PermohonanBangunan> pBangunanLandParcel) {
        this.pBangunanLandParcel = pBangunanLandParcel;
    }

    public List<BangunanPetak> getPetakL() {
        return petakL;
    }

    public void setPetakL(List<BangunanPetak> petakL) {
        this.petakL = petakL;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PermohonanBangunan> getBgnList() {
        return bgnList;
    }

    public void setBgnList(List<PermohonanBangunan> bgnList) {
        this.bgnList = bgnList;
    }

    public List<BangunanTingkat> getBgnTgktList() {
        return bgnTgktList;
    }

    public void setBgnTgktList(List<BangunanTingkat> bgnTgktList) {
        this.bgnTgktList = bgnTgktList;
    }

    public List<BangunanPetak> getBgnPetakList() {
        return bgnPetakList;
    }

    public void setBgnPetakList(List<BangunanPetak> bgnPetakList) {
        this.bgnPetakList = bgnPetakList;
    }

    public List<BangunanPetakAksesori> getBgnPetakAksrList() {
        return bgnPetakAksrList;
    }

    public void setBgnPetakAksrList(List<BangunanPetakAksesori> bgnPetakAksrList) {
        this.bgnPetakAksrList = bgnPetakAksrList;
    }

    public List<BangunanPetakAksesori> getBgnPetakAksrList2() {
        return bgnPetakAksrList2;
    }

    public void setBgnPetakAksrList2(List<BangunanPetakAksesori> bgnPetakAksrList2) {
        this.bgnPetakAksrList2 = bgnPetakAksrList2;
    }

    public String getTempIdBangunan() {
        return tempIdBangunan;
    }

    public void setTempIdBangunan(String tempIdBangunan) {
        this.tempIdBangunan = tempIdBangunan;
    }

    public String getTempIdTingkat() {
        return tempIdTingkat;
    }

    public void setTempIdTingkat(String tempIdTingkat) {
        this.tempIdTingkat = tempIdTingkat;
    }

    public String getTempIdPetak() {
        return tempIdPetak;
    }

    public void setTempIdPetak(String tempIdPetak) {
        this.tempIdPetak = tempIdPetak;
    }
}
