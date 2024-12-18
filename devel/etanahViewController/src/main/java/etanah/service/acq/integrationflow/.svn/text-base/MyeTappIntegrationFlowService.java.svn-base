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
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.TugasanUtiliti2;
import etanah.model.ambil.InfoWarta;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import etanah.etapp.etappclient.MyEtappService;
import etanah.etapp.etappclient.form.DokumenForm;
import etanah.etapp.etappclient.form.HakmilikForm;
import etanah.etapp.etappclient.form.PermohonanForm;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.service.acq.service.MyEtappMapDataService;
import etanah.service.acq.service.MyEtappPendaftaranService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class MyeTappIntegrationFlowService {

    private static final String SEK4_CHARTING_MOHON = "http://xmlns.oracle.com/MyEtappSek4/MyEtappSek4_1/agihan_tugas";
    private static final String SEK4_KEPUTUSAN_MMK = "http://xmlns.oracle.com/MyEtappSek4/MyEtappSek4_2/keputusan_mmkn";
    private static final String SEK4_SEMAK_WARTA = "http://xmlns.oracle.com/MyEtappSek4/MyEtappSek4_3/terima_warta_borang_b";

    private static final String SEK8_CHARTING_MOHON = "http://xmlns.oracle.com/MyEtappSek8/MyEtappSek8_1/agih_charting";
    private static final String SEK8_KEPUTUSAN_MMK = "http://xmlns.oracle.com/MyEtappSek8/MyEtappSek8_2/terima_mmk";
  @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(MyeTappIntegrationFlowService.class);
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDAO;
    @Inject
    MyEtappMapDataService myEtappMapDataService;
    @Inject
    MyEtappPendaftaranService myEtappPendaftaranService;
    @Inject
    TugasanUtiliti2DAO tugasanUtiliti2DAO;
    @Inject
    MyEtappService myEtappService;
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

    public boolean completeTask(String kod, Permohonan p, Pengguna pengguna, KodDaerah daerah) throws IOException {
        RefPeringkat ref = new RefPeringkat();
        if (ref.SEK4_DAFTAR_BA_MMK.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEK4_CHARTING_MOHON, daerah)) {
            }
//            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEK4_HANTAR_MMK.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEK4_TERIMA_MMK.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEK4_KEPUTUSAN_MMK, daerah)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.SEK4_HANTAR_BORANGA.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarBorangA(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);

        } else if (ref.SEK4_TERIMA_WARTA.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEK4_SEMAK_WARTA.equals(kod)) {
            if (initiateBpel(p, SEK4_SEMAK_WARTA, daerah)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.SEK4_HANTAR_BORANGB.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarBorangB(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);

        } else if (ref.SEK8_DAFTAR_BORANGC.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEK8_CHARTING_MOHON, daerah)) {
                insertTugasanTable(p, ref.SEK8_HANTAR_MMK, pengguna.getIdPengguna());
            }
        } else if (ref.SEK8_HANTAR_MMK.equals(kod)) {
            //sedia kertas MMK
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
            return true;

        } else if (ref.SEK8_TERIMA_MMK.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            if (initiateBpel(p, SEK8_KEPUTUSAN_MMK, daerah)) {
                deleteTugasanTable(p);
                return true;
            }
        } else if (ref.SEK8_TERIMA_BORANGC.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            insertTugasanTable(p, kod, pengguna.getIdPengguna());
        } else if (ref.SEK8_HANTAR_BORANGC.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarBorangC(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);

        } else if (ref.SEK8_ENDORS_BD.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            myEtappPendaftaranService.endors(p, 1, null, pengguna);
            deleteTugasanTable(p);
            insertTugasanTable(p, ref.SEK8_HANTAR_BD, pengguna.getIdPengguna());
        } else if (ref.SEK8_HANTAR_BD.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarBorangD(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);
            deleteTugasanTable(p);

        } else if (ref.SEK8_ENDORS_BK.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            myEtappPendaftaranService.endors(p, 2, null, pengguna);
//             deleteTugasanTable(p);
            insertTugasanTable(p, ref.SEK8_HANTAR_BK, pengguna.getIdPengguna());
        } else if (ref.SEK8_HANTAR_BK.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarBorangK(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);
            deleteTugasanTable(p);

        } else if (ref.SEK8_SPU.equals(kod)) {
            LOG.info("Start initiate task" + kod);
//             deleteTugasanTable(p);
            insertTugasanTable(p, ref.SEK8_HANTAR_SPU, pengguna.getIdPengguna());
        } else if (ref.SEK8_HANTAR_SPU.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarsijilPembebasanUkur(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);
            deleteTugasanTable(p);
        } else if (ref.SEK8_TERIMA_HS.equals(kod)) {
            LOG.info("Start initiate task" + kod);
//             deleteTugasanTable(p);
            insertTugasanTable(p, ref.SEK8_TERIMA_HS, pengguna.getIdPengguna());
        } else if (ref.SEK8_HANTAR_HS.equals(kod)) {
            LOG.info("Start initiate task" + kod);
            PermohonanForm permohonanF = myEtappMapDataService.setPermohonanValue(p, kod);
            List< HakmilikForm> listHakmilikForm = myEtappMapDataService.setHakmilikValue(p, kod);
            List<DokumenForm> listDokumenForm = myEtappMapDataService.setDokumenValue(p, kod);
            myEtappService.hantarmaklumanPU(p.getIdPermohonan(), permohonanF, listHakmilikForm, listDokumenForm);
            deleteTugasanTable(p);
        }
        return false;
    }

    @Transactional
    public void insertTugasanTable(Permohonan mohon, String kodPeringkat, String idPengguna) {
        TugasanUtiliti tugas = findTugasanUtilitiByIdPermohonan(mohon.getIdPermohonan());
        if (tugas != null) {
        } else {
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
        insertToRelatedTable(mohon, kodPeringkat, idPengguna);
        tugasanUtilitiDAO.save(tugas);
    }

    public boolean initiateBpel(Permohonan p, String BPEL, KodDaerah daerah) {
        try {
            boolean success = false;
            if (WorkFlowService.initiateTask(BPEL,
                    p.getIdPermohonan(), daerah.getKod() + ",00", p.getInfoAudit().getDimasukOleh().getIdPengguna(),
                    p.getKodUrusan().getNama())) {
                success = Boolean.TRUE;
            }
            return success;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MyeTappIntegrationFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Transactional
    public void deleteTugasanTable(Permohonan mohon) {
        TugasanUtiliti2 tugas = tugasanUtiliti2DAO.findById(mohon.getIdPermohonan());
        if (tugas != null) {
            tugasanUtiliti2DAO.delete(tugas);
        }
    }

    private void insertToRelatedTable(Permohonan mohon, String kodPeringkat, String idPengguna) {
        RefPeringkat ref = new RefPeringkat();

        if (ref.SEK4_DAFTAR_BA_MMK.equals(kodPeringkat)) {
            MMKStage(mohon, kodPeringkat, idPengguna);
        }
        if (ref.SEK4_TERIMA_WARTA.equals(kodPeringkat)) {
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

    private TugasanUtiliti findTugasanUtilitiByIdPermohonan(String idPermohonan) {
        String query = "select t from  etanah.model.TugasanUtiliti t "
                + " where t.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (TugasanUtiliti) q.uniqueResult();
        }
}
