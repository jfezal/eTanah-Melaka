/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import etanah.view.stripes.hasil.*;
import com.google.inject.Inject;

import etanah.dao.*;
import etanah.model.*;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PihakService;
import etanah.service.StrataPtService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.initiateService.MohonHakmilikPelupusanService;
import etanah.service.RegService;
import etanah.service.PelupusanService;
import etanah.service.common.TaskDebugService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.model.gis.PelanGIS;
import etanah.model.strata.BadanPengurusan;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import java.math.BigDecimal;
import java.util.Map;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author abu.mansur
 */
public class GenerateIdPerserahanWorkflow extends BasicTabActionBean {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PihakService pihakService;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    MohonHakmilikPelupusanService mhservice;
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO;
    @Inject
    etanah.sequence.GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    private RegService regService;
    @Inject
    private KodRujukanDAO kodRujukanDAO;
    @Inject
    private KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    private KodPerintahDAO kodPerintahDAO;
    @Inject
    private KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    PermohonanSkimDAO permohonanSkimDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO berkepentinganDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PelupusanService disService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private BPelService bpelservice;
    private TanahRizabPermohonan mohonTrizab;
    private PermohonanRujukanLuar permohonanRujLuar;
    private PermohonanRujukanLuar mohonRujLuar;
    private List<PermohonanRujukanLuar> mrlListACQ;
    private List<PermohonanRujukanLuar> mrlListREG;
    private ArrayList<Hakmilik> SenaraiHmlkBaru = new ArrayList<Hakmilik>();
    private List<HakmilikPermohonan> SenaraiHmlkMhnBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<HakmilikPihakBerkepentingan> lhp = new ArrayList<HakmilikPihakBerkepentingan>();
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikACQ;
    private PermitSahLaku permitSahLaku;
    private Permit permit;
    @Inject
    private PendudukanSementaraMMKNService serviceMrl;
    PermohonanRujukanLuar rujluarREG;
    private KodRujukan kodRujukan;
    private KodStatusHakmilik kodHakmilik;
    @Inject
    PengambilanService service;
    private String code;
    Task task = null;
    @Inject
    private TaskDebugService tds;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        LOG.info("--hakmilik from strata--1" + senaraiHakmilik);
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

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("--hakmilik from strata--2" + hm.getIdHakmilik());

