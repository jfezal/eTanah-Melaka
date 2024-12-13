/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dev.integrationflow;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class SBMSIntegrationFlowService {

    private static final String RENCANA = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS2_Rencana/kertas_rencana";
    private static final String SRTKEPUTUSAN = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS3_SuratKeputusan/SuratKeputusan";
    private static final String RENCANAMENTERI = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS4_RencanaYABMenteri/sedia_rencana_menteri";
    private static final String TERIMAKEPUTUSANMENTERI = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS5_TerimaKeputusanYABMenteri/TerimaKeputusan";
    private static final String AGIHTUGASCHARTINGLULUS = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS6_AgihChartingLulus/ChartingKelulusan";
    private static final String ENDORSANPELANHITUNG = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS7_EndorsPelanHitung/EndorsPelanPraHitung";
    private static final String TERIMAPELANB2 = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS8_TerimaPelanB2/TerimaPelanB2";
    private static final String TERIMAPELANB1 = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS9_TerimaB1/TerimaPelanB1";
//    private static final String AGIHTUGASNOPUPT = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS6_AgihSediaNoPu/AgihSediaNoPu";
//      private static final String AGIHTUGASNOPUPT = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS6_AgihSediaNoPT/AgihSediaNoPt";
        private static final String AGIHTUGASNOPUPT = "http://xmlns.oracle.com/PindaanKanun/Bpel_SBMS6_AgihSediaNoPT/AgihSediaNoPt";

    private static final Logger LOG = Logger.getLogger(etanah.service.dev.integrationflow.SBMSIntegrationFlowService.class);
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
   
    public boolean initiateRencanaJKBB(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(RENCANA,
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

    public boolean initiateSrtKeputusan(Permohonan p) {
        boolean success = false;
        try {

            if (WorkFlowService.initiateTask(SRTKEPUTUSAN,
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

    public boolean initiateRencanaMenteri(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(RENCANAMENTERI,
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

    public boolean initiateEndorsanPelanHitung(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(ENDORSANPELANHITUNG,
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

    public boolean initiateAgihTugasChartingLulus(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(AGIHTUGASCHARTINGLULUS,
                    p.getIdPermohonan(), p.getCawangan().getKod()+ ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
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
        TugasanUtiliti tugas = tugasanUtilitiDAO.findById(mohon.getIdPermohonan());
        tugasanUtilitiDAO.delete(tugas);
    }

    public boolean findTugasan(Permohonan permohonan) {
        TugasanUtiliti t = tugasanUtilitiDAO.findById(permohonan.getIdPermohonan());
        if (t != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean initiateTerimaPelanB2(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(TERIMAPELANB2,
                    p.getIdPermohonan(), p.getCawangan().getKod()+ ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean initiateTerimaPelanB1(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(TERIMAPELANB1,
                    p.getIdPermohonan(), p.getCawangan().getKod()+ ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean initiateAgihTugasNoPuPt(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(AGIHTUGASNOPUPT,
                    p.getIdPermohonan(), p.getCawangan().getKod()+ ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean initiateKeputusanRencanaMenteri(Permohonan p) {
try {
            boolean success = false;
            if (WorkFlowService.initiateTask(TERIMAKEPUTUSANMENTERI,
                    p.getIdPermohonan(), p.getCawangan().getKod()+ ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SBMSIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    }

}
