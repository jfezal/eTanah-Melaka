/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AduanLokasiDAO;

import etanah.dao.NotisDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.AduanPemantauanDAO;
import etanah.dao.BongkarKehadiranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.EnkuiriPenguatkuasaanKehadiranDAO;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.KehadiranMesyuaratPenguatkuasaanDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.MesyuaratPenguatkuasaanDAO;
import etanah.dao.OperasiAgensiDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenguatkuasaanBarangJualDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarPerananDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.AduanPemantauan;
import etanah.model.AduanTindakan;
import etanah.model.Dokumen;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.EnkuiriPenguatkuasaanKehadiran;
import etanah.model.FasaPermohonan;
import etanah.model.KodRujukan;
import etanah.model.Kompaun;
import etanah.model.Notis;
import etanah.model.WarisOrangKenaSyak;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarPeranan;
import etanah.dao.WarisOrangKenaSyakDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Akaun;
import etanah.model.BarangRampasan;
import etanah.model.BongkarKehadiran;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.ImejLaporan;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.KehadiranMesyuaratPenguatkuasaan;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.MesyuaratPenguatkuasaan;
import etanah.model.Pemohon;
import etanah.model.PenggunaPeranan;
import etanah.model.PenguatkuasaanBarangJual;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanUkur;
import etanah.model.ambil.Penilaian;
import etanah.model.gis.PelanGIS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author farah.shafilla
 */
public class EnforcementService {

    private static final Logger logger = Logger.getLogger(EnforcementService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    WarisOrangKenaSyakDAO warisOrangKenaSyakDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    PemohonDAO pemohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    AduanPemantauanDAO aduanPemantauanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    @Inject
    EnkuiriPenguatkuasaanKehadiranDAO enkuiriPenguatkuasaanKehadiranDAO;
    @Inject
    PermohonanRujukanLuarPerananDAO permohonanRujukanLuarPerananDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    MesyuaratPenguatkuasaanDAO mesyuaratPenguatkuasaanDAO;
    @Inject
    KehadiranMesyuaratPenguatkuasaanDAO kehadiranMesyuaratPenguatkuasaanAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikSebelumPermohonanDAO mohonHakmilikSblmDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    BongkarKehadiranDAO bongkarKehadiranDAO;
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PenguatkuasaanBarangJualDAO penguatkuasaanBarangJualDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;

    @Transactional
    public void simpanNotisPenguatkuasaan(Notis notisPenguatkuasaan) {
        notisDAO.saveOrUpdate(notisPenguatkuasaan);
    }

    @Transactional
    public void simpanOperasi(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        operasiPenguatkuasaanDAO.saveOrUpdate(operasiPenguatkuasaan);
    }

    @Transactional
    public void simpanEnkuiriHadir(EnkuiriPenguatkuasaanKehadiran enkuiriPenguatkuasaanKehadiran) {
        enkuiriPenguatkuasaanKehadiranDAO.saveOrUpdate(enkuiriPenguatkuasaanKehadiran);
    }

    @Transactional
    public void simpanRujukan(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanPemohon(Pemohon pemohon) {
        pemohonanDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public void simpanPermohonanPihak(PermohonanPihak permohonanPihak) {
        permohonanPihakDAO.saveOrUpdate(permohonanPihak);
    }

    @Transactional
    public void simpanKertas(PermohonanKertas kertas, PermohonanKertasKandungan kand) {
        permohonanKertasDAO.saveOrUpdate(kertas);
        if (kand != null) {
            permohonanKertasKandunganDAO.saveOrUpdate(kand);
        }
    }

    @Transactional
    public void simpanKompaun(Kompaun kompaun) {
        kompaunDAO.saveOrUpdate(kompaun);

    }

    @Transactional
    public void deleteAduanPemantauan(AduanPemantauan aduanPemantauan) {
        aduanPemantauanDAO.delete(aduanPemantauan);
    }

    @Transactional
    public void deleteNotis(Notis notis) {
        notisDAO.delete(notis);
    }

    @Transactional
    public void deleteDokumen(Dokumen dokumen) {
        dokumenDAO.delete(dokumen);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.delete(permohonanKertasKandungan);
    }

    @Transactional
    public void deleteMesy(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.delete(permohonanRujukanLuar);
    }

    @Transactional
    public void deleteKompaun(Kompaun kompaun) {
        kompaunDAO.delete(kompaun);
    }

    @Transactional
    public void deleteOperasiAgensi(OperasiAgensi operasiAgensi) {
        operasiAgensiDAO.delete(operasiAgensi);
    }

    @Transactional
    public void simpanAduanPemantauan(AduanPemantauan aduanPemantauan) {
        aduanPemantauanDAO.saveOrUpdate(aduanPemantauan);
    }

    @Transactional
    public void simpanMaklumatPihak(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }
    
    @Transactional
    public void simpanPermohonanUkur(PermohonanUkur mohonUkur) {
        permohonanUkurDAO.saveOrUpdate(mohonUkur);
    }
    
    @Transactional
    public void simpanPermohonanTandatanganDokumen(PermohonanTandatanganDokumen tt) {
        permohonanTandatanganDokumenDAO.saveOrUpdate(tt);
    }
    
    public List<KodUrusan> findListOfEnfKodUrusanAktif(){
        String query = "SELECT p FROM etanah.model.KodUrusan p WHERE p.jabatan.kod = :kodJabatan AND p.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodJabatan", "24");
        return q.list();
    }
    
    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonan(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public PermohonanUkur getMaxNoPUofPermohonanUkur(String year) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.noPermohonanUkur=(Select MAX(lt1.noPermohonanUkur) from etanah.model.PermohonanUkur lt1 where lt1.noPermohonanUkur LIKE :year)");
        q.setString("year", "%" + year + "%");
        return (PermohonanUkur) q.uniqueResult();
    }
    
    public PermohonanUkur findPermohonanUkurByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanUkur) q.uniqueResult();
    }
    