                Permohonan p = new Permohonan();
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));

                //ida update 26/06/2013
                if (conf.getProperty("kodNegeri").equals("05")) {
                    //permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setCawangan(hm.getCawangan());
                    p.setCawangan(hm.getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    p.setCawangan(hm.getCawangan());
                }

                // p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
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
                    p.setIdWorkflow(ku.getIdWorkflowIntegration());

                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
                LOG.info("--Intiating task--to Reg--");
                /*
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                p.getKodUrusan().getNama()); */
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), hm.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                permohonan = p;
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

    public boolean generateIdPerserahanWorkflow2(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageid) {
        LOG.info("--hakmilik from strata--1" + senaraiHakmilik);

        KodCawangan caw = pengguna.getKodCawangan();
        if (conf.getProperty("kodNegeri").equals("05")) {
            caw = senaraiHakmilik.get(0).getCawangan();
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABT-D") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABT-K") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABTKB")) {
            LOG.info("masuk sini");
            if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
                LOG.info("masuk sini utk gm, pn, hsd");
                caw = kodCawanganDAO.findById("00");
            } else {
                caw = pengguna.getKodCawangan();
            }
        }

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
            System.out.println("stageId >>>> " + stageid);
            //      permohonan.setIdStagePermohonanSebelum(stageid);
        } catch (Exception j) {
            System.out.println("Error update stage >> " + j.getMessage());
        }
        // update keputusan permohonan
        try {
            permohonan.setKeputusanOleh(pengguna);
            permohonan.setTarikhKeputusan(now);
            KodKeputusan keputusan = new KodKeputusan();
            keputusan = service.findByKodKeputusan("L");
            permohonan.setKeputusan(keputusan);
            permohonanDAO.saveOrUpdate(permohonan);
        } catch (Exception k) {
            System.out.println("Error update permohonan >> " + k.getMessage());
        }



        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            fd.setTajuk(permohonan.getIdPermohonan());
            folderDokumenDAO.save(fd);

            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setIdStagePermohonanSebelum(stageid);

            if (conf.getProperty("kodNegeri").equals("05")) {
                p.setCawangan(senaraiHakmilik.get(0).getCawangan());
            } else {
                p.setCawangan(pengguna.getKodCawangan());
            }
            p.setKodUrusan(ku);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            p.setFolderDokumen(fd);

            if (!p.getKodUrusan().getKod().equalsIgnoreCase("LMTP")) {
                KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(p.getCawangan().getKod());

                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                    caraPermohonan = pengambilanService.findKodCaraMohon("UD");
                    p.setCaraPermohonan(caraPermohonan);
                    // p.setPenyerahNama(permohonan.getPenyerahNama());
                    if (p.getKodUrusan().getKod().equals("LMTP")) {
                        p.setPenyerahNama("Unit Pelupusan");
                    } else {
                        p.setPenyerahNama("Unit Pengambilan");
                    }
                    // p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                    p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                    if (kodCawanganJabatan.getAlamat3() != null) {
                        p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                    }

                    if (kodCawanganJabatan.getAlamat4() != null) {
                        p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                    }
                    p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                    p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

                }
            } else {
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                    caraPermohonan = pengambilanService.findKodCaraMohon("UD");
                    p.setCaraPermohonan(caraPermohonan);
                    // p.setPenyerahNama(permohonan.getPenyerahNama());
//                    p.setPenyerahNama("Unit Pengambilan");
                    // p.setKodPenyerah(permohonan.getKodPenyerah());

                }
            }
            p.setInfoAudit(ia);

            permohonanDAO.save(p);

            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("--hakmilik from strata--2" + hm.getIdHakmilik());
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                List<HakmilikPermohonan> hakmilikPermohonanList;
                hakmilikPermohonanList = p.getPermohonanSebelum().getSenaraiHakmilik();

                for (HakmilikPermohonan h : hakmilikPermohonanList) {
                    if (hm.getIdHakmilik().equals(h.getHakmilik().getIdHakmilik())) {
                        hpa.setLuasTerlibat(h.getLuasTerlibat());
                        hpa.setKodUnitLuas(h.getKodUnitLuas());
                    }
                }
                hakmilikPermohonanDAO.save(hpa);

            }

            //added new coloumn Id_workflowintegration at kod-urusan table
            LOG.info("--Intiating task--to Reg--");
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
            //     permohonan = p;



            if (conf.getKodNegeri().equals("04")) {
                mrlListACQ = serviceMrl.findMRLListMLK(p.getPermohonanSebelum().getIdPermohonan());
                mrlListREG = serviceMrl.findMRLList(p.getIdPermohonan());
                senaraiHakmilikACQ = p.getPermohonanSebelum().getSenaraiHakmilik();
                int count = mrlListACQ.size() - 1;
                for (HakmilikPermohonan hp : senaraiHakmilikACQ) {
                    if (p.getKodUrusan().getKod().equals("ABTB") || p.getKodUrusan().getKod().equals("HLLA") || p.getKodUrusan().getKod().equals("HSBM") || p.getKodUrusan().getKod().equals("HKBM")) {
                        LOG.info("KOD URUSAN ABTB || KOD URUSAN HLLA, HSBM, HKBM");

                        mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                        if (mohonRujLuar == null) {
                            LOG.debug(":: mohonRujLuar is null ::");
                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ || DIS" + p.getPermohonanSebelum().getIdPermohonan());

                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("FL");
                            rujluarREG.setKodRujukan(kr);
                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                            if (p.getKodUrusan().getKod().equals("HLLA") || p.getKodUrusan().getKod().equals("HSBM") || p.getKodUrusan().getKod().equals("HKBM")) {
                                rujluarREG.setCatatan(p.getPermohonanSebelum().getSebab());
                            } else {
                                rujluarREG.setCatatan(p.getPermohonanSebelum().getPermohonanSebelum().getSebab());
                            }
                            rujluarREG.setCawangan(pengguna.getKodCawangan());
                            rujluarREG.setPermohonan(p);
                            service.saveOrUpdateMRL(rujluarREG);
                        }
                    } else {
                        if (!mrlListACQ.isEmpty()) {
//                    for (int i = 0; i < mrlListACQ.size(); i++) {
                            LOG.debug("masuk loop mrlListACQ");
                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ" + mrlListACQ.get(count).getPermohonan().getIdPermohonan());
                            LOG.debug("item ACQ" + mrlListACQ.get(count).getItem());
                            LOG.debug("tarikh lulus ACQ" + mrlListACQ.get(count).getTarikhLulus());
                            rujluarREG.setNoRujukan(mrlListACQ.get(count).getItem());
                            rujluarREG.setTarikhRujukan(mrlListACQ.get(count).getTarikhLulus());

                            if (mrlListACQ.get(count).getNoRujukan() != null) {
                                LOG.debug("no rujukan ACQ" + mrlListACQ.get(count).getNoRujukan());
                                rujluarREG.setNoFail(mrlListACQ.get(count).getNoRujukan());
                            }
                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("NF");
                            rujluarREG.setKodRujukan(kr);
                            rujluarREG.setCawangan(pengguna.getKodCawangan());
                            rujluarREG.setPermohonan(p);
                            rujluarREG.setHakmilik(hp.getHakmilik());
                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                            service.saveOrUpdateMRL(rujluarREG);
//                    }
                        }
                    }
                }
            } else {
                /// Untuk Negeri Sembilan
                if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("LSTP")) {
                    senaraiHakmilikACQ = p.getPermohonanSebelum().getSenaraiHakmilik();
                }

                if (p.getKodUrusan().getKod().equals("ABTB") || p.getKodUrusan().getKod().equals("ABT-D")) {
                    mrlListACQ = serviceMrl.findMRLListWartaAsal(p.getPermohonanSebelum().getIdPermohonan());// baca id permohonan ACQ
                    LOG.info("KOD URUSAN ABTB or ABT-D");
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    if (mohonRujLuar == null) {
                        for (HakmilikPermohonan hp : senaraiHakmilikACQ) {
                            LOG.debug(":: mohonRujLuar is null ::");
                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ" + p.getPermohonanSebelum().getIdPermohonan());

                            try {
                                rujluarREG.setNoRujukan(mrlListACQ.get(0).getItem()); // no warta asal
                                rujluarREG.setTarikhRujukan(mrlListACQ.get(0).getTarikhLulus()); //tarikh warta

                            } catch (Exception j) {
                            }
                            try {
                                rujluarREG.setCatatan(p.getPermohonanSebelum().getSebab());
                            } catch (Exception h) {
                            }
                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("FL");
                            rujluarREG.setKodRujukan(kr);
                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());

                            if (conf.getProperty("kodNegeri").equals("05")) {
                                rujluarREG.setCawangan(senaraiHakmilik.get(0).getCawangan());
                            } else {
                                rujluarREG.setCawangan(pengguna.getKodCawangan());
                            }
                            rujluarREG.setHakmilik(hp.getHakmilik());
                            rujluarREG.setPermohonan(p);
                            service.saveOrUpdateMRL(rujluarREG);
                        }
                    }
                } else if (p.getKodUrusan().getKod().equals("HLLA") || p.getKodUrusan().getKod().equals("HLLS") || p.getKodUrusan().getKod().equals("HLTE") || p.getKodUrusan().getKod().equals("HLLB")) {
                    // untuk kod ("HLLA") "HLLS" ("HLTE") ("HLLB")

                    mrlListACQ = serviceMrl.findMRLList(p.getPermohonanSebelum().getIdPermohonan());// baca id permohonan ACQ
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    if (mohonRujLuar == null) {
                        for (HakmilikPermohonan hpx : senaraiHakmilikACQ) {
                            LOG.debug("HLLA, HLLS :: mohonRujLuar is null ::");
                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ " + p.getPermohonanSebelum().getIdPermohonan());

                            rujluarREG.setNoRujukan(mrlListACQ.get(0).getItem()); // no warta asal
                            rujluarREG.setTarikhRujukan(mrlListACQ.get(0).getTarikhLulus()); //tarikh warta

                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("FL");
                            rujluarREG.setKodRujukan(kr);
                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                            try {
                                rujluarREG.setCatatan(p.getPermohonanSebelum().getSebab());
                            } catch (Exception h) {
                            }
                            if (conf.getProperty("kodNegeri").equals("05")) {
                                rujluarREG.setCawangan(senaraiHakmilik.get(0).getCawangan());
                            } else {
                                rujluarREG.setCawangan(pengguna.getKodCawangan());
                            }
                            rujluarREG.setHakmilik(hpx.getHakmilik());
                            rujluarREG.setPermohonan(p);
                            service.saveOrUpdateMRL(rujluarREG);
                        }
                    }
                } else if (p.getKodUrusan().getKod().equals("IRMB")) {
//                    mrlListACQ = serviceMrl.findMRLList(p.getPermohonanSebelum().getIdPermohonan());// baca id permohonan DIS
                    mohonTrizab = disService.findTanahRizabByIdPermohonan(p.getPermohonanSebelum().getIdPermohonan());// baca id permohonan DIS
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    if (mohonRujLuar == null) {
                        LOG.debug(":: mohonRujLuar is null ::");
                        rujluarREG = new PermohonanRujukanLuar();
                        LOG.debug("id mohon DIS " + p.getPermohonanSebelum().getIdPermohonan());

                        rujluarREG.setInfoAudit(ia);
                        rujluarREG.setNoRujukan(mohonTrizab.getNoWarta());
                        rujluarREG.setTarikhRujukan(mohonTrizab.getTarikhPengesahanWarta());
                        KodRujukan kr = new KodRujukan();
                        kr.setKod("FL");
                        rujluarREG.setKodRujukan(kr);
                        rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                        rujluarREG.setCatatan(p.getPermohonanSebelum().getSebab());
                        rujluarREG.setCawangan(pengguna.getKodCawangan());
                        rujluarREG.setPermohonan(p);
                        service.saveOrUpdateMRL(rujluarREG);
                    }
                } else if (p.getKodUrusan().getKod().equals("LMTP")) {

                    LOG.info("id semasa : " + p.getIdPermohonan());
                    LOG.info("id sebelum : " + p.getPermohonanSebelum().getIdPermohonan());
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    permit = disService.findPermitByIdPermohonan(p.getPermohonanSebelum().getIdPermohonan());
                    if (permit != null) {
                        permitSahLaku = disService.findPermitSahLakuByIdMohonIdPermit(p.getPermohonanSebelum().getIdPermohonan(), permit.getIdPermit());
                    }
                    if (mohonRujLuar == null) {
                        LOG.debug(":: mohonRujLuar is null ::");
                        rujluarREG = new PermohonanRujukanLuar();
                        LOG.debug("id mohon DIS " + p.getPermohonanSebelum().getIdPermohonan());

                        rujluarREG.setInfoAudit(ia);
                        KodRujukan kr = new KodRujukan();
                        kr.setKod("FL");
                        rujluarREG.setKodRujukan(kr);
                        rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                        rujluarREG.setCawangan(pengguna.getKodCawangan());
                        rujluarREG.setPermohonan(p);
                        rujluarREG.setTarikhKuatkuasa(permitSahLaku.getTarikhPermitMula());
                        rujluarREG.setTarikhTamat(permitSahLaku.getTarikhPermitTamat());
                        service.saveOrUpdateMRL(rujluarREG);
                    }
                }

