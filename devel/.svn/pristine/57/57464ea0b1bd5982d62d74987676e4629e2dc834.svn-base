/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.lelong.KemasukkanRekodActionBean;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

@UrlBinding("/penghantarnotis")
public class PenghantarNotisActionBean extends AbleActionBean{
 @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanService permohonanservice;
    @Inject
    NotisDAO notisDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    DasarTuntutanNotisDAO dasarNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private Permohonan permohonan;
    private Notis notis;
    private String idPermohonan;
    private List<KandunganFolder> listKandunganFolder;
    private List<Notis> listNotis;
    private List<PenghantarNotis> listPenghantar;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idNotis;
    private int idPenghantarNotis;
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(PenghantarNotisActionBean.class);
    private boolean button = false;
    private boolean view = false;
    private FasaPermohonan fasaMohon;
    private String stageId;
//    private Pengguna pengguna;
    Task task = null;
    private String workflowId;
    private String idFolder = "";
    private BPelService service;
    private String taskId;
    private String kodUrusan;
    private String disabled;
    private char status;
    private boolean showBtn = false;
    private long idDokumen;
    private PenghantarNotis penghantarNotis;
    private List<FasaPermohonan> fasaPermohonan;
    private boolean gantian;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution popupPenghantarNotis() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        listPenghantar = lelongService.findPenghantarNotisAK();
//        listPenghantar = penghantarNotisDAO.findAll();
        LOG.info("listPenghantar :" + listPenghantar.size());

        return new JSP("daftar/pembetulan/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPenghantarNotis() {

        LOG.info("simpan--");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        penghantarNotis.setInfoAudit(ia);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(penghantarNotis.getKodJenisPengenalan().getKod());
        penghantarNotis.setKodJenisPengenalan(kod);
        lelongService.saveOrUpdate(penghantarNotis);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        listPenghantar = lelongService.findPenghantarNotisAK();
//        listPenghantar = penghantarNotisDAO.findAll();

        return new JSP("daftar/pembetulan/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanEditStatus() {
        LOG.info("-------simpan status-------");

        PenghantarNotis pn = penghantarNotisDAO.findById(Integer.parseInt((getContext().getRequest().getParameter("idPenghantarNotis"))));
        pn.setAktif(status);
        lelongService.saveOrUpdate(pn);

        return new JSP("daftar/pembetulan/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //delete maklumat penghantar notis
    public Resolution delete() {

        LOG.info("Masuk delete");

        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();

        String results = "0";
        try {

            String idPenghantarNotis = getContext().getRequest().getParameter("idPenghantarNotis");

            penghantarNotis = penghantarNotisDAO.findById(Integer.parseInt(idPenghantarNotis));

            lelongService.deletePenghantarNotis(penghantarNotis);
            results = "1";

        } catch (Exception e) {
            results = "2";
            e.printStackTrace();
            tx.rollback();
            LOG.debug("results : " + results);
            return new StreamingResolution("text/plain", results);
        }
        LOG.debug("results : " + results);
        tx.commit();

        listPenghantar = lelongService.findPenghantarNotisAK();
        return new StreamingResolution("text/plain", results);
    }

    //added by nur.aqilah
    //reset page
    public Resolution reset() {
        LOG.info("---nk reset---");
        penghantarNotis = new PenghantarNotis();
        penghantarNotis = null;
        listPenghantar = lelongService.findPenghantarNotisAK();
        return new JSP("daftar/pembetulan/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }
//
//    public Pengguna getPengguna() {
//        return pengguna;
//    }
//
//    public void setPengguna(Pengguna pengguna) {
//        this.pengguna = pengguna;
//    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public List<FasaPermohonan> getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(List<FasaPermohonan> fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public boolean isGantian() {
        return gantian;
    }

    public void setGantian(boolean gantian) {
        this.gantian = gantian;
    }

    public List<PenghantarNotis> getListPenghantar() {
        return listPenghantar;
    }

    public void setListPenghantar(List<PenghantarNotis> listPenghantar) {
        this.listPenghantar = listPenghantar;
    }

    public int getIdPenghantarNotis() {
        return idPenghantarNotis;
    }

    public void setIdPenghantarNotis(int idPenghantarNotis) {
        this.idPenghantarNotis = idPenghantarNotis;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}