    public List<Pemohon> findPemohonByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanPihak p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Notis> findByIDNotis(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WarisOrangKenaSyak> findByIDWaris(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.WarisOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Notis findSamanByIdmohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod ='SML'";
        return (Notis) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public AduanTindakan findRemediByIdmohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanTindakan b where b.permohonan.idPermohonan = :idPermohonan and b.tindakan.kod ='REM'";
        return (AduanTindakan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public AduanTindakan findRemediTambahanByIdmohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanTindakan b where b.permohonan.idPermohonan = :idPermohonan and b.tindakan.kod ='RET'";
        return (AduanTindakan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Notis findNotisByIdMohon(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public List<Notis> findAllNotisByIdMohon(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return q.list();
    }

    public Notis findByIdNotis(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis and b.tempatHantar8 is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public List<Notis> findListNotisByIdAndTempat8Null(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis and b.tempatHantar8 is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return q.list();
    }

    public Notis findByIdNotis2(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis and b.tempatHantar8 is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public FasaPermohonan findByStageId(String idPermohonan, String idAliran) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan=:idPermohonan AND b.idAliran = :idAliran ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<Notis> SenaraiKodNotis(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

//    public PermohonanRujukanLuar findByKodDoc(String idPermohonan) {
//        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='KMD'";
//        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
//    }
    public Permohonan findByIDSebelum(String idMohon, String kodUrusan) {
        String query = "SELECT p FROM etanah.model.Permohonan p WHERE p.permohonanSebelum.idPermohonan = :idMohon AND p.kodUrusan.kod = :kodUrusan";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idMohon", idMohon).setString("kodUrusan", kodUrusan).uniqueResult();
    }

    public Pemohon findPemohonByIDMohon(String idMohon) {
        String query = "SELECT p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idMohon";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idMohon", idMohon).uniqueResult();
    }

    public PermohonanRujukanLuar findByKodDokumen(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='SMMR'";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanKertasKandungan findByKodIdKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        return (PermohonanKertasKandungan) sessionProvider.get().createQuery(query).setLong("idKertas", idKertas).uniqueResult();
    }

    public PermohonanKertas findByKodIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.tajuk='Draf Kertas Siasatan'";
        return (PermohonanKertas) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanKertas findMMKNByKodIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.kodDokumen.kod='MMKN'";
        return (PermohonanKertas) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<AduanPemantauan> findByID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanPemantauan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Notis> findNotisByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.tempatHantar8 is null order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Notis findUtilitiNotisByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.tempatHantar8 is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Notis) q.uniqueResult();
    }

    public List<WarisOrangKenaSyak> findWarisByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.WarisOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
//    public Notis findByKodNotis(String idPermohonan,String jenis) {
//        String query = "SELECT b FROM etanah.model.N b where b.permohonan.idPermohonan = :idPermohonan And b.kodNotis.kod :=jenis";
//        return (Notis) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
//    }

    public List<PermohonanRujukanLuar> findMesyByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='SMMR'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findMahkamahByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='KMD' order by b.tarikhSidang desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanRujukanLuar findMahkamahByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='KMD'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<Kompaun> findKompaunByID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=2 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findbil1ByIdKertas(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public AduanPemantauan findAduanPemantauanByIdPemantauan(Long idPemantauan) {
        String query = "Select p FROM etanah.model.AduanPemantauan p WHERE p.idPemantauan = :idPemantauan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPemantauan", idPemantauan);
        return (AduanPemantauan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public Notis findnotisByIdNotis(Long idNotis) {
        String query = "Select p FROM etanah.model.Notis p WHERE p.idNotis = :idNotis";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idNotis", idNotis);
        return (Notis) q.uniqueResult();
    }

    public Dokumen findDokumenByIdDokumen(Long idDokumen) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idDokumen", idDokumen);
        return (Dokumen) q.uniqueResult();
    }

    public PermohonanRujukanLuar findMesyByIdRujukan(Long idRujukan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public Kompaun findKompaunByIdKompaun(Long idKompaun) {
        String query = "Select p FROM etanah.model.Kompaun p WHERE p.idKompaun = :idKompaun";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKompaun", idKompaun);
        return (Kompaun) q.uniqueResult();
    }

    public Kompaun findKompaunByIdaduanOrangKenaSyak(Long idOrangKenaSyak) {
        String query = "Select p FROM etanah.model.Kompaun p WHERE p.orangKenaSyak.idOrangKenaSyak = :idOrangKenaSyak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOrangKenaSyak", idOrangKenaSyak);
        return (Kompaun) q.uniqueResult();
    }

    public OperasiAgensi findOperasiByIdOPAgensi(Long idOperasiAgensi) {
        String query = "Select p FROM etanah.model.OperasiAgensi p WHERE p.idOperasiAgensi = :idOperasiAgensi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOperasiAgensi", idOperasiAgensi);
        return (OperasiAgensi) q.uniqueResult();
    }

//    public OperasiPenguatkuasaan findOperasiByidPermohonan(String idPermohonan) {
//        String query = "SELECT b FROM etanah.model.OperasiPenguatkuasaan b where b.permohonan.idPermohonan = :idPermohonan";
//        return (OperasiPenguatkuasaan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
//    }
    public EnkuiriPenguatkuasaan findEnkuiriByidPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.EnkuiriPenguatkuasaan b where b.permohonan.idPermohonan = :idPermohonan";
        return (EnkuiriPenguatkuasaan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<OperasiAgensi> findOperasiByIDOpeasi(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.OperasiAgensi b where b.operasi.idOperasi = :idOperasi order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<EnkuiriPenguatkuasaanKehadiran> findEnHadirByIDEnkuiri(Long idEnkuiri) {
        String query = "SELECT b FROM etanah.model.EnkuiriPenguatkuasaanKehadiran b where b.enkuiri.idEnkuiri = :idEnkuiri";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public EnkuiriPenguatkuasaanKehadiran findByIdaduanOrangKenaSyak(Long idOrangKenaSyak) {
        String query = "Select p FROM etanah.model.EnkuiriPenguatkuasaanKehadiran p WHERE p.orangKenaSyak.idOrangKenaSyak = :idOrangKenaSyak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOrangKenaSyak", idOrangKenaSyak);
        return (EnkuiriPenguatkuasaanKehadiran) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan ORDER BY b.agensi.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PenghantarNotis findNoKadPengenalan(int idPenghantarNotis) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.idPenghantarNotis = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("id", idPenghantarNotis);
        return (PenghantarNotis) q.uniqueResult();
    }

    public List<EnkuiriPenguatkuasaan> getALLEnkuiri() {
        String query = "SELECT m FROM etanah.model.EnkuiriPenguatkuasaan m WHERE m.tarikhMula is not null and m.status='A'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        System.out.println("enkuiri size service : " + q.list().size());
        return q.list();
    }

    public List<Pengguna> getSenaraiPengguna() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPTK','PPTT')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaByIc() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPT','PPTK','PPTT') and p.noPengenalan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public PegawaiPenyiasat findPengguna(String idPermohonan) {
//        String query = "Select p FROM etanah.model.PegawaiPenyiasat p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.namaJabatan = :idPengguna";
        String query = "Select p FROM etanah.model.PegawaiPenyiasat p WHERE p.permohonan.idPermohonan = :idPermohonan AND statusPeranan is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idPengguna", idPengguna);
        return (PegawaiPenyiasat) q.uniqueResult();
    }

    public List<KodRujukan> senaraiKodRujukan() {
        String query = "SELECT k FROM etanah.model.KodRujukan k WHERE k.kod in('WA','WB','WC','W8')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodRujukan> senaraiKodRujukanWarta() {
        String query = "SELECT k FROM etanah.model.KodRujukan k WHERE k.kod in('WA','WB','W7A','W7B','W7E','W7F','W8A','W8')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getSenaraiWarta(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanRujukanLuar p WHERE p.kodRujukan in('WA','WB','W7A','W7B','W7E','W7F','W8A','W8') AND p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanRujukanLuarPeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        permohonanRujukanLuarPerananDAO.saveOrUpdate(permohonanRujukanLuarPeranan);
    }

    public List<PermohonanRujukanLuarPeranan> getSenaraiRujLuarPeranan(Long idRujukan) {
        String query = "SELECT p FROM etanah.model.PermohonanRujukanLuarPeranan p WHERE p.rujukan.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return q.list();
    }

    public PermohonanRujukanLuarPeranan findPeranan(Long idPeranan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuarPeranan p WHERE p.idPeranan = :idPeranan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPeranan", idPeranan);
        return (PermohonanRujukanLuarPeranan) q.uniqueResult();
    }

    @Transactional
    public void deletePeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        permohonanRujukanLuarPerananDAO.delete(permohonanRujukanLuarPeranan);
    }

    public Pengguna findNoKadPengenalan(String noPengenalan) {
        System.out.println("no pengenalan : " + noPengenalan);
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return (Pengguna) q.uniqueResult();
    }

    public Pengguna findByIdPengguna(String idPengguna) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.idPengguna = :idPengguna";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        return (Pengguna) q.uniqueResult();
    }

    public List<Pengguna> findNoKadPengenalanKetua(String noPengenalan) {
        System.out.println("no pengenalan : " + noPengenalan);
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return q.list();
    }

    public OperasiPenguatkuasaanPasukan findKetua(Long idKetua) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaanPasukan p WHERE p.idOperasiPenguatkuasaanPasukan = :idKetua";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKetua", idKetua);
        return (OperasiPenguatkuasaanPasukan) q.uniqueResult();
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanWithoutKetua(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.OperasiPenguatkuasaanPasukan b where b.idOperasiPenguatkuasaan.idOperasi = :idOperasi AND b.kodPerananOperasi.kod != 'K' order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    @Transactional
    public void deleteRujukanLuarPeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        permohonanRujukanLuarPerananDAO.delete(permohonanRujukanLuarPeranan);
    }

    
    public List<Kompaun> findUnpaidKompaun(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.permohonan.idPermohonan = :idPermohonan AND b.resit is null AND b.statusTerima.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public Notis findNotis(String idPermohonan, String kodDokumen) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.dokumenNotis.kodDokumen.kod =:kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Notis) q.uniqueResult();
    }

    public OperasiPenguatkuasaanPasukan findByIdPasukan(Long idPasukan) {
        String query = "SELECT p FROM etanah.model.OperasiPenguatkuasaanPasukan p where p.idOperasiPenguatkuasaanPasukan =:idPasukan and p.saksi = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPasukan", idPasukan);
        return (OperasiPenguatkuasaanPasukan) q.uniqueResult();
    }

    public List<OperasiPenguatkuasaanPasukan> findListByIdPasukan(Long idOperasi) {
        String query = "SELECT p FROM etanah.model.OperasiPenguatkuasaanPasukan p where p.idOperasiPenguatkuasaan.idOperasi =:idOperasi and p.saksi = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<Kompaun> findUnpaidKompaun(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.permohonan.idPermohonan = :idPermohonan AND b.statusTerima.kod is null AND b.resit is null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pengguna> getSenaraiPengguna(String perananUtama) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama = :perananUtama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("perananUtama", perananUtama);
        return q.list();
    }

    @Transactional
    public void deletePegawaiPenyiasat(PegawaiPenyiasat pp) {
        pegawaiPenyiasatDAO.delete(pp);
    }

    public List<Pengguna> getSenaraiPenggunaForPasukan() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPT','PPTK','PPTT') and p.noPengenalan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaForPasukan(List<String> peranan) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod in(:peranan) and p.noPengenalan is not null";
        //String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod in(:peranan)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("peranan", peranan);
        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaForPasukan2() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in('PPT','PPTK','PPTD') and p.noPengenalan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaForPasukan2(List<String> peranan) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod in(:peranan) and p.noPengenalan is not null";
        //String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod in(:peranan)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("peranan", peranan);
        return q.list();
    }

