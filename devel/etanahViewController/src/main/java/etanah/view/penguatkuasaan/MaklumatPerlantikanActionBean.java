/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;

import etanah.model.Pengguna;
import etanah.model.PermohonanNota;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusEnkuiriPenguatkuasaan;
import etanah.model.PegawaiPenyiasat;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.StreamingResolution;
import java.io.File;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/perlantikan_pegawai_penyiasat")
public class MaklumatPerlantikanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPerlantikanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiSiasatDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    EnforceService enforceService;
    private Pengguna pengguna;
    private PegawaiPenyiasat pegawaiSiasat;
    private List<Pengguna> senaraiPengguna;
    private String perananPengguna;
    private String nama;
    private String jawatan;
    private String tarikhLantik;
    private String idPermohonan;
    private String idPengguna;
    private Permohonan permohonan;
    private String noPengenalan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna selectedPengguna;
    IWorkflowContext ctx = null;
    private boolean isBerangkai = Boolean.FALSE;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private String kodNegeri;
    private long idDokumen;
    private PermohonanNota permohonanNota;
    private String stageId;
    private String keputusan;
    private boolean statusNotaExist = Boolean.TRUE;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/perlantikan_pegawai_penyiasat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");


        try {
            ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        System.out.println("taskid perlantikan: " + taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            String stageidLa = t.getSystemAttributes().getStage();
            System.out.println("stageidLa : " + stageidLa);
        }

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                logger.info("--------------stage Id BPEL ON--------------- : " + stageId);
            }
        } else {
            stageId = "keputusan_op";
            logger.info("--------------stage Id BPEL OFF--------------- : " + stageId);
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

            pegawaiSiasat = enforcementService.findPengguna(idPermohonan);

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        logger.info("--------------keputusan--------------- : " + keputusan);
                    }
                }
            }

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                senaraiPengguna = enforcementService.getSenaraiPengguna();
                System.out.println("senarai pengguna size: " + senaraiPengguna.size());
            } else {

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    //for melaka seskyen425: list of pegawai penyiasat will be based on keputusan
                    String peggunaIo = "IO";

                    //check whether selected pengguna already exist at pegawai_penyiasat or not
                    if (pegawaiSiasat != null) {
                        selectedPengguna = penggunaDAO.findById(pegawaiSiasat.getNamaJabatan()); //nama jabatan will save id pengguna
                        logger.info("selected pengguna :: " + selectedPengguna.getPerananUtama().getKod());
                    }

                    //get list for senarai pengguna based on keputusan BS (Siasatan Untuk Pendakwaan)
                    if (keputusan.equalsIgnoreCase("BS")) { //BS = Siasatan Untuk Pendakwaan
                        logger.info("--------------senarai pengguna --------------- : " + peggunaIo);
                        senaraiPengguna = enforcementService.getSenaraiPengguna(peggunaIo);
                        if (selectedPengguna != null) {
                            if (!selectedPengguna.getPerananUtama().getKod().equalsIgnoreCase(peggunaIo)) {
                                logger.info("Delete io from pegawai_penyiasat");
                                //delete pegawai penyiasat if not equal with kod peranan based on keputusan
                                enforcementService.deletePegawaiPenyiasat(pegawaiSiasat);
                            }
                        }
                    }

                } else {

                    ArrayList kumpulanBpel = new ArrayList<String>();
                    if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        kumpulanBpel.add("pptkptgkuasa"); //PPTK
                        kumpulanBpel.add("ppttptgkuasa"); //PPTT
                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        kumpulanBpel.add("pptkptdkuasa"); //PPTK
                        kumpulanBpel.add("ppttptdkuasa"); //PPTT
                    }

                    senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