//                if (p.getKodUrusan().getKod().equals("ABT-D")) {//|| p.getKodUrusan().getKod().equals("ABT-K") || p.getKodUrusan().getKod().equals("ABTKB")
//
//                    mrlListACQ = serviceMrl.findMRLList(p.getPermohonanSebelum().getIdPermohonan());
//                    mrlListREG = serviceMrl.findMRLList(p.getIdPermohonan());
//                    if (!mrlListACQ.isEmpty()) {
//                        for (int i = 0; i < mrlListACQ.size(); i++) {
//                            LOG.debug("masuk loop mrlListACQ");
//                            rujluarREG = new PermohonanRujukanLuar();
//                            LOG.debug("id mohon ACQ" + mrlListACQ.get(i).getPermohonan().getIdPermohonan());
//                            LOG.debug("item ACQ" + mrlListACQ.get(i).getItem());
//                            LOG.debug("tarikh lulus ACQ" + mrlListACQ.get(i).getTarikhLulus());
//                            rujluarREG.setNoRujukan(mrlListACQ.get(i).getItem());
//                            rujluarREG.setTarikhRujukan(mrlListACQ.get(i).getTarikhLulus());
//
//                            if (mrlListACQ.get(i).getNoRujukan() != null) {
//                                LOG.debug("no rujukan ACQ" + mrlListACQ.get(i).getNoRujukan());
//                                rujluarREG.setNoFail(mrlListACQ.get(i).getNoRujukan());
//                            }
//                            rujluarREG.setInfoAudit(ia);
//                            KodRujukan kr = new KodRujukan();
//                            kr.setKod("NF");
//                            rujluarREG.setKodRujukan(kr);
//                            rujluarREG.setCawangan(pengguna.getKodCawangan());
//                            if (conf.getProperty("kodNegeri").equals("05")) {
//                                rujluarREG.setCawangan(senaraiHakmilik.get(0).getCawangan());
//                            } else {
//                                rujluarREG.setCawangan(pengguna.getKodCawangan());
//                            }
//                            rujluarREG.setPermohonan(p);
//                            rujluarREG.setHakmilik(p.getSenaraiHakmilik().get(i).getHakmilik());
//                            service.saveOrUpdateMRL(rujluarREG);
//                        }
//                    }
//                } else {
//                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
//                    senaraiHakmilikACQ = p.getPermohonanSebelum().getSenaraiHakmilik();
//                    if (mohonRujLuar == null) {
//                        System.out.println("masuk mohon ruj luar");
//                        for (HakmilikPermohonan hp : senaraiHakmilikACQ) {
//                            LOG.debug(":: mohonRujLuar is null ::");
//                            LOG.debug(":: hakmilik  :: >>> " + hp.getHakmilik().getNoHakmilik());
//                            rujluarREG = new PermohonanRujukanLuar();
//                            LOG.debug("id mohon ACQ" + p.getPermohonanSebelum().getIdPermohonan());
//
//                            rujluarREG.setInfoAudit(ia);
//                            KodRujukan kr = new KodRujukan();
//                            kr.setKod("FL");
//                            rujluarREG.setKodRujukan(kr);
//                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
//                            rujluarREG.setCawangan(pengguna.getKodCawangan());
//                            rujluarREG.setCatatan(p.getPermohonanSebelum().getSebab());
//                            rujluarREG.setHakmilik(hp.getHakmilik());
//                            rujluarREG.setPermohonan(p);
//                            service.saveOrUpdateMRL(rujluarREG);
//                        }
//                    }
//                }

                if (p.getKodUrusan().getKod().equals("ABT-K") || p.getKodUrusan().getKod().equals("ABTKB")) {

                    mrlListACQ = serviceMrl.findMRLListWartaAsal(p.getPermohonanSebelum().getIdPermohonan());

                    LOG.info("KOD URUSAN ABT-K  ABTKB ");
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    if (mohonRujLuar == null) {
                        for (HakmilikPermohonan hp : senaraiHakmilikACQ) {
                            LOG.debug(":: mohonRujLuar is null ::");
                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ" + p.getPermohonanSebelum().getIdPermohonan());

                            rujluarREG.setNoRujukan(mrlListACQ.get(0).getItem()); // no warta asal
                            rujluarREG.setTarikhRujukan(mrlListACQ.get(0).getTarikhLulus()); //tarikh warta
                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("FL");
                            rujluarREG.setKodRujukan(kr);
                            rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                            try {
                                rujluarREG.setCatatan(p.getPermohonanSebelum().getPermohonanSebelum().getSebab());
                            } catch (Exception h) {
                            }
                            if (conf.getProperty("kodNegeri").equals("05")) {
                                rujluarREG.setCawangan(senaraiHakmilik.get(0).getCawangan());
                            } else {
                                rujluarREG.setCawangan(pengguna.getKodCawangan());
                            }
                            rujluarREG.setHakmilik(hp.getHakmilik());
                            rujluarREG.setPermohonan(p);
                            service.saveOrUpdateMRL(rujluarREG);
                        }
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

    public boolean generateIdPerserahanWorkflowHKABS(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageId) {
        LOG.info("--hakmilik from HKABS >>> " + senaraiHakmilik.size());
        KodCawangan caw = pengguna.getKodCawangan();
        if (conf.getProperty("kodNegeri").equals("05")) {
            caw = senaraiHakmilik.get(0).getCawangan();
        }
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

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            fd.setTajuk(permohonan.getIdPermohonan());
            folderDokumenDAO.save(fd);


            Hakmilik hkAsal = new Hakmilik();
            HakmilikPermohonan hakmilikPermohonanBr = new HakmilikPermohonan();
            for (Hakmilik hakmilikAsal : senaraiHakmilik) {

                hkAsal = service.findByIdHakmilik(hakmilikAsal.getIdHakmilik());
//                error = janaHakmilikBaru(p, pengguna, permohonan, "", hakmilik);
//                 hakmilikLama.add(hakmilik);

                Hakmilik hakmilikBaru = new Hakmilik();
                hakmilikBaru.setBadanPengurusan(hkAsal.getBadanPengurusan());
                hakmilikBaru.setBandarPekanMukim(hkAsal.getBandarPekanMukim());
                hakmilikBaru.setCawangan(hkAsal.getCawangan());
                hakmilikBaru.setCukai(hkAsal.getCukai());
                hakmilikBaru.setCukaiSebenar(hkAsal.getCukaiSebenar());
                hakmilikBaru.setNoVersiDhde(0);
                hakmilikBaru.setNoVersiDhke(0);
                hakmilikBaru.setDaerah(hkAsal.getDaerah());
                hakmilikBaru.setDhde(hkAsal.getDhde());
                hakmilikBaru.setDhke(hkAsal.getDhke());
                hakmilikBaru.setGsaKawasan(hkAsal.getGsaKawasan());
                hakmilikBaru.setGsaNoWarta(hkAsal.getGsaNoWarta());
                hakmilikBaru.setGsaTarikhWarta(hkAsal.getGsaTarikhWarta());
                hakmilikBaru.setInfoAudit(ia);
                hakmilikBaru.setJumlahUnitSyer(hkAsal.getJumlahUnitSyer());
                hakmilikBaru.setKategoriTanah(hkAsal.getKategoriTanah());
                hakmilikBaru.setKegunaanTanah(hkAsal.getKegunaanTanah());
                hakmilikBaru.setKodHakmilik(hkAsal.getKodHakmilik());
                hakmilikBaru.setKodKadarCukai(hkAsal.getKodKadarCukai());
                hakmilikBaru.setKodKategoriBangunan(hkAsal.getKodKategoriBangunan());
                hakmilikBaru.setKodKegunaanBangunan(hkAsal.getKodKegunaanBangunan());
                String kodDaftar = "D";
                kodHakmilik = service.findByKodStatusHakmilik(kodDaftar);
                hakmilikBaru.setKodStatusHakmilik(kodHakmilik);
                hakmilikBaru.setKodTanah(hkAsal.getKodTanah());
                hakmilikBaru.setKodUnitLuas(hkAsal.getKodUnitLuas());
                hakmilikBaru.setKumpulan(hkAsal.getKumpulan());
                hakmilikBaru.setLokasi(hkAsal.getLokasi());
                hakmilikBaru.setLot(hkAsal.getLot());
                hakmilikBaru.setLotBumiputera(hkAsal.getLotBumiputera());
                hakmilikPermohonan = service.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hkAsal.getIdHakmilik());
                hakmilikPermohonanBr = service.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hkAsal.getIdHakmilik());
                hakmilikBaru.setLuas(hakmilikPermohonan.getLuasPelanB1());
                hakmilikBaru.setMaklumatAtasTanah(hkAsal.getMaklumatAtasTanah());
                hakmilikBaru.setMilikPersekutuan(hkAsal.getMilikPersekutuan());
                hakmilikBaru.setNoBangunan(hkAsal.getNoBangunan());
                hakmilikBaru.setNoBukuDaftarStrata(hkAsal.getNoBukuDaftarStrata());
                hakmilikBaru.setNoFail(hkAsal.getNoFail());
                hakmilikBaru.setNoLitho(hkAsal.getNoLitho());
                hakmilikBaru.setNoPelan(hkAsal.getNoPelan());
                hakmilikBaru.setNoPetak(hkAsal.getNoPetak());
                hakmilikBaru.setNoPu(hkAsal.getNoPu());
                hakmilikBaru.setNoSkim(hkAsal.getNoSkim());
                hakmilikBaru.setNoTingkat(hkAsal.getNoTingkat());
                hakmilikBaru.setPbt(hkAsal.getPbt());
                hakmilikBaru.setPbtKawasan(hkAsal.getPbtKawasan());
                hakmilikBaru.setPbtNoWarta(hkAsal.getPbtNoWarta());
                hakmilikBaru.setPbtTarikhWarta(hkAsal.getPbtTarikhWarta());
                hakmilikBaru.setPegangan(hkAsal.getPegangan());
                hakmilikBaru.setPelan(hkAsal.getPelan());
                hakmilikBaru.setRizab(hkAsal.getRizab());
                hakmilikBaru.setRizabKawasan(hkAsal.getRizabKawasan());
                hakmilikBaru.setRizabNoWarta(hkAsal.getRizabKawasan());
                hakmilikBaru.setRizabTarikhWarta(hkAsal.getRizabTarikhWarta());
                hakmilikBaru.setSekatanKepentingan(hkAsal.getSekatanKepentingan());
                hakmilikBaru.setSeksyen(hkAsal.getSeksyen());
                hakmilikBaru.setSyaratNyata(hkAsal.getSyaratNyata());
                hakmilikBaru.setUPI(hkAsal.getUPI());
                hakmilikBaru.setTarikhDaftar(now);
                hakmilikBaru.setTarikhDaftarAsal(hkAsal.getTarikhDaftarAsal());
                hakmilikBaru.setUnitSyer(hkAsal.getUnitSyer());
                /// simpan nolot baru
//                 hakmilikBaru.setNoLot(hkAsal.getNoLot());
                hakmilikBaru.setNoLot(hakmilikPermohonan.getLokaliti());

                String kodNegeri = conf.getProperty("kodNegeri");
                String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);

                SenaraiHmlkBaru.add(hakmilikBaru);
                SenaraiHmlkMhnBaru.add(hakmilikPermohonanBr);
            }


            for (Hakmilik hm : SenaraiHmlkBaru) {

                //  save id MH ikut bil hakmilik baru
                Permohonan p = new Permohonan();
                p.setStatus("TA");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), hm.getCawangan(), ku));
                    p.setCawangan(hm.getCawangan());
                } else {
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), ku));
                    p.setCawangan(pengguna.getKodCawangan());
                }

                p.setKodUrusan(ku);
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                p.setFolderDokumen(fd);
                p.setIdStagePermohonanSebelum(stageId);
                KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(p.getCawangan().getKod());
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                    caraPermohonan = pengambilanService.findKodCaraMohon("UD");
                    p.setCaraPermohonan(caraPermohonan);
                    //p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setPenyerahNama("Unit Pengambilan");
                    //  p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                    p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                    if (kodCawanganJabatan.getAlamat3() != null) {
                        p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                    }

                    if (kodCawanganJabatan.getAlamat4() != null) {
                        p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                    }
                    p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                    p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

                }
                p.setInfoAudit(ia);

                try {
                    System.out.println("stageId >>>> " + stageId);
                    permohonan.setIdStagePermohonanSebelum(stageId);
                    permohonanDAO.saveOrUpdate(permohonan);
                } catch (Exception j) {
                    System.out.println("Error update stage >> " + j.getMessage());
                }

                permohonanDAO.save(p);

                ////////////////  simpan hakmilik ////////
                hakmilikDAO.save(hm);
                LOG.info("--hakmilik baru >>>> " + hm.getNoHakmilik());
                String id = hm.getIdHakmilik();