    public List<Pengguna> getListSenaraiPasukan() {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE  p.nama is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public OperasiAgensi findLaporanPolis(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.OperasiAgensi b where b.operasi.idOperasi = :idOperasi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return (OperasiAgensi) q.uniqueResult();
    }

    public List<KandunganFolder> getListDokumen(Long idFolder, String kodDokumen) {
        String query = "SELECT k FROM etanah.model.KandunganFolder k, etanah.model.Dokumen d WHERE k.folder.folderId = :idFolder and d.kodDokumen.kod = :kodDokumen and d.idDokumen = k.dokumen.idDokumen and d.dalamanNilai1 is not null order by d.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFolder", idFolder);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    public FasaPermohonan IdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan=:idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonanByIdPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonanByIdDate(String idPermohonan, Date fromDate, Date untilDate) {
        Session session = sessionProvider.get();
        String query = "SELECT h FROM etanah.model.FasaPermohonan h ";
        if (!StringUtils.isEmpty(idPermohonan)) {
            query = query + " where h.permohonan.idPermohonan=:idPermohonan ";
            if (fromDate != null) {
                String rule = new String();
                if (untilDate != null) {
                    rule = " between ";
                } else {
                    rule = " >=";
                }
                query += " and h.infoAudit.tarikhMasuk " + rule + ":fromDate";
                if (untilDate != null) {
                    query = query + " and :untilDate";
                }
                query = query + " order by infoAudit.tarikhMasuk";
                logger.info("This is QUERY FOR MOHON FASA ->" + query);

            }
//            if (untilDate != null)
//                query += " and trim(h.infoAudit.tarikhMasuk) = :untilDate";
        } else {
            query = query + " where ";
            if (fromDate != null) {
                String rule = new String();
                if (untilDate != null) {
                    rule = " between ";
                } else {
                    rule = " >=";
                }
                query += " h.infoAudit.tarikhMasuk " + rule + ":fromDate";
                if (untilDate != null) {
                    query = query + " and :untilDate";
                }
                query = query + " order by infoAudit.tarikhMasuk";
                logger.info("This is QUERY FOR MOHON FASA ->" + query);
            }
        }

        Query q = session.createQuery(query);
        if (!StringUtils.isEmpty(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }
        if (fromDate != null) {
            q.setDate("fromDate", fromDate);
        }
        if (untilDate != null) {
            q.setDate("untilDate", untilDate);
        }
        return q.list();
    }

//
//    @Transactional
//    public void simpanMaklumatWaris(WarisOrangKenaSyak warisOrangKenaSyak) {
//        warisOrangKenaSyakDAO.saveOrUpdate(warisOrangKenaSyak);
//    }
    public List<Kompaun> findKompaun(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.permohonan.idPermohonan = :idPermohonan AND b.resit.idDokumenKewangan is null AND b.statusTerima.kod is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Kompaun findNewKompaun(Long idKompaun) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.idKompaun =:idKompaun AND b.resit.idDokumenKewangan is null AND b.statusTerima.kod is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKompaun", idKompaun);
        return (Kompaun) q.uniqueResult();
    }

    public List<BarangRampasan> findBarangRampasanOks(Long idOks, String kod) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b, etanah.model.AduanOrangKenaSyak a "
                + "WHERE b.aduanOrangKenaSyak.idOrangKenaSyak = a.idOrangKenaSyak AND a.idOrangKenaSyak =:idOks AND b.kodKategoriItemRampasan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOks", idOks);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findSenaraiAnggota(String idPermohonan, String kod, String idOks) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan and p.agensi.kod =:kod and p.noRujukan =:idOks order by p.idRujukan asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        q.setString("idOks", idOks);
        return q.list();
    }

