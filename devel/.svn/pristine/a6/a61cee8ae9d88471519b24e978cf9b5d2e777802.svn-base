/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class CetakSuratValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(CetakSuratValidator.class);
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

         List<FasaPermohonan> listFasa = lelongService.findFasaPermohonanCetakSuratList(idPermohonan);
        FasaPermohonan fasa = listFasa.get(0);
        if (fasa.getKeputusan() == null) {
            context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Keputusan Di Tab Maklumat Keputusan.");
            return null;
        }
        List<Lelongan> senaraiLelongan = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());

        List<Enkuiri> listEn = null;
        if (permohonan.getPermohonanSebelum() == null) {
            listEn = lelongService.getEnkuiri(permohonan.getIdPermohonan());
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                listEn = lelongService.getEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            } else {
                listEn = lelongService.getEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
        }

        Enkuiri en = listEn.get(0);
        LOG.info("Enkuiri Status : " + en.getCaraLelong());

        if (permohonan.getSenaraiHakmilik().size() == 1) {
            LOG.info("1 hak milik sahaja");
//            if (en.getCaraLelong().equals("A")) {
//                LOG.info("Cara lelongan Berasingan");
//                if (fasa.getKeputusan().getKod().equals("DB")) {
//                    LOG.info("Keputusan Pembida dh jelaskn baki");
//                    for (Lelongan L : senaraiLelongan) {
//                        if (L.getBaki().intValue() != 0) {
//                            LOG.info("Pembida dh jelaskn baki tp tak reset lg baki");
//                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Adalah Telah Jelas Bayaran Baki. Sila Klik Butang Pembayaran Untuk Mengemaskini Baki Harga Bidaan Pada Tab Maklumat Pembayaran Baki.");
//                            return null;
//                        }
//                    }
//                }
//
//                if (fasa.getKeputusan().getKod().equals("LB")) {
//                    LOG.info("Keputusan Pembida belum jelaskn baki");
//                    for (Lelongan L : senaraiLelongan) {
//                        if (L.getBaki().intValue() == 0) {
//                            LOG.info("Tiada Baki Tp Keputusan Belum Jelas Baki");
//                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Salah. Sila Pilih Keputusan Telah Jelas Bayaran Baki.");
//                            return null;
//                        }
//                    }
//                }
//            }

            if (en.getCaraLelong().equals("B")) {
                LOG.info("Cara lelongan Bersama");
                if (fasa.getKeputusan().getKod().equals("DB")) {
                    LOG.info("Keputusan Pembida dh jelaskn baki");
                    for (Lelongan L : senaraiLelongan) {
                        if (en.getBaki().intValue() != 0) {
                            LOG.info("Pembida dh jelaskn baki tp tak reset lg baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Adalah Telah Jelas Bayaran Baki. Sila Klik Butang Pembayaran Untuk Mengemaskini Baki Harga Bidaan Pada Tab Maklumat Pembayaran Baki.");
                            return null;
                        }
                    }
                }

                if (fasa.getKeputusan().getKod().equals("LB")) {
                    LOG.info("Keputusan Pembida belum jelaskn baki");
                    for (Lelongan L : senaraiLelongan) {
                        if (en.getBaki().intValue() == 0) {
                            LOG.info("Tiada Baki Tp Keputusan Belum Jelas Baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Salah. Sila Pilih Keputusan Telah Jelas Bayaran Baki.");
                            return null;
                        }
                    }
                }

            }

        }

        if (permohonan.getSenaraiHakmilik().size() >= 2) {
            LOG.info("2 atau lebih hak milik");
            if (en.getCaraLelong().equals("A")) {
                LOG.info("Cara lelongan Berasingan");
                if (fasa.getKeputusan().getKod().equals("DB")) {
                    LOG.info("Keputusan Pembida dh jelaskn baki");
                    for (Lelongan L : senaraiLelongan) {
                        if (L.getBaki().intValue() != 0) {
                            LOG.info("Pembida dh jelaskn baki tp tak reset lg baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Adalah Telah Jelas Bayaran Baki. Sila Klik Butang Pembayaran Untuk Mengemaskini Baki Harga Bidaan Pada Tab Maklumat Pembayaran Baki.");
                            return null;
                        }
                    }
                }

                if (fasa.getKeputusan().getKod().equals("LB")) {
                    LOG.info("Keputusan Pembida belum jelaskn baki");                   
                    for (Lelongan L : senaraiLelongan) {
                        if (L.getBaki().intValue() == 0) {
                            LOG.info("Salah satu hak milik dah jelaskn baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Salah. Sila Pilih Keputusan Telah Jelas Bayaran Baki. Atau Sila Klik Butang Teruskan Permohonan Bagi Hak Milik Yang Telah Jelas Bayaran Baki Harga Bidaan Pada Tab Maklumat Pembayaran Baki.");
                            return null;
                        }
                    }
                }
            }

            if (en.getCaraLelong().equals("B")) {
                LOG.info("Cara lelongan Bersama");
                if (fasa.getKeputusan().getKod().equals("DB")) {
                    LOG.info("Keputusan Pembida dh jelaskn baki");
                    for (Lelongan L : senaraiLelongan) {
                        if (en.getBaki().intValue() != 0) {
                            LOG.info("Pembida dh jelaskn baki tp tak reset lg baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Adalah Telah Jelas Bayaran Baki. Sila Klik Butang Pembayaran Untuk Mengemaskini Baki Harga Bidaan Pada Tab Maklumat Pembayaran Baki.");
                            return null;
                        }
                    }
                }

                if (fasa.getKeputusan().getKod().equals("LB")) {
                    LOG.info("Keputusan Pembida belum jelaskn baki");
                    for (Lelongan L : senaraiLelongan) {
                        if (en.getBaki().intValue() == 0) {
                            LOG.info("Tiada Baki Tp Keputusan Belum Jelas Baki");
                            context.addMessage(permohonan.getIdPermohonan() + " -  Keputusan Yang Dipilih Salah. Sila Pilih Keputusan Telah Jelas Bayaran Baki.");
                            return null;
                        }
                    }
                }
            }
        }


        KodStatusLelongan kod = null;
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
                FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fp);
                if (fasaPermohonanLog != null) {
                    lelongService.deletetest(fasaPermohonanLog, fp);
                }
                lelongService.delete(fp);
            }
            if (fasa.getKeputusan().getKod().equals("LB")) {
                LOG.info("---LB---");
                proposedOutcome = fasa.getKeputusan().getKod();
            } else {
                proposedOutcome = "";
            }
        } else {
            LOG.info("---else---");
            proposedOutcome = "";
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
