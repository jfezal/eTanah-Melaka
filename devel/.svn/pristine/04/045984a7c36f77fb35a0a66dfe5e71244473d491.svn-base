/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import java.io.File;
import etanah.view.etanahActionBeanContext;
import java.io.FileInputStream;
import java.math.BigDecimal;
import org.hibernate.Session;
//import weblogic.jms.interception.service;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/bayaranbaki")
public class BayaranBakiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(BayaranBakiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    LelonganDAO lelonganDAO;
    Permohonan permohonan;
    private String idPermohonan;
    private Enkuiri enkuiri;
    private long idEnkuiri;
    private String baki;
    private String hari;
    private Lelongan lelong;
    private String tarikhLelong;
    private String tarikhAkhirBayaran;
    private boolean flag = false;
    private boolean showBtn = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Lelongan> list;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private BPelService service;
    private String disabled;
    private Pengguna pengguna;
    private String taskId;
    private String stageId;
    Task task = null;
    private String kodUrusan;
    private String workflowId;
    private String idFolder = "";
    private String idHakmilik;
    private Hakmilik hakmilik;
    @Inject
    EnkuiriService ES;
    @Inject
    LelongService LS;
        @Inject
    private PembetulanService pService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiBayaranBaki.jsp");
    }

    public Resolution find() {
        LOG.debug("cariiii");
        senaraiLelongan = ES.getALLLelongan(idPermohonan);
        LOG.info("senaraiLelongan.size :" + senaraiLelongan.size());

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
            Date now = new Date();
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());
//                List<Lelongan> senaraiLelongan = new ArrayList<Lelongan>();
//                senaraiLelongan = ES.getSenaraiLelong(permohonan.getIdPermohonan());
                LOG.info("senaraiLelongan.size :" + senaraiLelongan.size());

                for (Lelongan L : senaraiLelongan) {
                    if (L.getBaki() == null) {
                        continue;
                    }
                    baki = L.getBaki().toString();
                    tarikhLelong = sdf.format(L.getTarikhLelong());
                    tarikhAkhirBayaran = sdf.format(L.getTarikhAkhirBayaran());
                    if (now.after(L.getTarikhLelong())) {
                        long count = (now.getTime() - L.getTarikhLelong().getTime()) / 1000 / 60 / 60 / 24;
                        LOG.info("count :" + count);
                        hari = count + "";
                        if (count >= 90) {
                            showBtn = true;
                        }
                    } else {
                        LOG.info("count : before");
                    }
                    LOG.info("baki: " + baki);
                    if (L.getBaki().intValue() != 0) {
                        disabled = "disabled";
                    }
                }
            }
        }
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiBayaranBaki.jsp");
    }

    public Resolution cetak() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("LLGSuratBayarBaki_MLK.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution reset() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = ctx.getUser();
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String lelongan = getContext().getRequest().getParameter("idLelongan");
        Long idLelong = Long.parseLong(lelongan);
        LOG.info("idPermohonan: " + idPermohonan);
        if (StringUtils.isNotBlank(idPermohonan) && idLelong != null) {
            LOG.info("idLelong :" + idLelong);
            lelong = lelonganDAO.findById(idLelong);
            lelong.setBaki(BigDecimal.valueOf(0));
            ES.saveResetBaki(lelong, pguna);

            idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
            LOG.info("idhakmilikkkkkkk :" + idHakmilik);
        }
//        return new JSP("/lelong/UtilitiBayaranBaki_Reset.jsp").addParameter("popup", "true");
        find();
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiBayaranBaki.jsp");

    }

    public Resolution viewhakmilikDetail() {
        
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = pService.findHakmilik(idHakmilik);
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }
    
    
    
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getBaki() {
        return baki;
    }

    public void setBaki(String baki) {
        this.baki = baki;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public List<Lelongan> getList() {
        return list;
    }

    public void setList(List<Lelongan> list) {
        this.list = list;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public long getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(long idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
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

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
}