    public List<PegawaiPenyiasat> findSenaraiPegawaiPenyiasat(String idPermohonan) {
        String query = "Select p FROM etanah.model.PegawaiPenyiasat p WHERE p.permohonan.idPermohonan = :idPermohonan AND statusPeranan is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PegawaiPenyiasat> findSenaraiPegawaiPenyiasatIp(String idPermohonan) {
        String query = "Select pp FROM etanah.model.PegawaiPenyiasat pp, etanah.model.Permohonan p, etanah.model.OperasiPenguatkuasaan op WHERE p.permohonanSebelum.idPermohonan = op.permohonan.idPermohonan "
                + "AND p.idPermohonan = pp.permohonan.idPermohonan AND op.idOperasi = pp.operasiPenguatkuasaan.idOperasi AND p.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//    select m.id_mohon,op.id_op,m.id_sblm,ps.nama,ps.no_pengenalan
//from mohon m,kkuasa_op op ,pegawai_siasat ps
//where m.id_sblm=op.id_mohon
//and m.id_mohon=ps.id_mohon
//and op.id_op=ps.id_op
//and m.id_mohon='0401ENF2012012860'
    public Kompaun findKompaunByNoRujukan(String noRujukan) {
        String query = "Select p FROM etanah.model.Kompaun p WHERE p.noRujukan = :noRujukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noRujukan", noRujukan);
        return (Kompaun) q.uniqueResult();
    }

