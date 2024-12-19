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
import etanah.model.ambil.PermohonanPengambilan;
import etanah.ref.pengambilan.sek4aduan.RefPeringkat;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author zipzap
 */
public class Sek4AduanIntegrationFlowService {

    private static final String SURATBAYARAN = "http://xmlns.oracle.com/Application1/Sek4_Aduan_Terima/Sek4_Aduan_Arah_Bayaran";
    private static final String SEMAKTANDATANGAN = "http://xmlns.oracle.com/AduanKerosakan/Bpel_Sek4Aduan_tandatanganTerimaCek/tandatangan_terima_cek";

    private static final Logger LOG = Logger.getLogger(Sek4AduanIntegrationFlowService.class);
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
        if (ref.REKOD_KPSN_BNCG.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        }  else if (ref.SURAT_BAYAR.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SURATBAYARAN)) {
                deleteTugasanTable(p);
                return true;
            }
        }else if (ref.REKOD_TERIMA_BYR_AP.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEDIA_SURAT_AMBIL_BAYARAN.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEMAKTANDATANGAN)) {
                 deleteTugasanTable(p);
                return true;
            }
            
        }else if (ref.REKOD_TERIMA_PAMPASAN.equals(kod)) {
            //
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        }else if (ref.REKOD_KPSN_MMK.equals(kod)) {
            //
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        }else if (ref.SELESAI_TERIMA_PAMPASAN.equals(kod)) {
            deleteTugasanTable(p);
             return true;

        }
        return false;

    }

    @Transactional
    public void insertTugasanTable(Permohonan mohon, String kodPeringkat, String idPengguna) {
        TugasanUtiliti tugas = tugasanUtilitiDAO.findById(mohon.getIdPermohonan());
        if(tugas!=null){}else{
        tugas = new TugasanUtiliti();
        }
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
}
