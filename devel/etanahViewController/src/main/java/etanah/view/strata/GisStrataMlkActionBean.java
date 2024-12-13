package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.IntegrasiPermohonanDokumenDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Dokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.Transaksi;
import etanah.model.gis.PelanGIS;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/*
 * @author Murali 
 */
@UrlBinding("/strata/mlk/gis")
public class GisStrataMlkActionBean extends AbleActionBean {

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
    OutBoundIntegrationStrata obi;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrasiPermohonanButirDAO;
    @Inject
    IntegrasiPermohonanDokumenDAO integrasiPermohonanDokumenDAO;
    private static final Logger LOG = Logger.getLogger(GisStrataMlkActionBean.class);
    public Transaksi trans;
    private Permohonan permohonan = new Permohonan();
    private String idPermohonan = new String();
    private Pengguna pguna;
    private String stageId;
    private String path;
    private String idPermohonan1 = new String();
    private String stageId1;
    private boolean isDokumenUploaded = Boolean.FALSE;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
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
        LOG.info("----pelanGIS----" + pelanGIS);
        if (pelanGIS != null) {
            getContext().getRequest().setAttribute("adaPelan", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("adaPelan", Boolean.FALSE);
        }
    }

    @DefaultHandler
    public Resolution PLKTA() throws ParserConfigurationException {
        getPathPLKTA();
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

        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLKTA");
        LOG.info("----Dokumen----:" + d);
        if ((d != null && d.getNamaFizikal() != null)) {
            if (d.getFormat().contains("xml") || d.getFormat().contains("XML")) {
                LOG.info("----format xml----:");
                isDokumenUploaded = true;
            }
        }

        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        getContext().getRequest().setAttribute("pengguna", peng.getIdPengguna());
        return new JSP("strata/pbbm/gisStrataMlk.jsp").addParameter("tab", "true");
    }

    public String getPathPLKTA() throws ParserConfigurationException {
        Dokumen d = strService.findDok(idPermohonan, "PLKTA");
        LOG.info("----Dokumen PLKTA----:" + d);
        if (d != null) {
            String docPath = conf.getProperty("document.path");
            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            path = f.getAbsolutePath() + ".xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
        }
        LOG.info("----path----:" + path);
        return path;
    }

    public Resolution JPP() throws ParserConfigurationException {
        getPathJPP();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService serviceBpel = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }

        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
        LOG.info("----Dokumen----:" + d);
        if (!mhnBngn.isEmpty()) {
            if (mhnBngn.get(0).getNamaLain() == null) {
                if ((d != null && d.getNamaFizikal() != null)) {
                    isDokumenUploaded = true;
                }
            } else {
                getContext().getRequest().setAttribute("manual", Boolean.TRUE);
            }
        }

        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        getContext().getRequest().setAttribute("pengguna", peng.getIdPengguna());
        return new JSP("strata/pbbm/gisStrataMlk.jsp").addParameter("tab", "true");
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

    public Resolution transferFile() {
        LOG.info("----transferFile To Outbound Integration----:");
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        String[] kodursns = {"P8", "P22A", "P40A", "RTHS", "RTPS", "PPRUS"};
        if (ArrayUtils.contains(kodursns, permohonan.getKodUrusan().getKod())) {
            error = obi.copyfile();
            addSimpleError(error);
            addSimpleMessage(error);
            return new JSP("strata/pbbm/gisStrataMlk.jsp").addParameter("tab", "true");
        } else {
            return new JSP("strata/pbbm/mlk_pelan_lokasi_GIS.jsp").addParameter("tab", "true");
        }
    }

    public Resolution transferJppFile() {
        LOG.info("----transferFile To Outbound Integration----:");
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();
        addSimpleError(error);
        addSimpleMessage(error);
        return new JSP("strata/pbbm/gisStrataMlk.jsp").addParameter("tab", "true");
    }

    public Resolution showPelanLokasi() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService serviceBpel = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.debug("STAGE ID : " + stageId);
        }

        if (permohonan.getKodUrusan().getKod().equals("P14A") || permohonan.getKodUrusan().getKod().equals("P22B") || permohonan.getKodUrusan().getKod().equals("PNB")) {
            if (permohonan.getPermohonanSebelum() != null) {
                Dokumen d = strService.findDok(permohonan.getPermohonanSebelum().getIdPermohonan(), "JPP");
                LOG.info("----Dokumen----:" + d);
                if ((d != null && d.getNamaFizikal() != null)) {
                    isDokumenUploaded = true;
                }
                idPermohonan1 = permohonan.getPermohonanSebelum().getIdPermohonan();
                stageId1 = "g_semakkemasukan";
            }
        } else {
            Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
            LOG.info("----Dokumen----:" + d);
            if (!mhnBngn.isEmpty()) {
                if (mhnBngn.get(0).getNamaLain() == null) {
                    if ((d != null && d.getNamaFizikal() != null)) {
                        isDokumenUploaded = true;
                    }
                }
                else{
                    isDokumenUploaded = false;
                    getContext().getRequest().setAttribute("manual", Boolean.TRUE);
                }
            }
            idPermohonan1 = permohonan.getIdPermohonan();
            if (stageId.equals("g_sedialaporan")) {
                stageId1 = "g_semakkemasukan";
            }
            if (stageId.equals("g_keputusan2")) {
                stageId1 = "g_cetakpelan";
            }
        }
        return new JSP("strata/pbbm/mlk_pelan_lokasi_GIS.jsp").addParameter("tab", "true");
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

    public boolean isIsDokumenUploaded() {
        return isDokumenUploaded;
    }

    public void setIsDokumenUploaded(boolean isDokumenUploaded) {
        this.isDokumenUploaded = isDokumenUploaded;
    }
}
