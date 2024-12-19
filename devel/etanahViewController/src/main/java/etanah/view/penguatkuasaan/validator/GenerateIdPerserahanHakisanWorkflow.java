/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.daftar.MohonHakmilikService;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
public class GenerateIdPerserahanHakisanWorkflow {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanHakisanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    StrataPtService strService;
    @Inject
    MohonHakmilikService mhservice;
    @Inject
    EnforceService enforceService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodRujukanDAO kodRujukanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    private Permohonan permohonanPenguatkuasaan;
    private String stageId;
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    private String keputusan;
    private FolderDokumen fd;
    private String idPermohonanPenguatkuasaanPertama;
    private String idPermohonanPenguatkuasaanKedua;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, String idHakmilik, List<HakmilikPermohonan> senaraiHakmilikPendaftaran, Permohonan permohonan) {

        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            int bil = 0;

            if (ku.getKod().equalsIgnoreCase("STMA")) {
                bil = 2;
            } else {
                bil = 1;
            }

            for (int i = 0; i < bil; i++) {
                String idPermohonanBaru = idPermohonanGenerator.generate(
                        conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), permohonan.getKodUrusan());

                //create id mohon baru
                permohonanPenguatkuasaan = new Permohonan();
                permohonanPenguatkuasaan.setStatus("TA");
                permohonanPenguatkuasaan.setIdPermohonan(idPermohonanBaru);
                permohonanPenguatkuasaan.setCawangan(pengguna.getKodCawangan());
                permohonanPenguatkuasaan.setKodUrusan(permohonan.getKodUrusan());
                permohonanPenguatkuasaan.setCaraPermohonan(permohonan.getCaraPermohonan());
                permohonanPenguatkuasaan.setSebab(permohonan.getSebab());
                permohonanPenguatkuasaan.setPenyerahNama(permohonan.getPenyerahNama());
                permohonanPenguatkuasaan.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
                permohonanPenguatkuasaan.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
                permohonanPenguatkuasaan.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                permohonanPenguatkuasaan.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                permohonanPenguatkuasaan.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                permohonanPenguatkuasaan.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                permohonanPenguatkuasaan.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                permohonanPenguatkuasaan.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                permohonanPenguatkuasaan.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
                permohonanPenguatkuasaan.setPenyerahEmail(permohonan.getPenyerahEmail());
                permohonanPenguatkuasaan.setPermohonanSebelum(permohonan);
                permohonanPenguatkuasaan.setInfoAudit(ia);
                permohonanPenguatkuasaan.setKeputusan(kodKeputusanDAO.findById("L"));

                permohonanDAO.save(permohonanPenguatkuasaan);

                if (permohonanPenguatkuasaan.getFolderDokumen() == null) {
                    FolderDokumen folderDok = new FolderDokumen();
                    folderDok.setTajuk(permohonanPenguatkuasaan.getIdPermohonan());
                    folderDok.setInfoAudit(ia);
                    folderDokumenDAO.save(folderDok);
                    permohonanPenguatkuasaan.setFolderDokumen(folderDok);
                }

                permohonanDAO.saveOrUpdate(permohonanPenguatkuasaan);

                // open folder for storing submitted documents
                // only one folder for all submission
                fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);

                if (i == 0) {
                    idPermohonanPenguatkuasaanPertama = permohonanPenguatkuasaan.getIdPermohonan();
                } else {
                    idPermohonanPenguatkuasaanKedua = permohonanPenguatkuasaan.getIdPermohonan();
                }
            }

            LOG.info(":::::::::: idPermohonanPenguatkuasaanPertama : " + idPermohonanPenguatkuasaanPertama);
            LOG.info(":::::::::: idPermohonanPenguatkuasaanKedua :" + idPermohonanPenguatkuasaanKedua);

            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonanPenguatkuasaan);
                p.setPenyerahNama(pengguna.getKodCawangan().getName());
