/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AduanStrataDAO;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.BangunanPetaAksesoriDAO;
import etanah.dao.BangunanPetakDAO;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KodCaraPermohonanDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.LanjutanTempohDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PelaksanaWaranDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanPermitButirDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanSemakDokumenDAO;
import etanah.dao.PermohonanSkimDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.PermohonanSyarikatLelongDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanWaranItemDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.dao.SiasatanAduanImejDAO;
import etanah.dao.StorRampasanDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.dao.WaranDAO;
import etanah.dao.WaranPihakDAO;
import etanah.model.AduanLokasi;
import etanah.model.AduanStrata;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodHartaBersama;
import etanah.model.KodKadarBayaran;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodUrusan;
import etanah.model.LanjutanTempoh;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.PelaksanaWaran;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanJUBL;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanPermitButir;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSemakDokumen;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanSyarikatLelong;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanWaranItem;
import etanah.model.Pihak;
import etanah.model.SiasatanAduanImej;
import etanah.model.StorRampasan;
import etanah.model.SytJuruLelong;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.model.Waran;
import etanah.model.WaranPihak;
import etanah.model.strata.BadanPengurusan;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

public class PelupusanPtService {

    @Inject
    PermohonanWaranItemDAO permohonanWaranItemDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    BangunanPetakDAO bangunanPetakDAO;
    @Inject
    BangunanPetaAksesoriDAO bangunanPetakAksesoriDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanJUBLDAO mohonJUBLDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    SenaraiRujukanDAO senarairujukDAO;
    @Inject
    WakilKuasaDAO wakilKuasaDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanKandunganDAO laporanKandunganDAO;
    @Inject
    PermohonanPermitButirDAO permohonanPermitButirDAO;
    @Inject
    JUBLDAO jublDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PermohonanSkimDAO mohonSkimDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PelaksanaWaranDAO pelaksanaWaranDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanSyarikatLelongDAO permohonanSyarikatLelongDAO;
    @Inject
    SytJuruLelongDAO sytJurulelongDAO;
    @Inject
    StorRampasanDAO storRampasanDAO;
    @Inject
    WaranDAO waranDAO;
    @Inject
    WaranPihakDAO waranPihakDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    AduanStrataDAO aduanStrataDAO;
    @Inject
    LanjutanTempohDAO lanjutanTempohDAO;

    @Inject
    UrusanDokumenDAO urusanDokumenDAO;
    @Inject
    PermohonanSemakDokumenDAO permohonanSemakDokumenDAO;

    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    SiasatanAduanImejDAO siasatanAduanImejDAO;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Transactional
    public void updateMohon(Permohonan p) {
        permohonanDAO.update(p);
    }

    @Transactional
    public void simpanBangunan(PermohonanBangunan bangunan) {
        permohonanBangunanDAO.saveOrUpdate(bangunan);
    }

    @Transactional
    public void updateBangunan(PermohonanBangunan bangunan) {
        permohonanBangunanDAO.update(bangunan);
    }

    @Transactional
    public void simpanBangunanTingkat(BangunanTingkat tingkat) {
        bangunanTingkatDAO.saveOrUpdate(tingkat);
    }

    @Transactional
    public void updateTingkat(BangunanTingkat tingkat) {
        bangunanTingkatDAO.update(tingkat);
    }

    @Transactional
    public void simpanPetak(BangunanPetak petak) {
        bangunanPetakDAO.saveOrUpdate(petak);
    }

    @Transactional
    public void updatePetak(BangunanPetak petak) {
        bangunanPetakDAO.update(petak);
    }

    @Transactional
    public void simpanSemua(BangunanPetak petak) {
        bangunanPetakDAO.update(petak);
    }

    @Transactional
    public void simpanPemilik(PermohonanStrata pemilik) {
        permohonanStrataDAO.saveOrUpdate(pemilik);
    }

    @Transactional
    public void updatePemilik(PermohonanStrata pemilik) {
        permohonanStrataDAO.update(pemilik);
    }

    @Transactional
    public void simpanMaklumatBangunan(BadanPengurusan mc) {
        badanPengurusanDAO.saveOrUpdate(mc);
    }

    @Transactional
    public void updateMaklumatBangunan(BadanPengurusan mc) {
        badanPengurusanDAO.update(mc);
    }

