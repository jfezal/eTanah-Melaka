/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.integrationflow;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.InfoMmknDAO;
import etanah.dao.InfoWartaDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TugasanUtiliti2DAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.InfoAudit;
import etanah.model.InfoMmkn;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TugasanUtiliti;
import etanah.model.TugasanUtiliti2;
import etanah.model.ambil.InfoWarta;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class Sek4IntegrationFlowService {

    private static final String REKODSRTKPSN = "http://xmlns.oracle.com/PindaanAPT/Bpel_SEK4_2/sek4_2";
    private static final String CETAKBRGB = "http://xmlns.oracle.com/mywork/PindaanAPT/Bpel_SEK4_3";
    private static final String PERTIKAIAN = "http://xmlns.oracle.com/Application1/Sek4_Aduan_Tolak/Sek4_Aduan_Pertikaian";
    private static final String SEDIABYRN = "http://xmlns.oracle.com/Application1/Sek4_Aduan_Terima/Sek4_Aduan_Arah_Bayaran";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(Sek4IntegrationFlowService.class);
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDAO;
    @Inject
    TugasanUtiliti2DAO tugasanUtiliti2DAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    @Inject
    InfoMmknDAO infoMmknDAO;
    @Inject
    InfoWartaDAO infoWartaDAO;

    public boolean completeTask(String kod, Permohonan p, Pengguna pengguna) {
        RefPeringkat ref = new RefPeringkat();
        if (ref.REKOD_KPSN_MMK.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEDIA_TERIMA_KPSN_MMK.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, REKODSRTKPSN)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.HANTAR_WARTA.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEMAK_WARTA.equals(kod)) {
            if (initiateBpel(p, CETAKBRGB)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.PENYAMPAIAN_AB.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
        } //UNTUK SEK4 ADUAN KEROSAKAN!!!!!!!!!!
        else if (ref.REKOD_KPSN_HEARING.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
        } else if (ref.SEDIA_SURAT_BYR.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEDIABYRN)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.TERIMA_KPSN_MMKN.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
//            if (initiateBpel(p, PERTIKAIAN)) {
//                deleteTugasanTable(p);
//                return true;
//            }
        }else if (ref.SELESAI.equals(kod)) {
            LOG.info("Start initiate task" + kod);
                deleteTugasanTable(p);
                return true;
        } 
        return false;
    }

    public boolean initiateBpel(Permohonan p, String BPEL) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(BPEL,
                    p.getIdPermohonan(), p.getCawangan().getKod() + ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean initiateRekodSuratKpsn(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(REKODSRTKPSN,
                    p.getIdPermohonan(), p.getCawangan().getKod() + ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean initiateCetakBrgB(Permohonan p) {
        boolean success = false;
        try {

            if (WorkFlowService.initiateTask(CETAKBRGB,
                    p.getIdPermohonan(), p.getCawangan().getKod() + ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Transactional
    public void insertTugasanTable(Permohonan mohon, String kodPeringkat, String idPengguna) {
        TugasanUtiliti tugas = new TugasanUtiliti();
        KodPeringkat kod = kodPeringkatDAO.findById(kodPeringkat);
        Pengguna pguna = penggunaDAO.findById(idPengguna);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        tugas.setIdPermohonan(mohon.getIdPermohonan());
        tugas.setMohon(mohon);
        tugas.setKodPeringkat(kod);
        tugas.setInfoAudit(ia);
        insertToRelatedTable(mohon, kodPeringkat, idPengguna);
        tugasanUtilitiDAO.save(tugas);
    }

    @Transactional
    public void deleteTugasanTable(Permohonan mohon) {
        TugasanUtiliti2 tugas = tugasanUtiliti2DAO.findById(mohon.getIdPermohonan());
        tugasanUtiliti2DAO.delete(tugas);
    }

    public boolean findTugasan(Permohonan permohonan) {
        TugasanUtiliti t = tugasanUtilitiDAO.findById(permohonan.getIdPermohonan());
        if (t != null) {
            return true;
        } else {
            return false;
        }
    }

    private void insertToRelatedTable(Permohonan mohon, String kodPeringkat, String idPengguna) {
        RefPeringkat ref = new RefPeringkat();

        if (ref.REKOD_KPSN_MMK.equals(kodPeringkat)) {
            MMKStage(mohon, kodPeringkat, idPengguna);
        }
        if (ref.HANTAR_WARTA.equals(kodPeringkat)) {
            WartaStage(mohon, kodPeringkat, idPengguna);
        } else if ("".equals(kodPeringkat)) {
        }

    }

    @Transactional
    private void MMKStage(Permohonan mohon, String kodPeringkat, String idPengguna) {
        InfoMmkn mmkn = new InfoMmkn();
        KodPeringkat kod = kodPeringkatDAO.findById(kodPeringkat);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(penggunaDAO.findById(idPengguna));
        infoAudit.setTarikhMasuk(new Date());
        mmkn.setKodPeringkat(kod);
        mmkn.setPermohonan(mohon);
        mmkn.setInfoAudit(infoAudit);
        infoMmknDAO.save(mmkn);

    }

    @Transactional
    private void WartaStage(Permohonan mohon, String kodPeringkat, String idPengguna) {
        InfoWarta warta = new InfoWarta();
        KodPeringkat kod = kodPeringkatDAO.findById(kodPeringkat);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(penggunaDAO.findById(idPengguna));
        infoAudit.setTarikhMasuk(new Date());
        warta.setKodPeringkat(kod);
        warta.setPermohonan(mohon);
        warta.setInfoAudit(infoAudit);
        infoWartaDAO.save(warta);

    }

    public InfoMmkn findMMKByKpsn(String idPermohonan, String rkmmk) {
        String query = "SELECT b FROM etanah.model.InfoMmkn b where b.permohonan.idPermohonan = :idMohon "
                + "and b.kodPeringkat.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idPermohonan);
        q.setString("kod", rkmmk);
        return (InfoMmkn) q.uniqueResult();
    }

    public String withdrawTaskTolak(String proposedOutcome, InfoMmkn mmk) throws WorkflowException {
        if (mmk.getKodKpsn().equals("T")) {
            List<Task> l = WorkFlowService.queryTasksByAdmin(mmk.getPermohonan().getIdPermohonan());
            if (l != null) {
                for (Task t : l) {
                    String taskId = t.getSystemAttributes().getTaskId();
                    if (StringUtils.isNotBlank(taskId)) {
                        try {
                            WorkFlowService.withdrawTask(taskId);
                        } catch (StaleObjectException ex) {
                            ex.printStackTrace();
                            LOG.error(ex);
                        }
                    }
                }
            }
            proposedOutcome = null;
        }
        return proposedOutcome;
    }

}