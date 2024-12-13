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
import etanah.ref.pengambilan.sek8a.RefPeringkat;
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
public class Sek8aIntegrationFlowService {

    //bpel_sek8a_1agihan
    private static final String AGIHANLAPORANTANAH = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_1Agihan/agihan_ppelan";
    //bpel_sek8a_1agihanptg
    private static final String AFLOW = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8A_1AgihanPtg/agihanPTG";
    //bpel_sek8bc 
    private static final String BCFLOW = "http://xmlns.oracle.com/Application2/Seksyen_8bc_1/kemasukan";
    //bpel_sek8a_2
    private static final String REKODSRTKPSN = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8A_2/sek8a_2_rekod_surat_kpsn";
    //bpel_sek8a_3
    private static final String WARTABORANGD = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_3/semak_warta";

    private static final String TETAPBICARA = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_4_semak_tetapbicara/semak_tetapbicara";
    private static final String KEDESAKAN = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_6_kedesakan/sedia_Bi";
    private static final String NOTASIASATAN = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_7_notasiasatan/notasiasatan";
    private static final String SEDIAGH = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_7_sediaborangGH/sediaborangGH";
    private static final String SEDIABAUCER = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_sediabaucer_deposit/sediabaucerdeposit";
    private static final String AWARDBKL = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_8_SediaBrgKL/SediaBrgKL";
    private static final String SIJILUKUR = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_8_SijilPUPTG/sijilpuptg";
    private static final String TERIMAUKUR = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_TerimaSijilUkur/terimasijilukur";
    private static final String TERIMAPAB = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_9_pab1/pa_b1";
    private static final String WARTASEMULALUAS = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_10_wartasemula/wartasemula";

    private static final String PINDAANKPTSN = "http://xmlns.oracle.com/Sek8APT/Bpel_Sek8a_pindaankpsn/pindaan_kpsn";
    private static final String BORANGI = "http://xmlns.oracle.com/Application2/Sek_8abc_BorangI/Humantask1";

    //bpel_sek8bc
    private static final String SEDIASRTUPEN = "http://xmlns.oracle.com/Application2/Seksyen_8bc_1/kemasukan";
    private static final String SEDIABRGCD = "http://xmlns.oracle.com/Application2/Seksyen_8bc_2/sek_8bc_task";
    private static final String SEDIASRTMAKLUM = "http://xmlns.oracle.com/Application2/Seksyen_8bc_3/task_sek8bc_3";

    private static final Logger LOG = Logger.getLogger(Sek8aIntegrationFlowService.class);
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
//    public final String AGIHAN_PPELAN_LAPORAN_TANAH = "";
//    public final String SEDIA_TERIMA_KPSN_MMK = "";
//    public final String SEMAK_WARTA = "";
//    public final String SEMAK_TETAP_BICARA = "";
//    public final String KEDESAKAN = "";
//    public final String NOTA_SIASATAN = "";
//    public final String SEDIA_BORANGKL = "";
//    public final String TERIMA_PAB = "";
//    public final String WARTA_KELUASAN_SEMULA = "";

