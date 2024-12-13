package etanah.view.daftar;

import able.stripes.JSP;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import able.stripes.AbleActionBean;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPerhubunganHakmilik;
import etanah.model.PermohonanUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.service.RegService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**

 * @author wan.fairul
 *
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/betul_tarikh")
public class UtilitiBetulTarikh extends AbleActionBean {

    private Permohonan permohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FasaPermohonanService fService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    RegService regService;
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    
    public String tarikhBaru;

    public String getTarikhBaru() {
        return tarikhBaru;
    }

    public void setTarikhBaru(String tarikhBaru) {
        this.tarikhBaru = tarikhBaru;
    }
    
    private String kodUrusan;
    private String kodNegeri;
    private String idHakmilik;
    private Pengguna pengguna;
    private String taskId;
    private String idHakmilikBaru;
    private String idPermohonan;
    private Hakmilik hb;
    private List<FasaPermohonan> fasapermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private static final Logger LOG = Logger.getLogger(UtilitiBetulHakmilik.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private boolean form = false;
    private String stage;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private PermohonanUrusan permohonanUrusan;

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Hakmilik getHb() {
        return hb;
    }

    public void setHb(Hakmilik hb) {
        this.hb = hb;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanKemaskini() {
        return hakmilikPermohonanKemaskini;
    }

    public void setHakmilikPermohonanKemaskini(List<HakmilikPermohonan> hakmilikPermohonanKemaskini) {
        this.hakmilikPermohonanKemaskini = hakmilikPermohonanKemaskini;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<FasaPermohonan> getFasapermohonan() {
        return fasapermohonan;
    }

    public void setFasapermohonan(List<FasaPermohonan> fasapermohonan) {
        this.fasapermohonan = fasapermohonan;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/pembetulan_tarikh.jsp");
    }

    public Resolution checkPermohonan() throws WorkflowException {
//        if (permohonan == null) {
//            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
//            return showForm();
//        }
//        idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return showForm();
        }

        permohonanUrusan = permohonanService.findPermohonanUrusan(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonanUrusan == null) {
            System.out.print("Permohonan " + idPermohonan + " tidak wujud.");
            addSimpleError("Permohonan " + idPermohonan + " tidak wujud.");
            return showForm();
        } else {
            fasapermohonan = fService.findStatus(idPermohonan);
            if (fasapermohonan == null) {
                addSimpleError("Permohonan " + idPermohonan + " tidak wujud");
                return showForm();
            }
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
            }
            if (stage != null) {
                if (stage.equalsIgnoreCase("keputusan")) {
                    addSimpleError("Permohonan " + idPermohonan + " tidak dibolehkan untuk baiki perserahan hakmilik");
                    return showForm();
                } else {
                    hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                    form = true;
                }
            } else {
                addSimpleError("Permohonan " + idPermohonan + " tidak wujud.");
                return showForm();
            }
        }
        return showForm();

    }


    public Resolution simpanTarikh() throws ParseException {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonanUrusan = permohonanService.findPermohonanUrusan(idPermohonan);
        LOG.debug("------TARIKHBARU:" + tarikhBaru);
        LOG.debug("------PERMOHONAN:" + permohonan);
        LOG.debug("------IDPERMOHONAN:" + idPermohonan);
//        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        if(tarikhBaru == null)
        {
            addSimpleError("Sila Isi Tarikh");
            return showForm();
        }
        Date dt1 = sd.parse(tarikhBaru);
        dt1.setHours(0);
        dt1.setMinutes(0);
        dt1.setSeconds(0);
        String d1 = sdf.format(dt1);
        LOG.debug("------D1:" + d1);
        LOG.debug("------DT1:" + dt1);
        
        InfoAudit info = new InfoAudit();
//                permohonan.getInfoAudit();
        LOG.debug("------INFO:" + info);
//        info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
//        info.setTarikhMasuk(dt1);
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(dt1);
        permohonanUrusan.setTarikhSaksi(dt1);
        permohonanUrusan.setInfoAudit(info);
        permohonanService.saveOrUpdate(permohonanUrusan);
        msg = "Kemaskini Data Berjaya";
                addSimpleMessage(msg);
        
            
            
//        LOG.debug("IDHakmilik :" + idHakmilik);
//        LOG.debug("IDHakmilik yg dicari : " + idHakmilikBaru);
//
//        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
//            if (hp.getHakmilik().getIdHakmilik().equals(idHakmilikBaru)) {
//                LOG.debug("id hakmilik sama");
//                error = "Id hakmilik sama";
//                addSimpleError(error);
//                form = true;
//                return showForm();
//            }
//        }
//
//
//        if (idHakmilikBaru != null) {
//            hb = hakmilikDAO.findById(idHakmilikBaru);
//
//            if (hb == null) {
//                error = "Tiada Hakmilik Dijumpai";
//                addSimpleError(error);
//                form = true;
//
//            } else {
//
//                LOG.debug("saving hakmilik");
//                LOG.debug("idHakmilikBaru : " + idHakmilikBaru);
//                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//                InfoAudit info = peng.getInfoAudit();
//                info.setDimasukOleh(peng);
//                info.setTarikhMasuk(new java.util.Date());
//                HakmilikPermohonan hp = new HakmilikPermohonan();
//                hp.setHakmilik(hb);
//                hp.setPermohonan(permohonan);
//                //set kod tangung ismen K = Lot menguasai
//                KodPerhubunganHakmilik kph = new KodPerhubunganHakmilik();
//
//                kph.setKod("K");
//                hp.setHubunganHakmilik(kph);
//                hp.setInfoAudit(info);
//                regService.simpanMohonHakmilik(hp);
//
//                msg = "Kemasukan Data Berjaya";
//                addSimpleMessage(msg);
//                form = true;
//            }
//
//        }
        form=true;
        return showForm();

    }

    
    private void updateTarikh(Date d){
        InfoAudit ia = new InfoAudit();
        ia.setTarikhKemaskini(d);
    }
    
    public Resolution simpanTarikhBaru(){
        InfoAudit ia = new InfoAudit();
        return showForm();
    }
}