//                    p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                }

                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                }
                p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());

            }
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            String id = idHakmilik;
            Hakmilik hmb = new Hakmilik();
            hmb = hakmilikDAO.findById(id);
            if (hmb == null) {
                throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
            }

            //create data for mohon hakmilik for pendaftaran
            for (HakmilikPermohonan mh : senaraiHakmilikPendaftaran) {
                BigDecimal luas = mh.getLuasTerlibat();
                KodUOM kodUOM = new KodUOM();
                kodUOM.setKod(mh.getKodUnitLuas().getKod());
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hmb);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hpa.setLuasTerlibat(luas);
                hpa.setKodUnitLuas(kodUOM);
                hakmilikPermohonanDAO.save(hpa);
            }
            LOG.info("::: id workflow integration for " + p.getKodUrusan().getKod() + " :::: " + p.getKodUrusan().getIdWorkflowIntegration());
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

            LOG.info(permohonan);

            try {

                //to get no warta from laporan tanah


                List<PermohonanRujukanLuar> listRujukanLuar;
//                    listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);



                listRujukanLuar = enforceService.findPermohonanRujukanLuar(permohonan.getIdPermohonan());
                permohonanRujLuar = new PermohonanRujukanLuar();


                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                        rujL = listRujukanLuar.get(i);
                        if (rujL.getKodDokumenRujukan() != null) {
                            if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                                permohonanRujLuar = listRujukanLuar.get(i);
                                LOG.info("No warta : " + permohonanRujLuar.getNoRujukan());
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            KodRujukan kodRujuk = kodRujukanDAO.findById("KW");
            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setCawangan(p.getCawangan());
            permohonanRujukanLuar.setPermohonan(p);
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            if (permohonanRujLuar.getNoRujukan() != null) {
                permohonanRujukanLuar.setNoRujukan(permohonanRujLuar.getNoRujukan());
            }
            permohonanRujukanLuar.setNoFail(permohonanPenguatkuasaan.getIdPermohonan());
            permohonanRujukanLuar.setKodRujukan(kodRujuk);
            if (hmb != null) {
                permohonanRujukanLuar.setHakmilik(hmb);
            }
            strService.SimpanMohonRujukLuar(permohonanRujukanLuar);



            //initiate tugasan
            for (int i = 0; i < bil; i++) {

                if (bil == 2) {
                    if (i == 0) {
                        permohonanPenguatkuasaan = permohonanDAO.findById(idPermohonanPenguatkuasaanPertama);
                    } else {
                        permohonanPenguatkuasaan = permohonanDAO.findById(idPermohonanPenguatkuasaanKedua);
                    }
                }
                try {
                    if (permohonanPenguatkuasaan.getKodUrusan().getKePTG() == 'Y') {
                        WorkFlowService.initiateTask(permohonanPenguatkuasaan.getKodUrusan().getIdWorkflow(),
                                permohonanPenguatkuasaan.getIdPermohonan(), permohonanPenguatkuasaan.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                                permohonanPenguatkuasaan.getKodUrusan().getNama());
                    } else if (permohonanPenguatkuasaan.getKodUrusan().getKePTG() == 'T') {
                        WorkFlowService.initiateTask(permohonanPenguatkuasaan.getKodUrusan().getIdWorkflow(),
                                permohonanPenguatkuasaan.getIdPermohonan(), permohonanPenguatkuasaan.getCawangan().getKod(), pengguna.getIdPengguna(),
                                permohonanPenguatkuasaan.getKodUrusan().getNama());
                    }
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            }


            LOG.info("------------- SKIP STAGE ---------------");

            if (p.getKodUrusan().getKod().equalsIgnoreCase("TMA")) {
                keputusan = "P";
            } else {
                keputusan = "S";
            }

            //assign @ set new id hakmilik for enf at column no_ruj (mohon_hakmilik) in order to get data for laporan tanah
            if (bil == 1) {
                for (HakmilikPermohonan mh : senaraiHakmilikPendaftaran) {
                    if (StringUtils.isEmpty(mh.getNomborRujukan()) && mh.getJenisHakisan().equalsIgnoreCase(keputusan)) {
                        mh.setNomborRujukan(permohonanPenguatkuasaan.getIdPermohonan());
                    }
                    enforceService.simpanhakmilikPermohonan(mh);
                }
            }

            if (bil == 2) {
                for (HakmilikPermohonan mh : senaraiHakmilikPendaftaran) {
                    for (int i = 0; i < bil; i++) {
                        if (i == 0) {
                            if (StringUtils.isEmpty(mh.getNomborRujukan()) && mh.getJenisHakisan().equalsIgnoreCase(keputusan)) {
                                mh.setNomborRujukan(idPermohonanPenguatkuasaanPertama);
                                enforceService.simpanhakmilikPermohonan(mh);
                            }
                        } else {
                            if (!StringUtils.isEmpty(mh.getNomborRujukan()) && mh.getJenisHakisan().equalsIgnoreCase(keputusan)) {
                                String j = mh.getNomborRujukan();
                                j = j + "," + idPermohonanPenguatkuasaanKedua;
                                mh.setNomborRujukan(j);
                                enforceService.simpanhakmilikPermohonan(mh);
                            }
                        }


                    }

                }
            }

            for (int i = 0; i < bil; i++) {
                if (bil == 2) {
                    if (i == 0) {
                        permohonanPenguatkuasaan = permohonanDAO.findById(idPermohonanPenguatkuasaanPertama);
                        keputusan = "S";
                    } else {
                        permohonanPenguatkuasaan = permohonanDAO.findById(idPermohonanPenguatkuasaanKedua);
                        keputusan = "P";
                    }
                }

                ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
                if (ctxOnBehalf != null) {
                    System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                    System.out.println("id mohon : " + permohonanPenguatkuasaan.getIdPermohonan());

                    List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanPenguatkuasaan.getIdPermohonan());
                    LOG.info("1) Task FOund(size)::" + l.size());
                    if (l.isEmpty()) {
                        try {
                            Thread.sleep(5000);
                            l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanPenguatkuasaan.getIdPermohonan());
                        } catch (Exception ex) {
                            LOG.error(ex);
                        }
                    }
                    LOG.info("2) Task FOund (size)::" + l.size());
                    for (Task t : l) {
                        stageId = t.getSystemAttributes().getStage();
                        taskId = t.getSystemAttributes().getTaskId();
                        WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                        LOG.debug("Claim Found Task::" + taskId);
                        LOG.debug("Keputusan::" + keputusan);

                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, keputusan);
//                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "APPROVE"); // 


                        LOG.debug("stage id ::::::::::::::::" + stageId);
                        LOG.debug("next stage ::::::::::::::::" + nextStage);
                        LOG.debug("Tugasan dihantar ke : " + nextStage);
                    }
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
            return false;
        }
        return true;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public Permohonan getPermohonanPenguatkuasaan() {
        return permohonanPenguatkuasaan;
    }

    public void setPermohonanPenguatkuasaan(Permohonan permohonanPenguatkuasaan) {
        this.permohonanPenguatkuasaan = permohonanPenguatkuasaan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public FolderDokumen getFd() {
        return fd;
    }

    public void setFd(FolderDokumen fd) {
        this.fd = fd;
    }

    public String getIdPermohonanPenguatkuasaanPertama() {
        return idPermohonanPenguatkuasaanPertama;
    }

    public void setIdPermohonanPenguatkuasaanPertama(String idPermohonanPenguatkuasaanPertama) {
        this.idPermohonanPenguatkuasaanPertama = idPermohonanPenguatkuasaanPertama;
    }

    public String getIdPermohonanPenguatkuasaanKedua() {
        return idPermohonanPenguatkuasaanKedua;
    }

    public void setIdPermohonanPenguatkuasaanKedua(String idPermohonanPenguatkuasaanKedua) {
        this.idPermohonanPenguatkuasaanKedua = idPermohonanPenguatkuasaanKedua;
    }
}
