/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.integrationflow;

import etanah.service.acq.integrationflow.*;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.InfoMmknDAO;
import etanah.dao.InfoWartaDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TugasanUtiliti2DAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.InfoMmkn;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.TugasanUtiliti2;
import etanah.ref.strata.pkbk.RefPeringkat;
import etanah.service.common.FasaPermohonanService;
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
public class PkbkIntegrationFlowService {

    private static final String REKODSRTKPSN = "http://xmlns.oracle.com/BangKhasStrata/KeputusanMmkn/Humantask1";

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
    @Inject
    FasaPermohonanService fasaPermohonanManager;

    public boolean completeTask(String kod, Permohonan p, Pengguna pengguna) {
        RefPeringkat ref = new RefPeringkat();
        if (ref.REKOD_KPSN_MMK_PKBK.equals(kod)) {
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
        } else if (ref.SELESAI.equals(kod)) {
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
        
        doCreateUpdateFasa(pguna, mohon);
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

        if (ref.REKOD_KPSN_MMK_PKBK.equals(kodPeringkat)) {
            MMKStage(mohon, kodPeringkat, idPengguna);
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
    
    private void doCreateUpdateFasa(Pengguna p, Permohonan mohon) {
        InfoAudit au = new InfoAudit();
        au.setTarikhMasuk(new Date());
        au.setDimasukOleh(p);
        String stageId = "keputusan";
        List<FasaPermohonan> senaraiFasa = mohon.getSenaraiFasa();
        FasaPermohonan _fp = null;

        for (FasaPermohonan fp : senaraiFasa) {
            if(fp.getIdAliran().equals(stageId)
                    && fp.getIdPengguna().equals(p.getIdPengguna())) {
                _fp = fp;
                break;
            }
        }

        if(_fp != null) {
            au = _fp.getInfoAudit();
            au.setTarikhKemaskini(new Date());
            au.setDiKemaskiniOleh(p);
            _fp.setInfoAudit(au);
        } else {
            _fp = new FasaPermohonan();
            _fp.setInfoAudit(au);
            _fp.setPermohonan(mohon);
            _fp.setCawangan(p.getKodCawangan());
            _fp.setIdAliran(stageId);
            _fp.setIdPengguna(p.getIdPengguna());
        }

        fasaPermohonanManager.saveOrUpdate(_fp);
    }

}