//                hm = hakmilikDAO.findById(id);
                if (hm.getNoFail() == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak ada no Fail.Sila masukkan no Fail pada id hakmilik");
                }
                LOG.info("hm >> " + hm.getNoHakmilik());
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                List<HakmilikPermohonan> hakmilikPermohonanList;
                hakmilikPermohonanList = p.getPermohonanSebelum().getSenaraiHakmilik();
                //  LOG.info("saiz SenaraiHmlkMhnBaru >>> " + SenaraiHmlkMhnBaru.size());
                try {
                    for (HakmilikPermohonan h : SenaraiHmlkMhnBaru) {
                        // hakmilikPermohonan = service.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), h.getHakmilik().getIdHakmilik());
                        if (hm.getNoFail().equalsIgnoreCase(h.getHakmilik().getNoFail())) {
                            hpa.setLuasTerlibat(h.getLuasTerlibat());
                            hpa.setKodUnitLuas(h.getKodUnitLuas());
                        }
                    }
                    hakmilikPermohonanDAO.save(hpa);
                } catch (Exception jp) {
                }
                /////// simpan pada mohon hakmilik sebelum 
                try {
                    for (HakmilikPermohonan hkb : SenaraiHmlkMhnBaru) {
//                    if (hm.getPelan().getIdDokumen() == (hkb.getHakmilik().getPelan().getIdDokumen())) {
                        if (hm.getNoFail().equalsIgnoreCase(hkb.getHakmilik().getNoFail())) {
                            try {
                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hkb.getHakmilik().getIdHakmilik());
                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                hsp.setHakmilikPermohonan(hpa);
                                hsp.setPermohonan(p);
                                if (conf.getProperty("kodNegeri").equals("05")) {
                                    hsp.setCawangan(hm.getCawangan());
                                } else {
                                    hsp.setCawangan(pengguna.getKodCawangan());
                                }

                                hsp.setHakmilik(hm);
                                hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                hsp.setInfoAudit(ia);
                                hakmilikSblmPermohonanDAO.save(hsp);
                                LOG.debug("No id mohon hakmilik sebelum " + hsp.getIdHakmilikSebelumPermohonan());
                            } catch (Exception jk) {
                                LOG.debug("Error " + jk.getMessage());
                            }
                        }
                    }
                } catch (Exception jc) {
                }

                /////// simpan pada mohon hakmilik asal 
                try {
                    for (HakmilikPermohonan hkb : SenaraiHmlkMhnBaru) {
//                    if (hm.getPelan().getIdDokumen() == (hkb.getHakmilik().getPelan().getIdDokumen())) {
                        if (hm.getNoFail().equalsIgnoreCase(hkb.getHakmilik().getNoFail())) {
                            try {
                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hkb.getHakmilik().getIdHakmilik());
                                HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                hap.setHakmilik(hm);
                                hap.setHakmilikPermohonan(hpa);
                                hap.setInfoAudit(ia);
                                hakmilikAsalPermohonanDAO.save(hap);
                            } catch (Exception jk) {
                                LOG.debug("Error hakmilik Asal >>>" + jk.getMessage());
                            }
                        }
                    }
                } catch (Exception hj) {
                }

                try {
                    for (HakmilikPermohonan hkc : SenaraiHmlkMhnBaru) {
//                    if (hm.getPelan().getIdDokumen() == (hkc.getHakmilik().getPelan().getIdDokumen())) {
                        if (hm.getNoFail().equalsIgnoreCase(hkc.getHakmilik().getNoFail())) {
                            // lhp = new ArrayList<HakmilikPihakBerkepentingan>();
                            try {
                                /*INSERT INTO HAKMILIK PIHAK*/
                                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hkc.getHakmilik());
                                LOG.debug("---Hakmilik Pihak---" + senaraiHakmilikPihak.size());
                                int i = 0;
                                for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                                    i++;
                                    LOG.debug("hpk " + hpk);
                                    //  if (hpk == null || hpk.getAktif() == 'T') {
                                    //     continue;
                                    // }
                                    LOG.debug("---Saving in Hakmilik Pihak");
                                    LOG.debug("---creating new Hakmilik Pihak object---");
                                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                                    hpb.setHakmilik(hm);
                                    hpb.setCawangan(hm.getCawangan());
                                    hpb.setPihakCawangan(hpk.getPihakCawangan());
                                    hpb.setJenis(hpk.getJenis());
                                    hpb.setSyerPembilang(hpk.getSyerPembilang());
                                    hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                                    hpb.setJumlahPembilang(hpk.getSyerPembilang());
                                    hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                    hpb.setPerserahan(hpk.getPerserahan());
                                    hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                                    hpb.setKaveatAktif(hpk.getKaveatAktif());
                                    hpb.setAktif(hpk.getAktif());
                                    hpb.setPihak(hpk.getPihak());
                                    hpb.setInfoAudit(ia);

                                    hpb.setNama(hpk.getNama());
                                    hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                    hpb.setNoPengenalan(hpb.getNoPengenalan());
                                    hpb.setAlamat1(hpk.getAlamat1());
                                    hpb.setAlamat2(hpk.getAlamat2());
                                    hpb.setAlamat3(hpk.getAlamat3());
                                    hpb.setAlamat4(hpk.getAlamat4());
                                    hpb.setPoskod(hpk.getPoskod());
                                    hpb.setNegeri(hpk.getNegeri());
                                    hpb.setWargaNegara(hpk.getWargaNegara());
                                    hpb.setAlamatSurat(hpk.getAlamatSurat());
                                    hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
                                    // berkepentinganDAO.save(hpb);
                                    lhp.add(hpb);
                                }

                            } catch (Exception jh) {
                                LOG.debug("Error insert into hakmilik pihak >>>>>>>>>>>>> " + jh);
                            }
                        }
                    }
                } catch (Exception hh) {
                }
                ///////// generate id MH
                LOG.info("--Intiating task--to Reg--");
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                //permohonan = p;

                if (p.getKodUrusan().getKod().equals("HKABS")) {
                    //  mrlListACQ = serviceMrl.findMRLList(p.getPermohonanSebelum().getIdPermohonan());
                    mrlListACQ = serviceMrl.findMRLListWartaAsal(p.getPermohonanSebelum().getIdPermohonan());
                    mrlListREG = serviceMrl.findMRLList(p.getIdPermohonan());
                    if (!mrlListACQ.isEmpty()) {
                        for (int i = 0; i < mrlListACQ.size(); i++) {
                            LOG.debug("permohonan baru >> " + p.getIdPermohonan());
                            LOG.debug("masuk loop mrlListACQ");

                            rujluarREG = new PermohonanRujukanLuar();
                            LOG.debug("id mohon ACQ >> " + mrlListACQ.get(i).getPermohonan().getIdPermohonan());
                            LOG.debug("item ACQ >> " + mrlListACQ.get(i).getItem());
                            LOG.debug("tarikh lulus ACQ >> " + mrlListACQ.get(i).getTarikhLulus());
                            rujluarREG.setNoRujukan(mrlListACQ.get(i).getItem());
                            rujluarREG.setTarikhRujukan(mrlListACQ.get(i).getTarikhLulus());

                            // if (mrlListACQ.get(i).getNoRujukan() != null) {
                            LOG.debug("no rujukan ACQ" + mrlListACQ.get(i).getNoRujukan());
                            rujluarREG.setNoFail(SenaraiHmlkBaru.get(i).getNoFail());//hm.getNoFail());
                            // }
                            rujluarREG.setInfoAudit(ia);
                            KodRujukan kr = new KodRujukan();
                            kr.setKod("NF");
                            rujluarREG.setKodRujukan(kr);
                            if (conf.getProperty("kodNegeri").equals("05")) {
                                rujluarREG.setCawangan(hm.getCawangan());
                            } else {
                                rujluarREG.setCawangan(pengguna.getKodCawangan());
                            }

                            rujluarREG.setPermohonan(p);
//                            LOG.debug("senarai hakmilik baru >> " + SenaraiHmlkBaru.get(i).getIdHakmilik());
                            rujluarREG.setHakmilik(SenaraiHmlkBaru.get(i));//) hm;
                            service.saveOrUpdateMRL(rujluarREG);
                        }
                    }

                } else {
                    mohonRujLuar = serviceMrl.findMRL(p.getIdPermohonan());
                    if (mohonRujLuar == null) {
                        LOG.debug(":: mohonRujLuar is null ::");
                        rujluarREG = new PermohonanRujukanLuar();
                        LOG.debug("id mohon ACQ" + p.getPermohonanSebelum().getIdPermohonan());

                        rujluarREG.setInfoAudit(ia);
                        KodRujukan kr = new KodRujukan();
                        kr.setKod("FL");
                        rujluarREG.setKodRujukan(kr);
                        rujluarREG.setNoFail(p.getPermohonanSebelum().getIdPermohonan());
                        rujluarREG.setCawangan(pengguna.getKodCawangan());
                        rujluarREG.setPermohonan(p);
                        service.saveOrUpdateMRL(rujluarREG);
                    }
                }
            }

            // batalkan hakmilik asal
            // Hakmilik asal dibatalkan selepas hakmilik baru didaftarkan oleh pendaftaran. Pendaftran yang akan batalkan hakmilik asal.
            String codeB = "B";