    public boolean completeTask(String kod, Permohonan p, Pengguna pengguna) {
        RefPeringkat ref = new RefPeringkat();
        if (ref.AGIHAN_PPELAN_LAPORAN_TANAH.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, AGIHANLAPORANTANAH)) {
                return true;
            }
        } else if (ref.ABC_STAGE.equals(kod)) {
            PermohonanPengambilan permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(p.getIdPermohonan());
            if ((permohonanPengambilan.getKatPengambilan().equals("1"))) {
                if (initiateBpel(p, AFLOW)) {
                    return true;
                }
            } else {
                if (initiateBpel(p, BCFLOW)) {
                    return true;
                }
            }
        } else if (ref.REKOD_KPSN_MMK.equals(kod)) {
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
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
        } else if (false) {
            //hantar ke daerah maklumat warta
            //need to check if required to create utiliti task to daerah receive the maklumat warta record before iniatiate new bpel 
        } else if (ref.SEMAK_WARTA.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, TETAPBICARA)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.PENYAMPAIAN_EF.equals(kod)) {
            //penyampaian borang EF
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
        } else if (ref.KEDESAKAN.equals(kod)) {
            //alternative flow if application have borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, KEDESAKAN)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.KEMUKA_BORANG_K.equals(kod)) {
            //alternative flow if application have borang I
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
        } else if (ref.NOTA_SIASATAN.equals(kod)) {
            //alternative flow if application have borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, NOTASIASATAN)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.SEMAK_TETAP_BICARA.equals(kod)) {
            //hantar notis borang k
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.NOTA_SIASATAN.equals(kod)) {
            //initiate notasiasatan
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, NOTASIASATAN)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.SEDIA_GH.equals(kod)) {
            //initiate sedia GH
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEDIAGH)) {
                return true;
            }

        } else if (ref.PENYAMPAIAN_GH.equals(kod)) {
            //hantar notis borang H
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;
        } else if (ref.SEDIA_BAUCER_DEPOSIT.equals(kod)) {
            //initiate award kemaskini bayaran borang K L
            //do not initiate back to endorsan borang K if application have a borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEDIABAUCER)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.TERIMA_AWARD.equals(kod)) {
            //initiate award kemaskini bayaran borang K L
            //do not initiate back to endorsan borang K if application have a borang I
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEDIA_BORANGKL.equals(kod)) {
            //initiate award kemaskini bayaran borang K L
            //do not initiate back to endorsan borang K if application have a borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, AWARDBKL)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.SIJILUKUR.equals(kod)) {
            //initiate award kemaskini bayaran borang K L
            //do not initiate back to endorsan borang K if application have a borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SIJILUKUR)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.TERIMASIJILUKUR.equals(kod)) {
            //initiate award kemaskini bayaran borang K L
            //do not initiate back to endorsan borang K if application have a borang I
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, TERIMAUKUR)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.TERIMA_PAB.equals(kod)) {
            //initiate terima PAB jupem
            //note: check from jupem bulk or one by one PAB
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, TERIMAPAB)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.WARTA_KELUASAN_SEMULA.equals(kod)) {
            //initiate warta semula luas if not equal to submission (more than 1/4 or 1%)
            //note: if less than 1/4 create task for sedia surat ang pampasan
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, WARTASEMULALUAS)) {
//                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.KEMASKINI_MAKLUMAT_TANAH.equals(kod)) {
            //Kemaskini maklumat tanah lps warta
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.KPSN_TIADA_PINDAAN.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, TETAPBICARA)) {
                return true;
            }

        } else if (ref.PEMBETULAN_WARTA.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, WARTABORANGD)) {
                return true;
            }
        } else if (ref.AGIHAN_LAPORAN_TANAH.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, AGIHANLAPORANTANAH)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.PINDAAN_KEPUTUSAN.equals(kod)) {
            if (initiateBpel(p, PINDAANKPTSN)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.SEDIA_BORANG_I.equals(kod)) {
            if (initiateBpel(p, BORANGI)) {
                deleteTugasanTable(p);
                return true;
            }

        } else if (ref.SEMAK_PA.equals(kod)) {
            //Kemaskini maklumat tanah lps warta
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.TAMBAHAN_PAMPASAN_LBHN_LUAS.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            deleteTugasanTable(p);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.AGIH_PA.equals(kod)) {
            if (initiateBpel(p, TERIMAPAB)) {
                deleteTugasanTable(p);
                return true;
            }

        }
        return false;

    }

    public boolean initiateAgihanLaporanTanah(Permohonan p) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(AGIHANLAPORANTANAH,
                    p.getIdPermohonan(), p.getCawangan().getKod() + ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Sek8aIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
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
        if (tugas != null) {
            tugasanUtiliti2DAO.delete(tugas);
        }
    }
}
