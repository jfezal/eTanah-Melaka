/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.report.ReportUtil;
import etanah.service.ConsentPtdService;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/batal_permohonan")
public class BatalPermohonanActionBean extends AbleActionBean {

    private String idPerserahan;
    private Permohonan perserahan;
    private Pengguna pengguna;
    private String sebab;
    private String taskId;
    private String stage;
    private String status;
    private boolean isExist = false;
    private boolean cetak = false;
    private Dokumen d1;
    private List<PenggunaPeranan> penggunaPerananList;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private List<Dokumen> senaraiDokumen;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(BatalPermohonanActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/batal_permohonan.jsp");
    }

    @HandlesEvent("search")
    public Resolution cariPerserahan() {
        if (idPerserahan == null) {
            addSimpleError("Sila masukkan ID Permohonan");
        } else {
            try {
                if (isDebug) {
                    LOG.debug("ID Permohonan =" + idPerserahan);
                }

                perserahan = permohonanService.findById(idPerserahan);
                List senaraiTask = WorkFlowService.queryTasksByAdmin(idPerserahan);
                if (senaraiTask.isEmpty()) {

                    if (perserahan == null) {
                        addSimpleError("Permohonan tidak di jumpai.");
                    } else {
                        if (perserahan.getStatus().equals("TK")) {
                            addSimpleError("Permohonan ini telah dibatalkan.");
                        } else {
                            addSimpleError("Permohonan ini telah diselesaikan.");
                        }
                    }

                } else {
                    if (isDebug) {
                        LOG.debug("size = " + senaraiTask.size());
                    }
                    List<Task> l = WorkFlowService.queryTasksByAdmin(idPerserahan);
                    if (l != null) {
                        for (Task t : l) {
                            stage = t.getSystemAttributes().getStage();
                            taskId = t.getSystemAttributes().getTaskId();

                            if (perserahan != null) {
                                status = perserahan.getStatus();
                                if ((stage.equalsIgnoreCase("keputusan")) || ("SS".equals(status))) {
                                    isExist = false;
                                    addSimpleError("Permohonan " + idPerserahan + " tidak dibolehkan untuk pembatalan");
                                    return new ForwardResolution("consent/batal_permohonan.jsp");
                                }
                                isExist = true;

                                if ("04".equals(conf.getProperty("kodNegeri"))) {
                                    if (perserahan.getCawangan().getKod().equals("00")) {
                                        String[] kumpBPEL = {"kptmmkn", "pptgmmkn"};
                                        penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                                    } else {
                                        String[] kumpBPEL = {"pptd","tptd", "ptd", "pentadbirtanah"};
                                        penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                                    }
                                } else {
                                    String[] kumpBPEL = {"tptd", "ptd", "pentadbirtanah"};
                                    penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                                }

                            } else {
                                addSimpleError("Perserahan tidak di jumpai.");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                LOG.error(ex);
                addSimpleError(ex.getMessage());
            }
        }
        return new JSP("consent/batal_permohonan.jsp");
    }

    @HandlesEvent("save")
    public Resolution batalPerserahan() {
        try {
            if (sebab == null || mohonTandatanganDokumen == null) {
                if (sebab == null) {
                    addSimpleError("Sila Masukkan Sebab Pembatalan.");
                } else if (mohonTandatanganDokumen == null) {
                    addSimpleError("Sila Masukkan Maklumat Tandatangan.");
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (perserahan.getCawangan().getKod().equals("00")) {
                        String[] kumpBPEL = {"kptmmkn", "pptgmmkn"};
                        penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                    } else {
                        String[] kumpBPEL = {"pptd","tptd", "ptd", "pentadbirtanah"};
                        penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                    }
                } else {
                    String[] kumpBPEL = {"tptd", "ptd", "pentadbirtanah"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(perserahan.getCawangan(), kumpBPEL);
                }
                isExist = true;
                return new JSP("consent/batal_permohonan.jsp");
            }
            if (isDebug) {
                LOG.debug("taskId = " + taskId);
            }
            if (StringUtils.isNotBlank(taskId)) {
                WorkFlowService.withdrawTask(taskId);

                perserahan = permohonanService.findById(idPerserahan);
                KodKeputusan k = new KodKeputusan();
                k.setKod("TK");
                perserahan.setKeputusan(k);
                perserahan.setSebab(sebab);
                perserahan.setStatus("TK");
                InfoAudit ia = new InfoAudit();
                ia = perserahan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                permohonanService.saveOrUpdate(perserahan);

                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodDokumen kodDokumen = new KodDokumen();
                kodDokumen.setKod("SBRP");
                mohonTandatanganDokumen.setKodDokumen(kodDokumen);
                mohonTandatanganDokumen.setPermohonan(perserahan);
                mohonTandatanganDokumen.setCawangan(perserahan.getCawangan());
                mohonTandatanganDokumen.setInfoAudit(infoAudit);
                mohonTandatanganDokumen.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumen);

                String gen1 = "";
                String code1 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{perserahan.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;
                FolderDokumen fd = folderDokumenDAO.findById(perserahan.getFolderDokumen().getFolderId());

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    gen1 = "CONS_SuratBatalPerserahan_MLK.rdf";
                } else {
                    gen1 = "CONS_SuratBatalPerserahan_NS.rdf";
                }
                code1 = "SBRP";
                kd = kodDokumenDAO.findById(code1);
                d = saveOrUpdateDokumen(fd, kd, perserahan.getIdPermohonan());
                path = perserahan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                isExist = false;
                cetak = true;
                d1 = dokumenService.findDok(perserahan.getIdPermohonan(), "SBRP");
                notifikasi(perserahan, pengguna);
                addSimpleMessage("Pembatalan Berjaya");
                return new JSP("consent/batal_permohonan.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
            addSimpleError(ex.getMessage());
            return new JSP("consent/batal_permohonan.jsp");
        }

        return new JSP("consent/batal_permohonan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isBlank(idPerserahan)) {
            idPerserahan = (String) getContext().getRequest().getParameter("perserahan.idPermohonan");
            if (idPerserahan != null) {
                perserahan = permohonanService.findById(idPerserahan);
            }
        }
        if (isDebug) {
            LOG.debug("idPerserahan =" + idPerserahan);
        }
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, String[] listKod) {
        try {
            String kumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    kumpBPEL = "('";
                    kumpBPEL = kumpBPEL + pu;
                } else {
                    kumpBPEL = kumpBPEL + "','" + pu;
                }

                if (count == listKod.length) {
                    kumpBPEL = kumpBPEL + "')";
                }
                count++;
            }
            String query = "Select u.pengguna from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status ='A' order by u.pengguna.nama";
            LOG.info("QUERY->" + query);
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk("SBRP -" + id);
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public void notifikasi(Permohonan p, Pengguna pguna) {
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Pembatalan Perserahan - " + p.getIdPermohonan());
        n.setMesej("Permohonan Pembatalan Perserahan " + p.getIdPermohonan() + "<br/><br/>Sebab Pembatalan :" + p.getSebab());
        n.setCawangan(pguna.getKodCawangan());
        ArrayList<KodPeranan> listrole = new ArrayList<KodPeranan>();
        listrole.add(kodPerananDAO.findById("7"));
        listrole.add(kodPerananDAO.findById("4"));
        listrole.add(kodPerananDAO.findById("13"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan(), listrole);
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public Permohonan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(Permohonan perserahan) {
        this.perserahan = perserahan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isIsExist() {
        return isExist;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public boolean isCetak() {
        return cetak;
    }

    public void setCetak(boolean cetak) {
        this.cetak = cetak;
    }

    public Dokumen getD1() {
        return d1;
    }

    public void setD1(Dokumen d1) {
        this.d1 = d1;
    }

    public List<PenggunaPeranan> getPenggunaPerananList() {
        return penggunaPerananList;
    }

    public void setPenggunaPerananList(List<PenggunaPeranan> penggunaPerananList) {
        this.penggunaPerananList = penggunaPerananList;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }
}
