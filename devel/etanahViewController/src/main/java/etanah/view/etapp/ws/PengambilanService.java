/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import com.Ostermiller.util.Base64;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.EtappPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TBLINTPPTDERAFMMKDAO;
import etanah.dao.TBLINTPPTDOKUMENDAO;
import etanah.dao.TBLINTPPTHAKMILIKDAO;
import etanah.dao.TBLINTPPTMAKLUMATPENGAMBILANDAO;
import etanah.dao.TBLINTPPTWARTADAO;
import etanah.dao.TBLPPTINTDERAFMMKTAJUKDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.etapp.EtappPermohonan;
import etanah.model.etapp.TBLINTPPTDERAFMMK;
import etanah.model.etapp.TBLINTPPTDOKUMEN;
import etanah.model.etapp.TBLINTPPTHAKMILIK;
import etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN;
import etanah.model.etapp.TBLINTPPTWARTA;
import etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import etanah.view.etapp.PengambilanEtanahService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class PengambilanService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    EtappPermohonanDAO etappPermohonanDAO;
    @Inject
    TBLINTPPTMAKLUMATPENGAMBILANDAO tblintpptmaklumatpengambilandao;
    @Inject
    TBLINTPPTHAKMILIKDAO tblintppthakmilikdao;
    @Inject
    TBLINTPPTDOKUMENDAO tblintpptdokumendao;
    @Inject
    TBLINTPPTDERAFMMKDAO tblintpptderafmmkdao;
    @Inject
    TBLINTPPTWARTADAO tblintpptwartadao;
    @Inject
    TBLPPTINTDERAFMMKTAJUKDAO tblpptintderafmmktajukdao;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PengambilanEtanahService ambilService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PermohonanDAO mohonDAO;

    public EtappPermohonan etappPermohonan(String noFail) {
        EtappPermohonan etappMohon = new EtappPermohonan();
        Permohonan mohon = new Permohonan();
        etappMohon.setMohon(mohon);
        etappMohon.setNoFail(noFail);
        etappMohon.setInfoAudit(setInfoAudit());
        saveEtappPermohonan(etappMohon);
        return etappMohon;
    }

    public String insertMaklumatPengambilan_byObject(Tblintpptmaklumatpengambilan[] tblintpptmaklumatpengambilan) throws ParseException, Exception {
        String s = "";
        for (int i = 0; i < tblintpptmaklumatpengambilan.length; i++) {

            TBLINTPPTMAKLUMATPENGAMBILAN t = findByNofail(tblintpptmaklumatpengambilan[i].getNo_fail_jkptg());
            if (t != null) {
            } else {
                t = new TBLINTPPTMAKLUMATPENGAMBILAN();
                ambilService.setMohon(tblintpptmaklumatpengambilan[i]);
            }
            t.setFlagPemohonanSegera(tblintpptmaklumatpengambilan[i].getFlag_permohonan_segera());
            t.setFlagProses(tblintpptmaklumatpengambilan[i].getFlag_proses());
            t.setIdAgensi_myetapp(tblintpptmaklumatpengambilan[i].getId_agensi_myetapp());
            t.setIdKementerian_myetapp(tblintpptmaklumatpengambilan[i].getId_kementerian_myetapp());
            t.setJenisPengambilan(tblintpptmaklumatpengambilan[i].getJenis_pengambilan());
            t.setJenisPengambilanSegera(tblintpptmaklumatpengambilan[i].getJenis_pengambilan_segera());
            t.setJenisProjek_pengambilan(tblintpptmaklumatpengambilan[i].getJenis_projek_pengambilan());
            t.setKodDaerahPengambilan(tblintpptmaklumatpengambilan[i].getKod_daerah_pengambilan());
            t.setKodNegeriPengambilan(tblintpptmaklumatpengambilan[i].getKod_negeri_pengambilan());
            t.setNamaAgensi(tblintpptmaklumatpengambilan[i].getNama_agensi());
            t.setNamaDaerahPengambilan(tblintpptmaklumatpengambilan[i].getNama_daerah_pengambilan());
            t.setNamaKementerian(tblintpptmaklumatpengambilan[i].getNama_kementerian());
            t.setNamaNegeriPengambilan(tblintpptmaklumatpengambilan[i].getNama_negeri_pengambilan());
            t.setNoFailJKPTG(tblintpptmaklumatpengambilan[i].getNo_fail_jkptg());
            t.setNoRujukPTD(tblintpptmaklumatpengambilan[i].getNo_rujukan_ptd());
            t.setNoRujukPTG(tblintpptmaklumatpengambilan[i].getNo_rujukan_ptg());
            t.setNoRujSuratKJP(tblintpptmaklumatpengambilan[i].getNo_rujukan_surat_kjp());
            t.setTarikhPermohonan(tblintpptmaklumatpengambilan[i].getTarikh_permohonan() != null ? formatDate(tblintpptmaklumatpengambilan[i].getTarikh_permohonan()) : null);
            t.setTarikhSuratKJP(tblintpptmaklumatpengambilan[i].getTarikh_surat_kjp() != null ? formatDate(tblintpptmaklumatpengambilan[i].getTarikh_surat_kjp()) : null);
            t.setTujuan(tblintpptmaklumatpengambilan[i].getTujuan());
            t.setTujuanDlmEnglish(tblintpptmaklumatpengambilan[i].getTujuan_dalam_english());
            t.setTurut(tblintpptmaklumatpengambilan[i].getTurutan().toString());
            t.setAlamat1(tblintpptmaklumatpengambilan[i].getAlamat1());
            t.setAlamat2(tblintpptmaklumatpengambilan[i].getAlamat2());
            t.setAlamat3(tblintpptmaklumatpengambilan[i].getAlamat3());
            t.setAlamat4(tblintpptmaklumatpengambilan[i].getAlamat4());
            t.setPoskod(tblintpptmaklumatpengambilan[i].getPoskod());
            t.setKodNegeri(tblintpptmaklumatpengambilan[i].getKod_negeri());
            t.setEmail(tblintpptmaklumatpengambilan[i].getEmail());
            t.setNoTel(tblintpptmaklumatpengambilan[i].getNoTel());
            t.setInfoAudit(setInfoAudit());
            t = saveTblintpptmaklumatpengambilan(t);

            ambilService.setPemohon(tblintpptmaklumatpengambilan[i].getNo_fail_jkptg());
        }
        return s;
    }

    @Transactional
    public TBLINTPPTMAKLUMATPENGAMBILAN saveTblintpptmaklumatpengambilan(TBLINTPPTMAKLUMATPENGAMBILAN t) {

        return tblintpptmaklumatpengambilandao.saveOrUpdate(t);
    }

    public String insertHakmilikList_byObject(Tblintppthakmilik[] tblintppthakmilik) throws ParseException, Exception {
        String s = "";
        for (int i = 0; i < tblintppthakmilik.length; i++) {
            ambilService.setMohon(tblintppthakmilik[i].getNo_fail_jkptg(), tblintppthakmilik[i].getFlag_proses(), "");
            etanah.model.Hakmilik hak = hakmilikDAO.findById(tblintppthakmilik[i].getId_hakmilik());
            if (hak == null) {
                throw new RuntimeException("Hakmilik tidak dikenali");
            }
            TBLINTPPTHAKMILIK th = new TBLINTPPTHAKMILIK();

            th.setFlagProses(tblintppthakmilik[i].getFlag_proses());
            th.setIdHakmilik(tblintppthakmilik[i].getId_hakmilik());
            th.setKodDaerah(tblintppthakmilik[i].getKod_daerah());
            th.setKodLuasAmbil(tblintppthakmilik[i].getKod_luas_ambil());
            th.setKodLuasPA(tblintppthakmilik[i].getKod_luas_pa());
            th.setKodMukim(tblintppthakmilik[i].getKod_mukim());
            th.setKodNegeri(tblintppthakmilik[i].getKod_negeri());
            th.setKodUnitHakmilik(tblintppthakmilik[i].getKod_unit_hakmilik());
            th.setKod_luas(tblintppthakmilik[i].getKod_luas_asal());
            th.setLuas(tblintppthakmilik[i].getLuas_asal() != null ? tblintppthakmilik[i].getLuas_asal() : null);
            th.setLuasAmbil(tblintppthakmilik[i].getLuas_ambil() != null ? tblintppthakmilik[i].getLuas_ambil() : null);
            th.setLuasPA(tblintppthakmilik[i].getLuas_pa() != null ? tblintppthakmilik[i].getLuas_pa() : null);
            th.setNoFailJKPTG(tblintppthakmilik[i].getNo_fail_jkptg());
            th.setNoHakmilik(tblintppthakmilik[i].getNo_hakmilik());
            th.setNoLot(tblintppthakmilik[i].getNo_lot());
            th.setNoLotBaru(tblintppthakmilik[i].getNo_lot_baru());
            th.setNoPA(tblintppthakmilik[i].getNo_pa());
            th.setNoPU(tblintppthakmilik[i].getNo_pu());
            th.setNoSyit(tblintppthakmilik[i].getNo_syit());
            th.setTurut(String.valueOf(tblintppthakmilik[i].getTurutan()));
            th.setInfoAudit(setInfoAudit());
            if (th.getFlagProses().equals("BorangK")) {
                th.setStatusBorangK(tblintppthakmilik[i].getStatus_borangk());
                th.setTarikhBorangK(formatDate(tblintppthakmilik[i].getTarikh_borangk()) != null ? formatDate(tblintppthakmilik[i].getTarikh_borangk()) : null);

            }

            th = saveTblintppthakmilik(th);
            ambilService.setHakmilikPermohonan(th);
//            if (tblintppthakmilik[i].getFlag_proses().equals("WartaS8") || tblintppthakmilik[i].getFlag_proses().equals("BorangK")) {
//                ambilService.initiateREG(tblintppthakmilik[i].getNo_fail_jkptg(), tblintppthakmilik[i].getFlag_proses());
//            }
        }

//        if (tblintppthakmilik[i].getFlag_proses().equals("WartaS8") || tblintppthakmilik[i].getFlag_proses().equals("BorangK")) {
//                ambilService.initiateREG(tblintppthakmilik[i].getNo_fail_jkptg(), tblintppthakmilik[i].getFlag_proses());
//            }
//        
        return s;
    }

    public String validateString(String s) {


        return s;
    }

    public Date formatDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (StringUtils.isBlank(s)) {
            date = null;
        } else {
            date = sdf.parse(s);
        }

        return date;
    }

    @Transactional
    public TBLINTPPTHAKMILIK saveTblintppthakmilik(TBLINTPPTHAKMILIK t) {
        return tblintppthakmilikdao.saveOrUpdate(t);
    }

    public String insertMaklumatWarta_byObject(Tblintpptwarta[] tblintpptwarta) throws ParseException, Exception {
        String s = "";
        for (int i = 0; i < tblintpptwarta.length; i++) {
            ambilService.setMohon(tblintpptwarta[i].getNo_fail_jkptg(), tblintpptwarta[i].getFlag_proses(), "");

            TBLINTPPTWARTA warta = new TBLINTPPTWARTA();
            // warta.setInfoAudit();
            warta.setNoFailJKPTG(tblintpptwarta[i].getNo_fail_jkptg());
            warta.setNoWarta(tblintpptwarta[i].getNo_warta());
            warta.setTarikhWarta(tblintpptwarta[i].getTarikh_warta() != null ? formatDate(tblintpptwarta[i].getTarikh_warta()) : null);
            warta.setTurut(String.valueOf(tblintpptwarta[i].getTurutan()));
            warta.setInfoAudit(setInfoAudit());
            warta = saveTblintpptwarta(warta);
            ambilService.setMohonRujLuar(tblintpptwarta[i].getNo_fail_jkptg());
//            if (tblintpptwarta[i].getFlag_proses().equals("WartaS8") || tblintpptwarta[i].getFlag_proses().equals("BorangK")) {
//                ambilService.initiateREG(tblintpptwarta[i].getNo_fail_jkptg(), tblintpptwarta[i].getFlag_proses());
//            }
            // ambilService.set
        }
        return s;
    }

    @Transactional
    public TBLINTPPTWARTA saveTblintpptwarta(TBLINTPPTWARTA t) {
        return tblintpptwartadao.saveOrUpdate(t);
    }

    public String insertDerafMMKTajuk_byObject(Tblintpptderafmmktajuk[] tblintpptderafmmktajuk) throws ParseException, Exception {
        String s = "";
        for (int i = 0; i < tblintpptderafmmktajuk.length; i++) {
            ambilService.setMohon(tblintpptderafmmktajuk[i].getNo_fail_jkptg(), tblintpptderafmmktajuk[i].getFlag_proses(), "");

            TBLPPTINTDERAFMMKTAJUK deraftajuk = new TBLPPTINTDERAFMMKTAJUK();
            deraftajuk.setFlagProses(tblintpptderafmmktajuk[i].getFlag_proses());
            deraftajuk.setJenisWaktuSidang(tblintpptderafmmktajuk[i].getJenis_waktu_sidang());
            deraftajuk.setKeteranganSidang(tblintpptderafmmktajuk[i].getKeterangan_sidang());
            deraftajuk.setNoFailJKPTG(tblintpptderafmmktajuk[i].getNo_fail_jkptg());
            deraftajuk.setTajuk(tblintpptderafmmktajuk[i].getTajuk());
            deraftajuk.setTarikhSidang(tblintpptderafmmktajuk[i].getTarikh_sidang() != null ? formatDate(tblintpptderafmmktajuk[i].getTarikh_sidang()) : null);
            deraftajuk.setTempatSidang(tblintpptderafmmktajuk[i].getTempat_sidang());
            deraftajuk.setTurut(String.valueOf(tblintpptderafmmktajuk[i].getTurutan()));
            deraftajuk.setWaktuSidang(tblintpptderafmmktajuk[i].getWaktu_sidang());
            deraftajuk.setWaktuSidang_keterangan(tblintpptderafmmktajuk[i].getWaktu_sidang_keterangan());
            deraftajuk.setInfoAudit(setInfoAudit());
            deraftajuk = saveTblpptintderafmmktajuk(deraftajuk);
//            ambilService.setPermohonanKertas(tblintpptderafmmktajuk[i].getNo_fail_jkptg());
        }
        return s;
    }

    @Transactional
    public TBLPPTINTDERAFMMKTAJUK saveTblpptintderafmmktajuk(TBLPPTINTDERAFMMKTAJUK t) {
        return tblpptintderafmmktajukdao.saveOrUpdate(t);
    }

    public String insertDokumen_byObject(Tblintpptdokumen[] tblintpptdokumen) throws Exception {
        String s = "";
        for (int i = 0; i < tblintpptdokumen.length; i++) {

            ambilService.setMohon(tblintpptdokumen[i].getNo_fail_jkptg(), tblintpptdokumen[i].getFlag_proses(), "");

            TBLINTPPTDOKUMEN dok = new TBLINTPPTDOKUMEN();
            dok.setFlagProses(tblintpptdokumen[i].getFlag_proses());
            dok.setIdHakmilik(tblintpptdokumen[i].getId_hakmilik());
            dok.setJenisFail(tblintpptdokumen[i].getJenis_fail());
            dok.setJenisFormatFail(tblintpptdokumen[i].getJenis_format_fail());
            // dok.setKandungan(tblintpptdokumen[i].getContent_upload());
            dok.setKodDaerah(tblintpptdokumen[i].getKod_daerah());
            dok.setKodMukim(tblintpptdokumen[i].getKod_mukim());
            dok.setKodNegeri(tblintpptdokumen[i].getKod_negeri());
            dok.setNamaDokumen(tblintpptdokumen[i].getNama_dokumen());
            dok.setNoFailJKPTG(tblintpptdokumen[i].getNo_fail_jkptg());
            dok.setNoHakmilik(tblintpptdokumen[i].getNo_hakmilik());
            dok.setTajukDokumen(tblintpptdokumen[i].getTajuk_dokumen());
            dok.setTurut(String.valueOf(tblintpptdokumen[i].getTurutan()));
            dok.setInfoAudit(setInfoAudit());
            ambilService.setDokumen(dok, tblintpptdokumen[i].getDataBytes(), tblintpptdokumen[i].getContent64());
            dok = saveTblintpptdokumen(dok);
        }
        return s;
    }

    @Transactional
    public TBLINTPPTDOKUMEN saveTblintpptdokumen(TBLINTPPTDOKUMEN a) {
        return tblintpptdokumendao.saveOrUpdate(a);
    }

    public String insertDerafMMK_byObject(Tblintpptderafmmk[] tblintpptderafmmk) throws Exception {
        String s = "";
        String noFail = "";
        for (int i = 0; i < tblintpptderafmmk.length; i++) {
            noFail = tblintpptderafmmk[i].getNo_fail_jkptg();
            ambilService.setMohon(tblintpptderafmmk[i].getNo_fail_jkptg(), tblintpptderafmmk[i].getFlag_proses(), "");
            TBLINTPPTDERAFMMK deraf = new TBLINTPPTDERAFMMK();
            deraf.setFlagProses(tblintpptderafmmk[i].getFlag_proses());
            deraf.setJenisMaklumatMMK(tblintpptderafmmk[i].getFlag_jenis_mmk());
            deraf.setKeteranganMMK(tblintpptderafmmk[i].getKeterangan_item_mmk());
            deraf.setNoFailJKPTG(tblintpptderafmmk[i].getNo_fail_jkptg());
            deraf.setNoItemMMK(String.valueOf(tblintpptderafmmk[i].getNo_item_mmk()));
            deraf.setTurut(String.valueOf(tblintpptderafmmk[i].getTurutan()));
            deraf.setInfoAudit(setInfoAudit());
            deraf = saveTblintpptderafmmk(deraf);

        }

        ambilService.setMohonRujLuar(noFail);
        return s;
    }

    @Transactional
    public TBLINTPPTDERAFMMK saveTblintpptderafmmk(TBLINTPPTDERAFMMK d) {
        return tblintpptderafmmkdao.saveOrUpdate(d);
    }

    @Transactional
    public EtappPermohonan saveEtappPermohonan(EtappPermohonan e) {
        return etappPermohonanDAO.saveOrUpdate(e);
    }

    public InfoAudit setInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        return ia;
    }

    public TBLINTPPTMAKLUMATPENGAMBILAN findByNofail(String noFail) {
        String query = "select p from etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (TBLINTPPTMAKLUMATPENGAMBILAN) q.uniqueResult();
    }

    public String uploadDokumen(String idFail, String tajuk, String convertBacktoNormalString, String kodDokumen, String fileName, String contentType, String catatan) {
        try {
            EtappPermohonan e = ambilService.findByNofail(idFail);
            Permohonan mohon = mohonDAO.findById(e.getMohon().getIdPermohonan());
            Dokumen doc = new Dokumen();
            KodKlasifikasi kodk = new KodKlasifikasi();
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
//            KodDokumen kodd = kodDokumenDAO.findById(kodDokumen);
            KodDokumen kodd = new KodDokumen();
            //            if (kodd != null) 
            //edit for kodDokumen 

            if (catatan == "Borang A") {
                kodd = kodDokumenDAO.findById("A");
            } else if (catatan == "Borang B") {
                kodd = kodDokumenDAO.findById("B");
            } else if (catatan == "Borang C") {
                kodd = kodDokumenDAO.findById("C");
            } else if (catatan == "Borang D") {
                kodd = kodDokumenDAO.findById("D");
            } else if (catatan == "Borang I") {
                kodd = kodDokumenDAO.findById("I");
            } else if (catatan == "Borang K") {
                kodd = kodDokumenDAO.findById("K");
            } else if (catatan == "DERAF MB/MMK") {
                kodd = kodDokumenDAO.findById("MMKN");
            } else if (catatan == "SIJIL PEMBEBASAN UKUR") {
                kodd = kodDokumenDAO.findById("SPU");
            } else if (catatan == "SURAT IRINGAN") {
                kodd = kodDokumenDAO.findById("SII");
            } else {
                kodd = kodDokumenDAO.findById("ETP");
            }
            doc.setTajuk(kodd.getNama() + "-" + catatan);
            doc.setInfoAudit(setInfoAudit());
            doc.setKodDokumen(kodd);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc = dokumenDAO.saveOrUpdate(doc);


            DMSUtil dmsUtil = new DMSUtil();
            byte[] b = Base64.decodeToBytes(convertBacktoNormalString);
            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(mohon));
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            fileUtil.saveFile(fileName, in);
            String fizikalPath = dmsUtil.getFizikalPath(mohon) + File.separator + fileName;
            //FileOutputStream fout = new FileOutputStream(fizikalPath);
//		Base64.decodeToStream(convertBacktoNormalString, fout);
//            jTekUlas.getPermohonan().getFolderDokumen().get
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(mohon.getFolderDokumen());
            kf.setInfoAudit(setInfoAudit());
            updateKandunganFolder(kf);
            updatePathDokumen(fizikalPath, doc, contentType);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return "";
    }
    @Transactional
    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
//        Session sess = injector.getProvider(Session.class).get();
//        Transaction tx = sess.beginTransaction();
//        tx.begin();

        d = dokumenDAO.saveOrUpdate(d);
//        if (d != null) {
//            tx.commit();
//        } else {
//            tx.rollback();
//        }
    }
    @Transactional
    private void updateKandunganFolder(KandunganFolder kf) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");

//        Session sess = injector.getProvider(Session.class).get();
//        Transaction tx = sess.beginTransaction();
//        tx.begin();

        kf = kandunganFolderDAO.saveOrUpdate(kf);
//        if (kf != null) {
//            tx.commit();
//        } else {
//            tx.rollback();
//        }
    }
}
