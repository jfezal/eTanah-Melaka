package etanah.view.penguatkuasaan.utiliti;

import etanah.view.kaunter.*;

import net.sourceforge.stripes.action.ForwardResolution;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.HakmilikService;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author wan.fairul
 *
 */
@UrlBinding("/penguatkuasaan/utiliti_tukar_urusan")
public class UtilitiTukarUrusan extends PermohonanKaunter {

    private Permohonan permohonan;
    private List<FasaPermohonan> fasapermohonan;
    private List<KodUrusan> kodUrusanEnf;

    public List<KodUrusan> getKodUrusanEnf() {
        return kodUrusanEnf;
    }

    public void setKodUrusanEnf(List<KodUrusan> kodUrusanEnf) {
        this.kodUrusanEnf = kodUrusanEnf;
    }
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    FasaPermohonanService fService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
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
    private DokumenDAO dokumenDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    private String kodUrusan;
    private String taskId;
    private KodUrusan kod;
    private static final Logger LOG = Logger.getLogger(UtilitiTukarUrusan.class);
    private String stage;
    private String idPermohonan;
    private String sebab;

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<FasaPermohonan> getFasapermohonan() {
        return fasapermohonan;
    }

    public void setFasapermohonan(List<FasaPermohonan> fasapermohonan) {
        this.fasapermohonan = fasapermohonan;
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

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
    }

    public Resolution showForm2() throws WorkflowException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("permohonan.idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/permohonan_tkr_urusan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    @Override
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("permohonan.idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

        }
    }

    public Resolution checkTkr() throws WorkflowException {
        if (permohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
        }
        idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
        }
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
        }else if(permohonan.getKeputusan() != null && permohonan.getTarikhKeputusan() != null){
            addSimpleError("Permohonan " + idPermohonan + " tidak dibenarkan untuk pertukaran urusan kerana keputusan bagi permohonan ini telah dikeluarkan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
        } else {
            addSimpleMessage("Pertukaran urusan bagi permohonan " + idPermohonan + " boleh diteruskan. Sila pilih urusan yang berkenaan.");
            kodUrusanEnf = enforcementService.findListOfEnfKodUrusanAktif();
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/permohonan_tkr_urusan.jsp");
        }
    }

    public Resolution changeUrusan() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            idPermohonan = permohonan.getIdPermohonan();
            List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
            if (senaraiTask.isEmpty()) {
                addSimpleError("Permohonan tidak di jumpai!");
            } else {
                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    taskId = task.getSystemAttributes().getTaskId();
                    permohonan = permohonanService.findById(idPermohonan);
                    if (StringUtils.isNotBlank(taskId)) {
                        WorkFlowService.withdrawTask(taskId);
                        LOG.info("Pembatalan Berjaya");
                    } else {
                        addSimpleError("Permohonan tidak berjaya!");
                        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
                    }
                }
            }

            
            LOG.info(kodUrusan);
            KodUrusan kodUrusanLama = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
            KodUrusan kodUrusanBaru = kodUrusanDAO.findById(kodUrusan);
            permohonan.setKodUrusan(kodUrusanBaru);
            InfoAudit iaPermohonan = permohonan.getInfoAudit();
            iaPermohonan.setTarikhKemaskini(new java.util.Date());
            iaPermohonan.setDiKemaskiniOleh(peng);
            permohonan.setInfoAudit(iaPermohonan);
            permohonan.setSebab(sebab);
            permohonanService.saveOrUpdate(permohonan);

            KodUrusan ku = kodUrusanDAO.findById(kodUrusanBaru.getKod());
            WorkFlowService.initiateTask(ku.getIdWorkflow(),
                    idPermohonan, peng.getKodCawangan().getKod(), peng.getIdPengguna(),
                    ku.getNama());
            generateAkuanPenerimaan(permohonan);
            
            notifikasi(idPermohonan, kodUrusanLama, kodUrusanBaru, peng, sebab);
            addSimpleMessage("Permohonan Tukar Urusan Telah Berjaya");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");

        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/main_tukar_urusan.jsp");
        }

    }

    public void generateAkuanPenerimaan(Permohonan p) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        InfoAudit ia = new InfoAudit();
        Pengguna pengguna = ctx.getUser();
        if (p != null) {
            // Update Akuan Penerimaan Lama - Batal
            Dokumen d1 = dokumenService.findDok(idPermohonan, "UNKN1");
            if (d1 != null) {
                /*  List<HakmilikPermohonan> senarai = p.getSenaraiHakmilik();
                 for (HakmilikPermohonan hp : senarai) {
                 if (hp.getDokumen1() != null && hp.getDokumen1().getIdDokumen() == d1.getIdDokumen()) {
                 hp.setDokumen1(null);
                 }
                 if (hp.getDokumen2() != null && hp.getDokumen2().getIdDokumen() == d1.getIdDokumen()) {
                 hp.setDokumen2(null);
                 }
                 if (hp.getDokumen3() != null && hp.getDokumen3().getIdDokumen() == d1.getIdDokumen()) {
                 hp.setDokumen3(null);
                 }
                 if (hp.getDokumen4() != null && hp.getDokumen4().getIdDokumen() == d1.getIdDokumen()) {
                 hp.setDokumen4(null);
                 }
                 if (hp.getDokumen5() != null && hp.getDokumen5().getIdDokumen() == d1.getIdDokumen()) {
                 hp.setDokumen5(null);
                 }
                 hakmilikPermohonanService.saveOrUpdateWithoutConnection(hp);
                 }
                 * */
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                d1.setInfoAudit(ia);
                d1.setTajuk("Akuan Penerimaan (Batal) -" + permohonan.getIdPermohonan());
                dokumenDAO.update(d1);
            }

            // Generate Akuan Penerimaan Baru
            String gen1 = "";
            String code1 = "";
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{p.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                gen1 = "HSLResitAkuanPenerimaan002_MLK.rdf";
            } else {
                gen1 = "HSLResitAkuanPenerimaan002.rdf";
            }
            code1 = "UNKN1";
            kd = kodDokumenDAO.findById(code1);
            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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
        doc.setTajuk("Akuan Penerimaan Baru -" + id);
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

    public void notifikasi(String idPermohonan, KodUrusan urusanLama, KodUrusan urusanBaru, Pengguna pguna, String sebab) {
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Pertukaran Urusan Permohonan - " + idPermohonan);
        n.setMesej("Permohonan Pertukaran Urusan " + urusanLama.getNama() + " Kepada Urusan " + urusanBaru.getNama() + "<br/><br/>Sebab Pertukaran :" + sebab);
        n.setCawangan(pguna.getKodCawangan());
        ArrayList<KodPeranan> listrole = new ArrayList<KodPeranan>();
        listrole.add(kodPerananDAO.findById("10"));
        listrole.add(kodPerananDAO.findById("12"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan(), listrole);
    }
}