//            for (Hakmilik hakmilikAsal : senaraiHakmilik) {
//                hakmilikAsal.setTarikhBatal(now);
//                kodHakmilik = service.findByKodStatusHakmilik(codeB);
//                hakmilikAsal.setKodStatusHakmilik(kodHakmilik);
//                hakmilikDAO.saveOrUpdate(hakmilikAsal);
//
//            }

            LOG.info("saiz semua hak milik pihak penting >>>>>> " + lhp.size());
            for (HakmilikPihakBerkepentingan berkepentingan : lhp) {
                berkepentinganDAO.save(berkepentingan);
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
            LOG.error("Error Roll back >>>>>> " + t);
            return false;
        }
        return true;
    }

    public Permohonan genWorkflowIdPerserahan(
            KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        Permohonan permohonanBaru = new Permohonan();
        try {
            generateIdPerserahanWorkflow(ku, pengguna, senaraiHakmilik, permohonan);
            permohonanBaru =
                    permohonan;
            LOG.debug("idPermohonan :" + permohonanBaru.getIdPermohonan());
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return permohonanBaru;
    }

    public Permohonan generateIdPerserahanWorkflowPNSS(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
//            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq
        Permohonan p = new Permohonan();
        try {
            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            for (Hakmilik hm : senaraiHakmilik) {
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                LOG.info(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
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
                    KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                    caraPermohonan = kodCaraPermohonanDAO.findById("UD");
                    p.setCaraPermohonan(caraPermohonan);
                    // p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setPenyerahNama("Unit Strata");
                    p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());
                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                permohonan = p;
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
//            return false;
        }
        return p;
    }

    public Permohonan generateIdPerserahanWorkflowPHPP(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        LOG.info("--hakmiliks from strata--1--:" + senaraiHakmilik);
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
//            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq
        Permohonan p = new Permohonan();
        try {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);
            p.setStatus("TA");
            if (conf.getProperty("kodNegeri").equals("05")) {
                KodCawangan caw2 = new KodCawangan();
                for (Hakmilik hm : senaraiHakmilik) {
                    caw2 = hm.getCawangan();
                }
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw2, ku));
                p.setCawangan(caw2);
            }
            if (conf.getProperty("kodNegeri").equals("04")) {
                KodCawangan caw1 = new KodCawangan();
                for (Hakmilik hm : senaraiHakmilik) {
                    caw1 = hm.getCawangan();
                }
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw1, ku));
                p.setCawangan(caw1);
            }

            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
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
                p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());
                //added by murali: saving stageIdSblm
                Map m = tds.traceTask(permohonan.getIdPermohonan());
                String stageId = (String) m.get("stage");
                if (stageId != null) {
                    p.setIdStagePermohonanSebelum(stageId);
                }
            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("--hakmilik from strata--2--:" + hm.getIdHakmilik());
                code = hm.getCawangan().getKod();
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                LOG.info("--permohonan--" + p);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);

                LOG.info("--Intiating task--to Reg--");
                LOG.info("--permohonan--" + p);
            }
            /*
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
            p.getKodUrusan().getNama());
             */
            LOG.info("--hakmilik code cawangan--:" + code);
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), code, pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

            permohonan = p;
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
//            return false;
        }
        return p;
    }

    //add by ida 231013 for n9
    public Permohonan generateIdPerserahanWorkflowN9(String stageId, KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        LOG.info("--hakmiliks from strata--1--:" + senaraiHakmilik);
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
//            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq
        Permohonan p = new Permohonan();
        try {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);
            p.setStatus("TA");
            if (conf.getProperty("kodNegeri").equals("05")) {
                KodCawangan caw2 = new KodCawangan();
                for (Hakmilik hm : senaraiHakmilik) {
                    caw2 = hm.getCawangan();
                }
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw2, ku));
                p.setCawangan(caw2);
            }

            // insert StageId in table mohon

            LOG.info("stage Id: " + stageId);
            p.setIdStagePermohonanSebelum(stageId);

            // edited by ida 231013

            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
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
                KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                caraPermohonan = kodCaraPermohonanDAO.findById("UD");
                p.setCaraPermohonan(caraPermohonan);
                // p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setPenyerahNama("Unit Strata");
                p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());

            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("--hakmilik from strata--2--:" + hm.getIdHakmilik());
                code = hm.getCawangan().getKod();
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                LOG.info("--permohonan--" + p);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);

                LOG.info("--Intiating task--to Reg--");
                LOG.info("--permohonan--" + p);
            }
            /*
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
            p.getKodUrusan().getNama());
             */
            LOG.info("--hakmilik code cawangan--:" + code);
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), code, pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

            permohonan = p;
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
//            return false;
        }
        return p;
    }

    public Permohonan generateIdPerserahanWorkflow1(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        LOG.info("--hakmilik from strata--1" + senaraiHakmilik);
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            //return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Permohonan p = new Permohonan();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("--hakmilik from strata--2" + hm.getIdHakmilik());

                //Permohonan p = new Permohonan();
                p.setStatus("TA");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    KodCawangan caw3 = hm.getCawangan();
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw3, ku));
                    //p.setCawangan(pengguna.getKodCawangan());
                    p.setCawangan(caw3);
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    KodCawangan caw1 = hm.getCawangan();
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw1, ku));
                    p.setCawangan(caw1);
                }
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
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
                    p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());
                    //added by murali: saving stageIdSblm
                    Map m = tds.traceTask(permohonan.getIdPermohonan());
                    String stageId = (String) m.get("stage");
                    if (stageId != null) {
                        p.setIdStagePermohonanSebelum(stageId);
                    }
                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);

                LOG.info("--Intiating task--to Reg--");
                /*
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());
                 */
                LOG.info("--hm.getCawangan().getKod()--:" + hm.getCawangan().getKod());
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), hm.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());

                permohonan = p;
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
            //return false;
        }
        return p;
    }

    public Permohonan generateIdPerserahanWorkflow4(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan, String stageid) {
        LOG.info("--generateIdPerserahanWorkflow4" + senaraiHakmilik);
        Permohonan p = new Permohonan();
        HakmilikPermohonan hm = strService.findIdbyIDMohon(permohonan.getIdPermohonan());

        KodCawangan caw = strService.findByKodCaw("00");

        LOG.info("cawangan" + caw);
        LOG.info("kod negeri" + conf.getProperty("kodNegeri"));
        LOG.info("kod urusan" + ku);

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
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(caw);
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
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
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                p.setIdStagePermohonanSebelum(stageid);
                p.setCaraPermohonan(kodCaraPermohonanDAO.findById("UD"));

            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            HakmilikPermohonan hpa = new HakmilikPermohonan();
            if (ku.getKod().equals("BMSTM")) {

                if (senaraiHakmilik != null) {
                    for (HakmilikPermohonan hp : senaraiHakmilik) {
                        DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                        String idMohonHakmilik = new String();
                        String idP = new String();
                        idMohonHakmilik = String.valueOf(hpa.getId());
                        idP = hp.getPermohonan() != null ? hp.getPermohonan().getIdPermohonan() : new String();
                        LOG.info(" idMH : " + idMohonHakmilik);
                        LOG.info(" idMohon =" + idP);

                        HakmilikPihakBerkepentingan hpb = null;


                        String kbpm = new String();
                        String ksek = new String();
                        String khakmilik = new String();
                        String klot = new String();
                        String kktanah = new String();
                        String ksyarat = new String();
                        String kguna = new String();
                        String ksekatan = new String();
                        String tP = new String();
                        String luas = new String();
                        String luas2 = new String();
                        String kuom = new String();
                        String kuom2 = new String();
                        String cukai = new String();

                        kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                        ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;

                        khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;

                        klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                        kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                        ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                        kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                        ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                        tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                        luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
                        kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                        luas2 = hp.getKedalamanTanah() != null ? hp.getKedalamanTanah().toString() : null;
                        kuom2 = hp.getKedalamanTanahUOM() != null ? hp.getKedalamanTanahUOM().getKod() : null;
                        cukai = hp.getKeteranganCukaiBaru() != null ? hp.getKeteranganCukaiBaru().toString() : null;
                        hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, luas2, kuom2, cukai}, disLaporanTanahService);


                        LOG.info(" hp.getBandarPekanMukimBaru().getKod() : " + hp.getBandarPekanMukimBaru().getKod());

                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik = disHakmilikPermohonan.copyPropertiesMohonHMToHakmilik(permohonan, hp, hakmilik, new String[]{}, disLaporanTanahService);

                        hakmilik.setDaerah(permohonan.getCawangan().getDaerah());

                        hakmilik.setNoFail(permohonan.getIdPermohonan());

//                        FasaPermohonan fasaPermohonan = new FasaPermohonan();
                        PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                        PermohonanUkur mohonUkur = new PermohonanUkur();
//                        PelanGIS pelanGIS = new PelanGIS();
//                        NoPt noPt = new NoPt();


//                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HKBM")) {
//                                pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(permohonan.getIdPermohonan());
//                            }

                        mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                        mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);


                        if (mohonLaporPelan != null) {
                            hakmilik.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                        }
                        if (mohonUkur != null) {
                            hakmilik.setNoPu(mohonUkur.getNoPermohonanUkur());
                        }



                        String idPermohonan = new String();

                        idPermohonan = permohonan.getIdPermohonan();


                        mhservice.janaHakmilikBaruPendaftar2(hakmilik, ia, p, pengguna, idPermohonan, idMohonHakmilik, 1);

                    }
                }

            } else {//KPESL@KPEBL
                Hakmilik id = hm.getHakmilik();
                hpa.setHakmilik(id);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
            }


            LOG.info("--Intiating task--to Reg--");
            /*
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
            p.getKodUrusan().getNama()); */
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
            permohonan = p;

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
            //return false;
        }
        return p;
    }

    public Permohonan generateIdPerserahanWorkflow5(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, String noSkim, String idPihak) {
        LOG.info("--generateIdPerserahanWorkflow5" + senaraiHakmilik);
        Permohonan p = new Permohonan();
//        HakmilikPermohonan hm = disService.findIdHakmilikByIdMohon(permohonan.getIdPermohonan());

        KodCawangan caw = pengguna.getKodCawangan();
        if (conf.getProperty("kodNegeri").equals("05")) {
            caw = senaraiHakmilik.get(0).getCawangan();
        }
        LOG.info(ku.getNama());
//        LOG.info(permohonan.getFolderDokumen());
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
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            p.setStatus("SL");
//            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setIdPermohonan(idPermohonanGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
//            LOG.info("id permohonan ---- "+ p.getIdPermohonan());
            p.setCawangan(caw);
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setPenyerahNama("Unit Strata");

            if (ku.getKod().equals("PHPC") || ku.getKod().equals("PHPP")) {
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
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
                    p.setIdWorkflow(ku.getIdWorkflowIntegration());

                }
            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            for (Hakmilik hk : senaraiHakmilik) {
                String id = hk.getIdHakmilik();
                Hakmilik hakmilik = strService.findIdHakmilikequalIdInduk(id);
                HakmilikPermohonan mh = strService.findIdHakmilik(hakmilik.getIdHakmilikInduk());
                HakmilikPermohonan hpa = new HakmilikPermohonan();

                if (mh == null) {
                    HakmilikPermohonan hpa1 = new HakmilikPermohonan();
                    LOG.debug("--- saving id hakmilik induk to mohon hakmilik ---");
                    String idinduk = hk.getIdHakmilikInduk();
                    LOG.debug("--- id hakmilik induk ---" + idinduk);
                    Hakmilik idHakmilik1 = strService.findIdHakmilikequalIdInduk(idinduk);
                    LOG.info("id hakmilik " + idHakmilik1.getIdHakmilik());
                    LOG.info("id permohonan " + p.getIdPermohonan());
                    hpa1.setHakmilik(idHakmilik1);
                    hpa1.setInfoAudit(ia);
                    hpa1.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa1);

                    LOG.debug("--- saving to mohon skim ---");
                    Integer petak = strService.findMaxPetakByIdHakmilik1(idinduk);
                    PermohonanSkim ps = new PermohonanSkim();
                    ps.setHakmilikPermohonan(hpa1);
                    ps.setNoSkim(noSkim);
                    ps.setJumlahUnitSyer(hakmilik.getJumlahUnitSyer().longValue());
                    ps.setBilPetak(petak.longValue());
                    ps.setCawangan(caw);
                    ps.setInfoAudit(ia);
                    permohonanSkimDAO.save(ps);

                    LOG.debug("--- saving to mohon strata ---");
                    PermohonanStrata pstr = new PermohonanStrata();
                    pstr.setInfoAudit(ia);
                    pstr.setPermohonan(p);
                    pstr.setCawangan(caw);
                    pstr.setNoBuku(hakmilik.getNoBukuDaftarStrata());
                    pstr.setNama(" ");
                    pstr.setNamaSkim(noSkim);
                    pstr.setPemilikNama(" ");
                    permohonanStrataDAO.save(pstr);

                    LOG.debug("--- saving to strata badan urus ---");
                    Pihak pihaksearch = pihakService.findById(idPihak);
                    BadanPengurusan sbu1 = new BadanPengurusan();
                    sbu1.setInfoAudit(ia);
                    sbu1.setPermohonan(p);
                    sbu1.setCawangan(caw);
                    sbu1.setPihak(pihaksearch);
                    badanPengurusanDAO.save(sbu1);

                }

                hpa.setHakmilik(hakmilik);
                LOG.info("hakmilik---- " + hakmilik.getIdHakmilik());
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                LOG.info("id hakmilik " + id);
                LOG.info("id permohonan " + p.getIdPermohonan());
                hakmilikPermohonanDAO.save(hpa);



            }