    public List<OperasiPenguatkuasaanPasukan> findSenaraiPasukanIp(String idPermohonan, Long idOperasi) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaanPasukan p, etanah.model.Permohonan m, etanah.model.OperasiPenguatkuasaan op WHERE m.permohonanSebelum.idPermohonan = op.permohonan.idPermohonan "
                + "AND op.idOperasi = p.idOperasiPenguatkuasaan.idOperasi AND m.idPermohonan = :idPermohonan AND op.idOperasi = :idOperasi";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<OperasiPenguatkuasaanPasukan> findSenaraiSaksiPasukanIp(String idPermohonan, Long idOperasi) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaanPasukan p, etanah.model.Permohonan m, etanah.model.OperasiPenguatkuasaan op WHERE m.permohonanSebelum.idPermohonan = op.permohonan.idPermohonan "
                + "AND op.idOperasi = p.idOperasiPenguatkuasaan.idOperasi AND m.idPermohonan = :idPermohonan AND op.idOperasi = :idOperasi AND p.saksi = 'Y'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<KandunganFolder> getSenaraiDokumen(Long idFolder, String kodDokumen) {
        String query = "SELECT k FROM etanah.model.KandunganFolder k, etanah.model.Dokumen d WHERE k.folder.folderId = :idFolder and d.kodDokumen.kod = :kodDokumen and d.idDokumen = k.dokumen.idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFolder", idFolder);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    @Transactional
    public void simpanDokumen(Dokumen dokumen) {
        dokumenDAO.saveOrUpdate(dokumen);
    }

    public Kompaun findKompaunByIdSaksi(Long idSaksi) {
        String query = "Select p FROM etanah.model.Kompaun p WHERE p.saksi.idSaksi = :idSaksi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idSaksi", idSaksi);
        return (Kompaun) q.uniqueResult();

    }

    public List<Kompaun> findKompaunIP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Kompaun b where b.permohonan.idPermohonan = :idPermohonan AND b.saksi.idSaksi is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pengguna> findPenggunaByBpel(ArrayList<String> data) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama in (";
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
        //logger.info(query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findStatusByIDPermohonan(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.keputusanPendakwaan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findKodRujByIDPermohonan(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodRujukan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanKertas> findListSuratSaksi(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertas p where p.permohonan.idPermohonan = :idPermohonan and p.kodDokumen.kod = :kod");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }
    
    public PelanGIS findPelanB1ByMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PelanGIS) q.uniqueResult();
    }

    @Transactional
    public void deleteKertas(PermohonanKertas kertas) {
        permohonanKertasDAO.delete(kertas);
    }

    public List<OperasiPenguatkuasaan> findListLaporanOperasi(Long idOperasi) {
        String query = "Select p FROM etanah.model.OperasiPenguatkuasaan p WHERE p.idOperasi = :idOperasi order by p.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public PermohonanNota findIdNota(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.idMohonNota = (Select MAX(p1.idMohonNota) from etanah.model.PermohonanNota p1) AND p.idAliran = :stageId";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public List<AduanOrangKenaSyak> getListAvailableOKSForIP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan AND b.permohonanAduanOrangKenaSyak.idPermohonan is null AND b.operasiPenguatkuasaan.idOperasi is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> findSenaraiPermohonanBaruForOks(String idPermohonanSebelum) {
        String query = "SELECT p FROM etanah.model.Permohonan p WHERE p.permohonanSebelum.idPermohonan =:idPermohonanSebelum AND p.status != 'BP'";
//                + " AND p.idPermohonan != :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
//        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> getListAduanOrangkenaSyak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND b.statusIp is not null AND b.amaunKompaunSyor is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> getListAduanOrangkenaSyak2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan AND b.amaunKompaunSyor is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findSuratPelepasan(String idPermohonan, String kod, String noRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.kodDokumen.kod='MMKN'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        q.setString("noRujukan", noRujukan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<AduanOrangKenaSyak> getListKompaunDakwaOks(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan AND b.statusIp is not null AND b.permohonanAduanOrangKenaSyak.idPermohonan is not null AND b.statusDakwaan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanNota findCurrentNotaMinit(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.idAliran = :stageId AND p.statusNota = 'A'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public PermohonanNota findCurrentNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.idAliran = :idAliran AND p.statusNota = 'A'";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public PermohonanNota findEmptyNotaMinit(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.idAliran = :stageId AND p.statusNota = 'A' AND p.nota is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public PermohonanNota findNotaMinit(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.idAliran = :stageId AND p.statusNota = 'A'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public PermohonanNota findNotEmptyNotaMinit(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " AND p.idAliran = :stageId AND p.statusNota = 'A' AND p.nota is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public List<Pengguna> getSenaraiKumpulanBpel(List<String> kumpulanBpel) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod in(:kumpulanBpel) ORDER BY p.kekananan asc";
//        logger.info(query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("kumpulanBpel", kumpulanBpel);
        //q.setString("kumpulanBpel", kumpulanBpel);
        return q.list();
    }

    public List<AduanOrangKenaSyak> getLisOKSForDakwa(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND b.statusIp is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> getListOKSKompaun(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonanAduanOrangKenaSyak.idPermohonan = :idPermohonan AND b.statusIp is not null AND b.amaunKompaunSyor is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Akaun> findSenaraiAkaunRemedi(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.Akaun b WHERE b.permohonan.idPermohonan = :idPermohonan AND b.kodAkaun.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> getSenaraiPtb(Long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos ORDER BY b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
    }

    public List<Permohonan> getSenaraiPermohonanPendaftaran(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> getSenaraiPtb(Long idKos, String dokumenKewangan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos and b.dokumenKewangan.idDokumenKewangan = :dokumenKewangan ORDER BY b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        q.setString("dokumenKewangan", dokumenKewangan);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> findSenaraiPtb(ArrayList<Long> data) {
        System.out.println("size data : " + data.size());
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos in (";
            int count = 1;
            for (Long a : data) {
                query = query + "'" + a + "'";
                if (count < data.size()) {
                    query = query + ",";
                } else if (count == data.size()) {
                    query = query + ")";
                    break;
                }
                count++;
            }
        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
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
            query = query + " AND p.aktif = 'Y'"; //AND p.jenis.kod = 'PM' 
        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();

    }

    public List<HakmilikPihakBerkepentingan> findPihakTerlibat(ArrayList<String> data, Permohonan permohonan, String i, String kod) {
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
            query = query + " AND p.aktif = 'Y' AND p.pihak.idPihak " + i + " (SELECT m.pihak.idPihak FROM etanah.model.Pemohon m WHERE m.permohonan.idPermohonan = :idPermohonan";

            if (StringUtils.isNotBlank(kod)) {
                query = query + " AND m.dalamanNilai1 = '" + kod + "')";
            } else {
                query = query + ")";
            }
        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", permohonan.getIdPermohonan());
        return q.list();

    }

    @Transactional
    public void simpanMohonPihak(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        hakmilikPihakBerkepentinganDAO.saveOrUpdate(hakmilikPihakBerkepentingan);
    }

    public Notis findMaxBil(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis and b.tempatHantar8 is not null"
                + " AND b.bilangan = (Select MAX(n.bilangan) FROM etanah.model.Notis n where n.permohonan.idPermohonan = :idPermohonan)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        System.out.println("query ::: " + query.toString());
        return (Notis) q.uniqueResult();
    }

    public List<Notis> findListNotis(String idPermohonan, String kodNotis) {
        String query = "SELECT b FROM etanah.model.Notis b where b.permohonan.idPermohonan = :idPermohonan and b.kodNotis.kod =:kodNotis and b.tempatHantar8 is not null order by b.bilangan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodNotis", kodNotis);
        return q.list();
    }

    public List<MesyuaratPenguatkuasaan> findListMesyuarat(String idPermohonan) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT b FROM etanah.model.MesyuaratPenguatkuasaan b where b.permohonan.idPermohonan = :idPermohonan order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public MesyuaratPenguatkuasaan findByIdMesyuarat(Long idMesyuarat) {
        String query = "Select p FROM etanah.model.MesyuaratPenguatkuasaan m WHERE m.idMesyuarat = :idMesyuarat";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMesyuarat", idMesyuarat);
        return (MesyuaratPenguatkuasaan) q.uniqueResult();
    }

    @Transactional
    public void simpanMesyuaratPenguatkuasaan(MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan) {
        mesyuaratPenguatkuasaanDAO.saveOrUpdate(mesyuaratPenguatkuasaan);
    }

    @Transactional
    public void simpanKehadiranMesyuaratPenguatkuasaan(KehadiranMesyuaratPenguatkuasaan kehadiranMesyuaratPenguatkuasaan) {
        kehadiranMesyuaratPenguatkuasaanAO.saveOrUpdate(kehadiranMesyuaratPenguatkuasaan);
    }

    public List<KehadiranMesyuaratPenguatkuasaan> findListKehadiranMesyuarat(Long idMesyuarat) {
        String query = "SELECT b FROM etanah.model.KehadiranMesyuaratPenguatkuasaan b where b.mesyuaratPenguatkuasaan.idMesyuarat = :idMesyuarat order by b.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMesyuarat", idMesyuarat);
        return q.list();
    }

    @Transactional
    public void deleteMesyuaratPenguatkuasaan(MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan) {
        mesyuaratPenguatkuasaanDAO.delete(mesyuaratPenguatkuasaan);
    }

    @Transactional
    public void deleteKehadiranMesyuaratPenguatkuasaan(KehadiranMesyuaratPenguatkuasaan kehadiranMesyuaratPenguatkuasaan) {
        kehadiranMesyuaratPenguatkuasaanAO.delete(kehadiranMesyuaratPenguatkuasaan);
    }

    public List<HakmilikPihakBerkepentingan> findPihakBerkepentingan(ArrayList<String> data, String i) {
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
            query = query + " AND p.aktif = 'Y'"; //AND p.jenis.kod = 'PM' 
            if (i.equalsIgnoreCase("eq")) {
                query = query + " AND p.jenis.kod = 'PM'";
            } else {
                query = query + " AND p.jenis.kod != 'PM'";
            }
        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<HakmilikWaris> findWarisByIdHakmilik(ArrayList<String> data) {
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "SELECT p FROM etanah.model.HakmilikWaris p, etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik in (";
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
            query = query + " and h.idHakmilikPihakBerkepentingan = p.pemegangAmanah.idHakmilikPihakBerkepentingan and p.syerPembilang is not null"; //AND p.jenis.kod = 'PM' 

        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

//    public BigDecimal sumPenilaian(long idKehadiran) {
//        String query = "Select SUM(b.amaun) FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setLong("idKehadiran", idKehadiran);
//        System.out.println("val : " + q.toString());
//        return (BigDecimal) q.uniqueResult();
//    }
    public List<Pemohon> findListPemohon(String idPermohonan) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> findByIdPermohonan(String idPermohonan) {
        String query = "Select b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanMaklumatPermohonanPihak(PermohonanPihak permohonanPihak) {
        permohonanPihakDAO.saveOrUpdate(permohonanPihak);
    }

    public List<Pemohon> findListPemohon(String idPermohonan, String kod) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan AND p.dalamanNilai1 = :kod order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanPihak> findByIdPermohonan(String idPermohonan, String kod) {
        String query = "Select b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan AND b.dalamanNilai1 = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<Pengguna> findPenggunaByBPEL(List<String> bpelName, String kodCaw) {
        String query = "Select u from etanah.model.PenggunaPeranan u WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + " u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
            count++;
        }
        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "' and u.peranan.kodJabatan = '24'";

//        logger.info("::: Query to get list pengguna : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    public List<Pengguna> findUserKumpBpel(ArrayList<String> data, String i) {
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "Select pp from etanah.model.PenggunaPeranan pp, etanah.model.KodPeranan kp, etanah.model.Pengguna p WHERE pp.peranan.kumpBPEL in (";
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
            query = query + " AND p.idPengguna = pp.pengguna.idPengguna AND p.status.kod = 'A' AND p.perananUtama.kod = pp.peranan.kod AND pp.peranan.kod = kp.kod"
                    + " AND pp.pengguna.kodCawangan.kod = '" + i + "' and kp.kodJabatan = p.kodJabatan";

        }
//        logger.info("::: query : " + query);
//        logger.info("::: Query to get list pengguna based on kump bpel: " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }
    


    public List<Pengguna> findPenggunaAgihTugasanByBPEL(List<String> bpelName, String kodCaw) {
        String query = "Select pp from etanah.model.PenggunaPeranan pp, etanah.model.KodPeranan kp, etanah.model.Pengguna p WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + " pp.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or pp.peranan.kumpBPEL ='" + s + "'";
            }
            count++;
        }
        query = query + " AND p.idPengguna = pp.pengguna.idPengguna AND p.status.kod = 'A' AND pp.peranan.kod = kp.kod"
                + " AND p.kodCawangan.kod = '" + kodCaw + "'" + "order by p.nama asc";

//where p.ID_PGUNA = pp.ID_PGUNA--dah
//and p.peranan_utama=pp.kod_peranan -- dah
//and pp.kod_peranan = kp.kod
//and kp.KUMP_BPEL = 'pptd' -dah
//and kp.KOD_JAB = '24'
//and p.KOD_CAW = '01'

//        logger.info("::: Query to get list pengguna : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    public List<KodPeranan> senaraiKumpBpel(ArrayList<String> data) {
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "SELECT k FROM etanah.model.KodPeranan k WHERE k.kumpBPEL in (";
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

        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusan(ArrayList<String> data) {
        String query = null;
        if (!data.isEmpty()) {
            query = "SELECT p FROM etanah.model.HakmilikUrusan p WHERE p.hakmilik.idHakmilik in (";
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

        }
//        logger.info("::: query : " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PelanGIS> findPelanByIdMohon(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
     public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilikAndUrusan(String idHakmilik, String kodUrusan) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h"
                + " where h.hakmilik.idHakmilik = :idHakmilik"
                + " and h.aktif='Y'"
                + " and h.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public Hakmilik findHakmilikAsal(String idHakmilik) {
        String query = "Select h FROM etanah.model.Hakmilik h WHERE h.idHakmilik IN"
                + "(Select a.hakmilikAsal FROM etanah.model.HakmilikAsal a, etanah.model.HakmilikSebelum s "
                + "WHERE a.hakmilik.idHakmilik = s.hakmilikSebelum "
                + "AND s.hakmilik.idHakmilik = :idHakmilik AND rownum=1)";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Hakmilik) q.uniqueResult();
    }

    @Transactional
    public void simpanHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);
    }

    @Transactional
    public void simpanMohonHakmilik(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    @Transactional
    public void simpanMohonHakmilikSebelum(HakmilikSebelumPermohonan mohonHakmilikSblm) {
        mohonHakmilikSblmDAO.save(mohonHakmilikSblm);
    }

    @Transactional
    public void simpanHakmilikAsal(HakmilikAsal hakmilikAsal) {
        hakmilikAsalDAO.save(hakmilikAsal);
    }

    public List<HakmilikSebelumPermohonan> findHakmilikSebelum(String idHakmilikLama, Long idMH) {
        String query = "Select h FROM etanah.model.HakmilikSebelumPermohonan h where h.hakmilikSejarah.idHakmilik = :idHakmilikLama and h.hakmilikPermohonan.id = :idMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikLama", idHakmilikLama);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<BongkarKehadiran> findSenaraiBongkarKehadiran(Long idOks) {
        String query = "Select h FROM etanah.model.BongkarKehadiran h where h.aduanOrangKenaSyak.idOrangKenaSyak = :idOks order by h.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOks", idOks);
        return q.list();
    }

    @Transactional
    public void simpanBongkarKehadiran(BongkarKehadiran bongkarKehadiran) {
        bongkarKehadiranDAO.save(bongkarKehadiran);
    }

    @Transactional
    public void deleteKehadiran(BongkarKehadiran bongkarKehadiran) {
        bongkarKehadiranDAO.delete(bongkarKehadiran);
    }

    public List<Kehadiran> getSenaraiPerbicaraan(String idRujukan) {//Integer idRujukan
        String query = "SELECT p FROM etanah.model.Kehadiran p WHERE p.wakilJawatan = :idRujukan order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        return q.list();
    }

    @Transactional
    public void simpanKehadiran(Kehadiran kehadiran) {
        kehadiranDAO.save(kehadiran);
    }

    @Transactional
    public void deleteKehadiranPerbicaraan(Kehadiran kehadiran) {
        kehadiranDAO.delete(kehadiran);
    }

    @Transactional
    public void deleteImejLaporanDokumen(ImejLaporan imejLaporan) {
        imejLaporanDAO.delete(imejLaporan);
    }

    @Transactional
    public void deleteMohonHakmilik(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    public List<BarangRampasan> findBarangBelumDijual(Long idOperasi) {
        String query = "SELECT b FROM etanah.model.BarangRampasan b where b.operasi.idOperasi = :idOperasi AND b.statusJual != 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idOperasi", idOperasi);
        return q.list();
    }

    public List<Kompaun> getSenaraiPembelian(List<Long> id) {
        String query = "SELECT k FROM etanah.model.Kompaun k WHERE k.idKompaun in(:id)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("id", id);
        return q.list();
    }

    @Transactional
    public void simpanPembelianBarang(PenguatkuasaanBarangJual penguatkuasaanBarangJual) {
        penguatkuasaanBarangJualDAO.saveOrUpdate(penguatkuasaanBarangJual);
    }

    public List<Pemohon> findListPembeli(String idPermohonan, String kod) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan AND p.jenis.kod = :kod order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PenguatkuasaanBarangJual> findPembelianBarang(String idPermohonan) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT p FROM etanah.model.PenguatkuasaanBarangJual p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PenguatkuasaanBarangJual> findJumlahBarang(Long idBarang) {
        String query = "SELECT p FROM etanah.model.PenguatkuasaanBarangJual p where p.rampasan.idBarang = :idBarang";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBarang", idBarang);
        return q.list();
    }

    public List<PenguatkuasaanBarangJual> findBarangPemohon(Long idPemohon) {
        System.out.println("idPemohon :" + idPemohon);
        String query = "SELECT p FROM etanah.model.PenguatkuasaanBarangJual p where p.pemohon.idPemohon = :idPemohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }

    @Transactional
    public void deletePermohonanTuntutan(PermohonanTuntutan permohonanTuntutan) {
        permohonanTuntutanDAO.delete(permohonanTuntutan);
    }

    @Transactional
    public void deletePermohonanTuntutanButiran(PermohonanTuntutanButiran permohonanTuntutanButiran) {
        permohonanTuntutanButiranDAO.delete(permohonanTuntutanButiran);
    }

    @Transactional
    public void deletePembeliBarangJualan(PenguatkuasaanBarangJual penguatkuasaanBarangJual) {
        penguatkuasaanBarangJualDAO.delete(penguatkuasaanBarangJual);
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosPembelian(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p, etanah.model.PenguatkuasaanBarangJual k where p.permohonan.idPermohonan = :idPermohonan AND p.permohonan.idPermohonan = k.permohonan.idPermohonan AND p.idKos = k.kos.idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PenguatkuasaanBarangJual> findUnpaidPembelian(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PenguatkuasaanBarangJual b where b.permohonan.idPermohonan = :idPermohonan AND b.idDokumenKewangan is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
}
