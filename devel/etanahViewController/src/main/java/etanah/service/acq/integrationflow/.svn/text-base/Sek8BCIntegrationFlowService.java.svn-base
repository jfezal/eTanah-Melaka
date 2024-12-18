/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.integrationflow;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TugasanUtiliti2DAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.TugasanUtiliti2;
import etanah.ref.pengambilan.sek8bc.RefPeringkat;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class Sek8BCIntegrationFlowService {

    //bpel_sek8bc
    private static final String SEDIABRGCD = "http://xmlns.oracle.com/Application2/Seksyen_8bc_2/sek_8bc_task";
    private static final String SEDIASRTMAKLUM = "http://xmlns.oracle.com/Application2/Seksyen_8bc_3/task_sek8bc_3";

    private static final Logger LOG = Logger.getLogger(Sek8BCIntegrationFlowService.class);
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDAO;
    @Inject
    TugasanUtiliti2DAO tugasanUtiliti2DAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;

    public boolean completeTask(String kod, Permohonan p, Pengguna pengguna) {
        RefPeringkat ref = new RefPeringkat();
        if (ref.REKOD_KEPUTUSAN_JKKPT.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
        } else if (ref.SEDIA_TERIMA_KPSN_JKKPT.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEDIABRGCD)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.SEDIA_TERIMA_KPSN_MMK_8BC.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEDIASRTMAKLUM)) {
                deleteTugasanTable(p);
                return true;
            }
        }else if (ref.REKOD_KPSN_MMK_8BC.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
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
        tugasanUtilitiDAO.save(tugas);
    }

    @Transactional
    public void deleteTugasanTable(Permohonan mohon) {
        TugasanUtiliti2 tugas = tugasanUtiliti2DAO.findById(mohon.getIdPermohonan());
        tugasanUtiliti2DAO.delete(tugas);
    }
}
