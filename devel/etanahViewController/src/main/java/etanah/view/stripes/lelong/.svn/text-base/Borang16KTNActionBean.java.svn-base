/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/borang16KTN")
public class Borang16KTNActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Borang16KTNActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
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
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Pihak pihak;
    private String idLelong;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihak permohonanPihak2;
    private List<PermohonanPihak> listPermohonanPihak;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPihakBerkepentingan> listHakmilikPP;
    private List<HakmilikPermohonan> listHakmilikPermohonan;
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String tarikhLelong;
    private String idPermohonan;
    private String idhakmilik;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tempat;
    private String tarikhAkhirBayaran;
    private String ejaanHarga;
    private String disabled;
    private String disabled1;
    private Integer bil;
    private BigDecimal hargaRizab;
    private BigDecimal baki;
    private BigDecimal deposit;
    private String status;
    private String negeri;
    private boolean flag = false;
    private String edit = "false";
    private String stageId;
    private Pengguna pengguna;
    Task task = null;
    private String workflowId;
    private String idFolder = "";
    private BPelService service;
    private String taskId;
    private String kodUrusan;
    private FasaPermohonan fasaMohon;
    private List<KandunganFolder> listKandunganFolder;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/Borang16H_KTN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        edit = "true";
        return new JSP("lelong/Borang16H_KTN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        service = new BPelService();
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idLelong = (String) getContext().getRequest().getSession().getAttribute("idLelong");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            if (StringUtils.isBlank(taskId)) {
                taskId = getContext().getRequest().getParameter("taskId");
            }
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                LOG.info("stageId:" + stageId);
            } else {
                stageId = getContext().getRequest().getParameter("stageId");
                LOG.info("stageId:" + stageId);
            }

            if (permohonan != null) {
                KodUrusan ku = permohonan.getKodUrusan();
                kodUrusan = ku.getKod();
                workflowId = ku.getIdWorkflow();
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            }


            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                try {
                    String[] v1 = {"permohonan"};
                    Object[] n1 = {permohonan};
                    listPermohonanPihak = permohonanPihakDAO.findByEqualCriterias(v1, n1, null);
                    LOG.info("listPermohonanPihak : " + listPermohonanPihak.size());

                    for (PermohonanPihak pp : listPermohonanPihak) {
                        permohonanPihak = pp;
                        if (pp.getJenis().getKod().equals("PG")) {
                            permohonanPihak2 = pp;
                        }
                    }

                    String[] v3 = {"permohonan"};
                    Object[] n3 = {permohonan};
                    listHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(v3, n3, null);
                    for (HakmilikPermohonan oo : listHakmilikPermohonan) {
                        hakmilikPermohonan = oo;
                        hakmilik = oo.getHakmilik();
                        idhakmilik = oo.getHakmilik().getIdHakmilik();
                    }

                    String[] v2 = {"hakmilik"};
                    Object[] n2 = {hakmilik};
                    listHakmilikPP = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(v2, n2, null);

                    for (HakmilikPihakBerkepentingan hh : listHakmilikPP) {
                        hakmilikPihakBerkepentingan = hh;
                        hakmilik = hh.getHakmilik();
                        idhakmilik = hh.getHakmilik().getIdHakmilik();
                        LOG.debug("idhakmilik:" + idhakmilik);

                    }

                    Session ss = sessionProvider.get();
                    Query qq = ss.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
                    qq.setString("idPermohonan", idPermohonan);
                    senaraiEnkuiri = qq.list();


                    LOG.debug("senaraiEnkuiri size :" + senaraiEnkuiri.size());
                    enkuiri = senaraiEnkuiri.get(senaraiEnkuiri.size() - 1);


                    Session sss = sessionProvider.get();
                    Query qqq = sss.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
                    qqq.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
                    senaraiLelongan = qqq.list();

                    if (senaraiLelongan.size() != 0) {
                        LOG.debug("senaraiLelong size :" + senaraiLelongan.size());
                        lelong = senaraiLelongan.get(senaraiLelongan.size() - 1);

                        for (Lelongan lelongan : senaraiLelongan) {
                            LOG.debug("idLelong :" + lelongan.getIdLelong());
                        }

//                        tarikhLelong = sdf.format(lelong.getTarikhLelong());
                        tempat = new String(lelong.getTempat());
                        ejaanHarga = new String(lelong.getEjaanHarga());
                        hargaRizab = lelong.getHargaRizab();
                        deposit = lelong.getDeposit();
                        status = lelong.getKodStatusLelongan().getKod();

                        if (senaraiLelongan.size() == 3) {
                            addSimpleMessage("Lelongan sebanyak 3 kali telah dilakukan.");
                            disabled = "disabled";
                        }

                        senaraiLelongan.remove(senaraiLelongan.size() - 1);

                        if (senaraiLelongan.size() == 0) {
                            lelong.setBil(1);
                        } else {
                            for (Lelongan lelongan : senaraiLelongan) {
                                lelong.setBil(lelongan.getBil() + 1);
                            }
                        }

                        tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            try {
                if (lelong.getTarikhLelong() != null) {

                    tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                    jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                    minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                    ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
                }
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }

    public Resolution simpan16KTN() throws ParseException {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = lelong.getInfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
        lelong.setTarikhLelong(sdf.parse(tarikhLelong));
        lelong.setTempat(tempat);
        enService.simpan(lelong);
        edit = "true";
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new JSP("lelong/Borang16H_KTN.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
            LOG.info("genReportFromXML");
            genReportFromXml();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void genReportFromXml() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        stageId = task.getSystemAttributes().getStage();
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            String gen = "LLGBorang16H_MLK.rdf";
            String prefix = "VDOC";
            String code = "16H";

            String[] params = null;
            String[] values = null;
            KodDokumen kd = kodDokumenDAO.findById(code);
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            params = new String[]{"p_id_mohon"};
            values = new String[]{idPermohonan};
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("::Path To save :: " + (dokumenPath + path));
            LOG.info("::Report Name ::" + gen);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                hakmilikPermohonan.setDokumen1(d);
            }
            if (kd.getKod().equals("DHKE")) {
                hakmilikPermohonan.setDokumen3(d);
            }
            if (kd.getKod().equals("DHDE")) {
                hakmilikPermohonan.setDokumen2(d);
            }
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(String idLelong) {
        this.idLelong = idLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public Integer getBil() {
        return bil;
    }

    public void setBil(Integer bil) {
        this.bil = bil;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getEjaanHarga() {
        return ejaanHarga;
    }

    public void setEjaanHarga(String ejaanHarga) {
        this.ejaanHarga = ejaanHarga;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled1() {
        return disabled1;
    }

    public void setDisabled1(String disabled1) {
        this.disabled1 = disabled1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPihak getPermohonanPihak2() {
        return permohonanPihak2;
    }

    public void setPermohonanPihak2(PermohonanPihak permohonanPihak2) {
        this.permohonanPihak2 = permohonanPihak2;
    }

    public List<PermohonanPihak> getListPermohonanPihak() {
        return listPermohonanPihak;
    }

    public void setListPermohonanPihak(List<PermohonanPihak> listPermohonanPihak) {
        this.listPermohonanPihak = listPermohonanPihak;
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

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdhakmilik() {
        return idhakmilik;
    }

    public void setIdhakmilik(String idhakmilik) {
        this.idhakmilik = idhakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPP() {
        return listHakmilikPP;
    }

    public void setListHakmilikPP(List<HakmilikPihakBerkepentingan> listHakmilikPP) {
        this.listHakmilikPP = listHakmilikPP;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }
}
