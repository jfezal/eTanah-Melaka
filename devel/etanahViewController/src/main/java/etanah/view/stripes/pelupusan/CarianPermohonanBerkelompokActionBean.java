/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorIdPermohonanKelompok;
import etanah.service.RegService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
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
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author afham
 */
@HttpCache(allow = false)
@UrlBinding("/pelupusan/permohonan_berkelompok")
public class CarianPermohonanBerkelompokActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CarianPermohonanBerkelompokActionBean.class);
    private List<PermohonanKelompok> listPermohonanBerkelompok;
    private List<PermohonanKelompok> listPermohonanBerkelompokUntukIdPermohoan;
    private List<Permohonan> senaraiPermohonan;
    private List<HakmilikPermohonan> listHakmilikPermohonan;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private List<KodSeksyen> listKodSeksyen;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    List<Pengguna> listPengguna = new ArrayList<Pengguna>();
    private PermohonanKelompok permohonanKelompok;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private HakmilikPermohonan hakmilikPermohonan;
    private DisHakmilikPermohonan disHakmilikPermohonan;
    private FasaPermohonan mohonf;
    private boolean flag = false;
    private boolean simpan = false;
    private boolean save = true;
    private boolean jana = false;
    private String idPermohonan;
    private String kodNegeri;
    private String status;
    private String idAliranTerkini;
    private String tujuan;
    private String jenisKelompok;
    private String idKelompok;
    private String sebab;
    private String noLot;
    private int sizePermohonan;
    private String stageId;
    private String nextStage;
    private String taskId;
    private String stage;
    IWorkflowContext ctxOnBehalf = null;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private TaskDebugService ts;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPermohonanKelompok idGenerator;
    @Inject
    private GeneratorIdKelompok idKelompokGenerator;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private KodLotDAO kodLotDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    RegService regService;
    @Inject
    private TaskDebugService taskDebugService;

    @DefaultHandler
    public Resolution showForm() {
        setFlag(Boolean.FALSE);
        return new JSP("/pelupusan/utiliti/carian_kelompok.jsp");
    }

    public Resolution senaraiKodSeksyen() {
        String kodBpm = (String) getContext().getRequest().getParameter("kodBPM");
        if (StringUtils.isNotBlank(kodBpm)) {
            listKodSeksyen = disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(kodBpm);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan/kemasukanEdit/partial_kodseksyen.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodGunaTanahByKatTanah() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan/kemasukanEdit/partial_kodgunatanah.jsp").addParameter("popup", "true");
    }

    public Resolution carianKelompok() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                Map m = ts.traceTask(idPermohonan);
                idAliranTerkini = (String) m.get("stage");
                if (permohonan.getKodUrusan() != null) {
                    KodUrusan kd = permohonan.getKodUrusan();
                    String kodDok = kertasDok(kd.getKod(), kodNegeri);
                    permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                    if (permohonanKertas != null) {
                        status = "Tidak Boleh Dikelompokkan";
                        setSimpan(Boolean.FALSE);
                    } else {
                        listPermohonanBerkelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
                        if (listPermohonanBerkelompok.size() > 0) {
                            status = "Telah Dikelompokkan";
                            setSimpan(Boolean.FALSE);
                        } else {
                            status = "Boleh Dikelompokkan";
                            setSimpan(Boolean.TRUE);
                        }

                    }
                    tujuan = permohonan.getSebab();
                }
                setFlag(Boolean.TRUE);
            } else {
                addSimpleError("Id Permohonan tidak dijumpai");
                setFlag(Boolean.FALSE);
                setSimpan(Boolean.FALSE);
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
            setFlag(Boolean.FALSE);
            setSimpan(Boolean.FALSE);
        }
        setSave(Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_kelompok.jsp").addParameter("popup", "true");

    }

    public Resolution editKelompok() throws Exception {
        String idMohon = getContext().getRequest().getParameter("idMohon");
        listPermohonanBerkelompokUntukIdPermohoan = new ArrayList<PermohonanKelompok>();
        senaraiPermohonan = new ArrayList<Permohonan>();
        if (StringUtils.isNotBlank(idMohon)) {
            listPermohonanBerkelompokUntukIdPermohoan = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idMohon);
            if (listPermohonanBerkelompokUntukIdPermohoan.size() > 0) {
                sizePermohonan = listPermohonanBerkelompokUntukIdPermohoan.size();
            } else {
                sizePermohonan = 0;
            }
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idMohon);
            if (permohonan.getCatatan() != null) {
                if (permohonan.getCatatan().equals("K")) {
                    Map m = ts.traceTask(idMohon);
                    String task_number = "" + m.get("taskNumber");
                    LOG.info("Check task :" + task_number);
                    if (task_number.equals("null")) {
                        jana = true;
                    } else {
                        jana = false;
                        addSimpleMessage("Permohonan telah dijana");
                    }
                }
            }
            senaraiPermohonan.add(permohonan);
        }
        setFlag(Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/carian_kelompok.jsp");

    }

    public Resolution deleteKelompok() throws Exception {
        String idMohon = getContext().getRequest().getParameter("idMohon");
        if (StringUtils.isNotBlank(idMohon)) {
            listPermohonanBerkelompokUntukIdPermohoan = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idMohon);
            if (listPermohonanBerkelompokUntukIdPermohoan.size() > 0) {
                for (PermohonanKelompok pk : listPermohonanBerkelompokUntukIdPermohoan) {
                    if (pk != null) {
                        disLaporanTanahService.getPelupusanService().deletePermohonanKelompok(pk);
                    }
                }
            }
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idMohon);
            if (permohonan != null) {
                InfoAudit ia = new InfoAudit();
                ia = permohonan.getInfoAudit();
                permohonan.setInfoAudit(ia);
                permohonan.setStatus("SD");
                disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
            }
            addSimpleError("Id Kelompok telah dihapuskan");
            senaraiPermohonan = disLaporanTanahService.getPelupusanService().findAllKelompokGRP();
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/carian_kelompok.jsp");
    }

    public Resolution findKelompok() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        senaraiPermohonan = new ArrayList<Permohonan>();
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if ((permohonan.getStatus() != null) && (permohonan.getStatus().equals("SD"))) {
                addSimpleError("Id Permohonan tidak aktif atau tidak wujud");
            } else {
                senaraiPermohonan.add(permohonan);
            }
        } else {
            senaraiPermohonan = disLaporanTanahService.getPelupusanService().findAllKelompokGRP();
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/carian_kelompok.jsp");

    }

    public Resolution tambahKelompok() {
        setSave(Boolean.FALSE);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodDaerah = pengguna.getKodCawangan().getDaerah() != null ? pengguna.getKodCawangan().getDaerah().getKod() : new String();
        String kodCawangan = pengguna.getKodCawangan() != null ? pengguna.getKodCawangan().getKod() : new String();
        if (kodDaerah.equals("08")) {
            kodDaerah = "06";
        }
        if (kodCawangan.equals("00")) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
        } else {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerahAndCawangan(kodDaerah, kodCawangan);
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_kelompok_manual.jsp").addParameter("popup", "true");
    }

    public Resolution tambahBertindih() {
        setSave(Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_kelompok.jsp").addParameter("popup", "true");
    }

    public Resolution simpanBertindih() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idMohon");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                KodUrusan ku = permohonan.getKodUrusan();
                KodCawangan caw = pengguna.getKodCawangan();
                LOG.info(ku.getNama());
                LOG.info(permohonan.getFolderDokumen());
                InfoAudit ia = new InfoAudit();
                Date now = new Date();
                String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                long idFolder = Long.parseLong(tarikh); // TODO create seq

                try {

                    // open folder for storing submitted documents
                    // only one folder for all submission
                    FolderDokumen fd = new FolderDokumen();
                    fd.setTajuk("TEST_" + tarikh); // TODO
                    fd.setInfoAudit(ia);
                    fd.setFolderId(idFolder);
                    folderDokumenDAO.save(fd);

                    Permohonan p = new Permohonan();
                    p.setStatus("TA");
                    p.setIdPermohonan(idGenerator.generate(disLaporanTanahService.getConf().getProperty("kodNegeri"), caw, ku));
                    p.setCawangan(pengguna.getKodCawangan());
                    p.setKodUrusan(ku);
                    p.setFolderDokumen(fd);
                    p.setIdWorkflow(ku.getIdWorkflow());
                    p.setKumpulanNo(0);
                    p.setBilanganPermohonan(0);
                    p.setCatatan(jenisKelompok);
                    p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                    p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                    if (permohonan.getPenyerahAlamat3() != null) {
                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                    }

                    if (permohonan.getPenyerahAlamat4() != null) {
                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                    }
                    p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                    p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                    if (permohonan.getSebab() != null) {
                        p.setSebab(permohonan.getSebab());
                    }
//                }
                    p.setInfoAudit(ia);
                    disLaporanTanahService.getPermohonanDAO().save(p);
                    LOG.info("p.getKodUrusan().getIdWorkflow() - " + p.getKodUrusan().getIdWorkflow());
                    LOG.info("p.getIdPermohonan() - " + p.getIdPermohonan());
                    LOG.info("p.getCawangan().getKod() - " + p.getCawangan().getKod());
                    LOG.info("pengguna.getIdPengguna() - " + pengguna.getIdPengguna());
                    LOG.info("p.getKodUrusan().getNama() - " + p.getKodUrusan().getNama());
                    WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());

                    idPermohonan = p.getIdPermohonan();

                    /*
                     * Permohoonan Berkelompok
                     */
                    permohonanKelompok = new PermohonanKelompok();
                    permohonanKelompok.setInfoAudit(ia);
                    permohonanKelompok.setPermohonanInduk(p);
                    permohonanKelompok.setPermohonan(permohonan);
                    permohonanKelompok.setJenisKelopok(jenisKelompok);
                    String sequenceIdKelompok = idKelompokGenerator.generate(disLaporanTanahService.getConf().getProperty("kodNegeri"), pengguna.getKodCawangan(), permohonanKelompok);
                    permohonanKelompok.setIdMohonKelompok(sequenceIdKelompok);
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKelompok(permohonanKelompok);

                    List<Dokumen> listDokumen = disLaporanTanahService.getPelupusanService().cariIdDokumen(permohonan.getIdPermohonan());
                    LOG.info("list dokumen : " + listDokumen.size());
                    for (int j = 0; j < listDokumen.size(); j++) {
                        KandunganFolder kf = new KandunganFolder();
                        kf.setInfoAudit(ia);
                        FolderDokumen folder = new FolderDokumen();
                        folder = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
                        kf.setFolder(folder);
                        Dokumen d = new Dokumen();
                        d = dokumenDAO.findById(listDokumen.get(j).getIdDokumen());
                        kf.setDokumen(d);
                        disLaporanTanahService.getPelupusanService().saveKandunganFolder(kf);

                    }

                    Map m = taskDebugService.traceTask(idPermohonan);
                    stage = (String) m.get("stage");

                    ArrayList kumpulanBpel = new ArrayList<String>();
                    kumpulanBpel.add("ptlupus");

                    listPengguna = disLaporanTanahService.getPelupusanService().findUserKumpBpel(kumpulanBpel, p.getCawangan().getKod());
//        untuk find pengguna based on kumpulan bpel
                    String idPengguna = listPengguna.get(0).getIdPengguna();
                    ctxOnBehalf = WorkFlowService.authenticate(idPengguna);

                    if (ctxOnBehalf != null) {
                        System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                        System.out.println("id mohon : " + p.getIdPermohonan());

                        List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                        LOG.info("1) Task FOund(size)::" + l.size());
                        if (l.isEmpty()) {
                            try {
                                Thread.sleep(5000);
//                                l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, p.getIdPermohonan());
                                PermohonanKelompok pk = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon1(permohonan.getIdPermohonan());
                                l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, pk.getPermohonan().getIdPermohonan());
                            } catch (Exception ex) {
                                LOG.error(ex);
                            }
                        }
                        LOG.info("2) Task FOund (size)::" + l.size());
                        for (Task t : l) {
                            stageId = t.getSystemAttributes().getStage();
                            taskId = t.getSystemAttributes().getTaskId();
                            LOG.debug("Claim Found Task::" + taskId);
                            WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "KK"); //


                            LOG.debug("stage id ::::::::::::::::" + stageId);
                            LOG.debug("next stage ::::::::::::::::" + nextStage);
                            LOG.debug("Tugasan dihantar ke : " + nextStage);
                        }
                    }

                    tx.commit();

                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    LOG.error(t);
                }
            }
        }
        setFlag(Boolean.FALSE);
        setSave(Boolean.TRUE);
        listPermohonanBerkelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_kelompok.jsp");
    }

    public Resolution simpanKelompok() {
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (!kodUrusan.equals("0")) {
            KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
            KodCawangan caw = pengguna.getKodCawangan();
            LOG.info(ku.getNama());
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            long idFolder = Long.parseLong(tarikh); // TODO create seq

            try {

                // open folder for storing submitted documents
                // only one folder for all submission
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);

                Permohonan p = new Permohonan();
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(disLaporanTanahService.getConf().getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                p.setIdWorkflow(ku.getIdWorkflow());
                p.setKumpulanNo(0);
                p.setBilanganPermohonan(0);
                p.setCatatan(jenisKelompok);
                p.setPenyerahNama(pengguna.getKodCawangan().getName());
                if (pengguna.getKodCawangan().getAlamat() != null) {
//                    p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                    p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                    if (pengguna.getKodCawangan().getAlamat().getAlamat3() != null) {
                        p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                    }

                    if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                        p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                    }
                    p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                    p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());
                }
                if (sebab != null) {
                    p.setSebab(sebab);
                }