//                    senaraiPengguna = enforcementService.getSenaraiPengguna();
                }

            }

            logger.info("--------------pegawaiSiasat --------------- : " + pegawaiSiasat);

            if (pegawaiSiasat != null) {
                perananPengguna = pegawaiSiasat.getNamaJabatan();
                nama = pegawaiSiasat.getNama();
                noPengenalan = pegawaiSiasat.getNoPengenalan();
                jawatan = pegawaiSiasat.getJawatan();
                idPengguna = pegawaiSiasat.getNamaJabatan();
                if (pegawaiSiasat.getTarikhLantikan() != null) {
                    tarikhLantik = sdf.format(pegawaiSiasat.getTarikhLantikan());
                }
            }

            //validation for nota/kertas minit
            permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
            if (permohonanNota != null) {
                logger.info("::: kandungan nota :" + permohonanNota.getNota());
                statusNotaExist = false;
            }

        }





    }

    public Resolution simpan() {
        logger.info("------------simpan--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            pegawaiSiasat = enforcementService.findPengguna(idPermohonan);

            InfoAudit info = pengguna.getInfoAudit();

            if (pegawaiSiasat == null) {
                pegawaiSiasat = new PegawaiPenyiasat();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
            } else {
                info = pegawaiSiasat.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
            }
            KodJenisPengenalan kodKp = kodJenisPengenalanDAO.findById("B");

            pegawaiSiasat.setNama(nama);
            pegawaiSiasat.setNoPengenalan(noPengenalan);
            pegawaiSiasat.setJawatan(jawatan);
            pegawaiSiasat.setJenisPengenalan(kodKp);
            pegawaiSiasat.setPermohonan(permohonan);
            pegawaiSiasat.setCawangan(pengguna.getKodCawangan());
            pegawaiSiasat.setNamaJabatan(idPengguna);
            pegawaiSiasat.setInfoAudit(info);

            try {
                pegawaiSiasat.setTarikhLantikan(sdf.parse(tarikhLantik));
            } catch (Exception e) {
                logger.error(e);
            }
            pegawaiSiasatDAO.saveOrUpdate(pegawaiSiasat);
            tx.commit();

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, need to generate report for Perlantikan Pegawai Penyiasat
                logger.info("------------generate report for Melaka : ENFSPPP_MLK --------------");

                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{permohonan.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                String reportName = "";

                kd = kodDokumenDAO.findById("SPPP");
                reportName = "ENFSPPP_MLK.rdf";
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                idDokumen = d.getIdDokumen();
                logger.info("-------id dokumen : --------" + idDokumen);
            }

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/perlantikan_pegawai_penyiasat.jsp").addParameter("tab", "true");



    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            logger.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        logger.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk("Surat Pelantikan Pengawai Penyiasat");
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

    public Resolution findPengguna() {
        String id = getContext().getRequest().getParameter("id");
        pengguna = penggunaDAO.findById(id);

        if (pengguna != null) {
            nama = pengguna.getNama();
            noPengenalan = pengguna.getNoPengenalan();
            jawatan = pengguna.getJawatan();
            idPengguna = pengguna.getIdPengguna();

            System.out.println("nama : " + nama);
            System.out.println("nama : " + noPengenalan);
            System.out.println("nama : " + jawatan);
            System.out.println("nama : " + idPengguna);


        }
        return new JSP("penguatkuasaan/perlantikan_pegawai_penyiasat.jsp").addParameter("tab", "true");
    }

    public Resolution agihTugasan() {

        logger.info("------------agihTugasan--------------");

        logger.info("keputusan when agih tugasan : " + keputusan);

        idPengguna = getContext().getRequest().getParameter("idPengguna");
        logger.info("perananPengguna agihTugasan::" + idPengguna);

        StringBuilder sb = new StringBuilder();
        try {


            List<Map<String, String>> list = getPermohonanWithTaskID(pengguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
                logger.info("perananPengguna ::" + idPengguna);
                selectedPengguna = penggunaDAO.findById(idPengguna);
                logger.info(pengguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + selectedPengguna.getIdPengguna() + "(" + selectedPengguna.getNama() + ")");
                if (keputusan != null) {
                    logger.info("------------Agihan tugasan based on keputusan------------");
                    Task task = WorkFlowService.reassignTask(ctx, taskID, selectedPengguna.getIdPengguna(), "user", keputusan);
                    stageId = task.getSystemAttributes().getStage();
                } else {
                    logger.info("------------Agihan tugasan not based on keputusan------------");
                    Task task = WorkFlowService.reassignTask(ctx, taskID, selectedPengguna.getIdPengguna(), "user");
                    stageId = task.getSystemAttributes().getStage();
                }

                permohonan = permohonanDAO.findById(idPermohonan);

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equals(stageId)
                            && fp.getIdPengguna().equals(selectedPengguna.getIdPengguna())) {
                        _fp = fp;
                        break;
                    }
                }

                logger.debug("cawangan " + selectedPengguna.getKodCawangan());

                if (_fp != null) {
                    au = _fp.getInfoAudit();
                    au.setTarikhKemaskini(new Date());
                    au.setDiKemaskiniOleh(pengguna);
                    _fp.setInfoAudit(au);
                } else {
                    _fp = new FasaPermohonan();
                    au.setDimasukOleh(pengguna);
                    au.setTarikhMasuk(new Date());
                    _fp.setInfoAudit(au);
                    _fp.setPermohonan(permohonan);
                    _fp.setCawangan(selectedPengguna.getKodCawangan());
                    _fp.setIdAliran(stageId);
                    _fp.setIdPengguna(selectedPengguna.getIdPengguna());
                }
                _fp.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(_fp);
            }

            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                logger.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya kepada " + selectedPengguna.getIdPengguna() + "(" + selectedPengguna.getNama() + ").");
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if (isBerangkai) {
            logger.info("Urusan Berangkai");
            List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            logger.info("taskList :: " + taskList.size());
            for (int i = 0; i < taskList.size(); i++) {
                Task impl = (Task) taskList.get(i);
                String taskId = impl.getSystemAttributes().getTaskId();
                String idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
                System.out.println("taskID::" + taskId);
                Task temp = WorkFlowService.getTask(taskId, ctx);

                if (temp.getSystemAttributes().getAcquiredBy() == null) {
                    WorkFlowService.acquireTask(taskId, ctx);
                }

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    logger.info("idPermohonan :: " + idPermohonan);
                    logger.info("p.idPermohonan :: " + p.getIdPermohonan());
                    if (p.getIdPermohonan().equals(idPermohonan)) {
                        map = new HashMap<String, String>();
                        map.put("idPermohonan", p.getIdPermohonan());
                        map.put("taskId", taskId);
                        list.add(map);
                    }
                }
            }
        } else {
            //standalone
            logger.info("Urusan tidak berangkai");
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            map = new HashMap<String, String>();
            map.put("idPermohonan", permohonan.getIdPermohonan());
            map.put("taskId", taskId);
            list.add(map);
        }
        return list;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPerananPengguna() {
        return perananPengguna;
    }

    public void setPerananPengguna(String perananPengguna) {
        this.perananPengguna = perananPengguna;
    }

    public String getTarikhLantik() {
        return tarikhLantik;
    }

    public void setTarikhLantik(String tarikhLantik) {
        this.tarikhLantik = tarikhLantik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getSelectedPengguna() {
        return selectedPengguna;
    }

    public void setSelectedPengguna(Pengguna selectedPengguna) {
        this.selectedPengguna = selectedPengguna;
    }

    public List<Permohonan> getSenaraiPermohonanBerangkai() {
        return senaraiPermohonanBerangkai;
    }

    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }
}
