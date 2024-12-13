/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.BangunanPetaAksesoriDAO;
import etanah.dao.BangunanPetakDAO;
import etanah.dao.BangunanTingkatDAO;
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
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanPermitButirDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.AkaunDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.model.AduanLokasi;
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
import etanah.model.KodKadarBayaran;
import etanah.model.KodTuntut;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.Permohonan;
import etanah.model.Pemohon;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanJUBL;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanPermitButir;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.KodTransaksi;

import etanah.model.SenaraiRujukan;
import etanah.model.Transaksi;
import etanah.model.WakilKuasa;
import etanah.model.strata.BadanPengurusan;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PengambilanDepositService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
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
    AkaunDAO akaunDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanKandunganDAO laporanKandunganDAO;
    @Inject
    Pemohon pemohon;
    @Inject
    Akaun akaun;
    @Inject
    CaraBayaran carabayaran;

    @Transactional
    public void updateMohon(Permohonan p) {
        permohonanDAO.update(p);
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
    public PermohonanKertas simpanPermohonanKertasObject(PermohonanKertas permohonanKertas) {
        permohonanKertas = permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        return permohonanKertas;
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
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

//    public Akaun getAkaunDepositForCawangan(String idPermohonan,String kodAkaun){
//    	Session s = sessionProvider.get();
//    	return (Akaun) s.createQuery("select a from Akaun a where a.permohonan.idPermohonan = :idPermohonan and a.kodAkaun.id = :kodAkaun and " )
//    			.setString("kodAkaun", kodAkaun)
//                        .setString("idPermohonan", idPermohonan);
//
//    }
    public Akaun getAkaunDeposit(String idPermohonan, String idPihak) {
        String query = "SELECT b FROM etanah.model.Akaun b,etanah.model.Pihak p where b.permohonan.idPermohonan = :idPermohonan and b.pemegang.idPihak =p.idPihak ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idPihak", idPihak);
        return (Akaun) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findByIDMohonTuntut(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTransaksi.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findByIDMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanKos findByIDMohonTuntutKod2(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findByIDMohonTuntutIDMH(String idPermohonan, String kod, long hakmilikPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kod and b.hakmilikPermohonan.id = :hakmilikPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        q.setLong("hakmilikPermohonan", hakmilikPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findByIDMohonTuntutIDMHUnique(String idPermohonan, long hakmilikPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :hakmilikPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("hakmilikPermohonan", hakmilikPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findByIDMohonTuntutIDMHList(String idPermohonan, long hakmilikPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :hakmilikPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("hakmilikPermohonan", hakmilikPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findByIDMohonTuntutKod(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public PermohonanTuntutanKos findByIDMohonTuntutMelaka(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTransaksi.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<Akaun> findByIDMohonIDPihak(String idPermohonan, String idPihak) {
        String query = "SELECT b FROM etanah.model.Akaun b,etanah.model.Pihak p where b.permohonan.idPermohonan = :idPermohonan and b.pemegang.idPihak =p.idPihak ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idPihak", idPihak);
        return q.list();
    }

    public CaraBayaran findCaraBayarbyIdKos(Long idKos) {
        Session s = sessionProvider.get();
        String query = "select c from PermohonanTuntutanBayar a, DokumenKewanganBayaran b, CaraBayaran c "
                + "where a.permohonanTuntutanKos.idKos = :idKos and a.dokumenKewangan.idDokumenKewangan = b.dokumenKewangan.idDokumenKewangan "
                + "and c.idCaraBayaran = b.caraBayaran.idCaraBayaran ";

        Query q = s.createQuery(query);
        q.setParameter("idKos", idKos);
        return (CaraBayaran) q.uniqueResult();
    }

    public List<CaraBayaran> findCaraBayarbyIdCaraBayar(String idPermohonan, int tahun) {
        Session s = sessionProvider.get();
        String query = "select c from Transaksi a, DokumenKewanganBayaran b, CaraBayaran c "
                + "where a.permohonan.idPermohonan = :idPermohonan and a.dokumenKewangan.idDokumenKewangan = b.dokumenKewangan.idDokumenKewangan "
                + "and c.idCaraBayaran = b.caraBayaran.idCaraBayaran and a.untukTahun = :tahun ";

        Query q = s.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("tahun", tahun);
        return q.list();
    }

    public KodTransaksi findKodTransaksibyKod(String kod) {
        Session s = sessionProvider.get();
        String query = "select a from KodTransaksi a "
                + "where a.kod = :idKos  ";

        Query q = s.createQuery(query);
        q.setParameter("idKos", kod);
        return (KodTransaksi) q.uniqueResult();
    }

    public KodTuntut findKodTuntutbyKod(String kod) {
        Session s = sessionProvider.get();
        String query = "select a from KodTuntut a "
                + "where a.kod = :idKos  ";

        Query q = s.createQuery(query);
        q.setParameter("idKos", kod);
        return (KodTuntut) q.uniqueResult();
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaranbyCaraBayaran(long idCaraBayaran) {
        Session s = sessionProvider.get();
        String query = "select a from DokumenKewanganBayaran a where a.caraBayaran.idCaraBayaran = :idCaraBayaran ";
        Query q = s.createQuery(query);
        q.setParameter("idCaraBayaran", idCaraBayaran);
        return (DokumenKewanganBayaran) q.uniqueResult();
    }

    public Transaksi getTranskasiByidKewanganBayaran(String idDokumenKewangan) {
        Session s = sessionProvider.get();
        String query = "select a from Transaksi a where a.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan ";
        Query q = s.createQuery(query);
        q.setParameter("idDokumenKewangan", idDokumenKewangan);
        return (Transaksi) q.uniqueResult();
    }
    
     public List<Transaksi> getListTranskasiByidKewanganBayaran(String idDokumenKewangan) {
        Session s = sessionProvider.get();
        String query = "select a from Transaksi a where a.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan ";
        Query q = s.createQuery(query);
        q.setString("idDokumenKewangan", idDokumenKewangan);
        return q.list();
    }

//     public List<CaraBayaran> findCaraBayarlist(String idKos) {
//        Session s = sessionProvider.get();
//        String query = "select d from PermohonanTuntutanKos k,"
//                + "PermohonanTuntutanBayar b,"
//                + "DokumenKewanganBayaran c,"
//                + "CaraBayaran f "
//                + "where k.idKos = b.idKos "
//                + "and b.DokumenKewanganBayaran.idKewDok = c.idKewDok "
//                + "and c.DokumenKewanganBayaran.idCaraBayar = f.idCaraBayar ";
//
//        Query q = s.createQuery(query);
//        q.setString("idKos", idKos);
//        return q.list();
//    }
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
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
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

    public InfoAudit getInfo(Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public BadanPengurusan bd(Permohonan permohonan) {


        String[] name = {"permohonan"};
        Object[] object = {permohonan};
        BadanPengurusan bds = null;
        List<BadanPengurusan> listbd = badanPengurusanDAO.findByEqualCriterias(name, object, null);
        if (listbd.size() > 0) {
            bds = listbd.get(0);
        }

        return bds;
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

//    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
//        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        return q.list();
//    }
    public PermohonanKertas findKertas(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanKertas h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();

    }

    @Transactional
    public void SimpanKand(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void SimpanLaporanKand(PermohonanLaporanKandungan laporanKandungan) {
        laporanKandunganDAO.saveOrUpdate(laporanKandungan);
    }

    @Transactional
    public void deleteLaporanKandungan(PermohonanLaporanKandungan laporanKandungan) {
        laporanKandunganDAO.delete(laporanKandungan);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        permohonanKertasKandunganDAO.delete(kertasKandungan);
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

    public List<HakmilikPermohonan> findIdPBBSByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
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
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kodPB)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", "PM");
        return (HakmilikPihakBerkepentingan) q.list().get(0);
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
        throw new UnsupportedOperationException("Not yet implemented");
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
//     public List<HakmilikPermohonan> getidMohonPBBS(String idHakmilik) {
//        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.idHakmilikInduk = :idHakmilikInduk and b.noBangunan = :noBangunan and b.noTingkat = :noTingkat ORDER BY noPetak DESC";
//         Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idHakmilikInduk", idHakmilikInduk);
//        q.setString("noBangunan", noBangunan);
//        q.setString("noTingkat", noTingkat);
//        return q.list();
//    }
}
