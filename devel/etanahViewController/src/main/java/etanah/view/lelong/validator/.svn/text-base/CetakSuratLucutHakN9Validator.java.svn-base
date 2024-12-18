package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author ${dom}
 */
public class CetakSuratLucutHakN9Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(CetakSuratLucutHakN9Validator.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    @SuppressWarnings("static-access")
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        String baki = "";
        Date now = new Date();
        List<Lelongan> listLelong = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
        for (Lelongan ll : listLelong) {
            if (!ll.getBaki().equals(BigDecimal.ZERO)) {
                baki = "ada";
                long count = (now.getTime() - ll.getTarikhLelong().getTime()) / 1000 / 60 / 60 / 24;
                count++;
                LOG.info("count :" + count);
                if (count <= 120) {
                    context.addMessage(permohonan.getIdPermohonan() +" - Baki Bidaan Masih Belum Dijelaskan Dalam Tempoh 120 Hari");
                    return null;
                }
                break;
            }
        }

        KodKeputusan kodKpsn = null;
        FasaPermohonan fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(permohonan.getIdPermohonan());
        if (baki.equals("ada")) {
            kodKpsn = kodKeputusanDAO.findById("LB");
            fasa.setKeputusan(kodKpsn);
        } else {
            kodKpsn = kodKeputusanDAO.findById("DB");
            fasa.setKeputusan(kodKpsn);
        }
        lelongService.saveUpdate2(fasa);
        fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(permohonan.getIdPermohonan());
        KodStatusLelongan kod = null;
        if (fasa.getKeputusan() != null) {
            LOG.info("fasa.getKeputusan().getKod() : " + fasa.getKeputusan().getKod());
            if (fasa.getKeputusan().getKod().equals("LB")) {
                LOG.info("----LB---");
                if (permohonan.getSenaraiHakmilik().size() >= 2) {
                    List<Lelongan> ll = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
                    List<Hakmilik> hh = new ArrayList<Hakmilik>();
                    List<Lelongan> lel = new ArrayList<Lelongan>();
                    KodUrusan kk = permohonan.getKodUrusan();
                    Pengguna pp = penggunaDAO.findById(fasa.getInfoAudit().getDimasukOleh().getIdPengguna());
                    FolderDokumen fff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    LOG.info("BigDecimal.ZERO : " + BigDecimal.ZERO);
                    for (Lelongan oo : ll) {
                        if (oo.getBaki() == BigDecimal.ZERO) {
                            hh.add(oo.getHakmilikPermohonan().getHakmilik());
                            lel.add(oo);
                        } else {
                            kod = kodStatusLelonganDAO.findById("AT");
                            oo.setKodStatusLelongan(kod);
                            lelongService.saveOrUpdate(oo);
                        }
                    }
                    LOG.info("hh.size() : " + hh.size());
                    if (hh.size() > 0) {
                        LOG.info("----tak bayar lagi----");
                        try {
                            String taskId = "";
                            Permohonan p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, fff, lel);
                            IWorkflowContext ctxx = workFlowService.authenticate(pp.getIdPengguna());
                            LOG.info("IDMohon : " + p.getIdPermohonan());
                            List<Task> taskList = null;
                            do {
                                taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                                LOG.info("taskList : " + taskList.size());
                            } while (taskList.isEmpty());
                            if (taskList.size() > 0) {
                                Task t = taskList.get(0);
                                String stageID = null;
                                if (t.getSystemAttributes().getTaskId() != null) {
                                    taskId = t.getSystemAttributes().getTaskId();
                                    LOG.info("taskId : " + taskId);
                                    Task tt = null;
                                    if (stageID != null) {
                                        ctxx = workFlowService.authenticate(pp.getIdPengguna());
                                    }
                                    tt = workFlowService.acquireTask(taskId, ctxx);
                                    LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                                    do {
                                        taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                                        LOG.info("taskList : " + taskList.size());
                                    } while (taskList.isEmpty());
                                    t = taskList.get(0);
                                    taskId = t.getSystemAttributes().getTaskId();
                                    stageID = workFlowService.updateTaskOutcome(taskId, ctxx, "T");
                                    LOG.info("stageID : " + stageID);
                                }
                            }
                        } catch (Exception ex) {
                            LOG.info("Error : " + ex);
                        }
                    }
                }
                if (permohonan.getSenaraiHakmilik().size() == 1) {
                    LOG.info("------1------");
                    List<Lelongan> ll = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
                    for (Lelongan oo : ll) {
                        kod = kodStatusLelonganDAO.findById("AT");
                        oo.setKodStatusLelongan(kod);
                        lelongService.saveOrUpdate(oo);

                    }
                }
                FasaPermohonan fp = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
                if (fp != null) {
                    lelongService.delete(fp);
                }
                if (fasa.getKeputusan().getKod().equals("LB")) {
                    LOG.info("---LB---");
                    FolderDokumen fd = permohonan.getFolderDokumen();
                    List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
                    if (!listKD.isEmpty()) {
                        KodDokumen kodD = null;
                        for (KandunganFolder kf : listKD) {
                            if (kf.getDokumen().getKodDokumen().getKod().equals("LEL")) {
                                kodD = kodDokumenDAO.findById("LELLM");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("NL")) {
                                kodD = kodDokumenDAO.findById("NLLM");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("PJ")) {
                                kodD = kodDokumenDAO.findById("PJLM");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                                kodD = kodDokumenDAO.findById("KMLM");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                                kodD = kodDokumenDAO.findById("MEMOL");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("SBI")) {
                                kodD = kodDokumenDAO.findById("SBIL");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("SLH")) {
                                kodD = kodDokumenDAO.findById("SLHL");
                                Dokumen d = kf.getDokumen();
                                d.setKodDokumen(kodD);
                                lelongService.saveOrUpdatDokumen(d);
                                kf.setDokumen(d);
                                lelongService.saveOrUpdate(kf);
                            }
                        }
                    }
                    proposedOutcome = fasa.getKeputusan().getKod();
                } else {
                    proposedOutcome = "";
                }
            } else {
                LOG.info("---else---");
                List<Lelongan> ll = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
                for (Lelongan oo : ll) {
                    if (oo.getBaki() != BigDecimal.ZERO) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Keputusan Adalah Telah Bayar Baki.Sila Tekan Butang Pembayaran Untuk Mengemaskini Baki Kepada RM0.00 Di Tab Pembayaran Baki");
                        return null;
                    }
                }
                proposedOutcome = "";
            }
        } else {
            context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Keputusan Di Tab Maklumat Keputusan");
            return null;
        }
        LOG.info("proposedOutcome : " + proposedOutcome);
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
