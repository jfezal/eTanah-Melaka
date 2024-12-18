/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author k.Hazwan
 */
import able.stripes.AbleActionBean;
import etanah.model.KandunganFolder;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.UrusanDokumen;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorIdPermohonanKelompok;
import etanah.service.KodService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisCarianBayaran;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakWakil;
import etanah.model.Pihak;
import etanah.model.SalinanKepada;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.utility.UtilitiSalinanActionBean;
import java.text.ParseException;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author khairul.hazwan
 */

@UrlBinding("/pembangunan/melaka/salinan_surat")
public class SalinanKepadaActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SalinanKepadaActionBean.class);
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    SalinanKepadaDAO salinanKepadaDAO;
    private Permohonan permohonan;
    private SalinanKepada salinanKepada;
    private List<SalinanKepada> listSalinanKepada;
    private String idPermohonan;
    private String kodNegeri;
    private String reportName;
    private String taskId;
    private String stageId;
    private Task task = null;
    private Pengguna pengguna;
    private BPelService service;
    private String kodDokumen;
    private SalinanKepada delSk;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/pembangunan/melaka/salinan_surat_edit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        stageId = currentStageId();
        LOG.info("---------stageId---------------:" + stageId);
        
        if (permohonan.getKodUrusan().getKod().equals("SBMS")) {
            if (stageId.equals("cetaksrtjkbbrekodtkhtt")) {
                kodDokumen = "SSBMS";
            }
        }

        if (kodDokumen != null) {
            listSalinanKepada = new ArrayList<SalinanKepada>();
            listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
        }

        LOG.info("rehydrate finish");
    }

    public Resolution showFormSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (idPermohonan != null && kodDokumen != null) {
            listSalinanKepada = new ArrayList<SalinanKepada>();
            listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
        }
        return new JSP("pembangunan/melaka/salinan_surat_edit.jsp").addParameter("tab", "true");
    }

    public Resolution showFormTambahSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        salinanKepada = new SalinanKepada();
        return new JSP("pembangunan/melaka/salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution showFormEditSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        String idSalinan = getContext().getRequest().getParameter("idSalinan");

        salinanKepada = new SalinanKepada();
        salinanKepada = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
        return new JSP("pembangunan/melaka/salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution simpanSalinan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = (String) getContext().getRequest().getParameter("kodDokumen");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                SalinanKepada salinanTemp = new SalinanKepada();
                String idSalinan = (String) getContext().getRequest().getParameter("salinanKepada.idSalinanKepada");
                if (idSalinan != null && !idSalinan.equals("0")) {
                    salinanTemp = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
                    infoAudit = salinanTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                salinanTemp.setInfoAudit(infoAudit);
                salinanTemp.setNama(salinanKepada.getNama());
                salinanTemp.setAlamat1(salinanKepada.getAlamat1());
                salinanTemp.setAlamat2(salinanKepada.getAlamat2());
                salinanTemp.setAlamat3(salinanKepada.getAlamat3());
                salinanTemp.setAlamat4(salinanKepada.getAlamat4());
                salinanTemp.setPoskod(salinanKepada.getPoskod());
                salinanTemp.setNegeri(salinanKepada.getNegeri());
                salinanTemp.setCatatan(salinanKepada.getCatatan());
                salinanTemp.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
                salinanTemp.setPermohonan(permohonan);
                salinanTemp.setCawangan(permohonan.getCawangan() != null ? permohonan.getCawangan() : null);

                disLaporanTanahService.getPelupusanService().kemaskiniSalian(salinanTemp);

            }

            if (idPermohonan != null && kodDokumen != null) {
                listSalinanKepada = new ArrayList<SalinanKepada>();
                listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
            }
           
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/salinan_surat_edit.jsp").addParameter("popup", true);

    }

    public Resolution deleteRow() throws ParseException {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        delSk = salinanKepadaDAO.findById(Long.parseLong(idKandungan));
        devService.deleteSK(delSk);
        addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        return new RedirectResolution(SalinanKepadaActionBean.class, "showForm");       
    }

    public String currentStageId() {
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        return stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public SalinanKepada getSalinanKepada() {
        return salinanKepada;
    }

    public void setSalinanKepada(SalinanKepada salinanKepada) {
        this.salinanKepada = salinanKepada;
    }

    public List<SalinanKepada> getListSalinanKepada() {
        return listSalinanKepada;
    }

    public void setListSalinanKepada(List<SalinanKepada> listSalinanKepada) {
        this.listSalinanKepada = listSalinanKepada;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public SalinanKepada getDelSk() {
        return delSk;
    }

    public void setDelSk(SalinanKepada delSk) {
        this.delSk = delSk;
    }
    
    
}
