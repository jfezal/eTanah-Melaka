/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.PermohonanDAO;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.AduanLokasiDAO;
import etanah.dao.AduanSiasatanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.EnkuiriPenguatkuasaanDAO;
import etanah.dao.OperasiAgensiDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.NotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.AduanTindakanDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.EnkuiriPenguatkuasaanKehadiranDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.IntegrasiPermohonanDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.OperasiBarangPenguatkuasaanDAO;
import etanah.dao.OperasiPenguatkuasaanPasukanDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PenjaminBarangRampasanDAO;
import etanah.dao.PenyerahBarangOperasiDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasImejDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanLaporanUsahaDAO;
import etanah.dao.PermohonanManualDAO;
//import etanah.dao.PermohonanNotaDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.dao.PermohonanPihakMembantahDAO;
import etanah.dao.PermohonanSaksiDAO;
import etanah.dao.PermohonanSemakDokumenDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.WarisOrangKenaSyakDAO;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanSemakDokumen;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.BarangRampasan;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.AduanLokasi;
import etanah.model.AduanSiasatan;
import etanah.model.AduanTindakan;
import etanah.model.Akaun;
import etanah.model.KodBandarPekanMukim;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodStatusBarangRampasan;
import etanah.model.OperasiAgensi;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.EnkuiriPenguatkuasaanKehadiran;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodBangsa;
import etanah.model.KodDokumen;
import etanah.model.KodKementerian;
import etanah.model.KodTransaksiTuntut;
import etanah.model.Kompaun;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.OperasiBarangPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.PenjaminBarangRampasan;
import etanah.model.PenyerahBarangOperasi;
import etanah.model.PermohonanDokumenIringan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasImej;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanLaporanUsaha;
import etanah.model.PermohonanManual;
//import etanah.model.PermohonanNota;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakMembantah;
import etanah.model.PermohonanSaksi;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanUkur;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.model.WarisOrangKenaSyak;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.mail.Folder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

//import java.util.List;
/**
 *
 * @author farah.shafilla
 */
public class EnforceService {

