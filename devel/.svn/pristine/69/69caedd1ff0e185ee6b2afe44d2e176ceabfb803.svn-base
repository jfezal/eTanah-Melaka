/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
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
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 *
 * @author mdizzat.mashrom
 */
public class RekodBidaanN9Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(RekodBidaanN9Validator.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;

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
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----List not empty----");
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
        
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
        FolderDokumen fd = permohonan.getFolderDokumen();
        FasaPermohonan fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(permohonan.getIdPermohonan());
        if (fasa != null && fasa.getKeputusan() != null) {
            lelongService.delete(fasa);
        }
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        if (fasaPermohonan != null) {
            FasaPermohonan ff2 = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
            //ada pembida
            if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {

                List<Lelongan> lel = lelongService.listLelonganAK(permohonan.getIdPermohonan());
                if (!lel.isEmpty()) {
                    for (Lelongan ll : lel) {
                        if (ll.getPembida() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Maklumat Pembida Di Tab Kemasukan Rekod Bidaan");
                            return null;
                        }
                    }
                }

                if (ff2.getKeputusan().getKod().equals("PH")) {
                    String memo = "";
                    String km = "";
                    for (KandunganFolder kf : listKD) {
                        if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                            memo = "MEMO";
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                            km = "KM";
                        }
                    }
                    if (!memo.equals("MEMO")) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Memorandum Jualan Di Tab Memorandum Jualan");
                        return null;
                    }
                    if (!km.equals("KM")) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Komisyen Jualan Di Tab Komisyen Jualan");
                        return null;
                    }
                }
            }

            //tiada pembida
            if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                FasaPermohonan fp = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
                if (fp != null) {
                    lelongService.delete(fp);
                }
                KodStatusLelongan kod = kodStatusLelonganDAO.findById("TB");
                LOG.info(this);
                if (permohonan.getSenaraiHakmilik().size() >= 2) {
                    List<Lelongan> ll = lelongService.listLelonganA(permohonan.getIdPermohonan());
                    List<Hakmilik> hh = new ArrayList<Hakmilik>();
                    List<Lelongan> lel = new ArrayList<Lelongan>();
                    List<Lelongan> lelTB = new ArrayList<Lelongan>();
                    KodUrusan kk = permohonan.getKodUrusan();
                    Pengguna pp = penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna());
                    FolderDokumen ff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    String tb = "";
                    for (Lelongan oo : ll) {
                        if (oo.getPembida() != null) {
                            hh.add(oo.getHakmilikPermohonan().getHakmilik());
                            lel.add(oo);
                        }
                        if (oo.getPembida() == null) {
                            tb = "tb";
                            lelTB.add(oo);
                        }
                    }
                    if (!tb.equals("tb")) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Keputusan Yang Dipilih Salah.Setiap Hakmilik Mempunyai Pembida.Sila Pilih Keputusan Ada Pembida/Tarik Permohonan Untuk Setiap Hakmilik");
                        return null;
                    }
                    if (!lelTB.isEmpty()) {
                        for (Lelongan lelongan : lelTB) {
                            lelongan.setKodStatusLelongan(kod);
                            lelongService.saveOrUpdate(lelongan);
                        }
                    }
                    LOG.info("List<Lelongan> : " + lel.size());
                    LOG.info("List<Hakmilik> : " + hh.size());
                    if (hh.size() > 0) {

                        if (ff2.getKeputusan().getKod().equals("PH")) {
                            String memo = "";
                            String km = "";
                            for (KandunganFolder kf : listKD) {
                                if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                                    memo = "MEMO";
                                }
                                if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                                    km = "KM";
                                }
                            }
                            if (!memo.equals("MEMO")) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Memorandum Jualan Di Tab Memorandum Jualan");
                                return null;
                            }
                            if (!km.equals("KM")) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Komisyen Jualan Di Tab Komisyen Jualan");
                                return null;
                            }
                        }
                        Permohonan p = null;
                        try {
                            p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, ff, lel);
                            updateOutcome(p, pp);
                        } catch (StaleObjectException ex) {
                            LOG.info(ex);
                        } catch (WorkflowException ex) {
                            LOG.info(ex);
                        }
                        StringBuilder sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            LOG.info("IdHakmilik : " + hk.getIdHakmilik());
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        String idHakmilik = sb.toString();

                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Telah Ada Pembida " + idHakmilik + " Akan Disambung Pada ID Permohonan Berikut : " + p.getIdPermohonan());
                    }
                } else {
                    List<Lelongan> ll = lelongService.listLelonganA(permohonan.getIdPermohonan());
                    for (Lelongan lelongan : ll) {
                        lelongan.setKodStatusLelongan(kod);
                        lelongService.saveOrUpdate(lelongan);
                    }
                }
                KodDokumen kodD = null;
                if (!listKD.isEmpty()) {
                    for (KandunganFolder kf : listKD) {
                        if (kf.getDokumen().getKodDokumen().getKod().equals("LEL")) {
                            kodD = kodDokumenDAO.findById("LELLM");
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
                        if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                            kodD = kodDokumenDAO.findById("KMLM");
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
                    }
                }
            }
            if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {

                FasaPermohonan fp = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
                if (fp != null) {
                    lelongService.delete(fp);
                }
            }
        }
        proposedOutcome = fasaPermohonan.getKeputusan().getKod();
//        context.setNoRujukan(context.getPermohonan().getIdPermohonan());
        return proposedOutcome;
    }

    @SuppressWarnings("static-access")
    public void updateOutcome(Permohonan p, Pengguna pp) throws WorkflowException, StaleObjectException {

        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
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
                String taskId = t.getSystemAttributes().getTaskId();
                LOG.info("taskId : " + taskId);
                Task tt = null;
                if (stageID != null) {
                    LOG.info("-----ptptdlelong----");
                    ctx = workFlowService.authenticate(pp.getIdPengguna());
                }
                tt = workFlowService.acquireTask(taskId, ctx);
                LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                do {
                    taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                    LOG.info("taskList : " + taskList.size());
                } while (taskList.isEmpty());
                t = taskList.get(0);
                taskId = t.getSystemAttributes().getTaskId();
                stageID = workFlowService.updateTaskOutcome(taskId, ctx, "L");
                LOG.info(" new stageID : " + stageID);
                LOG.info("----Abis Dah---- : " + stageID);
            }
        }
        LOG.info("------Finish------");
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
