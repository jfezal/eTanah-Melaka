/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.dokumen;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.manager.TabBean;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/keputusan/upload")
public class TabKeputusanUploadActionBean extends AbleActionBean {

    TabManager tabManager;
    FasaPermohonanDAO fasaPermohonanDAO;
    PermohonanDAO permohonanDAO;
    KodCawanganDAO kodCawanganDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanLogDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    EnkuiriService ES;
    @Inject
    private FolderDokumenDAO folderDAO;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonanLog fasaPermohonanLogs;
    private Permohonan permohonan;
    private String idPermohonan;
    private TabBean tabBean;
    private String stageId;
    private boolean pendaftar = Boolean.FALSE;
    private Pengguna pengguna;
    private List<FasaPermohonan> listFasa;
    private List<FasaPermohonanLog> listFasaLog;
    private boolean finalStage = Boolean.FALSE;
    private String idWorkFlow = "";
    private String txnCode = "";
    private Permohonan p;
    private String kodKeputusan;
    private KodKeputusan kp;
    PermohonanService permohonanManager;
    FasaPermohonanService fasaPermohonanManager;
    private String DEFAULT_SUCCESS_MSG = "Kemasukan Data Berjaya.";
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(TabKeputusanUploadActionBean.class);
    private FolderDokumen folderDokumen;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();

    @Inject
    public TabKeputusanUploadActionBean(FasaPermohonanDAO fasaPermohonanDAO, PermohonanDAO permohonanDAO,
            KodCawanganDAO kodCawanganDAO, TabManager tabManager, PermohonanService permohonanManager,
            FasaPermohonanService fasaPermohonanManager) {
        this.fasaPermohonanDAO = fasaPermohonanDAO;
        this.permohonanDAO = permohonanDAO;
        this.kodCawanganDAO = kodCawanganDAO;
        this.tabManager = tabManager;
        this.permohonanManager = permohonanManager;
        this.fasaPermohonanManager = fasaPermohonanManager;
    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("tab keputusan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/tab_keputusan_upload.jsp");
    }

    public Resolution save() {
        LOG.info("------keputusan-------");

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        p = permohonanDAO.findById(idMohon);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //save in mohon_fasa
        if (permohonan != null) {

                listFasa = ES.getSenaraiFasaPermohonan(p.getIdPermohonan());

                LOG.info("listFasa.size : " + listFasa.size());
 
            InfoAudit info = new InfoAudit();
            KodKeputusan kk = null;
            if (fasaPermohonan.getKeputusan() != null) {
                kk = kodKeputusanDAO.findById(fasaPermohonan.getKeputusan().getKod());
            }
            String ulasanTmp = fasaPermohonan.getUlasan();

            fasaPermohonan = fasaPermohonanDAO.findById(fasaPermohonan.getIdFasa());

            LOG.debug("fasaPermohonan=" + fasaPermohonan);
            LOG.debug("fasaPermohonanlog=" + fasaPermohonanLogs);

            if (fasaPermohonan != null) {
                LOG.debug("UPDATE Fasa Permohonan");
                info = fasaPermohonan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                LOG.debug("Dimasukan Oleh :" + info.getDikemaskiniOleh().getIdPengguna());
            } else {
                LOG.debug("INSERT Fasa Permohonan");
                fasaPermohonan = new FasaPermohonan();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                LOG.debug("Dimasukan Oleh :" + info.getDimasukOleh().getIdPengguna());
            }
            fasaPermohonan.setInfoAudit(info);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(permohonan);
            if (kk != null) {
                fasaPermohonan.setKeputusan(kk);
            }
            fasaPermohonan.setIdAliran("N/A");
            fasaPermohonan.setUlasan(ulasanTmp);
            fasaPermohonanManager.saveOrUpdate(fasaPermohonan);





            //save in mohon_fasa_log
            if (fasaPermohonanLogs != null) {
                LOG.debug("UPDATE Fasa PermohonanLogs");
                info = fasaPermohonanLogs.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                LOG.debug("Dimasukan Oleh :" + info.getDikemaskiniOleh().getIdPengguna());
            } else {
                LOG.debug("INSERT Fasa PermohonanLogs");
                fasaPermohonanLogs = new FasaPermohonanLog();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                LOG.debug("Dimasukan Oleh :" + info.getDimasukOleh().getIdPengguna());
            }

            fasaPermohonanLogs.setFasa(fasaPermohonan);
            fasaPermohonanLogs.setInfoAudit(info);
            fasaPermohonanLogs.setCawangan(pengguna.getKodCawangan());
            fasaPermohonanLogs.setNomborRujukan(p.getIdPermohonan());
            if (kk != null) {
                fasaPermohonanLogs.setKeputusan(kk);
            }
            fasaPermohonanLogs.setUlasan(ulasanTmp);
            ES.saveFasaLog(fasaPermohonanLogs);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/tab_keputusan_upload.jsp");
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdWorkFlow() {
        return idWorkFlow;
    }

    public void setIdWorkFlow(String idWorkFlow) {
        this.idWorkFlow = idWorkFlow;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public boolean isPendaftar() {
        return pendaftar;
    }

    public void setPendaftar(boolean pendaftar) {
        this.pendaftar = pendaftar;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public TabBean getTabBean() {
        return tabBean;
    }

    public void setTabBean(TabBean tabBean) {
        this.tabBean = tabBean;
    }

    public TabManager getTabManager() {
        return tabManager;
    }

    public void setTabManager(TabManager tabManager) {
        this.tabManager = tabManager;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(String kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public FasaPermohonanLog getFasaPermohonanLogs() {
        return fasaPermohonanLogs;
    }

    public void setFasaPermohonanLogs(FasaPermohonanLog fasaPermohonanLogs) {
        this.fasaPermohonanLogs = fasaPermohonanLogs;
    }

    public List<FasaPermohonanLog> getListFasaLog() {
        return listFasaLog;
    }

    public void setListFasaLog(List<FasaPermohonanLog> listFasaLog) {
        this.listFasaLog = listFasaLog;
    }
}