    @Transactional
    public void simpanPetakAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.save(petakAksesori);
    }

    @Transactional
    public void updatePetakAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.update(petakAksesori);
    }

    @Transactional
    public void deleteBngn(PermohonanSkim mohonSkim) {
//        deleteBngn(bangunan);
        mohonSkimDAO.delete(mohonSkim);

    }

    @Transactional
    public void deleteBngn(PermohonanBangunan bangunan) {
        deletechild(bangunan);
        permohonanBangunanDAO.delete(bangunan);

    }

    public void deletechild(PermohonanBangunan bangunan) {
        List<BangunanTingkat> listTingkat = findTingkat(bangunan);
        deletePtkAksrByIdBngn(bangunan.getIdBangunan());
        for (BangunanTingkat tingkat : listTingkat) {
            checkPetak(tingkat);
            deleteTgkt(tingkat);
        }
    }

    private List<BangunanTingkat> findTingkat(PermohonanBangunan bangunan) {
        String[] name = {"bangunan"};
        Object[] value = {bangunan};
        List tingkat = bangunanTingkatDAO.findByEqualCriterias(name, value, null);
        return tingkat;
    }

    public void checkPetak(BangunanTingkat tingkat) {
        List<BangunanPetak> listPetak = findPetak(tingkat);
        for (BangunanPetak petak : listPetak) {
            deletePetak(petak);
        }
    }

    private List<BangunanPetak> findPetak(BangunanTingkat tingkat) {
        String[] name = {"tingkat"};
        Object[] value = {tingkat};
        List petak = bangunanPetakDAO.findByEqualCriterias(name, value, null);
        return petak;
    }

    @Transactional
    public void deletePetak(BangunanPetak petak) {
        bangunanPetakDAO.delete(petak);
    }

    @Transactional
    public void deleteTgkt(BangunanTingkat tingkat) {
        bangunanTingkatDAO.delete(tingkat);
    }

    @Transactional
    public void deleteAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.delete(petakAksesori);
    }

    @Transactional
    public void SimpanLaporanTanah(PermohonanStrata laportanah) {
        permohonanStrataDAO.saveOrUpdate(laportanah);
    }

    @Transactional
    public void updateLaporanTanah(PermohonanStrata laportanah) {
        permohonanStrataDAO.update(laportanah);
    }

    @Transactional
    public void SimpanSempadan(PermohonanStrata sempadan) {
        permohonanStrataDAO.saveOrUpdate(sempadan);
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }
    @Transactional
    public void simpanPermohonanlaporanUlas(PermohonanLaporanUlasan mohonLaporUlas) {
        permohonanLaporanUlasanDAO.saveOrUpdate(mohonLaporUlas);
    }
    @Transactional
    public PermohonanKertas simpanPermohonanKertasObject(PermohonanKertas permohonanKertas) {
        permohonanKertas = permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        return permohonanKertas;
    }
    @Transactional
    public void simpanUpdateOnlyPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.update(permohonanKertasKandungan);
    }
    @Transactional
    public PermohonanKertasKandungan simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        return permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }
    
    @Transactional
    public PermohonanLaporanUlasan simpanPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        return permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    @Transactional
    public void SimpanBngnKosRendah(PermohonanStrata kos_rendah) {
        permohonanStrataDAO.saveOrUpdate(kos_rendah);
    }

    @Transactional
    public void updateBngnKosRendah(PermohonanStrata kos_rendah) {
        permohonanStrataDAO.update(kos_rendah);
    }

    @Transactional
    public void SimpanMohonRujukLuar(PermohonanRujukanLuar mohon) {
        permohonanRujukanLuarDAO.saveOrUpdate(mohon);
    }

    @Transactional
    public void updateMohonRujukLuar(PermohonanRujukanLuar mohon) {
        permohonanRujukanLuarDAO.update(mohon);
    }

    @Transactional
    public void updatePihak(Pihak pihak) {
        pihakDAO.update(pihak);
    }

    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void simpanSijil(BadanPengurusan sijil) {
        badanPengurusanDAO.saveOrUpdate(sijil);
    }

    @Transactional
    public void simpanBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.saveOrUpdate(bayaran);
    }

    @Transactional
    public void deleteBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.delete(bayaran);
    }

    @Transactional
    public void updateBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.update(bayaran);
    }

    //murali 06-07-2011
    @Transactional
    public void simpanAduanStrata(AduanStrata aduanStrata) {
        aduanStrataDAO.saveOrUpdate(aduanStrata);
    }

    @Transactional
    public void deletePtkAksr(long idPetak) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori b where b.petak.idPetak = :idPetak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPetak", idPetak);
        q.executeUpdate();
    }

    @Transactional
    public void deletePtkAksrByIdBngn(long idBangunan) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteMohonStrata(long idStrata) {
        String query = "DELETE FROM etanah.model.PermohonanStrata p where p.idStrata = :idStrata";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idStrata", idStrata);
        q.executeUpdate();
    }

    /**
     * dah xle pakai
     */
    public PermohonanStrata findID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanStrata) q.uniqueResult();

    }

    public List<PermohonanStrata> findIDMS(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public BadanPengurusan findBdn(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (BadanPengurusan) q.uniqueResult();

    }

    public PermohonanBangunan findALLID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBangunan) q.uniqueResult();

    }

    public Pemohon findById(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();

    }

    public PermohonanBangunan findByNama(String nama, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.nama = :nama and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("nama", nama);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBangunan) q.uniqueResult();

    }

    public Dokumen findDok(String idPermohonan, String kodDokumen) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Dokumen) q.uniqueResult();

    }

    public List<PermohonanBangunan> findByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanKos findByIDMohon(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<BangunanPetakAksesori> findByIDBgnn(String idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        return q.list();
    }

    public BangunanTingkat findByPetak(String idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.idTingkat = :idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idTingkat", idTingkat);
        return (BangunanTingkat) q.uniqueResult();

    }

    public List<BangunanTingkat> findByIDBangunan(String idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        return q.list();

    }

    public List<BangunanPetak> findByIDPetak(String idPetak) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.idPetak = :idPetak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPetak", idPetak);
        return q.list();

    }

    public int CountPetak(String idBangunan) {
        int count = 0;
        String query = "from etanah.model.BangunanPetakAksesori n where n.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        count = q.list().size();
        return count;

    }

    public int CountBangunan(String idPermohonan) {
        int count = 0;
        String query = "from etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        count = q.list().size();
        return count;

    }

    public int CountPetakAksr(String idBangunan) {
        int count = 0;
        String query = "from etanah.model.BangunanPetakAksesori n where n.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        count = q.list().size();
        return count;

    }

    public List<HakmilikUrusan> findByID(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikUrusan b where b.hakmilik.idHakmilik = :idHakmilik and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();

    }

    public List<PermohonanKertasKandungan> findByIdLapor(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }
    
    public List<PermohonanLaporanUlasan> findMohonLaporUlasByIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanLaporanKandungan> findByIdLaporKand(long idLapor, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanKandungan b where b.laporanTanah.idLaporan = :idLapor and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setInteger("bil", bil);
        return q.list();
    }

    public PermohonanRujukanLuar findPermohonan(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodRujukan= :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();

    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMByCawangan(String kodBandarPekanMukim) {
        String query = "SELECT b FROM etanah.model.KodBandarPekanMukim b where b.daerah.kod = :kodBandarPekanMukim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodBandarPekanMukim", kodBandarPekanMukim);
        return q.list();

    }

    public List<PermohonanBangunan> findByKodKatBngn(String idPermohonan, String katg_bngn) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.katg_bngn = :katg_bngn and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("katg_bngn", katg_bngn);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodUrusan> getSenaraiUrusan() {
        String query = "SELECT h FROM etanah.model.KodUrusan h where h.kod in( 'P8','P14', 'P22A', 'P22B', 'P40A') order by h.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return kodCaraPermohonanDAO.findAll();
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return kodBandarPekanMukimDAO.findAll();
    }

    @Transactional
    public void simpanMohonJUBL(PermohonanJUBL mohonJUBL) {
        mohonJUBLDAO.saveOrUpdate(mohonJUBL);
    }

    public KodCawangan getkodCawangan(String kod) {
        KodCawangan kodC = new KodCawangan() {
        };
        kodC = kodCawanganDAO.findById(kod);
        return kodC;
    }

    public InfoAudit getInfo(Pengguna peng) {
        InfoAudit ia = new InfoAudit() {
        };
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public PermohonanStrataDAO getPermohonanStrataDAO() {
        return permohonanStrataDAO;
    }

    public void setPermohonanStrataDAO(PermohonanStrataDAO permohonanStrataDAO) {
        this.permohonanStrataDAO = permohonanStrataDAO;
    }

    public List<PermohonanStrata> listMohonStrata(String[] criteriaNames, Object[] criteriaValues) {
        List<PermohonanStrata> list;
        list = permohonanStrataDAO.findByEqualCriterias(criteriaNames, criteriaValues, null);
        return list;
    }

    @Transactional
    public void saveorupdate(ImejLaporan imejLaporan) {
        imejLaporanDAO.saveOrUpdate(imejLaporan);
    }

    // Added by Murali
    @Transactional
    public void simpanTransaksi(Transaksi transaksi) {
        transaksiDAO.saveOrUpdate(transaksi);
    }

    public Permohonan findByIDSblm(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.permohonanSebelum.id = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public HakmilikUrusan findHakmilikUrusan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findKertas(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanKertas h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();

    }

    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<PermohonanLaporanKandungan> findLaporanTanahByLaporanTanah(long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanKandungan b where b.laporanTanah.idLaporan = :idLaporan ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findMohonKertasByMohonKertas(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    @Transactional
    public void SimpanKand(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public PermohonanLaporanKandungan SimpanLaporanKand(PermohonanLaporanKandungan laporanKandungan) {
        return laporanKandunganDAO.saveOrUpdate(laporanKandungan);
    }

    @Transactional
    public void deleteLaporanKandungan(PermohonanLaporanKandungan laporanKandungan) {
        laporanKandunganDAO.delete(laporanKandungan);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        permohonanKertasKandunganDAO.delete(kertasKandungan);
    }
    @Transactional
    public void deletePermohonanLaporanUlasan(PermohonanLaporanUlasan laporanUlasan) {
        permohonanLaporanUlasanDAO.delete(laporanUlasan);
    }
    @Transactional
    public void deleteLaporanTanahSempadan(LaporanTanahSempadan lts) {
        laporanTanahSempadanDAO.delete(lts);
    }
    @Transactional
    public void deleteHakmilikPermohonan(HakmilikPermohonan mohonHakmilik) {
        hakmilikPermohonanDAO.delete(mohonHakmilik);
    }
    public List<PermohonanPermitButir> getSumOfPermitButirByIdHakmilikPermohonan(long id) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :id ORDER BY idMpermitBtr";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public LaporanTanah getLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    @Transactional
    public LaporanTanah simpanLaporan(LaporanTanah laporanTanah) {
        return laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void SimpanPermohonanPermitButir(PermohonanPermitButir permohonanPermitButir) {
        permohonanPermitButirDAO.saveOrUpdate(permohonanPermitButir);
    }

    public List<HakmilikPermohonan> getMaklumatTan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).list();
    }

    public List<PermohonanKertasKandungan> findSenaraiKertas(int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> findMohonTuntutBayar(long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
    }

    @Transactional
    public void savePermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    public AduanLokasi getAduanLokasi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanLokasi b where b.permohonan.idPermohonan = :idPermohonan";
        return (AduanLokasi) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<Hakmilik> getNoPetakLatest(String idHakmilikInduk, String noBangunan, String noTingkat) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilikInduk and b.noBangunan = :noBangunan and b.noTingkat = :noTingkat ORDER BY noPetak DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("noBangunan", noBangunan);
        q.setString("noTingkat", noTingkat);
        return q.list();
    }

    public List<HakmilikPermohonan> findIdPBBSByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public PermohonanBangunan saveMohonBangunan(PermohonanBangunan mohonBngn) {
        mohonBngn = permohonanBangunanDAO.saveOrUpdate(mohonBngn);
        return mohonBngn;
    }

    @Transactional
    public BangunanTingkat saveBangunanTingkat(BangunanTingkat bangunanTingkat) {
        bangunanTingkat = bangunanTingkatDAO.saveOrUpdate(bangunanTingkat);
        return bangunanTingkat;
    }

    @Transactional
    public BangunanPetak saveBangunanPetak(BangunanPetak bangunanPetak) {
        bangunanPetak = bangunanPetakDAO.saveOrUpdate(bangunanPetak);
        return bangunanPetak;
    }

    public List<BangunanPetakAksesori> getListPetakAks(String idHakmilik) {
        String query = "select bpa from etanah.model.HakmilikPermohonan mh,"
                + " etanah.model.PermohonanBangunan mb,"
                + " etanah.model.BangunanTingkat bt,"
                + " etanah.model.BangunanPetak bp,"
                + " etanah.model.BangunanPetakAksesori bpa,"
                + " etanah.model.Hakmilik h"
                + " where"
                + " mb.idBangunan = bt.bangunan.idBangunan and"
                + " bt.idTingkat = bp.tingkat.idTingkat and"
                + " mh.permohonan.idPermohonan = mb.permohonan.idPermohonan and"
                + " h.idHakmilikInduk = mh.hakmilik.idHakmilik and"
                + " mb.nama = h.noBangunan and"
                + " bt.tingkat = h.noTingkat and"
                + " bp.nama = h.noPetak and"
                + " mb.idBangunan = bpa.bangunan.idBangunan and"
                + " bt.idTingkat = bpa.tingkat.idTingkat and"
                + " bp.idPetak =bpa.petak.idPetak and"
                + " mh.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        return q.list();
    }

    public BadanPengurusan findbyId(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan";
        return (BadanPengurusan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findMohonHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPihakBerkepentingan findPihakByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kodPB "
                + "and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", "PM");
        return (HakmilikPihakBerkepentingan) q.list().get(0);
    }

    public HakmilikPihakBerkepentingan findPihakByIdHakmilikNKod(String idHakmilik, String kodPB) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kodPB "
                + "and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", kodPB);
        return (HakmilikPihakBerkepentingan) q.list().get(0);
    }

    public PermohonanJUBL findMohonJUBL(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanJUBL b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanJUBL) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    @Transactional
    public JUBL savaJuruUkur(JUBL juruUkur) {
        return jublDAO.saveOrUpdate(juruUkur);
    }

    @Transactional
    public void deletePermit(PermohonanPermitButir mohonPermitB) {
        permohonanPermitButirDAO.delete(mohonPermitB);
    }

    public List<PermohonanPermitButir> findPermitButir(long id) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public PermohonanPermitButir findPermitButirByBlok(long idMH, String blok, String tingkat, String petak, String jenisStruk) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :idMH AND b.noBlok = :blok"
                + " AND b.noTingkat = :tingkat AND b.noPetak = :petak AND b.jenisStrukTambah = :jenisStruk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        q.setString("blok", blok);
        q.setString("tingkat", tingkat);
        q.setString("petak", petak);
        q.setString("jenisStruk", jenisStruk);
        return (PermohonanPermitButir) q.uniqueResult();
    }

    @Transactional
    public void savePermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);

    }

    public KodKadarBayaran findkodKadarBayar(String kod) {
        String query = "SELECT b FROM etanah.model.KodKadarBayaran b where b.kodUrusan.kod = :kod";
        return (KodKadarBayaran) sessionProvider.get().createQuery(query).setString("kod", kod).uniqueResult();
    }

    public PermohonanTuntutanButiran findTuntutButir(long idKos, long idTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanButiran b where b.permohonanTuntutanKos.idKos = :idKos and b.permohonanTuntutan = :idTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTuntut", idTuntut);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanButiran) q.uniqueResult();
    }

    public FasaPermohonan findMohonFasa(String idPermohonan, String stageId) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.idAliran = :stageId";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);
        return (FasaPermohonan) q.uniqueResult();
    }

    public KodKadarBayaran getKodByKat(String kod, String kategori) {
        String query = "SELECT b FROM etanah.model.KodKadarBayaran b where b.kodUrusan.kod = :kod and b.kategori = :kategori";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kategori", kategori);
        return (KodKadarBayaran) q.uniqueResult();
    }

    public String getFormat(String format) {
        if (format != null) {
            if (format.equals("application/pdf")) {
                format = ".pdf";
            }
            if (format.equals("image/jpeg")) {
                format = ".jpeg";
            }
            if (format.equals("text/plain")) {
                format = ".txt";
            }
            if (format.equals("text/xml")) {
                format = ".xml";
            }
            if (format.equals("application/octet-stream")) {
                format = ".jsp";
            }
            if (format.equals("application/msword")) {
                format = ".doc";
            }
            if (format.equals("image/tiff")) {
                format = ".tif";
            }
            if (format.equals("image/pjpeg")) {
                format = ".jpeg";
            }
        } else {
            format = null;
        }
        return format;
    }

    public Dokumen findDokByXML(String idPermohonan, String kodDokumen, String format) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen and d.format= :format";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        q.setString("format", format);
        return (Dokumen) q.uniqueResult();

    }

    public List<PermohonanSkim> getListSkim(String idHakmilik, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanSkim b where b.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik"
                + " and b.hakmilikPermohonan.permohonan.keputusan.kod ='Y'"
                + " and b.hakmilikPermohonan.permohonan.kodUrusan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    @Transactional
    public Pihak savePihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        return pihak;
    }

    @Transactional
    public Pemohon savePemohon(Pemohon pemohon) {
        return pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public PermohonanSkim saveSkim(PermohonanSkim mohonSkim) {
        mohonSkim = mohonSkimDAO.saveOrUpdate(mohonSkim);
        return mohonSkim;
    }

    public List<HakmilikPermohonan> findPermohonanIdHak(String idHakmilik, String kod) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik and b.permohonan.keputusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    public Permohonan getMhnSblmByKod(Permohonan permohonan, KodUrusan kod) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :permohonan and b.kodUrusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("permohonan", permohonan.getIdPermohonan());
        q.setString("kod", kod.getKod());
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohontuntutkos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<Hakmilik> findHakmilibyParent(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.kodStatusHakmilik.kod = 'D'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pihak> getPihakPM(String idHakmilik) {
        String query = "SELECT p FROM etanah.model.Hakmilik h,etanah.model.HakmilikPihakBerkepentingan hpb, "
                + "etanah.model.Pihak p "
                + "where p.idPihak = hpb.pihak.idPihak "
                + "and hpb.jenis.kod = 'PM' "
                + "and hpb.hakmilik.idHakmilik = h.idHakmilik "
                + "and hpb.aktif = 'Y'"
                + "and h.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public HakmilikPermohonan saveHakmilikPermohonan(HakmilikPermohonan mh) {
        return hakmilikPermohonanDAO.saveOrUpdate(mh);
    }

    @Transactional
    public PermohonanSyarikatLelong savePermohonanSyarikatLelong(PermohonanSyarikatLelong psl) {
        return permohonanSyarikatLelongDAO.saveOrUpdate(psl);
    }
//     public List<HakmilikPermohonan> getidMohonPBBS(String idHakmilik) {
//        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.idHakmilikInduk = :idHakmilikInduk and b.noBangunan = :noBangunan and b.noTingkat = :noTingkat ORDER BY noPetak DESC";
//         Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idHakmilikInduk", idHakmilikInduk);
//        q.setString("noBangunan", noBangunan);
//        q.setString("noTingkat", noTingkat);
//        return q.list();
//    }

//added by murali for Deleting image in laporan tanah jsp
    public Dokumen findDokumen(Long id) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        //q.setString("idH", idH);
        return (Dokumen) q.uniqueResult();
    }

    @Transactional
    public void deleteKandunganFolderByIdDokumen(Long folderId, Long dokumenId) {
        String query = "DELETE FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :folderId AND p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("folderId", folderId);
        q.setLong("dokumenId", dokumenId);
        q.executeUpdate();
    }

    @Transactional
    public void deleteDokumenCapaianByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.DokumenCapaian p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    @Transactional
    public void deletePelaksana(PelaksanaWaran pelaksanaWaran) {
        pelaksanaWaranDAO.delete(pelaksanaWaran);
    }

    @Transactional
    public void deleteMohonStrataImejByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporanStrata p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    @Transactional
    public void deleteAll2(Dokumen d) {
        dokumenDAO.delete(d);
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public List<PermohonanSyarikatLelong> findSytLelong(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanSyarikatLelong p WHERE p.idPermohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public FasaPermohonan saveFasaMohon(FasaPermohonan fasaPermohonan) {
        return fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);

    }

    public List<KodHartaBersama> findSenaraiKodHartaBersama() {
        String query = "Select p FROM etanah.model.KodHartaBersama p WHERE p.kod != 1 and p.kod != 11";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<SytJuruLelong> findSenaraiSytJuruLelongNama(String nama) {
        String query = "Select p FROM etanah.model.SytJuruLelong p WHERE p.nama like :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return q.list();
    }

    public List<SytJuruLelong> findSenaraiSytJuruLelongId(String idSyt) {
        String query = "Select p FROM etanah.model.SytJuruLelong p WHERE p.idSytJlb = :idSyt";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idSyt", idSyt);
        return q.list();
    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.parse(date);
    }

    public String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public StorRampasan findStor(String idPermohonan) {
        String query = "Select p FROM etanah.model.StorRampasan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (StorRampasan) q.uniqueResult();
    }

    public List<PelaksanaWaran> findListPelaksana(String idPermohonan) {
        String query = "Select p FROM etanah.model.PelaksanaWaran p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public SytJuruLelong saveSytJurulelong(SytJuruLelong sytJurulelong) {
        return sytJurulelongDAO.saveOrUpdate(sytJurulelong);
    }

    @Transactional
    public StorRampasan saveStorRampasan(StorRampasan storRampasan) {
        return storRampasanDAO.saveOrUpdate(storRampasan);
    }

    @Transactional
    public Waran saveWaran(Waran waran) {
        return waranDAO.saveOrUpdate(waran);
    }

    @Transactional
    public WaranPihak saveWaranPihak(WaranPihak waranPihak) {
        return waranPihakDAO.saveOrUpdate(waranPihak);
//        return waranPihak;
    }
    // added by zadirul for BangunanKosRendahActionBean

    public KodKegunaanPetak findGunaPetakByNama(String nama) {
        String query = "Select p FROM etanah.model.KodKegunaanPetak p WHERE p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return (KodKegunaanPetak) q.uniqueResult();
    }
    // added by zadirul for BangunanKosRendahActionBean

    public KodHartaBersama findKodHartaBersamaByNama(String nama) {
        String query = "Select p FROM etanah.model.KodHartaBersama p WHERE p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return (KodHartaBersama) q.uniqueResult();
    }

    //added by murali for kertasPertimbanganMMK
    public Pemohon findPemohonPihak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<PermohonanBangunan> getSenaraiMohonBangunan(String idPermohonan) {
        String query = "SELECT n FROM etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BangunanTingkat> findByIDBangunan(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();

    }

    public List<BangunanPetak> getSenaraiPetak(long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idTingkat", idTingkat);
        return q.list();

    }

    public PermohonanKertas findKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PelaksanaWaran findPelaksanaWaran(Long idPelaksanaWaran) {
        String query = "Select p FROM etanah.model.PelaksanaWaran p WHERE p.idPelaksanaWaran = :idPelaksanaWaran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPelaksanaWaran", idPelaksanaWaran);
        return (PelaksanaWaran) q.uniqueResult();
    }

    @Transactional
    public PermohonanPihak saveMohonPihak(PermohonanPihak mohonPihak) {
        return permohonanPihakDAO.saveOrUpdate(mohonPihak);
    }

    @Transactional
    public Dokumen saveDokumen(Dokumen dokumen) {
        return dokumenDAO.saveOrUpdate(dokumen);
    }

    @Transactional
    public PermohonanRujukanLuar saveMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(mohonRujukLuar);
    }

    @Transactional
    public void deleteWaranItem(PermohonanWaranItem waranItem) {
        permohonanWaranItemDAO.delete(waranItem);
    }

    public Permohonan findPermohonanByHakmilik(String idHakmilik, String kpsn) {
        String query = "Select p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan mh, etanah.model.KodUrusan ku WHERE mh.permohonan.idPermohonan = p.idPermohonan"
                + " and p.keputusan.kod is null"
                + " and p.kodUrusan.kod = ku.kod"
                + " and ku.jabatan.kod = 20"
                + " and ku.kod in(select kuu.kod from etanah.model.KodUrusan kuu where kuu.jabatan.kod =20 and kuu.kod = 'PBBS' or kuu.kod = 'PBBD' or kuu.kod = 'PBBL')"
                + " and mh.hakmilik.idHakmilik = :idHakmilik";
        if (kpsn != null) {
            query += " and p.keputusan.kod = :kpsn";
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        if (kpsn != null) {
            q.setString("kpsn", kpsn);
        }

        if (q.list().isEmpty()) {
            return (Permohonan) q.uniqueResult();
        } else {
            return (Permohonan) q.list().get(0);
        }

    }

    public FasaPermohonan findFasaPermohonanByIdAliran(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = :idAliran");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    //-------ADDED BY Zadirul--------------
    public Pemohon findPenyerahPemohon(String idPermohonan) {
        String query = "Select pmhn FROM etanah.model.Permohonan p, etanah.model.Pihak phk, etanah.model.Pemohon pmhn WHERE p.idPermohonan = :idPermohonan "
                + "and pmhn.permohonan.idPermohonan = p.idPermohonan and phk.idPihak = pmhn.pihak.idPihak and p.penyerahNama = phk.nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    //-------ADDED BY Zadirul--------------
    @Transactional
    public void deletePihakByIdPihak(Long idPihak) {
        String query = "DELETE FROM etanah.model.Pihak p WHERE p.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.executeUpdate();
    }

    //-------ADDED BY Zadirul--------------
    @Transactional
    public void deletePemohonByIDMohon(String idPermohonan) {
        String query = "DELETE FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public PermohonanTandatanganDokumen saveDokumenTT(PermohonanTandatanganDokumen ptd) {
        return permohonanTandatanganDokumenDAO.saveOrUpdate(ptd);
    }

    //added by murali 06-07-2011
    public AduanStrata findAduanStrataIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.AduanStrata p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanStrata) q.uniqueResult();
    }

    //-------ADDED BY Zadirul--------------
    public Pihak findPemohonAlamatSuratEqualAlamat(String kodPengenalan, String noPengenalan) {
        Session s = sessionProvider.get();
        String query = "Select phk FROM etanah.model.Pihak phk WHERE phk.jenisPengenalan.kod = :kodPengenalan and phk.noPengenalan = :noPengenalan "
                + "and ( (phk.suratAlamat1 = phk.alamat1 or (phk.alamat1 is null and phk.suratAlamat1 is null)) "
                + "and (phk.suratAlamat2 = phk.alamat2 or (phk.alamat2 is null and phk.suratAlamat2 is null)) "
                + "and (phk.suratAlamat3 = phk.alamat3 or (phk.alamat3 is null and phk.suratAlamat3 is null)) "
                + "and (phk.suratAlamat4 = phk.alamat4 or (phk.alamat4 is null and phk.suratAlamat4 is null)) "
                + "and (phk.suratPoskod = phk.poskod or (phk.poskod is null and phk.suratPoskod is null)) "
                + "and (phk.suratNegeri = phk.negeri or (phk.negeri is null and phk.suratNegeri is null)))";
        Query q = s.createQuery(query);
        q.setString("kodPengenalan", kodPengenalan);
        q.setString("noPengenalan", noPengenalan);
        return (Pihak) q.uniqueResult();
    }

    public List<ImejLaporan> findImejlaporan(long idLaporan) {
        String query = "Select p FROM etanah.model.ImejLaporan p WHERE p.laporanTanah.idLaporan = :idLaporan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void deleteImejLaporanByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporan p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    public PermohonanTandatanganDokumen findMohonDokTT(String idPermohonan, String idPengguna, String kod) {
        String query = "Select p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        if (StringUtils.isNotBlank(kod)) {
            query = "and p.kodDokumen.kod = :kod ";
        }
        if (StringUtils.isNotBlank(idPengguna)) {
            query = "and p.diTandatangan = :idPengguna";
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        if (StringUtils.isNotBlank(idPengguna)) {
            q.setString("idPengguna", idPengguna);
        }
        if (StringUtils.isNotBlank(kod)) {
            q.setString("kod", kod);
        }
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public LanjutanTempoh findMohonLanjutTempoh(Long idN) {
        String query = "Select p FROM etanah.model.LanjutanTempoh p WHERE p.hakmilikPermohonan.id = '" + idN + "'";
        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idN", idN);
        return (LanjutanTempoh) q.uniqueResult();
    }

    @Transactional
    public LanjutanTempoh saveLanjutTempoh(LanjutanTempoh lanjutTempoh) {
        return lanjutanTempohDAO.saveOrUpdate(lanjutTempoh);
    }

    public Permohonan findPermohonanSblm(String idPermohonan) {
        String query = "Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = '" + idPermohonan + "'";
        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idN", idN);
        return (Permohonan) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosExclude(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod <> :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        return q.list();
    }

    public PermohonanTandatanganDokumen findMohonDokTT(String idPermohonan, String kodDokumen) {
        String query = "Select p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan "
                + "and p.kodDokumen.kod = :kodDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    // added by murali 07-07-2011

    public List<UrusanDokumen> findUrusanDokumenBykodUrusan(String kodurusan){
        String query = "SELECT b FROM etanah.model.UrusanDokumen b where b.kodUrusan.kod = :kodurusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodurusan", kodurusan);
        return q.list();
     }

    public List<PermohonanSemakDokumen> findSenaraiPermohonanSemakDokumen(String idPermohonan){
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


    // added by Shah
    public PegawaiPenyiasat findPegawaiSiasatByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PegawaiPenyiasat p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PegawaiPenyiasat) q.uniqueResult();

    }
    // added by Shah

    @Transactional
    public void simpanPegawaiPenyiasat(PegawaiPenyiasat pegawaiPenyiasat) {
        pegawaiPenyiasatDAO.saveOrUpdate(pegawaiPenyiasat);
    }

    //added by zadirul
    @Transactional
    public void simpanSiasatanAduanImej(SiasatanAduanImej siasatanAduanImej) {
        siasatanAduanImejDAO.saveOrUpdate(siasatanAduanImej);

    }
    //added by zadirul
    @Transactional
    public void deleteSiasatanAduanImejByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.SiasatanAduanImej p WHERE p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }
    //added by zadirul
    public List<SiasatanAduanImej> findSiasatanImejByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanAduanImej b where b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }
    public List<PermohonanKertasKandungan> findByIdKertasNbilNsubTajuk(long idKertas, int bil,String subtajuk,String type) {
        String query = new String();
        query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where";
        if(type.equals("P"))
         query = query+" b.kertas.idKertas = :idKertas and b.bil = :bil AND b.subtajuk = :subtajuk AND b.bilSebelum IS NULL order by b.subtajuk";
        else if(type.equals("C"))
         query = query+" b.kertas.idKertas = :idKertas and b.bil = :bil AND b.subtajuk = :subtajuk AND b.bilSebelum IS NOT NULL order by b.bilSebelum";
        
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subtajuk", subtajuk);
        return q.list();
    }
    
}