    @Inject
    PermohonanKertasImejDAO permohonanKertasImejDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanSaksiDAO permohonanSaksiDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    AduanSiasatanDAO aduanSiasatanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    EnkuiriPenguatkuasaanDAO enkuiriPenguatkuasaanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    AduanTindakanDAO aduanTindakanDAO;
    @Inject
    PermohonanSemakDokumenDAO permohonanSemakDokumenDAO;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    PermohonanPihakMembantahDAO permohonanPihakMembantahDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    PenjaminBarangRampasanDAO penjaminBarangRampasanDAO;
    @Inject
    WarisOrangKenaSyakDAO warisOrangKenaSyakDAO;
    @Inject
    EnkuiriPenguatkuasaanKehadiranDAO enkuiriPenguatkuasaanKehadiranDAO;
    private static final Logger LOG = Logger.getLogger(EnforceService.class);
    @Inject
    IntegrasiPermohonanDokumenDAO integrasiPermohonanDokumenDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    FolderDokumenDAO folderDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    PenyerahBarangOperasiDAO penyerahBarangOperasiDAO;
    @Inject
    OperasiBarangPenguatkuasaanDAO operasiBarangPenguatkuasaanDAO;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanLogDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanLaporanUsahaDAO permohonanLaporanUsahaDAO;

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.update(permohonan);
    }

    @Transactional
    public void savePermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void deletePermohonan(Permohonan permohonan) {
        permohonanDAO.delete(permohonan);
    }

    @Transactional
    public void simpanAduanLokasi(AduanLokasi aduanLokasi) {
        aduanLokasiDAO.saveOrUpdate(aduanLokasi);
    }

    @Transactional
    public void simpanEnkuiriPenguatkuasaan(EnkuiriPenguatkuasaan enkuiriPenguatkuasaan) {
        enkuiriPenguatkuasaanDAO.saveOrUpdate(enkuiriPenguatkuasaan);
    }

    @Transactional
    public void simpanRujukan(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanDokumen(Dokumen dokumen) {
        dokumenDAO.saveOrUpdate(dokumen);
    }

    @Transactional
    public void simpanAduanOrangDisyaki(AduanOrangKenaSyak aduanOrangKenaSyak) {
        aduanOrangKenaSyakDAO.saveOrUpdate(aduanOrangKenaSyak);
    }

    @Transactional
    public void simpanSaksi(PermohonanSaksi permohonanSaksi) {
        permohonanSaksiDAO.saveOrUpdate(permohonanSaksi);
    }

    @Transactional
    public void simpanBarangRampasan(BarangRampasan barangRampasan) {
        barangRampasanDAO.saveOrUpdate(barangRampasan);
    }

    @Transactional
    public void updateBarangRampasan(BarangRampasan barangRampasan) {
        barangRampasanDAO.update(barangRampasan);
    }

    @Transactional
    public void updateOKSKompaun(AduanOrangKenaSyak aduanOrangKenaSyak) {
        aduanOrangKenaSyakDAO.saveOrUpdate(aduanOrangKenaSyak);
    }

    @Transactional
    public void updateSaksi(PermohonanSaksi permohonanSaksi) {
        permohonanSaksiDAO.saveOrUpdate(permohonanSaksi);
    }

    @Transactional
    public void updateBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        permohonanLaporanBangunanDAO.saveOrUpdate(permohonanLaporanBangunan);
    }

    @Transactional
    public void updateSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        laporanTanahSempadanDAO.saveOrUpdate(laporanTanahSempadan);
    }

    @Transactional
    public void updateOperasiAgensi(OperasiAgensi operasiAgensi) {
        operasiAgensiDAO.saveOrUpdate(operasiAgensi);
    }

    @Transactional
    public void deleteAll(AduanOrangKenaSyak aduanOrangKenaSyak) {
        aduanOrangKenaSyakDAO.delete(aduanOrangKenaSyak);
    }

    @Transactional
    public void deleteAll(PermohonanSaksi permohonanSaksi) {
        permohonanSaksiDAO.delete(permohonanSaksi);
    }

    @Transactional
    public void deleteAllLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.delete(laporanTanah);
    }

    @Transactional
    public void deleteAllLokasi(AduanLokasi aduanLokasi) {
        aduanLokasiDAO.delete(aduanLokasi);
    }

    @Transactional
    public void deleteAllBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        permohonanLaporanBangunanDAO.delete(permohonanLaporanBangunan);
    }

    @Transactional
    public void deleteAllSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        laporanTanahSempadanDAO.delete(laporanTanahSempadan);
    }

    @Transactional
    public void deleteBarangRampasan(BarangRampasan barangRampasan) {
        barangRampasanDAO.delete(barangRampasan);
    }

    @Transactional
    public void editOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        aduanOrangKenaSyakDAO.update(aduanOrangKenaSyak);
    }

    @Transactional
    public void editSaksi1(PermohonanSaksi permohonanSaksi) {
        permohonanSaksiDAO.update(permohonanSaksi);
    }

    @Transactional
    public void editBarangRampasan(BarangRampasan barangRampasan) {
        barangRampasanDAO.update(barangRampasan);
    }

    @Transactional
    public void simpanAduanSiasatan(AduanSiasatan aduanSiasatan) {
        aduanSiasatanDAO.saveOrUpdate(aduanSiasatan);
    }

    @Transactional
    public void simpanhakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    @Transactional
    public void simpanOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        operasiPenguatkuasaanDAO.saveOrUpdate(operasiPenguatkuasaan);
    }

    @Transactional
    public void simpanBayaran(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.saveOrUpdate(permohonanTuntutanKos);
    }

    @Transactional
    public void deleteBayaran(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }

    @Transactional
    public void deleteAll2(Dokumen d) {
        dokumenDAO.delete(d);
    }

    public Dokumen findDokumen(Long id) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        //q.setString("idH", idH);
        return (Dokumen) q.uniqueResult();
    }

    @Transactional
    public void saveNotis(Notis notis) {
        notisDAO.save(notis);
        System.out.println("idNotis :" + notis.getIdNotis());
    }

    @Transactional
    public void saveDokumenNotis(Dokumen docu) {
        dokumenDAO.save(docu);
        System.out.println("idDocument :" + docu.getIdDokumen());
    }

    public List<Dokumen> findDokumenList(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select d from Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = f.kod "
                + "and a.idPermohonan = :idPermohonan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> findDokumenListAndKodDokumen(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        String query = "select d from Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = f.kod "
                + "and a.idPermohonan = :idPermohonan "
                + "and d.kodDokumen.kod = :kodDokumen";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    //added by Tu
    @Transactional
    public void saveAduanTindakan(AduanTindakan aduanTindakan) {
        aduanTindakanDAO.saveOrUpdate(aduanTindakan);
    }

    public List<AduanOrangKenaSyak> findByID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public AduanOrangKenaSyak findOksByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.AduanOrangKenaSyak p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanOrangKenaSyak) q.uniqueResult();
    }

    public List<PermohonanSaksi> findByIDSaksi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanSaksi b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanSaksi> findByIDSaksi(String idPermohonan, Long idOperasi, String statusSaksi) {
        String query = "SELECT b FROM etanah.model.PermohonanSaksi b where b.permohonan.idPermohonan = :idPermohonan AND b.operasiPenguatkuasaan.idOperasi = :idOperasi AND b.statusSaksi = :statusSaksi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        q.setString("statusSaksi", statusSaksi);
        return q.list();
    }

    public List<BarangRampasan> findBarangRampasanByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public AduanLokasi findAduanLokasiByIdAduan(Long idAduanLokasi) {
        String query = "Select p FROM etanah.model.AduanLokasi p WHERE p.idAduanLokasi = :idAduanLokasi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idAduanLokasi", idAduanLokasi);
        return (AduanLokasi) q.uniqueResult();
    }

    public AduanLokasi findAduanLokasiByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.AduanLokasi p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanLokasi) q.uniqueResult();
    }

    public AduanOrangKenaSyak findAduanOrangKenaSyakByIdaduanOrangKenaSyak(Long idOrangKenaSyak) {
        String query = "Select p FROM etanah.model.AduanOrangKenaSyak p WHERE p.idOrangKenaSyak = :idOrangKenaSyak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOrangKenaSyak", idOrangKenaSyak);
        return (AduanOrangKenaSyak) q.uniqueResult();
    }

    public PermohonanSaksi findSaksiByIdSaksi(Long idSaksi) {
        String query = "Select p FROM etanah.model.PermohonanSaksi p WHERE p.idSaksi = :idSaksi order by p.nama desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idSaksi", idSaksi);
        return (PermohonanSaksi) q.uniqueResult();
    }

    public Kompaun findKompaunByIdSaksi(Long idSaksi) {
        String query = "Select p FROM etanah.model.Kompaun p WHERE p.saksi.idSaksi = :idSaksi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idSaksi", idSaksi);
        return (Kompaun) q.uniqueResult();
    }

    public PermohonanLaporanBangunan findByIdLaporanBangunan(Long idLaporBangunan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanBangunan p WHERE p.idLaporBangunan = :idLaporBangunan order by p.nama desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporBangunan", idLaporBangunan);
        return (PermohonanLaporanBangunan) q.uniqueResult();
    }

    public LaporanTanahSempadan findSaksiByIdLaporTanahSempadan(Long idLaporTanahSpdn) {
        String query = "Select p FROM etanah.model.LaporanTanahSempadan p WHERE p.idLaporTanahSpdn = :idLaporTanahSpdn order by p.nama desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporTanahSpdn", idLaporTanahSpdn);
        return (LaporanTanahSempadan) q.uniqueResult();
    }

    public OperasiPenguatkuasaan findOperasiPenguatkuasaanByIdpermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (OperasiPenguatkuasaan) q.uniqueResult();
    }

    public AduanSiasatan findByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanSiasatan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanSiasatan) q.uniqueResult();
    }

    public EnkuiriPenguatkuasaan findByIDEnkuiriPenguatkuasaan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.EnkuiriPenguatkuasaan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (EnkuiriPenguatkuasaan) q.uniqueResult();
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMByCawangan(String kodBandarPekanMukim) {
        String query = "SELECT b FROM etanah.model.KodBandarPekanMukim b where b.daerah.kod = :kodBandarPekanMukim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodBandarPekanMukim", kodBandarPekanMukim);
        return q.list();

    }
    
    public List<KodBandarPekanMukim> getSenaraiKodBPMAll() {
        String query = "SELECT b FROM etanah.model.KodBandarPekanMukim b";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setString("kodBandarPekanMukim", kodBandarPekanMukim);
        return q.list();

    }

    public BarangRampasan findBarangRampasanByIdBarang(Long idBarang) {
        String query = "Select p FROM etanah.model.BarangRampasan p WHERE p.idBarang = :idBarang";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBarang", idBarang);
        return (BarangRampasan) q.uniqueResult();
    }

    public List<AduanLokasi> findByIDs(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanLokasi b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findByIDOperasi(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi order by b.idBarang desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatusBarangRampasan() {
        String query = "SELECT b FROM etanah.model.KodStatusBarangRampasan b";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setString("idOperasi", idOperasi);
        return q.list();
    }

    public PermohonanRujukanLuar findLaporanPolisByIdpermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan and p.agensi.kod = '0302'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findLaporanPolisByIdpermohonan2(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public HakmilikPermohonan findhakmilikPermohonanByIdpermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public PermohonanKertas findhakmilikPermohonanKertasByIdpermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanKertas p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<Notis> getNotis(long idNotis) {
        System.out.println("idNotis service : " + idNotis);
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.idNotis = :id_notis");
        q.setParameter("id_notis", idNotis);
        return q.list();
    }

    public AduanTindakan findDendaByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanTindakan b where b.permohonan.idPermohonan = :idPermohonan and b.tindakan.kod ='DEN'";
        return (AduanTindakan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanTuntutanBayar findMohonTuntutBayar(Long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        return (PermohonanTuntutanBayar) sessionProvider.get().createQuery(query).setLong("idKos", idKos).uniqueResult();
    }

    public AduanTindakan findDendaTambahanByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanTindakan b where b.permohonan.idPermohonan = :idPermohonan and b.tindakan.kod ='DET'";
        return (AduanTindakan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public EnkuiriPenguatkuasaan getSenaraiPermohonanByTarikhEnkuiri(String idPermohonan, Date fromDate, String kod, String operator) {
        //String query = "SELECT h FROM etanah.model.EnkuiriPenguatkuasaan h WHERE h.permohonan.idPermohonan = :idPermohonan and trim(h.tarikhMula) > :fromDate AND h.kodJenisEnkuiri =:kod";
        String query = "SELECT h FROM etanah.model.EnkuiriPenguatkuasaan h WHERE h.permohonan.idPermohonan = :idPermohonan and trim(h.tarikhMula)" + operator + " :fromDate AND h.kodJenisEnkuiri =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        LOG.info("query ::" + query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        q.setDate("fromDate", fromDate);
        return (EnkuiriPenguatkuasaan) q.uniqueResult();
    }

    public EnkuiriPenguatkuasaan getSenaraiPermohonanByTarikhEnkuiri(String idPermohonan, Date fromDate) {
        String query = "SELECT h FROM etanah.model.EnkuiriPenguatkuasaan h WHERE h.permohonan.idPermohonan = :idPermohonan and trim(h.tarikhMula) > :fromDate";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        LOG.info("query ::" + query);
        q.setString("idPermohonan", idPermohonan);
        q.setDate("fromDate", fromDate);
        return (EnkuiriPenguatkuasaan) q.uniqueResult();
    }

    public List<EnkuiriPenguatkuasaan> getSenaraiEnkuiri(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.EnkuiriPenguatkuasaan h WHERE h.permohonan.idPermohonan = :idPermohonan order by h.tarikhMula desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        LOG.info("query ::" + query);
        q.setString("idPermohonan", idPermohonan);
//        q.setDate("fromDate", fromDate);
        return q.list();
    }

    //wazer add
    public List findByIdKrtsList(String idPermohonan, String kodDokumen) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen = :kodDokumen order by b.tarikhSidang desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    //add by Siti
    public PermohonanKertas findByIdKrts(String idPermohonan, String kodDokumen) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen = :kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByIdKertasList(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen = :kodDokumen");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findByIdKertasKand(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findByIdKandungan(Long idKandungan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.idKandungan = :idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findKertasKandByIdKertasSubtajuk(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk + "%");
        return q.list();
    }

    public List<PermohonanRujukanLuar> FindSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.namaAgensi is not null  ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByIdKertas2(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.tarikhButiran");
        q.setLong("idKertas", idKertas);
        return q.list();
    }
//add by ct

    public List<PermohonanKertas> findByIdKertasIO(String idPermohonan, Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertas p where p.permohonan.idPermohonan = :idPermohonan and p.idKertas = :idKertas");
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public List<PermohonanSemakDokumen> findSenaraiPermohonanSemakDokumen(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanSemakDokumen b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanPermohonanSemakDokumen(PermohonanSemakDokumen permohonanSemakDokumen) {
        permohonanSemakDokumenDAO.saveOrUpdate(permohonanSemakDokumen);
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public PermohonanKertas simpanPermohonanKertas2(PermohonanKertas permohonanKertas) {
        return permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public PermohonanKertasKandungan simpanPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan) {
        return permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public PermohonanKertasImej simpanPermohonanKertasImej(PermohonanKertasImej permohonanKertasImej) {
        return permohonanKertasImejDAO.saveOrUpdate(permohonanKertasImej);
    }

    public PermohonanKertas findMesyByIdKertas(Long idKertas) {
        String query = "Select p FROM etanah.model.PermohonanKertas p WHERE p.idKertas = :idKertas";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertas) q.uniqueResult();
    }

    @Transactional
    public void deleteDiariSiasatan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.delete(permohonanKertasKandungan);
    }

    public PermohonanKertas findByIdKrtsKand(String idPermohonan, String idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idKertas", idKertas);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertasKandungan findByIdKKand(String idKandungan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.idKandungan = :idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setString("idKertas", idKertas);
        q.setString("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public Permohonan semakIdAduan(String idAduan) {
        String query = "Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = :idAduan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idAduan", idAduan);
        return (Permohonan) q.uniqueResult();

    }

    public Hakmilik semakIdHakmilik(String idHakmilik) {
        String query = "Select p FROM etanah.model.Hakmilik p WHERE p.idHakmilik = :idHakmilik";//p.sekatanKepentingan.kod IN (decode(s.kod,NULL,'9999999')
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Hakmilik) q.uniqueResult();

    }

    //added by Murali for Semak Kertas siasatan
    public List<UrusanDokumen> findUrusanDokumenBykodUrusan(String kodurusan) {
        String query = "SELECT b FROM etanah.model.UrusanDokumen b where b.kodUrusan.kod = :kodurusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodurusan", kodurusan);
        return q.list();
    }

    //add by latifah.iskak
    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.OperasiPenguatkuasaanPasukan b where b.idOperasiPenguatkuasaan.idOperasi = :idOperasi order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    //add by sitifariza.hanim
    public List<BarangRampasan> getSenaraiBarangRampasan(Long idOperasiAgensi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasiAgensi.idOperasiAgensi = :idOperasiAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasiAgensi", idOperasiAgensi);
        return q.list();
    }

    public OperasiPenguatkuasaanPasukan findSenaraiPasukan(Long idOperasiPasukan) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaanPasukan p WHERE p.idOperasiPenguatkuasaanPasukan = :idOperasiPasukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOperasiPasukan", idOperasiPasukan);
        return (OperasiPenguatkuasaanPasukan) q.uniqueResult();
    }

    public HakmilikPermohonan findLatestRecord(String idHakmilik, String idPermohonan) {
        //String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmilik.idHakmilik = :idHakmilik AND p.permohonan.idPermohonan = :idPermohonan AND p.infoAudit.tarikhMasuk = (SELECT MAp.infoAudit.tarikhMasuk) FROM etanah.model.HakmilikPermohonan p)";
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmilik.idHakmilik = :idHakmilik AND p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    @Transactional
    public void simpanOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        operasiPenguatkuasaanPasukanDAO.saveOrUpdate(operasiPenguatkuasaanPasukan);
    }

    @Transactional
    public void deleteOperasiPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        operasiPenguatkuasaanPasukanDAO.delete(operasiPenguatkuasaanPasukan);
    }

    // add by Zabedah
    public Pihak semakNoPengenalan(String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return (Pihak) q.uniqueResult();

    }

    public PermohonanPihakMembantah findPermohonanPihakMembantahByIdpermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakMembantah p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPihakMembantah) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanPihakMembantah(PermohonanPihakMembantah permohonanPihakMembantah) {
        permohonanPihakMembantahDAO.saveOrUpdate(permohonanPihakMembantah);
    }

    public PermohonanUkur getMaxNoPUofPermohonanUkur(String year) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.noPermohonanUkur=(Select MAX(lt1.noPermohonanUkur) from etanah.model.PermohonanUkur lt1 where lt1.noPermohonanUkur LIKE :year)");
        q.setString("year", "%" + year + "%");
        return (PermohonanUkur) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanUkur(PermohonanUkur mohonUkur) {
        permohonanUkurDAO.saveOrUpdate(mohonUkur);
    }

    public PermohonanUkur findPermohonanUkurByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanUkur) q.uniqueResult();
    }

    public InfoAudit getInfo(Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public Pemohon findById(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();

    }

    public List<Akaun> findByIDMohonIDPihak(String idPermohonan, String idPihak) {
        String query = "SELECT b FROM etanah.model.Akaun b,etanah.model.Pihak p where b.permohonan.idPermohonan = :idPermohonan and b.pemegang.idPihak =p.idPihak ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idPihak", idPihak);
        return q.list();
    }

    public List<Akaun> findByIDMohonIDOKS(String idPermohonan, Long idOrangKenaSyak) {
        String query = "SELECT b FROM etanah.model.Akaun b,etanah.model.AduanOrangKenaSyak p where b.permohonan.idPermohonan = :idPermohonan and b.pemegang.idOrangKenaSyak =p.idOrangKenaSyak ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setLong("idOrangKenaSyak", idOrangKenaSyak);
        return q.list();
    }

    public PermohonanTuntutanKos findByIdMohon(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();

    }

    public List<HakmilikWaris> findByIdHp(String idhakmilik) {
        String query = "SELECT p FROM etanah.model.HakmilikWaris p, etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idhakmilik and h.idHakmilikPihakBerkepentingan = p.pemegangAmanah.idHakmilikPihakBerkepentingan and p.syerPembilang is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idhakmilik);
        return q.list();
    }

    public PermohonanTuntutanKos findByIdMohon(String idPermohonan, String tuntutKod) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p where p.permohonan.idPermohonan = :idPermohonan and p.kodTuntut = :tuntutKod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("tuntutKod", tuntutKod);
        return (PermohonanTuntutanKos) q.uniqueResult();

    }

    public List<HakmilikAsal> findByIDHakmilikAsal(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikAsal b, etanah.model.HakmilikPermohonan h where b.hakmilikAsal = h.hakmilik.idHakmilik and h.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodDokumen> findListDokumenIringan() {
        String query = "SELECT p FROM etanah.model.KodDokumen p, etanah.model.KodDokumenIringan k where p.kod = k.kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanDokumenIringan> findListByIdMohon(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanDokumenIringan p where p.idPermohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public AduanOrangKenaSyak findByIdDokumen(Long idDokumen) {
        String query = "Select p FROM etanah.model.AduanOrangKenaSyak p WHERE p.dokumen.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idDokumen", idDokumen);
        return (AduanOrangKenaSyak) q.uniqueResult();
    }

    public List<KandunganFolder> getListDokumen(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :idFolder");
        q.setParameter("idFolder", idFolder);
        return q.list();
    }

    public PermohonanPihak findByIdPihak(String idPermohonan, Long idPihak) {
        String query = "Select p FROM etanah.model.PermohonanPihak p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanLaporanUlasan findRingkasanKesByJenisUlasan(String idPermohonan, String jenisUlasan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.jenisUlasan = :jenisUlasan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    @Transactional
    public void simpanRingkasanKes(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    public PermohonanNota findNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.idAliran = :idAliran";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public List<PermohonanNota> findListNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan AND p.idAliran != :idAliran "
                + "AND p.statusNota = 'T' ORDER BY p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return q.list();

    }

    @Transactional
    public void simpanNota(PermohonanNota permohonanNota) {
        permohonanNotaDAO.saveOrUpdate(permohonanNota);
    }

    @Transactional
    public void deleteNota(PermohonanNota permohonanNota) {
        permohonanNotaDAO.delete(permohonanNota);
    }

    public List<AduanLokasi> searchDaerah(String daerah, String kodNegeri) {
        String query = "Select a from etanah.model.AduanLokasi a, etanah.model.KodBandarPekanMukim k, etanah.model.Permohonan p "
                + "WHERE lower(k.nama) LIKE :daerah AND k.kod = a.bandarPekanMukim.kod AND a.permohonan.idPermohonan = p.idPermohonan AND substring(a.permohonan.idPermohonan, 0,2) =:kodNegeri";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("daerah", "%" + daerah + "%");
        q.setString("kodNegeri", kodNegeri);
        return q.list();

//  select a.id_mohon,a.kod_bpm,k.KOD_DAERAH,k.nama
//  from aduan_lokasi a, kod_bpm k
//  where k.nama LIKE '%Durian%'
//  and k.kod=a.KOD_BPM
    }

    public List<FasaPermohonan> viewDetail(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanLokasi> searchBpm(String bpm, String kodNegeri) {
        String query = "Select a from etanah.model.AduanLokasi a, etanah.model.KodBandarPekanMukim k, etanah.model.Permohonan p "
                + "WHERE lower(k.nama) LIKE :bpm AND k.kod = a.bandarPekanMukim.kod AND a.permohonan.idPermohonan = p.idPermohonan AND substring(a.permohonan.idPermohonan, 0,2) =:kodNegeri";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("bpm", "%" + bpm + "%");
        q.setString("kodNegeri", kodNegeri);
        return q.list();

//        select a.id_mohon,a.kod_bpm,k.KOD_DAERAH,k.nama
//        from aduan_lokasi a, kod_bpm k
//        where k.nama LIKE '%Durian%'
//        and k.kod=a.KOD_BPM
    }

    public List<AduanLokasi> searchLokasi(String lokasi, String kodNegeri) {
        String query = "Select a from etanah.model.AduanLokasi a, etanah.model.Permohonan p "
                + "WHERE lower(a.lokasi) LIKE :lokasi AND a.permohonan.idPermohonan = p.idPermohonan AND substring(a.permohonan.idPermohonan, 0,2) =:kodNegeri";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("lokasi", "%" + lokasi + "%");
        q.setString("kodNegeri", kodNegeri);
        return q.list();
    }

    public List<Permohonan> searchJenisKesalahan(String jenisKesalahan, String kodNegeri) {
        String query = "Select a from etanah.model.Permohonan a "
                + "WHERE lower(a.kodUrusan.kod) LIKE :jenisKesalahan AND substring(a.idPermohonan, 0,2) =:kodNegeri";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("jenisKesalahan", "%" + jenisKesalahan + "%");
        q.setString("kodNegeri", kodNegeri);
        return q.list();
    }

    public Kompaun findKompaunByIdMohon(String idPermohonan) {
        String query = "SELECT k FROM etanah.model.Kompaun k where k.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Kompaun) q.uniqueResult();

    }

    public List<Kompaun> findKompaunByIdMohonOKS(String idPermohonan) {
        String query = "SELECT k FROM etanah.model.Kompaun k where k.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<BarangRampasan> findBarangRampasanByIdKompaun(Long idKompaun) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.kompaun.idKompaun = :idKompaun";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKompaun", idKompaun);
        return q.list();
    }

    public List<BarangRampasan> findBarangKompaun(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi AND b.amaunKompaunSyor is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<BarangRampasan> findBarangRampasan(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi AND b.amaunKompaunSyor is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public PenjaminBarangRampasan findPenjaminBarangRampasanByIdBarang(Long idBarang) {
        String query = "Select p FROM etanah.model.PenjaminBarangRampasan p WHERE p.barangRampasan.idBarang = :idBarang";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBarang", idBarang);
        return (PenjaminBarangRampasan) q.uniqueResult();
    }

    public WarisOrangKenaSyak findWarisOKS(Long idOrangKenaSyak) {
        String query = "Select p FROM etanah.model.WarisOrangKenaSyak p WHERE p.aduanOrangKenaSyak.idOrangKenaSyak = :idOrangKenaSyak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOrangKenaSyak", idOrangKenaSyak);
        return (WarisOrangKenaSyak) q.uniqueResult();
    }

    @Transactional
    public void simpanMaklumatPenjaminBrgRampas(PenjaminBarangRampasan penjaminBarangRampasan) {
        penjaminBarangRampasanDAO.saveOrUpdate(penjaminBarangRampasan);
    }

    @Transactional
    public void deletePenjaminBarangRampasan(PenjaminBarangRampasan penjaminBarangRampasan) {
        penjaminBarangRampasanDAO.delete(penjaminBarangRampasan);
    }

    @Transactional
    public void updatePenjaminBarangRampasan(PenjaminBarangRampasan penjaminBarangRampasan) {
        penjaminBarangRampasanDAO.saveOrUpdate(penjaminBarangRampasan);
    }

    @Transactional
    public void simpanMaklumatWaris(WarisOrangKenaSyak warisOrangKenaSyak) {
        warisOrangKenaSyakDAO.saveOrUpdate(warisOrangKenaSyak);
    }

    @Transactional
    public void editWarisOrangKenaSyak(WarisOrangKenaSyak warisOrangKenaSyak) {
        warisOrangKenaSyakDAO.saveOrUpdate(warisOrangKenaSyak);
    }

    @Transactional
    public void deleteAll(WarisOrangKenaSyak warisOrangKenaSyak) {
        warisOrangKenaSyakDAO.delete(warisOrangKenaSyak);
    }

    public PermohonanLaporanUlasan findKeputusanAduanByIdPermohonanTujuan(String idPermohonan, String jenisUlasan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanLaporanUlasan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.jenisUlasan = :jenisUlasan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan findPermohonanUkurByIdPermohonanTujuan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanLaporanUlasan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.jenisUlasan = 'SijilPU'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findSenaraiPihak(String idhakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idhakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findSenaraiPihakAktif(String idhakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.aktif= 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idhakmilik);
        return q.list();
    }

    @Transactional
    public void simpanEnkuiriKehadiran(EnkuiriPenguatkuasaanKehadiran enkuiriPenguatkuasaanKehadiran) {
        enkuiriPenguatkuasaanKehadiranDAO.saveOrUpdate(enkuiriPenguatkuasaanKehadiran);
    }

    public List<EnkuiriPenguatkuasaanKehadiran> findDetailsKehadiran(Long idEnkuiri) {
        String query = "SELECT h FROM etanah.model.EnkuiriPenguatkuasaanKehadiran h WHERE h.enkuiri.idEnkuiri = :idEnkuiri";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void saveIntegMohonDoc(IntegrasiPermohonanDokumen ipd) {
        integrasiPermohonanDokumenDAO.saveOrUpdate(ipd);
    }

    public List<BarangRampasan> findBarangEmpunya(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi and b.kodKategoriItemRampasan.kod = 'K' and b.namaPemunya is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<KodBangsa> senaraiBangsa() {
        String query = "SELECT b FROM etanah.model.KodBangsa b where b.kod in('MEL','CIN','IND','SIM','ASL','LN') order by b.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<HakmilikSebelum> findByIDHakmilikSblm(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikSebelum b, etanah.model.HakmilikPermohonan h where b.hakmilikSebelum = h.hakmilik.idHakmilik and h.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void updateKompaun(Kompaun kompaun) {
        kompaunDAO.update(kompaun);
    }

    public List<BarangRampasan> searchBarangRampasan(String idPermohonan) {
        String query = "Select b from etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p where p.permohonan.idPermohonan = :idPermohonan and b.operasi.idOperasi = p.idOperasi order by b.idBarang desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public OperasiPenguatkuasaan findOperasiPenguatkuasaanByIdpermohonan(String idPermohonan, String kategoriTindakan) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.kategoriTindakan = :kategoriTindakan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriTindakan", kategoriTindakan);
        return (OperasiPenguatkuasaan) q.uniqueResult();
    }

    public List<BarangRampasan> findBarangEmpunyaForDakwa(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi and b.namaPemunya is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<PermohonanKertasKandungan> listKandunganByBil(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk != :subTajuk order by b.idKandungan desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk);
        return q.list();
    }

    public List<PermohonanKertasKandungan> listKandunganByBil(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasKandungan(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.subtajuk = 1.1";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findAllKandungan(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil != 1 order by b.bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasDetail(Long idKertas, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.subtajuk = :subTajuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setString("subTajuk", subTajuk);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<LaporanTanah> findByIDMohonIDLaporan(String idPermohonan, String idLaporan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b,etanah.model.Permohonan p where b.permohonan.idPermohonan = :idPermohonan and b.laporanTanah.idLaporan =p.idLaporan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idLaporan", idLaporan);
        return q.list();
    }

    public LaporanTanah findByIDLaporan(String idLaporan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b,etanah.model.Permohonan p where b.permohonan.idPermohonan = :idPermohonan and b.laporanTanah.idLaporan =p.idLaporan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idLaporan", idLaporan);
        return (LaporanTanah) q.uniqueResult();
    }
    
    public LaporanTanah findByIDMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b,etanah.model.Permohonan p where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<PermohonanLaporanUlasan> findByIdLaporan(Long idLaporan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    public List<LaporanTanah> findByIdLaporanTanah(String idPermohonan) {
        String query = "Select p FROM etanah.model.LaporanTanah p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByIdKertas3(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.bil desc");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findListLaporanSusulan(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.bil asc");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public List<PermohonanKertasImej> findImejByIdKand(Long idKand) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasImej p where p.kertasKandungan.idKandungan = :idKand");
        q.setLong("idKand", idKand);
        return q.list();
    }

    public List<Kompaun> findKompaunByKodStatusTerima(String idPermohonan, String kodStatus) {
        String query = "SELECT k FROM etanah.model.Kompaun k where k.permohonan.idPermohonan = :idPermohonan and k.statusTerima.kod = :kodStatus";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodStatus", kodStatus);
        return q.list();
    }

    public PermohonanPihak findMohonPihak(String idPermohonan, Long idPihak, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPihak p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.pihak.idPihak = :idPihak AND p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findListPihak(String idPermohonan, String kod) {
        String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb where (hpb.hakmilik.idHakmilik in(SELECT hp.hakmilik.idHakmilik FROM etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan=:idPermohonan) and hpb.pihak.idPihak not in(SELECT mp.pihak.idPihak FROM etanah.model.PermohonanPihak mp where mp.permohonan.idPermohonan=:idPermohonan and mp.noRujukan = :kod))";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findListPihakAktif(String idPermohonan, String kod) {
        String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb where hpb.aktif = 'Y' AND (hpb.hakmilik.idHakmilik in(SELECT hp.hakmilik.idHakmilik FROM etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan=:idPermohonan) and hpb.pihak.idPihak not in(SELECT mp.pihak.idPihak FROM etanah.model.PermohonanPihak mp where mp.permohonan.idPermohonan=:idPermohonan and mp.noRujukan = :kod))";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan, String kod) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p, etanah.model.Kompaun k where p.permohonan.idPermohonan = :idPermohonan AND p.permohonan.idPermohonan = k.permohonan.idPermohonan AND p.idKos = k.permohonanTuntutanKos.idKos AND k.statusTerima.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p, etanah.model.Kompaun k where p.permohonan.idPermohonan = :idPermohonan AND p.permohonan.idPermohonan = k.permohonan.idPermohonan AND p.idKos = k.permohonanTuntutanKos.idKos AND k.statusTerima.kod is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getSenaraiFasa(String idPermohonan, String idAliran) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan=:idPermohonan AND b.idAliran = :idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> getListUlasan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> getListUlasan(String idPermohonan, String jenisLaporan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.jenisLaporan =:jenisLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        return q.list();

    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosJaminan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p WHERE p.permohonan.idPermohonan = p.permohonan.idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Kompaun findJaminanByKodStatusTerima(String idPermohonan, String kodStatus) {
        String query = "SELECT k FROM etanah.model.Kompaun k where k.permohonan.idPermohonan = :idPermohonan and k.statusTerima.kod = :kodStatus";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodStatus", kodStatus);
        return (Kompaun) q.uniqueResult();
    }

    public List<OperasiPenguatkuasaan> findListLaporanOperasi(String idPermohonan) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<OperasiPenguatkuasaan> findListLaporanOperasi(String idPermohonan, String kategoriTindakan) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.kategoriTindakan = :kategoriTindakan order by p.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriTindakan", kategoriTindakan);
        return q.list();
    }

    public OperasiPenguatkuasaan findByIdOp(Long idOp) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.idOperasi = :idOp";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOp", idOp);
        return (OperasiPenguatkuasaan) q.uniqueResult();
    }

    public OperasiAgensi findByIdOperasiAgensi(Long idOperasiAgensi) {
        String query = "Select p FROM etanah.model.OperasiAgensi p WHERE p.idOperasiAgensi = :idOperasiAgensi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOperasiAgensi", idOperasiAgensi);
        return (OperasiAgensi) q.uniqueResult();
    }

    @Transactional
    public void deleteOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        operasiPenguatkuasaanDAO.delete(operasiPenguatkuasaan);
    }

    public List<PermohonanTuntutanBayar> getSenaraiPtb(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b, etanah.model.PermohonanTuntutanKos k where k.permohonan.idPermohonan = :idPermohonan AND k.idKos = b.permohonanTuntutanKos.idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertasKandungan findByIdBil(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas AND p.bil = 1");
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> listKronologiKes(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.bil");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanSemakDokumen findPermohonanSemakDokumen(String idPermohonan, Long idDokumen) {
        String query = "SELECT b FROM etanah.model.PermohonanSemakDokumen b where b.permohonan.idPermohonan = :idPermohonan AND b.dokumen.idDokumen = :idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idDokumen", idDokumen);
        return (PermohonanSemakDokumen) q.uniqueResult();
    }

    public List<LaporanTanahSempadan> findByIDTanahSempadan(Long idLapor) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        return q.list();
    }

    public List<PermohonanLaporanBangunan> findByIDLaporBangunan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        permohonanLaporanBangunanDAO.saveOrUpdate(permohonanLaporanBangunan);
    }

    @Transactional
    public void simpanSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        laporanTanahSempadanDAO.saveOrUpdate(laporanTanahSempadan);
    }

    public List<BarangRampasan> findListKenderaan(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi AND b.kodKategoriItemRampasan.kod = 'K'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    @Transactional
    public void deleteOperasiAgensi(OperasiAgensi operasiAgensi) {
        operasiAgensiDAO.delete(operasiAgensi);
    }

    public List<HakmilikPermohonan> findSenaraiTanahMilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilik.idHakmilik is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findSenaraiMohonHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilik.idHakmilik is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void deleteHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    public KodTransaksiTuntut findKodTransTuntutByKod(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTransaksiTuntut kt where kt.kod = :kod");
        q.setString("kod", kod);
        return (KodTransaksiTuntut) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTuntutan(PermohonanTuntutan pt) {
        permohonanTuntutanDAO.saveOrUpdate(pt);
    }

    @Transactional
    public void simpanPermohonanTuntutanButiran(PermohonanTuntutanButiran tb) {
        permohonanTuntutanButiranDAO.saveOrUpdate(tb);
    }

    public PermohonanTuntutanButiran findPermohonanTuntutanButiran(Long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanButiran b where b.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanButiran) q.uniqueResult();

    }

    public List<PermohonanTuntutan> findMohonTuntutbyDate(Date date, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutan b where b.permohonan.idPermohonan = :idPermohonan AND b.tarikhAkhirBayaran < :date";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setDate("date", date);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenDisemak(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT k FROM etanah.model.KandunganFolder k, etanah.model.Dokumen d WHERE k.folder.folderId = :idFolder and d.idDokumen = k.dokumen.idDokumen and d.perihal = 'DokumenDisemak' order by d.infoAudit.tarikhMasuk asc");
        q.setParameter("idFolder", idFolder);
        return q.list();
    }

    @Transactional
    public void deletePermohonanSemakDokumen(PermohonanSemakDokumen permohonanSemakDokumen) {
        permohonanSemakDokumenDAO.delete(permohonanSemakDokumen);
    }

    public Permohonan findKodCawangan(String cawKod) {
        String query = "SELECT p FROM etanah.model.Permohonan p WHERE p.cawangan.kod = :kod and p.permohonan.kodUrusan.kod = '422','423','127','49','429','426','424','351','352','427','425','428','426'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", cawKod);
        return (Permohonan) q.uniqueResult();
    }

    public String constructAgensiAddress(String kodAgensi) {
        String query = "SELECT a FROM etanah.model.KodAgensi a WHERE a.kod = :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("kodAgensi", kodAgensi);
        KodAgensi agensi = (KodAgensi) q.uniqueResult();

        if (agensi == null) {
            return "";
        } else {
            query = "select (a.alamat1 || chr(10) || a.alamat2 || chr(10) || a.alamat3 || chr(10) || a.poskod || ' ' || a.alamat4 || chr(10) || a.kodNegeri.nama) from etanah.model.KodAgensi a "
                    + "where a.kod = :kodAgensi";
            q = session.createQuery(query);
            q.setParameter("kodAgensi", kodAgensi);
            return (String) q.uniqueResult();
        }
    }

    public PermohonanTandatanganDokumen findPermohonanTandatanganDok(String idPermohonan, String kod) {
        String query = "SELECT p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan= :idPermohonan AND p.kodDokumen.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTandatanganDok(PermohonanTandatanganDokumen ptd) {
        permohonanTandatanganDokumenDAO.saveOrUpdate(ptd);
    }

    public List<Pengguna> getSenaraiPengguna() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPTK','PPTT','PPTD','PTD')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodAgensi> getSenaraiAgensi(Integer kod) {
        String query = "SELECT a FROM etanah.model.KodAgensi a WHERE a.kodKementerian = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("kod", kod);
        return q.list();
    }

    public KodKementerian findKementerian(Integer kod) {
        String query = "SELECT k FROM etanah.model.KodKementerian k WHERE k.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("kod", kod);
        return (KodKementerian) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujukanLuar(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujukanLuar2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod = 'BR' and b.kodRujukan.kod = 'AP'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujukanLuar3(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod = 'AGEPU' and b.kodRujukan.kod = 'AP'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujukanLuar3(String idPermohonan, String dok, String ruj) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.kodDokumenRujukan.kod = dok AND b.kodRujukan.kod = ruj";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("dok", dok);
        q.setString("ruj", ruj);
        return q.list();
    }

    public PermohonanTuntutanButiran findTuntutanButiran(Long idTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanButiran b where b.permohonanTuntutan.idTuntut = :idTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTuntut", idTuntut);
        return (PermohonanTuntutanButiran) q.uniqueResult();

    }

    @Transactional
    public void simpanDokumenKewangan(DokumenKewangan dk) {
        dokumenKewanganDAO.saveOrUpdate(dk);
    }

    @Transactional
    public void simpanTransaksi(Transaksi t) {
        transaksiDAO.saveOrUpdate(t);
    }

    @Transactional
    public void simpanPermohonanTuntutanBayaran(PermohonanTuntutanBayar ptb) {
        permohonanTuntutanBayarDAO.saveOrUpdate(ptb);
    }

    @Transactional
    public void simpanKandunganFolder(KandunganFolder kf) {
        kandunganFolderDAO.saveOrUpdate(kf);
    }

    public List<AduanOrangKenaSyak> getSenaraiDakwaOks(String idPermohonan) {
        String query = "SELECT a FROM etanah.model.AduanOrangKenaSyak a WHERE a.permohonan.idPermohonan = :idPermohonan AND a.statusDakwaan is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> getSenaraiDakwaBarangRampasan(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b WHERE b.operasi.idOperasi = :idOperasi AND b.statusDakwaan is null AND b.namaPemunya is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<Pengguna> getSenaraiPguna() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPTD','PTD')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> getUlasanKeputusanAduan(String idPermohonan, String jenisUlasan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanLaporanUlasan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.jenisUlasan = :jenisUlasan order by lt.infoAudit.tarikhMasuk desc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findAllKeputusanAduan(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil != 2 order by b.bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findByIdBil(Long idKertas, Integer bil) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas AND p.bil = :bil");
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    @Transactional
    public void simpanAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        aduanOrangKenaSyakDAO.saveOrUpdate(aduanOrangKenaSyak);
    }

    public List<BarangRampasan> findKenderaanForDakwa(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan "
                + "AND b.namaSuspek is not null AND b.operasi.idOperasi = p.idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findBarangKompaun(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan AND b.operasi.idOperasi = p.idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> findOksByIdOp(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.operasiPenguatkuasaan.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanForMultipleOp(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.OperasiPenguatkuasaanPasukan b where b.idOperasiPenguatkuasaan.permohonan.idPermohonan = :idPermohonan order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<OperasiPenguatkuasaanPasukan> findListByIdPasukanForMultipleOp(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.OperasiPenguatkuasaanPasukan p where p.idOperasiPenguatkuasaan.permohonan.idPermohonan =:idPermohonan and p.saksi = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findBarangRampasanForDakwa(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan AND b.statusDakwaan is null "
                + "AND b.namaSuspek is not null AND b.operasi.idOperasi = p.idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findOksForDakwa(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan "
                + "AND b.aduanOrangKenaSyak.idOrangKenaSyak is not null AND b.operasi.idOperasi = p.idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> findAllOksForDakwa(String idPermohonan) {
        String query = "SELECT a FROM etanah.model.AduanOrangKenaSyak a "
                + "WHERE a.idOrangKenaSyak IN "
                + "(SELECT distinct b.aduanOrangKenaSyak.idOrangKenaSyak FROM etanah.model.BarangRampasan b WHERE b.operasi.idOperasi = a.operasiPenguatkuasaan.idOperasi AND b.namaSuspek is not null) "
                + "AND a.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findBarangOks(Long idOks) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b WHERE b.aduanOrangKenaSyak.idOrangKenaSyak =:idOks";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOks", idOks);
        return q.list();
    }

    public List<Permohonan> findSenaraiPermohonanBaru(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p WHERE p.permohonanSebelum.idPermohonan =:idPermohonan AND p.status != 'BP'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanFolderDokumen(FolderDokumen f) {
        folderDAO.saveOrUpdate(f);
    }

    @Transactional
    public void deleteFolderDokumen(FolderDokumen f) {
        folderDAO.delete(f);
    }

    @Transactional
    public void simpanPegawaiPenyiasat(PegawaiPenyiasat p) {
        pegawaiPenyiasatDAO.saveOrUpdate(p);
    }

    public PegawaiPenyiasat findKetuaPenyiasat(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.permohonan.idPermohonan = :idPermohonan AND b.statusPeranan = 'K'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PegawaiPenyiasat) q.uniqueResult();

    }

    public List<PegawaiPenyiasat> findPembantuPenyiasat(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.permohonan.idPermohonan = :idPermohonan AND b.statusPeranan = 'P'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PegawaiPenyiasat> findAllPenyiasat(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.permohonan.permohonanSebelum.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi(Long idOp) {
        String query = "SELECT b FROM etanah.model.OperasiAgensi b WHERE b.operasi.idOperasi =:idOp";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOp", idOp);
        return q.list();
    }

    @Transactional
    public void deletePegawai(PegawaiPenyiasat pegawaiPenyiasat) {
        pegawaiPenyiasatDAO.delete(pegawaiPenyiasat);
    }

    public List<PermohonanNota> findListNotaByIdMohonSebelum(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PegawaiPenyiasat> findPenyiasat(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> getListAduanOrangkenaSyak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND statusIp is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanButiranDAO getPermohonanTuntutanButiranDAO() {
        return permohonanTuntutanButiranDAO;
    }

    public List<PermohonanSaksi> findByIDOP(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.PermohonanSaksi b where b.operasiPenguatkuasaan.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<PermohonanSaksi> getKompaunSaksiLuar(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.PermohonanSaksi b where b.operasi.idOperasi = :idOperasi and b.saksi.statusDakwaanKompaun = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<BarangRampasan> findBarangKompaunIP(Long idOperasi, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.AduanOrangKenaSyak a WHERE b.operasi.idOperasi = :idOperasi AND b.amaunKompaunSyor is null "
                + "AND b.aduanOrangKenaSyak.idOrangKenaSyak = a.idOrangKenaSyak AND a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> findAllOksForDakwaForIP(String idPermohonan, Long idOperasi) {
        String query = "SELECT a FROM etanah.model.AduanOrangKenaSyak a "
                + "WHERE a.idOrangKenaSyak IN "
                + "(SELECT distinct b.aduanOrangKenaSyak.idOrangKenaSyak FROM etanah.model.BarangRampasan b WHERE b.operasi.idOperasi = a.operasiPenguatkuasaan.idOperasi AND b.aduanOrangKenaSyak.idOrangKenaSyak is not null) "
                + "AND a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND a.operasiPenguatkuasaan.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<BarangRampasan> findOksForDakwaForIP(String idPermohonan, Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.AduanOrangKenaSyak a , etanah.model.OperasiPenguatkuasaan p "
                + "WHERE a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan "
                + "AND b.aduanOrangKenaSyak.idOrangKenaSyak is not null AND b.operasi.idOperasi = p.idOperasi AND p.idOperasi = :idOperasi AND b.aduanOrangKenaSyak.idOrangKenaSyak = a.idOrangKenaSyak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<BarangRampasan> findListDakwaForIP(String idPermohonan, Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.AduanOrangKenaSyak a , etanah.model.OperasiPenguatkuasaan p "
                + "WHERE a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND b.statusDakwaan is null "
                + "AND b.aduanOrangKenaSyak.idOrangKenaSyak is not null AND b.operasi.idOperasi = p.idOperasi AND p.idOperasi = :idOperasi AND b.aduanOrangKenaSyak.idOrangKenaSyak = a.idOrangKenaSyak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaEksibit() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPTK','PPTT','PPTD','PTD','PUU')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<OperasiBarangPenguatkuasaan> findBarangSerahanByIdOperasi(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.OperasiBarangPenguatkuasaan b where b.operasi.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    @Transactional
    public void savePenyerahBarangOperasi(PenyerahBarangOperasi penyerahBarangOperasi) {
        penyerahBarangOperasiDAO.saveOrUpdate(penyerahBarangOperasi);
    }

    @Transactional
    public void saveOperasiBarangPenguatkuasaan(OperasiBarangPenguatkuasaan operasiBarangPenguatkuasaan) {
        operasiBarangPenguatkuasaanDAO.saveOrUpdate(operasiBarangPenguatkuasaan);
    }

    @Transactional
    public void deleteOperasiBarangPenguatkuasaan(OperasiBarangPenguatkuasaan operasiBarangPenguatkuasaan) {
        operasiBarangPenguatkuasaanDAO.delete(operasiBarangPenguatkuasaan);
    }

    public List<PenyerahBarangOperasi> checkNoSerahan(String noSerahan) {
        String query = "Select p from etanah.model.PenyerahBarangOperasi p WHERE lower(p.noPerserahan) = :noSerahan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noSerahan", noSerahan);
        return q.list();
    }

    public List<PermohonanNota> findCurrentNota(String idPermohonan, String stageId, Long idMohonNota) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.idAliran = :idAliran AND p.idMohonNota != :idMohonNota";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        q.setLong("idMohonNota", idMohonNota);
        return q.list();
    }

    public List<FasaPermohonanLog> findListFasaPermohonanLog(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.FasaPermohonanLog p, etanah.model.FasaPermohonan f WHERE f.permohonan.idPermohonan = :idPermohonan AND f.idAliran = :idAliran"
                + " AND p.fasa.idFasa = f.idFasa ORDER BY p.idLog desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return q.list();
    }

    @Transactional
    public void saveFasaLog(FasaPermohonanLog fasaPermohonanLog) {
        fasaPermohonanLogDAO.saveOrUpdate(fasaPermohonanLog);
    }

    public List<PermohonanNota> findCurrentNota(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.idAliran = :idAliran";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return q.list();
    }

    public List<BarangRampasan> findBarangSerahan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan AND b.operasi.idOperasi = p.idOperasi AND b.penyerahBarangOperasi.idPenyerahBarangOperasi is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BarangRampasan> findBarangSerahanByIdSerahan(String idPermohonan, Long idPenyerah) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan AND b.operasi.idOperasi = p.idOperasi AND b.penyerahBarangOperasi.idPenyerahBarangOperasi = :idPenyerah";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPenyerah", idPenyerah);
        return q.list();
    }

    public List<BarangRampasan> findBarangSerahan(String idPermohonan, Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p "
                + "WHERE p.permohonan.idPermohonan = :idPermohonan AND b.operasi.idOperasi = p.idOperasi "
                + "AND b.penyerahBarangOperasi.idPenyerahBarangOperasi is null AND b.operasi.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    @Transactional
    public void deletePenyerah(PenyerahBarangOperasi penyerahBarangOperasi) {
        penyerahBarangOperasiDAO.delete(penyerahBarangOperasi);
    }

    @Transactional
    public void deletePermohonanTandatanganDok(PermohonanTandatanganDokumen ptd) {
        permohonanTandatanganDokumenDAO.delete(ptd);
    }

    public List<BarangRampasan> findBarangSerahanForDelete(Long idPenyerahBarangOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b WHERE b.penyerahBarangOperasi.idPenyerahBarangOperasi = :idPenyerahBarangOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPenyerahBarangOperasi", idPenyerahBarangOperasi);
        return q.list();
    }

    @Transactional
    public void deleteFaktaKes(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.delete(permohonanKertasKandungan);
    }

    public List<PermohonanKertasKandungan> findByIdKertas4(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.bil asc");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PenyerahBarangOperasi findNoSerah(String noPerserahan) {
        String query = "SELECT p FROM etanah.model.PenyerahBarangOperasi p where p.noPerserahan = :noPerserahan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPerserahan", noPerserahan);
        return (PenyerahBarangOperasi) q.uniqueResult();
    }

    public PenyerahBarangOperasi findNoSerah(String noPerserahan, String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PenyerahBarangOperasi p, etanah.model.PegawaiPenyiasat s where p.operasi.idOperasi = s.operasiPenguatkuasaan.idOperasi AND s.permohonan.idPermohonan = :idPermohonan AND p.noPerserahan = :noPerserahan  ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPerserahan", noPerserahan);
        q.setString("idPermohonan", idPermohonan);
        return (PenyerahBarangOperasi) q.uniqueResult();
    }

    public List<PegawaiPenyiasat> findPegawaiPenyiasat(Long idOperasi, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.operasiPenguatkuasaan.idOperasi = :idOperasi AND b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PegawaiPenyiasat> findPegawaiPenyiasat(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.PegawaiPenyiasat b where b.operasiPenguatkuasaan.idOperasi = :idOperasi AND b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    @Transactional
    public void simpanPenyerahBarangOperasi(PenyerahBarangOperasi penyerahBarangOperasi) {
        penyerahBarangOperasiDAO.saveOrUpdate(penyerahBarangOperasi);
    }

    //add by Siti
    public List<Dokumen> findByIdDokumen(String kodDokumen) {
        String query = "SELECT b FROM etanah.model.Dokumen b where b.kodDokumen = :kodDokumen order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findAllKeputusanAduan3(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil != 3 order by b.bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    @Transactional
    public void editDokumen(Dokumen dokumen) {
        dokumenDAO.saveOrUpdate(dokumen);
    }

    public List<BarangRampasan> findBarangSerahanForView(String idPermohonan, Long idPenyerah) {
        System.out.println("id mohon :" + idPermohonan);
        System.out.println("id idPenyerah :" + idPenyerah);
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.OperasiPenguatkuasaan p, etanah.model.AduanOrangKenaSyak a"
                + " WHERE b.operasi.idOperasi = p.idOperasi AND b.penyerahBarangOperasi.idPenyerahBarangOperasi = :idPenyerah"
                + " AND a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND b.aduanOrangKenaSyak.idOrangKenaSyak = a.idOrangKenaSyak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPenyerah", idPenyerah);
        return q.list();
    }

    public Dokumen findByIdDokumen2(String kodDokumen) {
        String query = "SELECT b FROM etanah.model.Dokumen b where b.kodDokumen = :kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodDokumen", kodDokumen);
        return (Dokumen) q.uniqueResult();
    }

    @Transactional
    public void deleteDokumen(Dokumen dok) {
        dokumenDAO.delete(dok);
    }

    public List<PermohonanSaksi> checkSaksiDalaman(String nama, String noPengenalan, Long idOks) {
        String query = "Select p from etanah.model.PermohonanSaksi p WHERE lower(p.nama) = :nama AND p.noPengenalan = :noPengenalan AND p.statusSaksi = 'D' AND p.aduanOrangKenaSyak.idOrangKenaSyak = :idOks";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        q.setString("noPengenalan", noPengenalan);
        q.setLong("idOks", idOks);
        return q.list();
    }

    public List<HakmilikUrusan> findByIDHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikUrusan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanNota> findListNotaByIdMohon(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.statusNota = 'T' ORDER BY p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPihakBerkepentingan> findSenaraiPihakBerkepentingan(String idhakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.aktif= 'Y' and h.jenis.kod != 'PM'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idhakmilik);
        return q.list();
    }

    public PermohonanRujukanLuar findByIdRujukan(Long idRujukan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }
    
    public PermohonanLaporanUsaha findByIdLaporanUsaha(Long idLaporanUsaha) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUsaha p WHERE p.idLaporanUsaha = :idLaporanUsaha";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporanUsaha", idLaporanUsaha);
        return (PermohonanLaporanUsaha) q.uniqueResult();
    }

    @Transactional
    public void deleteMohonKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.delete(permohonanKertas);
    }

    @Transactional
    public void deleteMohonLaporUsaha(PermohonanLaporanUsaha permohonanLaporanUsaha) {
        permohonanLaporanUsahaDAO.delete(permohonanLaporanUsaha);
    }
    
    @Transactional
    public void deleteAgensi(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.delete(permohonanRujukanLuar);
    }

    @Transactional
    public void deleteKehadiran(EnkuiriPenguatkuasaanKehadiran enkuiriPenguatkuasaanKehadiran) {
        enkuiriPenguatkuasaanKehadiranDAO.delete(enkuiriPenguatkuasaanKehadiran);
    }

    @Transactional
    public void deleteImejLaporan(ImejLaporan imejLaporan) {
        imejLaporanDAO.delete(imejLaporan);
    }

    public List<PermohonanTuntutan> findMohonTuntutbyIdTuntut(Date date, Long idTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutan b where b.idTuntut = :idTuntut AND b.tarikhAkhirBayaran < :date";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setDate("date", date);
        q.setLong("idTuntut", idTuntut);
        return q.list();
    }

    public List<HakmilikPermohonan> findListMohonHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findListHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilik.idHakmilik is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findSenaraiHakmilik(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findListMohonHakmilik(String idPermohonanSebelum, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonanSebelum AND b.nomborRujukan = :idPermohonan order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findListMohonHakmilikById(Long id) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.id = :id order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public List<Hakmilik> findYearRegister() {
        String query = "SELECT distinct year(b.tarikhDaftar) FROM etanah.model.Hakmilik b WHERE b.tarikhDaftar is not null order by year(b.tarikhDaftar) asc";
//        String query = "SELECT distinct h FROM etanah.model.Hakmilik h WHERE h.tarikhDaftar is not null AND h.kegunaanTanah.kod IN (SELECT k.kod FROM etanah.model.KodKegunaanTanah k)"
//                + "AND h.syaratNyata.kod IN (SELECT s.kod FROM etanah.model.KodSyaratNyata s)"
//                + "order by h.tarikhDaftar asc group by (SELECT distinct year(b.tarikhDaftar) FROM etanah.model.Hakmilik b WHERE b.tarikhDaftar is not null)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<AduanOrangKenaSyak> findSenaraiDakwaOks(String idPermohonan, Long idOperasi) {
        String query = "SELECT a FROM etanah.model.AduanOrangKenaSyak a WHERE a.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND a.operasiPenguatkuasaan.idOperasi = :idOperasi "
                + "AND a.statusDakwaan is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<Hakmilik> searchbdnpgrsn(String tahun) {
        System.out.println("tahun ::::: " + tahun);
//        String query = "SELECT h "
//                + "FROM etanah.model.Hakmilik h, etanah.model.Pihak p, etanah.model.strata.BadanPengurusan sbu,etanah.model.KodBandarPekanMukim kb, etanah.model.KodNegeri kn "
//                + "WHERE h.idHakmilikInduk is not null AND h.badanPengurusan.idBadan is not null and sbu.idBadan = h.badanPengurusan.idBadan AND p.idPihak = sbu.pihak.idPihak "
//                + "AND p.negeri.kod = kn.kod AND h.bandarPekanMukim.kod = kb.kod";
//
//        if (tahun != null) {
//            query += " AND year(h.tarikhDaftar) LIKE :tahun";
//        }
        String query = "select distinct h.idHakmilikInduk IDHAKMILIKINDUK"
                + " from etanah.model.Hakmilik h, "
                + "etanah.model.Pihak p,"
                + " etanah.model.strata.BadanPengurusan sbu,"
                + " etanah.model.KodBandarPekanMukim kb, "
                + "etanah.model.KodNegeri kn "
                + "where "
                + "h.idHakmilikInduk is not null and "
                + "sbu.idBadan = h.badanPengurusan.idBadan and "
                + "sbu.pihak.idPihak = p.idPihak and "
                + "AND p.negeri.kod = kn.kod AND h.bandarPekanMukim.kod = kb.kod"
                + "year(h.tarikhDaftar) = :tahun";
        System.out.println("query ::::: " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("tahun", tahun);
        return q.list();


    }

    public List<PermohonanPihakMembantah> findSenaraiPihakPembantah(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakMembantah p WHERE p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> checkExistingHakmilikPermohonan(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pihak> senaraiPihakByNoPengenalan(String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return q.list();
    }

    public List<PermohonanPihakMembantah> findPihakPembantah(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakMembantah p WHERE p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPihakTerlibat(ArrayList<String> data) {
        System.out.println("size data : " + data.size());
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "SELECT p FROM etanah.model.HakmilikPihakBerkepentingan p WHERE p.hakmilik.idHakmilik in (";
            int count = 1;
            for (String a : data) {
                query = query + "'" + a + "'";
                if (count < data.size()) {
                    query = query + ",";
                } else if (count == data.size()) {
                    query = query + ")";
                    break;
                }
                count++;
            }
            query = query + " AND p.aktif = 'Y' AND p.jenis.kod = 'PM'"; // 
        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();

    }

    public List<Permohonan> findIdPerserahan(String idPermohonanSebelum) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonanSebelum";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        return q.list();
    }

    public List<Permohonan> findIdPerserahanByKodUrusan(String idPermohonanSebelum, String kodUrusan) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonanSebelum and p.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<Pemohon> getSenaraiPemohonByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<PermohonanPihak> getSenaraiPihakByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanPihak p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPermohonan> findListHakmilikPermohonan(Long id) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.id = :id ORDER BY b.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public PermohonanManual findPermohonanManual(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanManual p where p.idPermohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanManual) q.uniqueResult();
    }

    @Transactional
    public void savePermohonanManual(PermohonanManual permohonanManual) {
        permohonanManualDAO.saveOrUpdate(permohonanManual);
    }

    public List<LaporanTanah> findListLaporanTanah(String idPermohonan, String idMH) {
        String query = "SELECT lt FROM etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.hakmilikPermohonan.id = :idMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPermohonan> findListHakmilikPermohonan(Long id, String i) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.id = :id AND b.hakmilik.idHakmilik is " + i + " ORDER BY b.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public ImejLaporan findImejLaporanByIdLaporTanahSpdn(Long id) {
        String query = "SELECT i FROM etanah.model.ImejLaporan i WHERE i.laporanTanahSempadan.idLaporTanahSpdn = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return (ImejLaporan) q.uniqueResult();
    }

    public List<PermohonanLaporanBangunan> findByIDLaporTanah(Long idLapor) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanBangunan b where b.laporanTanah.idLaporan = :idLapor";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findAllKeputusanAduan(long idKertas, ArrayList<String> data) {
        String query = null;
        if (!data.isEmpty()) {
            query = " SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil NOT IN (";
            int count = 1;
            for (String a : data) {
                query = query + "'" + a + "'";
                if (count < data.size()) {
                    query = query + ",";
                } else if (count == data.size()) {
                    query = query + ")";
                    break;
                }
                count++;
            }
            query = query + " order by b.bil";

        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertas findMaxBil(String idPermohonan, String kodDokumen, Long idOks) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen = :kodDokumen"
                + " AND b.noKertas = (Select MAX(n.noKertas) FROM etanah.model.PermohonanKertas n where n.permohonan.idPermohonan = :idPermohonan AND n.nomborRujukan = :idOks) AND b.nomborRujukan = :idOks ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        q.setLong("idOks", idOks);
        System.out.println("query ::: " + query.toString());
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertas> findAllNotisOks(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertas p where p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanKertas> findMaxBilList(String idPermohonan, String kodDokumen) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen = :kodDokumen"
                + " AND b.noKertas = (Select MAX(n.noKertas) FROM etanah.model.PermohonanKertas n where n.permohonan.idPermohonan = :idPermohonan)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        System.out.println("query ::: " + query.toString());
        return q.list();
    }

    public List<PermohonanKertasKandungan> listKandunganByBilAsc(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk != :subTajuk order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk);
        return q.list();
    }

    public PermohonanKertasKandungan findTajuk(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
//        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }
}