//                }
                p.setInfoAudit(ia);
                disLaporanTanahService.getPermohonanDAO().save(p);
//                    WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
//                            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());

                idPermohonan = p.getIdPermohonan();

                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp.setInfoAudit(ia);
                hp.setPermohonan(p);
                hp.setLot(kodLotDAO.findById("2")); //Hardcode plot
                if (StringUtils.isNotBlank(noLot)) {
                    hp.setNoLot(noLot);
                }
                String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                if (StringUtils.isNotBlank(katgTanahInput)) {
                    kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                    if (kodKatTanah != null) {
                        hp.setKategoriTanahBaru(kodKatTanah);
                    }
                }
                KodKegunaanTanah kegunaanTanah = new KodKegunaanTanah();
                String gunaTanahInput = getContext().getRequest().getParameter("kodGunaTanah");
                if (StringUtils.isNotBlank(gunaTanahInput)) {
                    kegunaanTanah = (KodKegunaanTanah) disLaporanTanahService.findObject(kegunaanTanah, new String[]{gunaTanahInput}, 0);
                    if (kegunaanTanah != null) {
                        hp.setKodKegunaanTanah(kegunaanTanah);
                    }
                }

                KodSeksyen kodSeksyen = new KodSeksyen();
                String seksyenInput = getContext().getRequest().getParameter("seksyen");
                if (StringUtils.isNotBlank(seksyenInput)) {
                    kodSeksyen = (KodSeksyen) disLaporanTanahService.findObject(kodSeksyen, new String[]{seksyenInput}, 0);
                    hp.setKodSeksyen(kodSeksyen);
                }

                //if got other object
                KodBandarPekanMukim kodBPM = new KodBandarPekanMukim();
                String bpmInput = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
                if (StringUtils.isNotBlank(bpmInput)) {
                    kodBPM = (KodBandarPekanMukim) disLaporanTanahService.findObject(kodBPM, new String[]{bpmInput}, 0);
                    hp.setBandarPekanMukimBaru(kodBPM);
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hp);
                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                LOG.error(t);
            }
            setSave(Boolean.TRUE);
            listPermohonanBerkelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        } else {
            addSimpleError("Sila masukkan urusan");
            setSave(Boolean.FALSE);
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_kelompok_manual.jsp");
    }

    public Resolution tambahPermohonan() {
        setSave(Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution carianPermohonan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idKelompok = getContext().getRequest().getParameter("idKelompok");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                Map m = ts.traceTask(idPermohonan);
                idAliranTerkini = (String) m.get("stage");
                if (permohonan.getKodUrusan() != null) {
                    KodUrusan kd = permohonan.getKodUrusan();
                    String kodDok = kertasDok(kd.getKod(), kodNegeri);
                    permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                    if (permohonanKertas != null) {
                        status = "Tidak Boleh Dikelompokkan";
                        setSimpan(Boolean.FALSE);
                    } else {
                        listPermohonanBerkelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
                        if (listPermohonanBerkelompok.size() > 0) {
                            status = "Telah Dikelompokkan";
                            setSimpan(Boolean.FALSE);
                        } else {
                            listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                            listHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                            boolean check = false;
                            if (listHakmilikPermohonan.size() > 0) {
                                HakmilikPermohonan hpTemp = listHakmilikPermohonan.get(0); //Temporary
                                List<PermohonanKelompok> listTempPermohonanBerkelompok = new ArrayList<PermohonanKelompok>();
                                listTempPermohonanBerkelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idKelompok);
                                if (listTempPermohonanBerkelompok.size() > 0) {
                                    for (PermohonanKelompok pk : listTempPermohonanBerkelompok) {
                                        if (pk.getPermohonan() != null) {
                                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTemp = new ArrayList<HakmilikPermohonan>();
                                            senaraiHakmilikPermohonanTemp = pk.getPermohonan().getSenaraiHakmilik();
                                            if (senaraiHakmilikPermohonanTemp.size() > 0) {
                                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTemp) {
                                                    if (!hpTemp.getPermohonan().getKodUrusan().getKod().equals("PLPT")) {
                                                    LOG.info("Kod Kategori Tanah (Id Mohon Baru) :" + hpTemp.getKategoriTanahBaru().getKod());
                                                    LOG.info("Kod Kategori Tanah (Id Kelompok) :" + hp.getKategoriTanahBaru().getKod());
                                                    if (hpTemp.getBandarPekanMukimBaru().getKod() == hp.getBandarPekanMukimBaru().getKod()) {
                                                        if (!hpTemp.getKategoriTanahBaru().getKod().equals(hp.getKategoriTanahBaru().getKod())) {
                                                            check = true;
                                                        } else {
                                                            check = hpTemp.getKodKegunaanTanah().getKod().equals(hp.getKodKegunaanTanah().getKod()) ? false : true;
                                                        }
                                                    } else {
                                                        check = false;
                                                    }
                                                   }
                                                }
                                            }

                                        }
                                    }
                                } else {
                                    //Kes bila kelompok baru buat manual tp tiada data dlm kelompok
                                    Permohonan permohonanKelompokTemp = new Permohonan();
                                    permohonanKelompokTemp = disLaporanTanahService.getPermohonanDAO().findById(idKelompok);
                                    if (permohonanKelompokTemp != null) {
                                        if (!permohonanKelompokTemp.getSenaraiHakmilik().isEmpty()) {
                                            HakmilikPermohonan hpTempKelompok = permohonanKelompokTemp.getSenaraiHakmilik().get(0);
                                            LOG.info("ID HAKMILIK (Id Mohon Baru) :" + hpTemp.getHakmilik().getIdHakmilik());
                                            if (!hpTemp.getPermohonan().getKodUrusan().getKod().equals("PLPT")) {
                                            LOG.info("Kod Kategori Tanah (Id Mohon Baru) 3:" + hpTemp.getKategoriTanahBaru().getKod());
                                            LOG.info("Kod Kategori Tanah (Id Kelompok) 4:" + hpTempKelompok.getKategoriTanahBaru().getKod());
                                            if (hpTemp.getBandarPekanMukimBaru().getKod() == hpTempKelompok.getBandarPekanMukimBaru().getKod()) {
                                                if (!hpTemp.getKategoriTanahBaru().getKod().equals(hpTempKelompok.getKategoriTanahBaru().getKod())) {
                                                    check = true;
                                                } else {
                                                    check = hpTemp.getKodKegunaanTanah().getKod().equals(hpTempKelompok.getKodKegunaanTanah().getKod()) ? false : true;
                                                }
                                            } else {
                                                check = false;
                                            }
                                          }
                                        }
                                    }
                                }
                            }

                            if (check) {
                                status = "Tidak Boleh Dikelompokkan Kerana Bandar/Pekan/Mukim/Kategori Tanah/Kegunaan Tanah Tidak Sama";
                                setSimpan(Boolean.FALSE);
                            } else {
                                status = "Boleh Dikelompokkan";
                                setSimpan(Boolean.TRUE);
                            }

                        }

                    }
                    tujuan = permohonan.getSebab();
                }
                setFlag(Boolean.TRUE);
            } else {
                addSimpleError("Id Permohonan tidak dijumpai");
                setFlag(Boolean.FALSE);
                setSimpan(Boolean.FALSE);
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
            setFlag(Boolean.FALSE);
            setSimpan(Boolean.FALSE);
        }
        setSave(Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_permohonan.jsp").addParameter("popup", "true");

    }

    public Resolution simpanPermohonan() {
        idPermohonan = getContext().getRequest().getParameter("idMohon");
        idKelompok = getContext().getRequest().getParameter("idKelompok");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        listPermohonanBerkelompokUntukIdPermohoan = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idKelompok);
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                if (listPermohonanBerkelompokUntukIdPermohoan.size() > 0) {
                    permohonanKelompok = listPermohonanBerkelompokUntukIdPermohoan.get(0);
                    PermohonanKelompok pKTemp = new PermohonanKelompok();
                    pKTemp.setInfoAudit(ia);
                    pKTemp.setJenisKelopok(permohonanKelompok.getJenisKelopok());
                    pKTemp.setPermohonanInduk(permohonanKelompok.getPermohonanInduk());
                    pKTemp.setPermohonan(permohonan);
                    String sequenceIdKelompok = idKelompokGenerator.generate(disLaporanTanahService.getConf().getProperty("kodNegeri"), pengguna.getKodCawangan(), permohonanKelompok);
                    pKTemp.setIdMohonKelompok(sequenceIdKelompok);
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKelompok(pKTemp);
                } else {
                    Permohonan p = disLaporanTanahService.getPermohonanDAO().findById(idKelompok);
                    PermohonanKelompok pKTemp = new PermohonanKelompok();
                    pKTemp.setInfoAudit(ia);
                    pKTemp.setJenisKelopok(p.getCatatan());
                    pKTemp.setPermohonanInduk(p);
                    pKTemp.setPermohonan(permohonan);
                    String sequenceIdKelompok = idKelompokGenerator.generate(disLaporanTanahService.getConf().getProperty("kodNegeri"), pengguna.getKodCawangan(), pKTemp);
                    pKTemp.setIdMohonKelompok(sequenceIdKelompok);
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKelompok(pKTemp);
                }
            }

            PermohonanKelompok pk = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon1(idPermohonan);

            Permohonan p = disLaporanTanahService.getPermohonanDAO().findById(pk.getPermohonan().getIdPermohonan());

            Permohonan pm = disLaporanTanahService.getPermohonanDAO().findById(pk.getPermohonanInduk().getIdPermohonan());
            List<KandunganFolder> listKF = disLaporanTanahService.getPelupusanService().getListDokumen(pm.getFolderDokumen().getFolderId());
            for (int z = 0; z < listKF.size(); z++) {

                KandunganFolder kf = new KandunganFolder();
                kf.setInfoAudit(ia);
                FolderDokumen folder = new FolderDokumen();
                folder = folderDokumenDAO.findById(listKF.get(z).getFolder().getFolderId());
                kf.setFolder(folder);
                List<Dokumen> listDokumen = disLaporanTanahService.getPelupusanService().cariIdDokumen(p.getIdPermohonan());
                LOG.info("list dokumen : " + listDokumen.size());
                for (int j = 0; j < listDokumen.size(); j++) {
                    Dokumen d = new Dokumen();
                    d = dokumenDAO.findById(listDokumen.get(j).getIdDokumen());
                    kf.setDokumen(d);
                    disLaporanTanahService.getPelupusanService().saveKandunganFolder(kf);

                }
            }

        }
        setFlag(Boolean.FALSE);
        setSave(Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/tambah_permohonan.jsp");
    }

    public Resolution deletePermohonan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listIdKelompok = item.split(",");
        LOG.info("Size :" + listIdKelompok.length);
        for (int i = 0; i < listIdKelompok.length; i++) {

            if (!listIdKelompok[i].equals("T")) {
                permohonanKelompok = new PermohonanKelompok();
                permohonanKelompok = disLaporanTanahService.getPermohonanKelompokDAO().findById(listIdKelompok[i]);
                if (permohonanKelompok != null) {
                    disLaporanTanahService.getPelupusanService().deletePermohonanKelompok(permohonanKelompok);
                }
            }
        }
        addSimpleMessage("Rekod Berjaya Dihapuskan");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/carian_kelompok.jsp");
    }

    public Resolution janaPermohonan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idKelompok = getContext().getRequest().getParameter("idKelompok");
        if (idKelompok != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idKelompok);
            if (permohonan != null) {
                try {
                    WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                            permohonan.getIdPermohonan(), permohonan.getCawangan().getKod(), peng.getIdPengguna(),
                            permohonan.getKodUrusan().getNama());
                } catch (Exception ex) {
                    Logger.getLogger(CarianPermohonanBerkelompokActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        addSimpleMessage("Permohonan telah dijana");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/carian_kelompok.jsp");
    }

    /**
     *
     * Purpose to cater kod dokumen based on urusan and negeri
     */
    public String kertasDok(String urusan, String kodNegeri) {
        String kodDok = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : 0;

        switch (typeUrusan) {
            case 1: //BMBT
                if (kodNegeri.equals("04")) {
                    kodDok = "JKTD";
                } else {
                    kodDok = "JKTD";
                }
                break;
            case 2: //PJBTR
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            case 3: //PBMT
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
        }
        return kodDok;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanKelompok> getListPermohonanBerkelompok() {
        return listPermohonanBerkelompok;
    }

    public void setListPermohonanBerkelompok(List<PermohonanKelompok> listPermohonanBerkelompok) {
        this.listPermohonanBerkelompok = listPermohonanBerkelompok;
    }

    public PermohonanKelompok getPermohonanKelompok() {
        return permohonanKelompok;
    }

    public void setPermohonanKelompok(PermohonanKelompok permohonanKelompok) {
        this.permohonanKelompok = permohonanKelompok;
    }

    public List<PermohonanKelompok> getListPermohonanBerkelompokUntukIdPermohoan() {
        return listPermohonanBerkelompokUntukIdPermohoan;
    }

    public void setListPermohonanBerkelompokUntukIdPermohoan(List<PermohonanKelompok> listPermohonanBerkelompokUntukIdPermohoan) {
        this.listPermohonanBerkelompokUntukIdPermohoan = listPermohonanBerkelompokUntukIdPermohoan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdAliranTerkini() {
        return idAliranTerkini;
    }

    public void setIdAliranTerkini(String idAliranTerkini) {
        this.idAliranTerkini = idAliranTerkini;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public String getJenisKelompok() {
        return jenisKelompok;
    }

    public void setJenisKelompok(String jenisKelompok) {
        this.jenisKelompok = jenisKelompok;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public int getSizePermohonan() {
        return sizePermohonan;
    }

    public void setSizePermohonan(int sizePermohonan) {
        this.sizePermohonan = sizePermohonan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public boolean isJana() {
        return jana;
    }

    public void setJana(boolean jana) {
        this.jana = jana;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public DisHakmilikPermohonan getDisHakmilikPermohonan() {
        return disHakmilikPermohonan;
    }

    public void setDisHakmilikPermohonan(DisHakmilikPermohonan disHakmilikPermohonan) {
        this.disHakmilikPermohonan = disHakmilikPermohonan;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public List<Pengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<Pengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
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

    public FasaPermohonan getMohonf() {
        return mohonf;
    }

    public void setMohonf(FasaPermohonan mohonf) {
        this.mohonf = mohonf;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