//            LOG.info("--Intiating task--to Reg--");
            /*
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
            p.getKodUrusan().getNama()); */
//            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
//                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                    p.getKodUrusan().getNama());
            permohonan = p;

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
            //return false;
        }
        return p;
    }

    public Permohonan generateIdPerserahanWorkflow6(KodUrusan ku, Pengguna pengguna, Hakmilik hm) {
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(hm.getIdHakmilik());
//        Hakmilik hakmlk = strService.findInfoByIdHakmilik(hm.getIdHakmilik());
        if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("HSD")) {
            caw = strService.findByKodCaw("00");
        } else {
            caw = hm.getCawangan();
        }

        LOG.info(hm.getCawangan());
        LOG.info("kod urusan" + ku);
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        String idPermohonan = null;
        String kodNegeri;
        if (conf.getProperty("kodNegeri").equals("05")) {
            kodNegeri = "05";
        } else {
            kodNegeri = "04";
        }
        idPermohonan = idPermohonanGenerator.generate(
                kodNegeri, caw, ku);

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);


            p.setStatus("TA");
//            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setIdPermohonan(idPermohonan);

            if (conf.getProperty("kodNegeri").equals("05")) {
                p.setCawangan(caw);
            }
            if (conf.getProperty("kodNegeri").equals("04")) {
                p.setCawangan(caw);
            }

            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setPenyerahNama("Unit Strata");
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

//            String id = hm.getIdHakmilik();
//            hm = hakmilikDAO.findById(id);
//            if (hm == null) {
//                throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
//            }
//            HakmilikPermohonan hpa = new HakmilikPermohonan();
//            hpa.setHakmilik(hm);
//            hpa.setInfoAudit(ia);
//            hpa.setPermohonan(p);
//            hakmilikPermohonanDAO.save(hpa);


            permohonan = p;
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
            //return false;
        }
        return p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikACQ() {
        return senaraiHakmilikACQ;
    }

    public void setSenaraiHakmilikACQ(List<HakmilikPermohonan> senaraiHakmilikACQ) {
        this.senaraiHakmilikACQ = senaraiHakmilikACQ;
    }

    public TanahRizabPermohonan getMohonTrizab() {
        return mohonTrizab;
    }

    public void setMohonTrizab(TanahRizabPermohonan mohonTrizab) {
        this.mohonTrizab = mohonTrizab;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }
}
